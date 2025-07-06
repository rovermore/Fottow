// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    kotlin("kapt") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
    kotlin("jvm") version "2.1.0"
    alias(libs.plugins.google.gms.google.services) apply false
}
