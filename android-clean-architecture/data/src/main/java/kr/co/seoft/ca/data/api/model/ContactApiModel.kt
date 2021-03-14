package kr.co.seoft.ca.data.api.model

data class CreateContact(val name: String, val email: String)

data class ContactResponse(val contact: Contact)

data class ContactsResponse(val contacts: List<Contact>)

data class Contact(val id: Long, val name: String, val email: String)