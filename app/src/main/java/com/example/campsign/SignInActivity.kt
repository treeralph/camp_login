package com.example.campsign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    companion object {
        const val ID_EXTRA: String = "ID_EXTRA"
    }

    private lateinit var idEditText: EditText
    private lateinit var pwEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()
    }

    private fun initView() {
        idEditText = findViewById(R.id.signInIdEditText)
        pwEditText = findViewById(R.id.signInPwEditText)
        signInButton = findViewById(R.id.signInSignInButton)
        signUpButton = findViewById(R.id.signInSignUpButton)

        signInButton.setOnClickListener(signInButtonClickListener)
        signUpButton.setOnClickListener(signUpButtonClickListener)
    }

    private val signInButtonClickListener: (View) -> Unit = {
        if(inputValidation()) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(ID_EXTRA, idEditText.text.trim().toString())
            Log.e("TAG", "idEditText.text.trim(): ${idEditText.text.trim()}")
            startActivity(intent)
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private val signUpButtonClickListener: (View) -> Unit = {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun inputValidation(): Boolean {
        return idEditText.text.trim().isNotEmpty() && pwEditText.text.trim().isNotEmpty()
    }
}