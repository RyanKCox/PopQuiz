package com.revature.popquiz

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Container for dependencies that live for a certain amount of time
@Module
@InstallIn(SingletonComponent::class) //declares that the dependencies will live as long as the app lives
object AppModule {

    @Singleton
    @Provides
    fun provideTest() = "This is a test"
}