package uz.developersdreams.effectivemobile.feature.extensions

import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.developersdreams.effectivemobile.feature.domain.model.user_text.UserText
import uz.developersdreams.effectivemobile.feature.util.Constants
import uz.developersdreams.effectivemobile.feature.util.setViewInvisible
import uz.developersdreams.effectivemobile.feature.util.setViewVisible

fun Fragment.navigate(
    id: Int,
    bundle: Bundle? = null
) {
    this.findNavController().navigate(id, args = bundle)
}

fun Fragment.navigateUp() {
    this.findNavController().popBackStack()
}

fun List<UserText>.extractUserTextName(isFrom: Int = Constants.FROM) : String {
    return this.find { it.isFrom == isFrom }?.text ?: ""
}

fun List<UserText>.extractUserText(isFrom: Int = Constants.FROM) : UserText? {
    return this.find { it.isFrom == isFrom }
}

fun ImageView.showHideView(isShow: Boolean = true) {
    if (isShow) setViewVisible(this)
    else setViewInvisible(this)
}

fun CardView.showHideView(isShow: Boolean = true) {
    if (isShow) setViewVisible(this)
    else setViewInvisible(this)
}