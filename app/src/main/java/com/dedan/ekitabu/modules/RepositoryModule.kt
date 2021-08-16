package com.dedan.ekitabu.modules

import com.dedan.ekitabu.data.LoginRepository
import com.dedan.ekitabu.network.UserServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(userServices: UserServices)=LoginRepository(userServices)

}