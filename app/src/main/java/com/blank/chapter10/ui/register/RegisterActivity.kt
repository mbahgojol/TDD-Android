package com.blank.chapter10.ui.register

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blank.chapter10.R
import com.blank.chapter10.data.model.ResponseRegister
import com.blank.chapter10.utils.ambilText
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.observe
import com.blank.teamb_ex.BodyRegister
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()

    private val dialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        observe(viewModel.resultStateResponseRegister, this::manageResponseRegister)

        btnRegister.setOnClickListener {
            val username = etUsername.ambilText()
            val email = etEmail.ambilText()
            val pwd = etPwd.ambilText()
            val bodyRegister = BodyRegister(
                username = username,
                email = email,
                password = pwd
            )
            viewModel.register(bodyRegister)
        }
    }

    private fun manageResponseRegister(state: ResultState) {
        when (state) {
            is ResultState.Loading -> {
                showLoading()
            }

            is ResultState.Error -> {
                hideLoading()
                Toast.makeText(
                    this@RegisterActivity,
                    state.throwable.message.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }

            is ResultState.Success<*> -> {
                hideLoading()
                val data = state.data as ResponseRegister
                Toast.makeText(this@RegisterActivity, data.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showLoading() {
        dialog.let { }.takeIf { !dialog.isShowing }.also { dialog.show() }
    }

    private fun hideLoading() {
        dialog.let { }.takeIf { dialog.isShowing }.also { dialog.hide() }
    }
}