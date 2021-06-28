/* */
object Moshi {

    private object Version {

        /* */
        const val moshi = "1.9.3"

        /* */
        const val converter = "2.8.1"

    }

    /* */
    const val kotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

    /* */
    const val converter = "com.squareup.retrofit2:converter-moshi:${Version.converter}"

    /* */
    const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

}