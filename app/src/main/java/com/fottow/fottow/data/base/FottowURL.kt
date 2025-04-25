package com.fottow.fottow.data.base

object FottowURL {

    private const val BASE_URL = "https://778meigv57.execute-api.us-east-1.amazonaws.com/dev"

    const val IMAGES_UPLOAD = "$BASE_URL/images/upload"
    const val IDENTIFICATION_SELFIE_UPLOAD = "$BASE_URL/images/identify"
    const val IMAGES = "$BASE_URL/images/images"
    const val LOGIN = "$BASE_URL/auth/login"
    const val REGISTER = "$BASE_URL/auth/signup"
    const val LOGOUT = "$BASE_URL/auth/logout"
}