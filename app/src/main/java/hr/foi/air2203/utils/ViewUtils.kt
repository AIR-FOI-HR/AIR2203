package hr.foi.air2203.utils

import android.content.Context
import android.content.Intent
import hr.foi.air2203.ui.auth.LoginActivity
import hr.foi.air2203.ui.home.MainPageActivity

fun Context.startHomeActivity() =
    Intent(this, MainPageActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }