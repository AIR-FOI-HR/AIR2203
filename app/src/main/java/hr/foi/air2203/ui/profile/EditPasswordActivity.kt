package hr.foi.air2203.ui.profile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import hr.foi.air2203.R
import hr.foi.air2203.databinding.ActivityEditpasswordBinding
import hr.foi.air2203.utils.startHomeActivity
import hr.foi.air2203.utils.startProfilePageActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class EditPasswordActivity : AppCompatActivity(), EditListener, KodeinAware {

    override val kodein by kodein()
    private val factory : ProfileViewModelFactory by instance()

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editpassword)

        val binding: ActivityEditpasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_editpassword)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.editListener = this
        val actionbar = supportActionBar
        actionbar!!.title = "Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStarted() {

    }

    override fun onSuccess() {
        startProfilePageActivity()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        startProfilePageActivity()
    }
}