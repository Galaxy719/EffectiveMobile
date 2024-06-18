package uz.developersdreams.effectivemobile.feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText

@Dao
interface UserTextDao {

    @Query("SELECT * FROM user_text_table")
    fun getUserTexts(): Flow<List<UserText>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserText(userText: UserText)
}