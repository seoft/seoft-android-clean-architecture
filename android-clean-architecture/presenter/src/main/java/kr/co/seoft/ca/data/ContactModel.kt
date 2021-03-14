package kr.co.seoft.ca.data

import kr.co.seoft.ca.domain.entity.CreateContactEntity
import java.util.*
import kotlin.random.Random

fun CreateContactEntity.Companion.generateContact(): CreateContactEntity {
    return CreateContactEntity(
        UUID.randomUUID().toString().replace("-", "").substring(0, 5),
        UUID.randomUUID().toString().replace("-", "")
            .let {
                "${it.substring(0, Random.nextInt(5, 8))}@" +
                        "${it.reversed().substring(0, Random.nextInt(4, 8))}.com"
            }
    )
}