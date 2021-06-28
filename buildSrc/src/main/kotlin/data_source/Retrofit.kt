/* */
object Retrofit {

    /* */
    private object Version {

        /* */
        const val retrofit = "2.9.0"

        /* */
        const val loggingInterceptor = "4.8.0"

    }

    /* */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"

    /* */
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}"

}