package com.example.campsign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.campsign.databinding.ActivitySignUpBinding
import com.example.campsign.viewmodel.SignUpViewModel


class SignUpActivity : AppCompatActivity() {

    companion object {
        const val ACTIVITY_RESULT_ID = "id"
        const val ACTIVITY_RESULT_PW = "pw"
    }

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by lazy {
        ViewModelProvider(this)[SignUpViewModel::class.java]
    }

    private val tag2valid: MutableMap<String, Boolean> by lazy {
        mutableMapOf(
            binding.signUpNameEditText.tag.toString() to false,
            binding.signUpIdEditText.tag.toString() to false,
            binding.signUpPwEditText.tag.toString() to false
        )
    }

    private val tag2errorMessage: Map<String, String> by lazy {
        mapOf(
            binding.signUpNameEditText.tag.toString()
                    to resources.getString(R.string.name_invalidation_indic_text),
            binding.signUpIdEditText.tag.toString()
                    to resources.getString(R.string.id_invalidation_indic_text),
            binding.signUpPwEditText.tag.toString()
                    to resources.getString(R.string.pw_invalidation_indic_text)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {

        listOf(
            binding.signUpNameEditText,
            binding.signUpIdEditText,
            binding.signUpPwEditText
        ).forEach { editText ->
            with(editText) {
                addTextChangedListener(
                    onTextChanged = { text, _, _, _ ->
                        viewModel.onChanged(text.toString(), tag as String)
                    }
                )
                setOnFocusChangeListener { v, hasFocus ->
                    if(!hasFocus && !tag2valid[tag.toString()]!!) {
                        error = tag2errorMessage[tag.toString()]
                    }
                }
            }
        }

        binding.signUpSignUpButton.setOnClickListener(signUpButtonOnClickListener)
    }

    private fun initViewModel() {
        viewModel.name.observe(this) {
            tag2valid[NAME_TAG] = viewModel.isValidName() || it.isEmpty()
        }

        viewModel.id.observe(this) {
            tag2valid[ID_TAG] = viewModel.isValidId() || it.isEmpty()
        }

        viewModel.pw.observe(this) {
            tag2valid[PW_TAG] = viewModel.isValidPw() || it.isEmpty()
        }

        viewModel.makeAble.observe(this) {
            binding.signUpSignUpButton.isEnabled = it
        }
    }

    private val signUpButtonOnClickListener: (View) -> Unit = {
        setResult(RESULT_OK,
            Intent().apply {
                putExtra(ACTIVITY_RESULT_ID, viewModel.id.value)
                putExtra(ACTIVITY_RESULT_PW, viewModel.pw.value)
            }
        )
        Toast.makeText(
            this,
            "이름: ${viewModel.name.value}, " +
                    "아이디: ${viewModel.id.value}, " +
                    "비밀번호: ${viewModel.pw.value}",
            Toast.LENGTH_SHORT).show()
        finish()
    }
}