package hr.foi.air2203

import android.app.Application
import hr.foi.air2203.repository.FirebaseSource
import hr.foi.air2203.repository.UserRepository
import hr.foi.air2203.ui.auth.AuthViewModelFactory
import hr.foi.air2203.ui.home.HomeViewModelFactory
import hr.foi.air2203.ui.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FirebaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }
}