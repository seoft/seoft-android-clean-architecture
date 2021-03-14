package kr.co.seoft.ca.mapper

import kr.co.seoft.ca.data.LoginRequestModel
import kr.co.seoft.ca.data.LoginResponseModel
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity


//data class CreateContactModel(val name: String, val email: String) {
//    companion object {
//        fun generateContact(): CreateContactModel {
//            return CreateContactModel(
//                UUID.randomUUID().toString().replace("-", "").substring(0, 5),
//                UUID.randomUUID().toString().replace("-", "")
//                    .let {
//                        "${it.substring(0, Random.nextInt(5, 8))}@" +
//                                "${it.reversed().substring(0, Random.nextInt(4, 8))}.com"
//                    }
//            )
//        }
//    }
//}
//
//data class ContactModel(val id: Long, val name: String, val email: String, val isBookmark: Boolean)

//class ContactMapper {
//    fun convert(contacts: List<ContactEntity>): List<ContactModel> {
//        return contacts.map { convert(it) }
//    }
//
//    fun convert(contactEntity: ContactEntity): ContactModel {
//        return contactEntity.run { ContactModel(id, name, email, isBookmark) }
//    }
//
//    fun convert(generateContact: CreateContactModel): CreateContactEntity {
//        return generateContact.run { CreateContactEntity(name, email) }
//    }
//
//}

//data class LoginRequestModel(val id: String, val password: String)
//
//data class LoginResponseModel(val complete: Boolean)

//class LoginMapper {
//
//    fun convert(loginRequestModel: LoginRequestModel): LoginRequestEntity {
//        return loginRequestModel.run { LoginRequestEntity(id, password) }
//    }
//
//    fun convert(loginResponseEntity: LoginResponseEntity): LoginResponseModel {
//        return loginResponseEntity.run { LoginResponseModel(complete) }
//    }
//}
