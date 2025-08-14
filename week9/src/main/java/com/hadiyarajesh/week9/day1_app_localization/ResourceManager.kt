package com.hadiyarajesh.week9.day1_app_localization

import android.content.Context
import java.util.Locale

fun Context.updateResources(language: Language) {
    val locale = Locale(language.languageCode)
    Locale.setDefault(locale)

    val configuration = this.resources.configuration
    configuration.setLocale(locale)

    this.resources.updateConfiguration(configuration, resources.displayMetrics)
}
