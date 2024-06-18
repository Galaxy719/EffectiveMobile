package uz.developersdreams.effectivemobile.feature.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText

@Database(
    entities = [UserText::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract val userTextDao: UserTextDao
}