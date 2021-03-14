package kr.co.seoft.ca.data.mapper

import kr.co.seoft.ca.data.api.model.ContactResponse
import kr.co.seoft.ca.data.api.model.ContactsResponse
import kr.co.seoft.ca.data.api.model.CreateContact
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.domain.entity.CreateContactEntity

class ContactDataMapper {

    fun convert(contactResponse: ContactResponse): ContactEntity {
        return contactResponse.contact.run { ContactEntity(id, name, email, false) }
    }

    fun convert(contactsResponse: ContactsResponse): List<ContactEntity> {
        return contactsResponse.contacts.map { ContactEntity(it.id, it.name, it.email, false) }
    }

    fun convert(createContactEntity: CreateContactEntity): CreateContact {
        return createContactEntity.run { CreateContact(name, email) }
    }

    fun convert(count: Int): Boolean {
        return count == 1
    }

}