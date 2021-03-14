package kr.co.seoft.ca.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(

    @PrimaryKey
    @ColumnInfo(name = "contact_id")
    val contactId: Long

)