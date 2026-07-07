package edu.cnm.deepdive.codebreaker.app.repesitory

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindPreferenceRepository(impl: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    @Singleton
    fun bindGameRepository(impl: GameRepositoryImpl): GameRepository

}