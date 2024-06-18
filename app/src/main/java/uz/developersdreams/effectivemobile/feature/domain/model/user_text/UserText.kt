package uz.developersdreams.effectivemobile.feature.domain.model.user_text

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.developersdreams.effectivemobile.feature.util.Constants

@Entity(
    tableName = Constants.USER_TEXT_TABLE
)
data class UserText(
    val text: String,
    @PrimaryKey(autoGenerate = false)
    val isFrom: Int = Constants.FROM
)
