package com.example.campsign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    companion object {
        const val ACTIVITY_RESULT_ID = "id"
        const val ACTIVITY_RESULT_PW = "pw"
    }

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var pwEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
    }

    private fun initView() {
        nameEditText = findViewById(R.id.signUpNameEditText)
        idEditText = findViewById(R.id.signUpIdEditText)
        pwEditText = findViewById(R.id.signUpPwEditText)
        signUpButton = findViewById(R.id.signUpSignUpButton)

        signUpButton.setOnClickListener(signUpButtonOnClickListener)
    }

    private val signUpButtonOnClickListener: (View) -> Unit = {
        if(inputValidation()) {
            setResult(RESULT_OK,
                Intent().apply {
                    putExtra(ACTIVITY_RESULT_ID, idEditText.text.toString())
                    putExtra(ACTIVITY_RESULT_PW, pwEditText.text.toString())
                }
            )
            finish()
        }
        else Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
    }

    private fun inputValidation(): Boolean {
        return nameEditText.text.trim().isNotEmpty()
                && idEditText.text.trim().isNotEmpty()
                && pwEditText.text.trim().isNotEmpty()
    }
}