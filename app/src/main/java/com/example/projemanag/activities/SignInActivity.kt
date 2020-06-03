package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.api.LoginPostData
import com.example.projemanag.api.RetrofitAuthBuilder
import com.example.projemanag.models.Data.authUser
import com.example.projemanag.repository.Repository
import com.example.projemanag.utils.Constants
import kotlinx.android.synthetic.main.activity_sign_in.btn_sign_in
import kotlinx.android.synthetic.main.activity_sign_in.et_email_sign_in
import kotlinx.android.synthetic.main.activity_sign_in.et_password_sign_in
import kotlinx.android.synthetic.main.activity_sign_in.toolbar_sign_in_activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpRetryException

class SignInActivity : BaseActivity() {

//    private lateinit var auth: FirebaseAuth
    private val TAG = "SignIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
//        auth = FirebaseAuth.getInstance()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_sign_in.setOnClickListener {
            signInRegisteredUser()
        }

        setUpActionBar()
    }

    fun signInSuccess() {
        hideProgressDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar_sign_in_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolbar_sign_in_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun signInRegisteredUser() {
        val email: String = et_email_sign_in.text.toString().trim { it <= ' ' }
        val password: String = et_password_sign_in.text.toString().trim { it <= ' ' }
        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            Log.d("Progress Dialog", "signInRegisteredUser")
            CoroutineScope(Dispatchers.IO).launch {
                val response =
                    RetrofitAuthBuilder.apiService.signIn(
                        Constants.APIKey,
                        LoginPostData(email, password)
                    )
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            authUser = response.body()!!
                            Repository().loadUserData(this@SignInActivity)
                            Log.d(TAG, "signInWithEmail:success")
                        } else {
                            hideProgressDialog()
                            Log.w(TAG, "Failure to sigIn, Code:${response.code()}")
                            Toast.makeText(
                                this@SignInActivity,
                                "Auth failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: HttpRetryException) {
                        println("Exception ${e.message}")
                    } catch (e: Throwable) {
                        println("Ooops: Something else went wrong")
                    }
                }
            }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter a password")
                false
            }
            else -> {
                true
            }
        }
    }
}
