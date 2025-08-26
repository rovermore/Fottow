package com.fottow.fottow.data.base

import com.fottow.fottow.BuildConfig

object FottowURL {

    var BASE_URL = BuildConfig.base_url

    val IMAGES_UPLOAD = "$BASE_URL/images/upload"
    val IDENTIFICATION_SELFIE_UPLOAD = "$BASE_URL/images/identify"
    val IMAGES = "$BASE_URL/images/images"
    val LOGIN = "$BASE_URL/auth/login"
    val REGISTER = "$BASE_URL/auth/signup"
    val LOGOUT = "$BASE_URL/auth/logout"
}