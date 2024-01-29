package com.example.mapsapptest

data class SignUpState(
    var email: String = "",
    var password: String = ""
)

data class LoginState(
    var email: String = "",
    var password: String = ""
)

data class User (
    var name : String = "",
    var gtid : String = "",
    var gtuser : String = ""
)