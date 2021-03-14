package kr.co.seoft.ca.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.co.seoft.ca.data.local.model.Bookmark

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmark: Bookmark): Completable

    @Query("SELECT COUNT(*) FROM bookmark WHERE contact_id = :contactId")
    fun getBookmarkStatus(contactId: Long): Single<Int>

    @Query("SELECT contact_id FROM bookmark WHERE contact_id IN (:contactIds)")
    fun getBookmarksStatus(contactIds: List<Long>): Single<List<Long>>

    @Query("DELETE FROM bookmark WHERE contact_id = :contactId")
    fun deleteBookmark(contactId: Long): Completable
}