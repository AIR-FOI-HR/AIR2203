package hr.foi.air2203.utils

import android.content.Context
import android.content.Intent
import hr.foi.air2203.ui.auth.LoginActivity
import hr.foi.air2203.ui.home.MainPageActivity
import hr.foi.air2203.ui.profile.EditEmailActivity
import hr.foi.air2203.ui.profile.EditPasswordActivity
import hr.foi.air2203.ui.profile.EditUsernameActivity
import hr.foi.air2203.ui.profile.ProfilePageActivity

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

fun Context.startProfilePageActivity() =
    Intent(this, ProfilePageActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startEditPasswordActivity() =
    Intent(this, EditPasswordActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startEditUsernameActivity() =
    Intent(this, EditUsernameActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startEditEmailActivity() =
    Intent(this, EditEmailActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }