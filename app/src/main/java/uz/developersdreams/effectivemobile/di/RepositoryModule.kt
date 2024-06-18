package uz.developersdreams.effectivemobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.developersdreams.effectivemobile.feature.data.data_source.local.UserTextDao
import uz.developersdreams.effectivemobile.feature.data.data_source.remote.ApiHelper
import uz.developersdreams.effectivemobile.feature.data.repository_impl.MainRepositoryImpl
import uz.developersdreams.effectivemobile.feature.data.repository_impl.UserTextRepositoryImpl
import uz.developersdreams.effectivemobile.feature.domain.repository.MainRepository
import uz.developersdreams.effectivemobile.feature.domain.repository.UserTextRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @[Provides Singleton]
    fun provideMainRepository(apiHelper: ApiHelper): MainRepository {
        return MainRepositoryImpl(apiHelper)
    }

    @[Provides Singleton]
    fun provideUserTextRepository(userTextDao: UserTextDao): UserTextRepository {
        return UserTextRepositoryImpl(userTextDao)
    }
}