package hr.foi.air2203.ui.profile

interface EditListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}