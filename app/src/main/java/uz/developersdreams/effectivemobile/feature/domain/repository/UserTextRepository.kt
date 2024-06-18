package uz.developersdreams.effectivemobile.feature.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText

interface UserTextRepository {

    fun getUserTexts(): Flow<List<UserText>>

    suspend fun insertUserText(userText: UserText)
}