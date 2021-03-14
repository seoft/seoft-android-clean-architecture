package kr.co.seoft.ca.di

import kr.co.seoft.ca.domain.usecase.common.RxScheduler
import kr.co.seoft.ca.domain.usecase.contact.*
import kr.co.seoft.ca.domain.usecase.login.RequestLoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<RxScheduler> { AppScheduler() }
    factory { RequestLoginUseCase(get(), get()) }
    factory { GetInitContactsUseCase(get(), get()) }
    factory { GetMoreContactsUseCase(get(), get()) }
    factory { GetContactUseCase(get(), get()) }
    factory { DeleteContactUseCase(get(), get()) }
    factory { AddContactUseCase(get(), get()) }
    factory { SetContactBookmarkUseCase(get(), get()) }
    factory { UnSetContactBookmarkUseCase(get(), get()) }
}
