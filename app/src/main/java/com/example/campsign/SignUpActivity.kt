package com.example.campsign

import android.content.Intent
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.signUpNameEditText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                viewModel.nameOnChanged(text.toString())
            },
        )

        binding.signUpIdEditText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                viewModel.idOnChanged(text.toString())
            }
        )

        binding.signUpPwEditText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                viewModel.pwOnChanged(text.toString())
            }
        )

        binding.signUpSignUpButton.setOnClickListener(signUpButtonOnClickListener)
    }

    private fun initViewModel() {
        viewModel.name.observe(this) {
            if(!viewModel.isValidName() && it.isNotEmpty()) {
                binding.signUpNameEditText.error =
                    resources.getString(R.string.name_invalidation_indic_text)
            }
        }

        viewModel.id.observe(this) {
            if(!viewModel.isValidId() && it.isNotEmpty()) {
                binding.signUpIdEditText.error =
                    resources.getString(R.string.id_invalidation_indic_text)
            }
        }

        viewModel.pw.observe(this) {
            if(!viewModel.isValidPw() && it.isNotEmpty()) {
                binding.signUpPwEditText.error =
                    resources.getString(R.string.pw_invalidation_indic_text)
            }
        }
    }

    private val signUpButtonOnClickListener: (View) -> Unit = {
        if(viewModel.isValid()) {
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
        else Toast.makeText(
            this,
            "모든 정보를 알맞게 입력해주세요",
            Toast.LENGTH_SHORT).show()
    }
}