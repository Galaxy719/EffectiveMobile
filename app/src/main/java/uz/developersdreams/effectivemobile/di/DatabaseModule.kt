package uz.developersdreams.effectivemobile.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.developersdreams.effectivemobile.feature.data.data_source.local.AppDataBase
import uz.developersdreams.effectivemobile.feature.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideAppDatabase(
        app: Application,
    ) : AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            Constants.APP_DATABASE
        ).build()
    }

    @[Provides Singleton]
    fun provideUserTextDao(db: AppDataBase) = db.userTextDao
}