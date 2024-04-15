package github.natholdallas.enshrine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
open class App

lateinit var context: ApplicationContext

fun main(args: Array<String>) {
    context = runApplication<App>(*args)
}
