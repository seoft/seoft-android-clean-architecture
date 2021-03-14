package kr.co.seoft.ca.domain.usecase.contact

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kr.co.seoft.ca.domain.repository.ContactRepository
import kr.co.seoft.ca.domain.usecase.common.CUseCase
import kr.co.seoft.ca.domain.usecase.common.RxScheduler

class UnSetContactBookmarkUseCase(
    private val contactRepository: ContactRepository,
    override val scheduler: RxScheduler
) : CUseCase<Long>() {

    override fun implement(param: Long): Completable {
        return contactRepository.deleteContactBookmark(param).subscribeOn(Schedulers.io())
    }
}