package my.project.group.plugin

import kotlinx.coroutines.*
import org.gradle.api.*
import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger

open class MyPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        suspend fun printFromCoroutine(msg: String, logger: Logger? = null) =
            withContext(Dispatchers.Default) {
                logger?.lifecycle(msg) ?: println(msg)
            }

        target.tasks.register("my-task") { it ->
            it.doFirst {
                // Ignores coroutines' output
                it.logging.captureStandardOutput(LogLevel.LIFECYCLE)

                runBlocking {
                    printFromCoroutine("coroutine println 1")
                    printFromCoroutine("coroutine logger 1", it.logger)
                }

                println("where this message will be?")
                it.logger.lifecycle("and this?")

                runBlocking {
                    printFromCoroutine("coroutine println 2")
                    printFromCoroutine("coroutine logger 2", it.logger)
                }
            }
        }
    }
}