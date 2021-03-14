package kr.co.seoft.ca.data.repository.contact.interfaces

import io.reactivex.Completable
import io.reactivex.Single

interface ContactLocalDataSource {

    fun getContactBookmark(id: Long): Single<Boolean>

    fun getContactBookmarkedIds(ids: List<Long>): Single<List<Long>>

    fun addContactBookmark(id: Long): Completable

    fun deleteContactBookmark(id: Long): Completable
}