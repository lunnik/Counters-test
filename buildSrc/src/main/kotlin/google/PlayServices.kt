/**/
object PlayServices {
    /* */
    private object Version {

        /* */
        const val playServices = "4.3.8"

        /* */
        const val playServicesTasks = "17.2.1"
    }

    const val playServices = "com.google.gms:google-services:${Version.playServices}"

    const val playServicesTasks =
        "com.google.android.gms:play-services-tasks:${Version.playServicesTasks}"

}