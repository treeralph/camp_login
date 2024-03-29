package com.example.campsign.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignUpViewModel: ViewModel() {

    /**
     * link: https://stackoverflow.com/questions/23214434/regular-expression-in-android-for-password-field
     * */
    private val pwRegex = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[a-zA-Z])" +
            "(?=.*[~!@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{8,}" +
            "$"
    )

    private val nameRegex = "^[a-zA-Z]{2,20}$".toRegex()

    private val _name = MutableLiveData("")
    private val _id = MutableLiveData("")
    private val _pw = MutableLiveData("")

    val name: LiveData<String> = _name
    val id: LiveData<String> = _id
    val pw: LiveData<String> = _pw

    fun nameOnChanged(input: String) {
        _name.value = input
    }

    fun idOnChanged(input: String) {
        _id.value = input
    }

    fun pwOnChanged(input: String) {
        _pw.value = input
    }

    fun isValidName(): Boolean {
        return nameRegex.matches(_name.value.toString())
    }

    fun isValidId(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(_id.value.toString()).matches()
    }

    fun isValidPw(): Boolean {
        return pwRegex.matcher(_pw.value.toString()).matches()
    }

    fun isValid(): Boolean {
        return isValidName() && isValidId() && isValidPw()
    }
}