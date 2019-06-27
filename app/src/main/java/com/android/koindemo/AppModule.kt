package com.android.koindemo

import androidx.room.Room
import com.android.koindemo.repos.KoinRepo
import com.android.koindemo.repos.UserDetailsRepo
import com.android.koindemo.viewmodels.KoinViewModel
import com.android.koindemo.viewmodels.UserDetailsViewModel
import com.android.koindemo.views.activities.KoinFragmentActivity
import com.android.koindemo.views.activities.KoinScopeActivity
import com.android.koindemo.views.fragments.KoinFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * single - singleton
 * factory - each time new instance
 */

/**
 * Define app level instances here
 */
val appModule = module {
    single {
        Room.databaseBuilder(get(), KoinDatabase::class.java, "koin_db").build()
    }
}

/**
 * Multiple ways to define viewmodel in koin has been demonstrated here. You can use any of them according to your convenience
 */
val viewModelModule = module {
    /**
     * Global scope. These viewmodels are available to all activities and fragments
     * Use any of them
     */
    viewModel { KoinViewModel(get()) } //----->viewmodel without qualifier
    viewModel(named<KoinScopeActivity>()) { KoinViewModel(get()) } //----->viewmodel with class name as qualifier
    viewModel(named("vm")) { KoinViewModel(get()) } //-------> ----->viewmodel with name(string) qualifier
    viewModel { UserDetailsViewModel(get()) }

    /**
     * This will be used as currentScope in KoinScopeActivity only
     */
    scope(named<KoinScopeActivity>()) {
        scoped { KoinRepo(get()) }
        viewModel { KoinViewModel(get()) }
        viewModel(named<KoinScopeActivity>()) { KoinViewModel(get()) }
        viewModel(named("vm")) { KoinViewModel(get()) }
    }

    /**
     * You have to create your own scope and then use these viewmodels
     */
    scope(named("customScope")) {
        scoped { KoinRepo(get()) }
        viewModel { KoinViewModel(get()) }
        viewModel(named<KoinScopeActivity>()) { KoinViewModel(get()) }
        viewModel(named("vm")) { KoinViewModel(get()) }
    }

    scope(named<KoinFragmentActivity>()) {
        scoped { UserDetailsRepo(get()) }
        viewModel(named<KoinFragment>()) { UserDetailsViewModel(get()) }
    }

    scope(named("fragScope")) {
        scoped { UserDetailsRepo(get()) }
        viewModel { UserDetailsViewModel(get()) }
    }


}

/**
 * Factory modules - each time new instance
 */
val repoModule = module {
    factory { KoinRepo(get()) }
    factory { UserDetailsRepo(get()) }
}