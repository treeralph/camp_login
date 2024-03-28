package com.example.campsign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    private lateinit var bannerImageView: ImageView
    private lateinit var idTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var mbtiTextView: TextView
    private lateinit var exitButton: Button

    private val bannerImages = listOf(
        R.drawable.mango,
        R.drawable.user_mango_1,
        R.drawable.user_mango_2,
        R.drawable.user_mango_3,
        R.drawable.user_mango_4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userId = intent.getStringExtra(SignInActivity.ID_EXTRA)
        initView(userId?: "")
    }

    private fun initView(userId: String) {
        bannerImageView = findViewById(R.id.homeImageView)
        idTextView = findViewById(R.id.homeIdTextView)
        nameTextView = findViewById(R.id.homeNameTextView)
        ageTextView = findViewById(R.id.homeAgeTextView)
        mbtiTextView = findViewById(R.id.homeMBTITextView)
        exitButton = findViewById(R.id.homeExitButton)

        idTextView.text = if(userId.isEmpty()) "" else "아이디: $userId"
        nameTextView.text = "이름: JUN"
        ageTextView.text = "나이: 31"
        mbtiTextView.text = "MBTI: INTP"

        exitButton.setOnClickListener(exitButtonClickListener)

        bannerImageView.setImageResource(bannerImages[Random.nextInt(0, 5)])
    }

    private val exitButtonClickListener: (View) -> Unit = {
        finish()
    }
}