/* */
object Kotlin {

    /* */
    private object Version {

        /* @see [https://kotlinlang.org/docs/reference/whatsnew1420.html] */
        const val kotlinVersion = "1.4.20"

    }

    /* @see [https://kotlinlang.org/docs/reference/android-overview.html] */
    const val standardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"

    /* */
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"

}