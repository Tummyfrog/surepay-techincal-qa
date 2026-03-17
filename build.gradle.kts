plugins {
    java
}

group = "com.surepay"
version = "1.0.0"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.3"
val restAssuredVersion = "6.0.0"
val allureVersion = "2.29.1"
val assertjVersion = "3.27.7"
val gsonVersion = "2.11.0"
val lombokVersion = "1.18.44"
val apacheValidator = "1.10.1"
val slf4j = "2.0.13"

dependencies {
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$restAssuredVersion")
    implementation("io.rest-assured:xml-path:$restAssuredVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("org.assertj:assertj-core:$assertjVersion")
    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")
    implementation("commons-validator:commons-validator:$apacheValidator")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testRuntimeOnly("org.slf4j:slf4j-simple:$slf4j")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()

    systemProperty("env", System.getProperty("env", "qa"))

    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }

    reports {
        junitXml.required.set(true)
        html.required.set(true)
    }
}

tasks.register<Test>("smokeTest") {
    description = "Runs JUnit 5 smoke tests."
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useJUnitPlatform {
        includeTags("smoke")
    }

    systemProperty("env", System.getProperty("env", "qa"))
}

tasks.register<Test>("regressionTest") {
    description = "Runs JUnit 5 regression tests."
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useJUnitPlatform {
        includeTags("regression")
    }

    systemProperty("env", System.getProperty("env", "qa"))
}
