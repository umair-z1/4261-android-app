package com.example.mapsapptest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SessionScreen(viewModel : AuViewModel) {
    val state by viewModel.dataState

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "GT Info Passport")
        TextField(
            value = state.name,
            onValueChange = { viewModel.updateDataState(name = it) },
            placeholder = { Text(text = "Name") }
        )
        TextField(
            value = state.gtid,
            onValueChange = { viewModel.updateDataState(gtid = it) },
            placeholder = { Text(text = "GT ID") }
        )
        TextField(
            value = state.gtuser,
            onValueChange = { viewModel.updateDataState(gtuser = it) },
            placeholder = { Text(text = "GT Username") }
        )
        Button(onClick = viewModel::savedata) {
            Text("Save")
        }
        Button(onClick = viewModel::logOut) {
            Text("Log Out")
        }
    }
}

@Composable
fun SignUpScreen(viewModel : AuViewModel) {
    val state by viewModel.signUpState

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Register")
        TextField(
            value = state.email,
            onValueChange = { viewModel.updateSignUpState(email = it) },
            placeholder = { Text(text = "Email") }
        )

        TextField(
            value = state.password,
            onValueChange = { viewModel.updateSignUpState(password = it) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Password") }
        )

        Button(onClick = viewModel::signUp) {
            Text(text = "Sign Up")
        }

        TextButton(onClick = viewModel::showLogin) {
            Text(text = "Log in to an Existing Account")
        }
    }
}

@Composable
fun LoginScreen(viewModel : AuViewModel) {
    val state by viewModel.loginState

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Log In")
        TextField(
            value = state.email,
            onValueChange = { viewModel.updateLoginState(email = it) },
            placeholder = { Text(text = "Email") }
        )

        TextField(
            value = state.password,
            onValueChange = { viewModel.updateLoginState(password = it) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Password") }
        )

        Button(onClick = viewModel::login) {
            Text(text = "Login")
        }

        TextButton(onClick = viewModel::showSignUp) {
            Text(text = "Register New Account")
        }
    }
}