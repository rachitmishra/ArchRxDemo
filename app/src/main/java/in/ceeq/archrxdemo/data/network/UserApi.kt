package `in`.ceeq.archrxdemo.data.network

import `in`.ceeq.archrxdemo.data.entity.User
import io.reactivex.Single

interface UserApi {

    fun getUser(): Single<User>

    fun addUser(): Single<User>

    fun updateUser(): Single<User>
}
