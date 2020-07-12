package com.blank.chapter10.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.blank.chapter10.AppLoader
import com.blank.chapter10.R
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.model.ResponseLogin
import com.blank.chapter10.data.remote.AppApiHelper
import com.blank.chapter10.utils.ambilText
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.observe
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var factory: LoginViewModel.Factory
    private lateinit var viewModel: LoginViewModel
    private val dialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val appApiHelper = AppApiHelper(AppLoader.service())
        val appDataManager = AppDataManager(appApiHelper)
        factory = LoginViewModel.Factory(appDataManager)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
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