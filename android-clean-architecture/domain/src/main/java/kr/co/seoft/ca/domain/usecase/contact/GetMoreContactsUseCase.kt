package kr.co.seoft.ca.domain.usecase.contact

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.repository.ContactRepository
import kr.co.seoft.ca.domain.usecase.common.RxScheduler
import kr.co.seoft.ca.domain.usecase.common.SUseCase

class GetMoreContactsUseCase(
    private val contactRepository: ContactRepository,
    override val scheduler: RxScheduler
) : SUseCase<Long, List<ContactEntity>>() {

    override fun implement(param: Long): Single<List<ContactEntity>> {
        return contactRepository.getContacts(param).subscribeOn(Schedulers.io())
    }
}