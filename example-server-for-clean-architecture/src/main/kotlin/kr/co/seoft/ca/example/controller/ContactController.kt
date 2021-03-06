package kr.co.seoft.ca.example.controller

import kr.co.seoft.ca.example.entity.ContactResponse
import kr.co.seoft.ca.example.entity.ContactsResponse
import kr.co.seoft.ca.example.entity.CreateContact
import kr.co.seoft.ca.example.service.ContactService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ContactController {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @Autowired
    lateinit var contactService: ContactService

    @GetMapping("/contacts")
    fun getContacts(): ContactsResponse {
        return contactService.getContacts()
    }

    @GetMapping("/contacts/{cursorId}")
    fun getContacts(@PathVariable("cursorId") cursorId: Long): ContactsResponse {
        return contactService.getContacts(cursorId)
    }

    @GetMapping("/contact/{id}")
    fun getContact(@PathVariable("id") id: Long): ContactResponse {
        return contactService.getContact(id)
    }

    @PostMapping("/contact/{id}")
    fun addContact(createContact:CreateContact): ContactResponse {
        return contactService.addContact(createContact)
    }

    @DeleteMapping("/contact/{id}")
    fun deleteContact(@PathVariable("id") id: Long): ContactResponse {
        return contactService.deleteContact(id)
    }

}