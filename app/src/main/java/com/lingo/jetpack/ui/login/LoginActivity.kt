package com.lingo.jetpack.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lingo.jetpack.R
import com.lingo.jetpack.databinding.ActivityLoginBinding
import com.lingo.jetpack.intent.IntentUtils
import com.lingo.jetpack.intent.PendingIntentUtils
import com.lingo.jetpack.notification.NotificationActionUtils
import com.lingo.jetpack.notification.NotificationUtils
import com.lingo.jetpack.ui.settings.SettingsActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binder: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binder.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binder.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binder.password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binder.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        binder.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                binder.username.text.toString(),
                binder.password.text.toString()
            )
        }

        binder.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binder.username.text.toString(),
                    binder.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binder.username.text.toString(),
                            binder.password.text.toString()
                        )
                }
                false
            }

        }

        binder.login.setOnClickListener {
            binder.loading.visibility = View.VISIBLE
            loginViewModel.login(
                binder.username.text.toString(),
                binder.password.text.toString()
            )
        }

        binder.settings.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SettingsActivity::class.java))
        }

        binder.bubble.setOnClickListener {
            val context = this@LoginActivity
            val notificationId = 1
            NotificationUtils.showNotification(
                context, notificationId, NotificationUtils.createAlertNotificationBuilder(
                    context, "测试", "只是一个测试而已",
                    PendingIntentUtils.activityPendingIntent(
                        context,
                        IntentUtils.openSettingsWithNewTaskIntent(context)
                    )
                ).addAction(
                    NotificationActionUtils.createTestActionBuilder(
                        context,
                        PendingIntentUtils.broadcastPendingIntent(
                            context,
                            IntentUtils.sendTestBroadcast(context, notificationId)
                        )
                    ).build()
                )
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}