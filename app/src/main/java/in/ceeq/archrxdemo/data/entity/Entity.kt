package `in`.ceeq.archrxdemo.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

interface Entity {
    val uri: Uri
    fun toContentValues(): ContentValues
    fun fromCursor(cursor: Cursor): Entity
}
