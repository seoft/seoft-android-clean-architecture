package kr.co.seoft.ca.ui.contact

import kr.co.seoft.ca.domain.entity.ContactEntity

interface OnContactListener {
    fun onContactClick(contact: ContactEntity)
    fun onContactBookmarkClick(contact: ContactEntity)
    fun onContactLongClick(contact: ContactEntity): Boolean
}