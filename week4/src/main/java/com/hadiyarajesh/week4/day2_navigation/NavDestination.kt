package com.hadiyarajesh.week4.day2_navigation

import kotlinx.serialization.Serializable

//enum class NavDestination(
//    val id: Int,
//    val destinationName: String
//) {
//    Home(1, "home"),
//    Detail(2, "detail")
//}

//sealed class NavDestination(val route: String) {
//    data object Home : NavDestination("home")
//    data object Detail : NavDestination("detail")
//}

sealed interface NavDestination {
    val route: String

    data object Home : NavDestination {
        override val route: String
            get() = "home"
    }

    data object Detail : NavDestination {
        override val route: String
            get() = "detail"
    }

    data object SwapnaScreen : NavDestination {
        override val route: String
            get() = "swapna"
    }

    data object ViewModelScreen : NavDestination {
        override val route: String
            get() = "viewmodel"
    }

    @Serializable
    data class TypeSafeDestination(
        val name: String,
        val surname: String
    ) : NavDestination {
        override val route: String
            get() = "type_safe_destination"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}