package com.blank.chapter10.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blank.chapter10.R
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.model.ResponseLogin
import com.blank.chapter10.utils.ambilText
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    private val dialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        observe(viewModel.stateResponseLogin, this::manageResponseLogin)

        btnLogin.setOnClickListener {
            val pwd = etPwd.ambilText()
            val email = etEmail.ambilText()
            val bodLogin = BodyLogin(pwd, email)
            viewModel.login(bodLogin)
        }
    }

    private fun manageResponseLogin(state: ResultState) {
        when (state) {
            is ResultState.Loading -> {
                showLoading()
            }

            is ResultState.Error -> {
                hideLoading()
                Toast.makeText(
                    this@LoginActivity,
                    state.throwable.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            is ResultState.Success<*> -> {
                hideLoading()
                val data = state.data as ResponseLogin
                Toast.makeText(this@LoginActivity, data.toString(), Toast.LENGTH_LONG).show()
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