package hr.foi.air2203.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import hr.foi.air2203.repository.UserRepository
import hr.foi.air2203.utils.startEditPasswordActivity
import hr.foi.air2203.utils.startLoginActivity
import hr.foi.air2203.utils.startProfilePageActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    var name: String? = user?.displayName

    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }

    fun goToProfile(view: View){
        view.context.startProfilePageActivity()
    }



}