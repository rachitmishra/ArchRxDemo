package `in`.ceeq.archrxdemo.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

data class User(val name: String, val email: String): Entity {

    override val uri: Uri
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toContentValues(): ContentValues {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromCursor(cursor: Cursor): Entity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
