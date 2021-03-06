package kr.co.seoft.ca.example

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*

@SpringBootApplication
class ExampleApplication

const val DEFAULT_PORT = 5000

var logger = LoggerFactory.getLogger("ExampleApplication")
var EXCEPTION_MESSAGE = "\n>>> Input wrong arguments, Please run love this command\n>>> gradlew bootRun --args'port=5000'"

fun main(args: Array<String>) {
    var port = DEFAULT_PORT

    when {
        args.isEmpty() -> logger.info("###### Run using default value ######")
        args.size == 1 -> {
            logger.info("###### Run using user arguments ######")
            port = parsePort(args[0])
        }
        else -> throw Exception(EXCEPTION_MESSAGE)
    }

    val application = SpringApplication(ExampleApplication::class.java).apply {
        setDefaultProperties(Properties().apply {
            put("server.port", port)
        })
    }

    application.run(*args)

    logger.info("")
    logger.info("###############################################################")
    logger.info("##  Swagger UI page : http://127.0.0.1:$port/swagger-ui.html")
    logger.info("###############################################################")
}

@Throws(Exception::class)
fun parsePort(args: String): Int {
    return try {
        val params = args.split(",")
        when {
            params[0].contains("port") -> params[0].split("=")[1].toInt()
            params[1].contains("port") -> params[1].split("=")[1].toInt()
            else -> throw Exception(EXCEPTION_MESSAGE)
        }
    } catch (e: Exception) {
        throw Exception(EXCEPTION_MESSAGE)
    }
}
