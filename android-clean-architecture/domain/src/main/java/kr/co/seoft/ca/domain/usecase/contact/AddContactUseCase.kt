package kr.co.seoft.ca.domain.usecase.contact

import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity
import kr.co.seoft.ca.domain.repository.ContactRepository
import kr.co.seoft.ca.domain.usecase.common.RxScheduler
import kr.co.seoft.ca.domain.usecase.common.SUseCase

class AddContactUseCase(
    private val contactRepository: ContactRepository,
    override val scheduler: RxScheduler
) : SUseCase<CreateContactEntity, ContactEntity>() {

    override fun implement(param: CreateContactEntity): Single<ContactEntity> {
        return contactRepository.addContact(param)
    }
}