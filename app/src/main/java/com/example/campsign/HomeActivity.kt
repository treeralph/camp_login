package com.example.campsign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    private lateinit var idTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var mbtiTextView: TextView
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userId = intent.getStringExtra(SignInActivity.ID_EXTRA)
        initView(userId?: "")
    }

    private fun initView(userId: String) {
        idTextView = findViewById(R.id.homeIdTextView)
        nameTextView = findViewById(R.id.homeNameTextView)
        ageTextView = findViewById(R.id.homeAgeTextView)
        mbtiTextView = findViewById(R.id.homeMBTITextView)
        exitButton = findViewById(R.id.homeExitButton)

        idTextView.text = if(userId.isEmpty()) "" else "아이디: $userId"
        exitButton.setOnClickListener(exitButtonClickListener)
    }

    private val exitButtonClickListener: (View) -> Unit = {
        finish()
    }
}