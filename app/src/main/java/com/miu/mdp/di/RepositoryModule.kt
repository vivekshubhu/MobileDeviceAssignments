package com.miu.mdp.di

import com.miu.mdp.data.repository.QuizRepositoryImpl
import com.miu.mdp.data.repository.UserRepositoryImpl
import com.miu.mdp.domain.repository.QuizRepository
import com.miu.mdp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindQuizRepository(quizRepositoryImpl: QuizRepositoryImpl): QuizRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}