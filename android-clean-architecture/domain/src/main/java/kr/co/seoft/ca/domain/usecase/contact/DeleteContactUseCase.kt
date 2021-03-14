package kr.co.seoft.ca.domain.usecase.contact

import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.repository.ContactRepository
import kr.co.seoft.ca.domain.usecase.common.RxScheduler
import kr.co.seoft.ca.domain.usecase.common.SUseCase

class DeleteContactUseCase(
    private val contactRepository: ContactRepository,
    override val scheduler: RxScheduler
) : SUseCase<Long, ContactEntity>() {

    override fun implement(param: Long): Single<ContactEntity> {
        return contactRepository.deleteContact(param)
    }
}