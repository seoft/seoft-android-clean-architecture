package kr.co.seoft.ca.example.entity

import java.util.*
import kotlin.random.Random

data class ContactEntity(val id: Long, val name: String, val email: String) {
    companion object {
        fun generateContact(id: Long): ContactEntity {
            return ContactEntity(
                    id,
                    UUID.randomUUID().toString().replace("-", "").substring(0, 5),
                    UUID.randomUUID().toString().replace("-", "").let {
                        "${it.substring(0, Random.nextInt(5, 8))}@${it.substring(0, Random.nextInt(4, 8))}.com"
                    }
            )
        }
    }
}

data class CreateContact(val name: String, val email: String)

data class ContactResponse(val contact: ContactEntity)

data class ContactsResponse(val contacts: List<ContactEntity>)