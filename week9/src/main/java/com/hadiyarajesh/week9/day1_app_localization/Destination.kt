package com.hadiyarajesh.week9.day1_app_localization

sealed interface Destination {
    val route: String

    data object Home : Destination {
        override val route: String
            get() = "home"
    }

    data object Settings : Destination {
        override val route: String
            get() = "settings"
    }
}