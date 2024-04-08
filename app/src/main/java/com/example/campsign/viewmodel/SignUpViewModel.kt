package com.example.campsign.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campsign.ID_TAG
import com.example.campsign.NAME_TAG
import com.example.campsign.PW_TAG
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
    private val _makeAble = MutableLiveData(false)

    val name: LiveData<String> = _name
    val id: LiveData<String> = _id
    val pw: LiveData<String> = _pw
    val makeAble: LiveData<Boolean> = _makeAble

    fun onChanged(input: String, tag: String) {
        when(tag) {
            NAME_TAG -> { _name.value = input }
            ID_TAG -> { _id.value = input }
            PW_TAG -> { _pw.value = input }
            else -> throw Exception()
        }
        updateMakeAble()
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
    private fun updateMakeAble() {
        _makeAble.value = isValidName() && isValidId() && isValidPw()
    }
}