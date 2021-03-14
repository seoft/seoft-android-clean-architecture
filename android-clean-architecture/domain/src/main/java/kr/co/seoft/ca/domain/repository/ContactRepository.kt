package kr.co.seoft.ca.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity

interface ContactRepository {

    fun getContacts(): Single<List<ContactEntity>>

    fun getContacts(cursorId: Long): Single<List<ContactEntity>>

    fun getContact(id: Long): Single<ContactEntity>

    fun addContact(createContact: CreateContactEntity): Single<ContactEntity>

    fun deleteContact(id: Long): Single<ContactEntity>

    fun addContactBookmark(id: Long): Completable

    fun deleteContactBookmark(id: Long): Completable
}