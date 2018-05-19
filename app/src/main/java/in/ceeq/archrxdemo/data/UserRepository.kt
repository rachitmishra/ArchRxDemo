package `in`.ceeq.archrxdemo.data

import `in`.ceeq.archrxdemo.data.entity.User
import `in`.ceeq.archrxdemo.data.network.UserApi
import android.arch.lifecycle.LiveData
import android.content.ContentResolver
import android.net.Uri
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(contentResolver: ContentResolver,
                                         private val userApi: UserApi): DbRepository(contentResolver) {

    fun getUser(): Single<User> {
        return persist(userApi::getUser)
    }

    fun addUser(user: User): Single<User> {
        return persist(userApi::addUser)
    }

    fun updateUser(): Single<User> {
        return persist(userApi::updateUser)
    }

    fun observeUser(uri: Uri): LiveData<Boolean> {
        return observe(uri)
    }
}
