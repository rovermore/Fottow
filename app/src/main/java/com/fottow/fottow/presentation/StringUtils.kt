package com.fottow.fottow.presentation

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length >= 6 && containsDigit() && containsLetter()
}

private fun String.containsDigit(): Boolean {
    return any { it.isDigit() }
}

private fun String.containsLetter(): Boolean {
    return any { it.isLetter() }
}