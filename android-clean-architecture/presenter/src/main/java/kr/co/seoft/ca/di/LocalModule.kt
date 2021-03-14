package kr.co.seoft.ca.di

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.co.seoft.ca.data.local.BookmarkDao
import kr.co.seoft.ca.data.local.model.Bookmark
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.getInstance(get()).bookmarkDao() }
}

@Database(entities = [Bookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "bookmark.db"
            ).build()
    }
}