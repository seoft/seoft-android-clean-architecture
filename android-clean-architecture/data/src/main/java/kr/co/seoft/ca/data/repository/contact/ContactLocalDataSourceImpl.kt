package kr.co.seoft.ca.data.repository.contact

import io.reactivex.Completable
import io.reactivex.Single
import kr.co.seoft.ca.data.local.BookmarkDao
import kr.co.seoft.ca.data.local.model.Bookmark
import kr.co.seoft.ca.data.mapper.ContactDataMapper
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactLocalDataSource

class ContactLocalDataSourceImpl(
    private val bookmarkDao: BookmarkDao,
    private val mapper: ContactDataMapper
) : ContactLocalDataSource {
    override fun getContactBookmark(id: Long): Single<Boolean> {
        return bookmarkDao.getBookmarkStatus(id).map { mapper.convert(it) }
    }

    override fun getContactBookmarkedIds(ids: List<Long>): Single<List<Long>> {
        return bookmarkDao.getBookmarksStatus(ids)
    }

    override fun addContactBookmark(id: Long): Completable {
        return bookmarkDao.insertBookmark(Bookmark(id))//.ignoreElement()
    }

    override fun deleteContactBookmark(id: Long): Completable {
        return bookmarkDao.deleteBookmark(id)
    }
}