package kr.co.seoft.ca.ui.contact

import kr.co.seoft.ca.data.generateContact
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity
import kr.co.seoft.ca.domain.usecase.contact.*
import kr.co.seoft.ca.ui.BaseViewModel
import kr.co.seoft.ca.util.SafetyLiveData
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ContactViewModel(
    private val getInitContactsUseCase: GetInitContactsUseCase,
    private val getMoreContactsUseCase: GetMoreContactsUseCase,
    private val getContactUseCase: GetContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val setContactBookmarkUseCase: SetContactBookmarkUseCase,
    private val unSetContactBookmarkUseCase: UnSetContactBookmarkUseCase
) : BaseViewModel() {

    val contacts = SafetyLiveData<List<ContactEntity>>()
    var needScrollToTop = false
    val hideSwipeRefreshProgress = SafetyLiveData<Unit>()

    private val requestLock = AtomicBoolean(false)
    private var cursorId: Long? = null
    private var hasMore = true

    fun requestInitContact() {
        hasMore = true
        compositeDisposable += getInitContactsUseCase.execute(Unit)
            .doFinally { hideSwipeRefreshProgress.set(Unit) }
            .subscribe({
                if (it.isNullOrEmpty()) {
                    hasMore = false
                    return@subscribe
                }
                needScrollToTop = true
                contacts.set(it.apply {
                    cursorId = this.last().id
                })
                return@subscribe
            }, _throwable)
    }

    fun requestMoreContact() {
        val stableCursorId = cursorId ?: return
        if (!hasMore) return
        if (requestLock.getAndSet(true)) return
        compositeDisposable += getMoreContactsUseCase.execute(stableCursorId)
            .doFinally { requestLock.set(false) }
            .subscribe({
                if (it.isNullOrEmpty()) {
                    hasMore = false
                    return@subscribe
                }
                contacts.set(((contacts.value ?: emptyList()) + it).apply {
                    cursorId = this.last().id
                })
            }, _throwable)
    }

    val onContactListener = object : OnContactListener {
        override fun onContactClick(contact: ContactEntity) {
            compositeDisposable += deleteContactUseCase.execute(contact.id)
                .subscribe({ contactEntity ->
                    contacts.set(
                        (contacts.value ?: emptyList())
                            .filter { it.id != contactEntity.id }
                    )
                }, _throwable)
        }

        override fun onContactBookmarkClick(contact: ContactEntity) {
            compositeDisposable += (if (!contact.isBookmark) {
                setContactBookmarkUseCase.execute(contact.id)
            } else {
                unSetContactBookmarkUseCase.execute(contact.id)
            }).subscribe({
                val result = (contacts.value ?: emptyList()).map {
                    if (it.id == contact.id) it.copy(isBookmark = !it.isBookmark)
                    else it
                }
                contacts.set(result)
            }, _throwable)
        }

        override fun onContactLongClick(contact: ContactEntity): Boolean {
            compositeDisposable += getContactUseCase.execute(contact.id).subscribe({
                _showToast.set("LongClicked : ${it.name}(${it.id})\n${it.email}")
            }, _throwable)
            return true
        }
    }

    fun onAddContact() {
        compositeDisposable += addContactUseCase.execute(CreateContactEntity.generateContact())
            .subscribe({
                _showToast.set("Complete to add id:${it.id}\nSwipe from top to bottom")
            }, _throwable)
    }

}