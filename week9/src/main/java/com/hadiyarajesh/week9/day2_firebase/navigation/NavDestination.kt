package com.hadiyarajesh.week9.day2_firebase.navigation

sealed interface NavDestination {
    val route: String

    data object Home : NavDestination {
        override val route: String
            get() = "home"
    }

    data object SignUp : NavDestination {
        override val route: String
            get() = "sing_up"
    }

    data object SignIn : NavDestination {
        override val route: String
            get() = "sign_in"
    }
}
