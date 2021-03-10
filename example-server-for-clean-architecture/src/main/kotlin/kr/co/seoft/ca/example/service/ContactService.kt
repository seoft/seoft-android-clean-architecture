package kr.co.seoft.ca.example.service

import kr.co.seoft.ca.example.entity.ContactEntity
import kr.co.seoft.ca.example.entity.ContactResponse
import kr.co.seoft.ca.example.entity.ContactsResponse
import kr.co.seoft.ca.example.entity.CreateContact
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.min

@Service
class ContactService {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    companion object {
        var idCount = 0L
        val contactSimpleDataBase = mutableListOf<ContactEntity>().apply {
            addAll((0 until 200).map { ContactEntity.generateContact(idCount++) })
        }

        const val LOAD_COUNT = 30
    }

    fun getContacts(cursorId: Long? = null): ContactsResponse {
        val index = cursorId?.let { contactSimpleDataBase.indexOfFirst { it.id == cursorId } }
        val nextIndex = when (index) {
            -1 -> return ContactsResponse(emptyList())
            null -> contactSimpleDataBase.size
            else -> index - 2
        }
        return ContactsResponse(contactSimpleDataBase.subList(max(nextIndex - LOAD_COUNT, 0), max(nextIndex, 0)).asReversed())
    }

    fun getContact(id: Long): ContactResponse {
        return ContactResponse(contactSimpleDataBase.firstOrNull { it.id == id } ?: throw Exception("cant find contact : $id"))
    }

    fun addContact(createContact: CreateContact): ContactResponse {
        val createdContact = ContactEntity(idCount++, createContact.name, createContact.email)
        contactSimpleDataBase.add(createdContact)
        return ContactResponse(createdContact)
    }

    fun deleteContact(id: Long): ContactResponse {
        val deletedContact = contactSimpleDataBase.firstOrNull { it.id == id } ?: throw Exception("cant delete contact : $id")
        contactSimpleDataBase.removeIf { it.id == id }
        return ContactResponse(deletedContact)
    }

}