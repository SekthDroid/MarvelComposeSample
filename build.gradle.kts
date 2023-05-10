import org.gradle.api.tasks.Delete

plugins {
    // Add your plugins here
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}