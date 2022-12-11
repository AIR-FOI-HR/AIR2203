package hr.foi.air2203.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import hr.foi.air2203.R
import hr.foi.air2203.databinding.ActivityProfileBinding
import hr.foi.air2203.utils.startHomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class ProfilePageActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory : ProfileViewModelFactory by instance()

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel

        val actionbar = supportActionBar
        actionbar!!.title = "Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        startHomeActivity()
    }
}