package kr.co.seoft.ca.di

import kr.co.seoft.ca.ui.contact.ContactViewModel
import kr.co.seoft.ca.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ContactViewModel(get(), get(), get(), get(), get(), get(), get()) }
}