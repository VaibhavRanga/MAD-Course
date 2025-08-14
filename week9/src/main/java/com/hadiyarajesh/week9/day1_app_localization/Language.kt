package com.hadiyarajesh.week9.day1_app_localization

@JvmInline
value class Language(val languageCode: String)

const val LANGUAGE_CODE_ENGLISH = "en"
const val LANGUAGE_CODE_HINDI = "hi"
const val LANGUAGE_CODE_GUJARATI = "gu"
const val LANGUAGE_CODE_MARATHI = "mr"
const val LANGUAGE_CODE_BENGALI = "bn"
const val LANGUAGE_CODE_ORIYA = "or"

val supportedLanguages = mapOf(
    LANGUAGE_CODE_ENGLISH to "English",
    LANGUAGE_CODE_HINDI to "Hindi",
    LANGUAGE_CODE_GUJARATI to "Gujarati",
    LANGUAGE_CODE_MARATHI to "Marathi",
    LANGUAGE_CODE_BENGALI to "Bengali",
    LANGUAGE_CODE_ORIYA to "Oriya",
)
