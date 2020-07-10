package abbos2101.mvvmdemo.common

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.terrabayt.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import retrofit2.Response

fun ImageView.setSrc(drawable: Int, animation: Int = 0) {
    Glide.with(context)
        .load(drawable)
        .into(this)
    if (animation != 0)
        this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha))
}

fun ImageView.setSrc(url: String, animation: Int = 0) {
    Glide.with(context)
        .load(url)
        .into(this)
    if (animation != 0)
        this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha))
}

fun Any.toJsonString() = Gson().toJson(this)

fun <T> String.fromJsonObject(type: Class<T>) = Gson().fromJson(this, type)

data class Extensions<T>(
    val dataExtensions: ArrayList<T> = arrayListOf()
)

fun View.setVisible(visibility: Boolean) {
    if (visibility) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun <T : ViewModel> FragmentActivity.getViewModel(type: Class<T>) =
    ViewModelProviders.of(this).get(type)

fun <T> Response<T>.getNetInfo(): NetInfo? {
    return if (this.code() == 200) {
        if (this.body() != null) NetInfo.SUCCESS
        else NetInfo.EMPTY
    } else if (this == null) NetInfo.NULL
    else NetInfo.ERROR
}

enum class NetInfo {
    SUCCESS,
    ERROR,
    EMPTY,
    NULL
}

fun <T> lazyFast(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun FragmentActivity.showSnackbar(text: String) {
    Snackbar.make(this.window.decorView.rootView, text, Snackbar.LENGTH_LONG).show()
}