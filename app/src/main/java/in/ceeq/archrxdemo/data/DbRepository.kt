package `in`.ceeq.archrxdemo.data

import `in`.ceeq.archrxdemo.data.entity.Entity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import io.reactivex.Single
import javax.inject.Inject

open class DbRepository @Inject constructor(private val contentResolver: ContentResolver) {

    private lateinit var dataContentObserver: ContentObserver

    fun query(uri: Uri,
              projection: Array<String>?,
              selection: String,
              selectionArgs: Array<String>,
              sortOrder: String?): Single<Cursor> {
        return Single.fromCallable({
            contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        })
    }

    fun <T : Entity>persist(network: () -> Single<T>, where: String = "",
                            selectionArgs: Array<String> = emptyArray()): Single<T>  =
        network().flatMap {
            contentResolver.update(it.uri, it.toContentValues(), where, selectionArgs)
            Single.just(it)
        }

    fun observe(uri: Uri): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        dataContentObserver = ContentObserverImpl(liveData)
        contentResolver.registerContentObserver(uri, false, dataContentObserver)
        return liveData
    }

    fun removeObserver() {
        if (::dataContentObserver.isInitialized) {
            contentResolver.unregisterContentObserver(dataContentObserver)
        }
    }

    class ContentObserverImpl(private val observer: MutableLiveData<Boolean>) : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            observer.value = true //signaling just data changed
        }
    }
}
