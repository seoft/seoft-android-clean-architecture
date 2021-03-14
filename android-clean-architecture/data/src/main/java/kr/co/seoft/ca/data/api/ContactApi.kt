package kr.co.seoft.ca.data.api

import io.reactivex.Single
import kr.co.seoft.ca.data.api.model.ContactResponse
import kr.co.seoft.ca.data.api.model.ContactsResponse
import kr.co.seoft.ca.data.api.model.CreateContact
import retrofit2.http.*

interface ContactApi {

    @GET("api/contacts")
    fun getContacts(): Single<ContactsResponse>

    @GET("api/contacts/{cursorId}")
    fun getContacts(@Path("cursorId") cursorId: Long): Single<ContactsResponse>

    @GET("api/contact/{id}")
    fun getContact(@Path("id") id: Long): Single<ContactResponse>

    @POST("api/contact")
    fun addContact(@Body createContact: CreateContact): Single<ContactResponse>

    @DELETE("api/contact/{id}")
    fun deleteContact(@Path("id") id: Long): Single<ContactResponse>

}