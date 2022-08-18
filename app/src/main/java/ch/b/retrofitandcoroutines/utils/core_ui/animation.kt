package ch.b.retrofitandcoroutines.utils.core_ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.view.View
import android.widget.ImageView
import ch.b.retrofitandcoroutines.R
import com.bumptech.glide.Glide

fun ImageView.iconAnimation(borderDrawable: Int, fillDrawable: Int) {
    isClickable = false
    scaleTo(0f) {
        changeDrawable(borderDrawable, fillDrawable)
        scaleTo(1.2f) {
            scaleTo(1f) {
                isClickable = true
            }
        }
    }
}

private fun ImageView.scaleTo(scale: Float, listenerEnd: () -> Unit) {
    getScaleObjectAnimator(scale, scale).apply {
        animatorEndListener { listenerEnd() }
        duration = 200
        start()
    }
}

private fun ImageView.getScaleObjectAnimator(x: Float, y: Float): ObjectAnimator {
    val scaleX1 = PropertyValuesHolder.ofFloat(View.SCALE_X, x)
    val scaleY1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, y)
    return ObjectAnimator.ofPropertyValuesHolder(this, scaleX1, scaleY1)
}

private fun ObjectAnimator.animatorEndListener(listenerEnd: (() -> Unit)) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            listenerEnd()
        }
    })
}

private fun ImageView.changeDrawable(borderDrawable: Int, fillDrawable: Int) {
    if (tag == context.getString(R.string.ic_tag_border)) {
        tag = context.getString(R.string.ic_tag_fill)
        insertDrawable(fillDrawable)
    } else {
        tag = context.getString(R.string.ic_tag_border)
        insertDrawable(borderDrawable)
    }
}

fun ImageView.insertDrawable(ref: Int) {
    Glide.with(context).load(ref).into(this)
}

fun View.translateY(dp: Int) {
    val distance = context.dpToPx(dp).toFloat()
    visibility = View.VISIBLE
    ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, distance).start()
}

fun Context.dpToPx(dp: Int): Int {
    val d: Float = this.resources.displayMetrics.density
    return (dp * d).toInt()
}