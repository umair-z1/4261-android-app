package com.example.mapsapptest

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class AuViewModel : ViewModel() {
    lateinit var context: Context
    lateinit var navigateTo: (String) -> Unit
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var database = Firebase.firestore.collection("user")


    var loginState = mutableStateOf(LoginState())
        private set
    var signUpState = mutableStateOf(SignUpState())
        private set
    var dataState = mutableStateOf(User())
        private set

    fun logOut() {
        firebaseAuth.signOut()
        updateDataState("", "", "")
        updateLoginState("", "")
        updateSignUpState("", "")
        navigateTo("login")
    }

    fun savedata() {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        val db = database.document(id)

        val userData = hashMapOf(
            "name" to dataState.value.name,
            "gtid" to dataState.value.gtid,
            "gtuser" to dataState.value.gtuser
        )

        db.set(userData)
            .addOnSuccessListener {
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to Save", Toast.LENGTH_SHORT).show()
            }
    }

    fun signUp() {
        if (signUpState.value.email.isNotEmpty() && signUpState.value.password.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(signUpState.value.email, signUpState.value.password).addOnCompleteListener{
                if (it.isSuccessful) {
                    navigateTo("login")
                } else {
                    AlertDialog.Builder(context)
                        .setMessage(it.exception.toString())
                        .setPositiveButton("OK"){
                            dialog, which -> dialog.dismiss()
                        }.show()
                }
            }
        }
    }

    fun login() {
        if (loginState.value.email.isNotEmpty() && loginState.value.password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(loginState.value.email, loginState.value.password).addOnCompleteListener{
                if (it.isSuccessful) {
                    val id = FirebaseAuth.getInstance().currentUser!!.uid
                    val db = database.document(id)
                    db.get()
                        .addOnSuccessListener {document ->
                            if (document != null && document.exists()) {
                                updateDataState(
                                    document.getString("name"),
                                    document.getString("gtid"),
                                    document.getString("gtuser")
                                )
                            }
                        }
                        .addOnFailureListener {
                            updateDataState("", "", "")
                        }
                    navigateTo("session")
                } else {
                    AlertDialog.Builder(context)
                        .setMessage(it.exception.toString())
                        .setPositiveButton("OK"){
                                dialog, which -> dialog.dismiss()
                        }.show()
                }
            }
        }
    }

    fun updateDataState(name : String? = null, gtid : String? = null, gtuser : String? = null) {
        name?.let {
            dataState.value = dataState.value.copy(name = it)
        }
        gtid?.let {
            dataState.value = dataState.value.copy(gtid = it)
        }
        gtuser?.let {
            dataState.value = dataState.value.copy(gtuser = it)
        }
    }

    fun updateSignUpState(email : String? = null, password : String? = null) {
        email?.let {
            signUpState.value = signUpState.value.copy(email = it)
        }
        password?.let {
            signUpState.value = signUpState.value.copy(password = it)
        }
    }

    fun updateLoginState(email : String? = null, password: String? = null) {
        email?.let {
            loginState.value = loginState.value.copy(email = it)
        }
        password?.let {
            loginState.value = loginState.value.copy(password = it)
        }
    }

    fun showSignUp() {
        updateLoginState("", "")
        updateSignUpState("", "")
        navigateTo("signUp")
    }

    fun showLogin() {
        updateLoginState("", "")
        updateSignUpState("", "")
        navigateTo("login")
    }
}