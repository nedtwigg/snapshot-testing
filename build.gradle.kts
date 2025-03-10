import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
}

val javaSnapshotTestingVersion = "4.0.7"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("io.github.origin-energy:java-snapshot-testing-junit5:$javaSnapshotTestingVersion")
    testImplementation("io.github.origin-energy:java-snapshot-testing-plugin-jackson:$javaSnapshotTestingVersion")
    testImplementation("org.slf4j:slf4j-simple:2.0.11")

    testImplementation(platform("com.fasterxml.jackson:jackson-bom:2.16.1"))
    testImplementation("com.fasterxml.jackson.core:jackson-core")
    testImplementation("com.fasterxml.jackson.core:jackson-databind")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")

    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.28.0")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(PASSED, SKIPPED, FAILED)
        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
