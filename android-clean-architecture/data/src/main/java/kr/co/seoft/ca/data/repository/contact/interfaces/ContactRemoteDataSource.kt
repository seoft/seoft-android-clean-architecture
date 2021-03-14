package kr.co.seoft.ca.data.repository.contact.interfaces

import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity

interface ContactRemoteDataSource {

    fun getContacts(): Single<List<ContactEntity>>

    fun getContacts(cursorId: Long): Single<List<ContactEntity>>

    fun getContact(id: Long): Single<ContactEntity>

    fun addContact(createContactEntity: CreateContactEntity): Single<ContactEntity>

    fun deleteContact(id: Long): Single<ContactEntity>

}