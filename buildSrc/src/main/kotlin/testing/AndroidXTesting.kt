/* */
object AndroidXTesting {

    /* */
    private object Version {

        /* */
        const val junit = "1.1.2"

        /* */
        const val espressoCore = "3.3.0"

    }

    /* */
    const val junit = "androidx.test.ext:junit:${Version.junit}"

    /* @see [https://developer.android.com/training/testing/espresso] */
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"

}
