import com.troy.tersive.server.dto.LoginDto
import com.troy.tersive.server.dto.UserDto
import com.troy.tersive.server.handler.UserHandler
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.text.DateFormat

fun main(args: Array<String>) {
    embeddedServer(
            Netty,
            watchPaths = listOf("troyhen/tersive-server-ktor"),
            port = 8080,
            module = Application::tersiveModule
    ).apply {
        start(wait = true)
    }
}

fun Application.tersiveModule() {
    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    routing {
        get("/hello") {
            call.respondText("Hello, world!", ContentType.Text.Html)
        }

        put("/user") {
            UserHandler.register(call.receive())
        }
        post("/user") {
            UserHandler.update(call.receive())
        }
        get("/user") {
            UserHandler.login(LoginDto(call.parameters["email"], call.parameters["password"]))
        }
        delete("/user") {
            UserHandler.delete(call.receive())
        }
    }
}
