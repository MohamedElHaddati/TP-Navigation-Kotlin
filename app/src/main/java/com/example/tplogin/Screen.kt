package com.example.tplogin

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_screen")
    object LoginScreen : Screen("login_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append( route )
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}