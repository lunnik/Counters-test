package com.example.data_source.data.remote.model


/** */
enum class HttpErrorCode(val code: Int) {

    /** 400 **/
    BAD_REQUEST(400),

    /** 401 **/
    UNAUTHORIZED(401),

    /** 403 **/
    FORBIDDEN(403),

    /** 404 **/
    NOT_FOUND(404),

    /** 405 **/
    METHOD_NOT_ALLOWED(405),

    /** 406 **/
    NOT_ACCEPTABLE(406),

    /** 408 **/
    REQUEST_TIMEOUT(408),

    /** 409 **/
    CONFLICT(409),

    /** 409 **/
    PRECONDITION_FAILED(412),

    /** 422 **/
    UN_PROCESSABLE_ENTITY(442),

    /** 500 **/
    SERVER_ERROR(500);


    /**
     *
     *
     */
    companion object {

        /**
         *
         * @param code
         * @return [HttpErrorCode]
         */
        fun fromCode(code: Int): HttpErrorCode = when (code) {
            400 -> BAD_REQUEST
            401 -> UNAUTHORIZED
            403 -> FORBIDDEN
            404 -> NOT_FOUND
            405 -> METHOD_NOT_ALLOWED
            406 -> NOT_ACCEPTABLE
            408 -> REQUEST_TIMEOUT
            409 -> CONFLICT
            412 -> PRECONDITION_FAILED
            422 -> UN_PROCESSABLE_ENTITY
            else -> SERVER_ERROR
        }

    }

}
