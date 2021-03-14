package kr.co.seoft.ca.data.repository.contact

import io.reactivex.Completable
import io.reactivex.Single
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactLocalDataSource
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactRemoteDataSource
import kr.co.seoft.ca.data.util.zipToPair
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity
import kr.co.seoft.ca.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val remoteDataSource: ContactRemoteDataSource,
    private val localDataSource: ContactLocalDataSource
) : ContactRepository {

    override fun getContacts(): Single<List<ContactEntity>> {
        return remoteDataSource.getContacts().applyBookMarkToContacts()
    }

    override fun getContacts(cursorId: Long): Single<List<ContactEntity>> {
        return remoteDataSource.getContacts(cursorId).applyBookMarkToContacts()
    }

    private fun Single<List<ContactEntity>>.applyBookMarkToContacts(): Single<List<ContactEntity>> {
        return this.flatMap { contacts ->
            localDataSource.getContactBookmarkedIds(contacts.map { it.id }).map { bookmarkedIds ->
                contacts.map { it.copy(isBookmark = bookmarkedIds.contains(it.id)) }
            }
        }
    }

    override fun getContact(id: Long): Single<ContactEntity> {
        return Single.zip(
            remoteDataSource.getContact(id),
            localDataSource.getContactBookmark(id),
            zipToPair()
        ).map { it.first.copy(isBookmark = it.second) }
    }

    override fun addContact(createContact: CreateContactEntity): Single<ContactEntity> {
        return remoteDataSource.addContact(createContact)
    }

    override fun deleteContact(id: Long): Single<ContactEntity> {
        return Single.zip(
            remoteDataSource.deleteContact(id),
            localDataSource.deleteContactBookmark(id).toSingleDefault(Unit),
            zipToPair()
        ).map {
            it.first
        }
    }

    override fun addContactBookmark(id: Long): Completable {
        return localDataSource.addContactBookmark(id)
    }

    override fun deleteContactBookmark(id: Long): Completable {
        return localDataSource.deleteContactBookmark(id)
    }
}