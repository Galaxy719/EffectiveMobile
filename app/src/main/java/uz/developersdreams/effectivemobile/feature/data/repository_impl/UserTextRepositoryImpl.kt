package uz.developersdreams.effectivemobile.feature.data.repository_impl

import kotlinx.coroutines.flow.Flow
import uz.developersdreams.effectivemobile.feature.data.data_source.local.UserTextDao
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText
import uz.developersdreams.effectivemobile.feature.domain.repository.UserTextRepository

class UserTextRepositoryImpl(
    private val userTextDao: UserTextDao
) : UserTextRepository {

    override fun getUserTexts(): Flow<List<UserText>> {
        return userTextDao.getUserTexts()
    }

    override suspend fun insertUserText(userText: UserText) {
        userTextDao.insertUserText(userText)
    }
}