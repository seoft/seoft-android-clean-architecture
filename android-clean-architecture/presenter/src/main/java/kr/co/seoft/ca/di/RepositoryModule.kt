package kr.co.seoft.ca.di

import kr.co.seoft.ca.data.mapper.ContactDataMapper
import kr.co.seoft.ca.data.mapper.LoginDataMapper
import kr.co.seoft.ca.data.repository.contact.ContactLocalDataSourceImpl
import kr.co.seoft.ca.data.repository.contact.ContactRemoteDataSourceImpl
import kr.co.seoft.ca.data.repository.contact.ContactRepositoryImpl
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactLocalDataSource
import kr.co.seoft.ca.data.repository.contact.interfaces.ContactRemoteDataSource
import kr.co.seoft.ca.data.repository.login.LoginRemoteDataSourceImpl
import kr.co.seoft.ca.data.repository.login.LoginRepositoryImpl
import kr.co.seoft.ca.data.repository.login.interfaces.LoginRemoteDataSource
import kr.co.seoft.ca.domain.repository.ContactRepository
import kr.co.seoft.ca.domain.repository.LoginRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(get(), LoginDataMapper()) }
    factory<ContactRemoteDataSource> { ContactRemoteDataSourceImpl(get(), ContactDataMapper()) }
    factory<ContactLocalDataSource> { ContactLocalDataSourceImpl(get(), ContactDataMapper()) }

    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<ContactRepository> { ContactRepositoryImpl(get(), get()) }
}
