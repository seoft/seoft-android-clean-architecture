package kr.co.seoft.ca.data.repository.contact

import io.reactivex.Single
import kr.co.seoft.ca.data.api.ContactApi
import kr.co.seoft.ca.data.mapper.ContactDataMapper
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactRemoteDataSource
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity

class ContactRemoteDataSourceImpl(
    private val contactApi: ContactApi,
    private val mapper: ContactDataMapper
) : ContactRemoteDataSource {
    override fun getContacts(): Single<List<ContactEntity>> {
        return contactApi.getContacts().map { mapper.convert(it) }
    }

    override fun getContacts(cursorId: Long): Single<List<ContactEntity>> {
        return contactApi.getContacts(cursorId).map { mapper.convert(it) }
    }

    override fun getContact(id: Long): Single<ContactEntity> {
        return contactApi.getContact(id).map { mapper.convert(it) }
    }

    override fun addContact(createContactEntity: CreateContactEntity): Single<ContactEntity> {
        return contactApi.addContact(mapper.convert(createContactEntity)).map { mapper.convert(it) }
    }

    override fun deleteContact(id: Long): Single<ContactEntity> {
        return contactApi.deleteContact(id).map { mapper.convert(it) }
    }

}