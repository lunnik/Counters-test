/* @see [https://developer.android.com/guide/navigation] */
object AndroidXNavigation {

    /* */
    private object Version {

        /* */
        const val navigationVersion = "2.3.3"

    }

    /* */
    const val fragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"

    /* */
    const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"

    /* @see [https://developer.android.com/guide/navigation/navigation-pass-data] */
    const val safeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigationVersion}"

    /* */
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"

}