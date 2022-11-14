package hr.foi.air2203.repository

import com.google.firebase.auth.UserProfileChangeRequest

class UserRepository (
    private val firebase: FirebaseSource
){
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun updateprofile(profileupdates: UserProfileChangeRequest) = firebase.UpdateProfile(profileupdates)

    fun sendverification() = firebase.SendVerificationForNewUser()
}