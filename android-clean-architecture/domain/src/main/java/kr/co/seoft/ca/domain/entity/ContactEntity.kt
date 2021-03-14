package kr.co.seoft.ca.domain.entity

data class ContactEntity(val id: Long, val name: String, val email: String, val isBookmark: Boolean)

data class CreateContactEntity(val name: String, val email: String) {
    companion object
}