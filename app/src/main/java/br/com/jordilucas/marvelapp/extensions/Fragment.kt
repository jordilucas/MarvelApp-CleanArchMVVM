package br.com.jordilucas.marvelapp.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.jordilucas.marvelapp.R

private val animDefault by lazy {
    NavOptions
        .Builder()
        .setEnterAnim(R.anim.slide_in_left)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_right)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()
}

private val animReverse by lazy {
    NavOptions
        .Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_right)
        .setPopEnterAnim(R.anim.slide_in_right)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()
}

fun Fragment.navigate(origin: Int? = null, dest: Int, bundle: Bundle? = null, options: NavOptions = animDefault) {
    if (isVisible) {
        if (origin == null) {
            findNavController().navigate(dest, bundle, options)
        } else if (findNavController().currentDestination?.id == origin) {
            findNavController().navigate(dest, bundle, options)
        }
    }
}

fun Fragment.navigateReverse(origin: Int? = null, dest: Int, bundle: Bundle? = null, options: NavOptions = animReverse) {
    if (isVisible) {
        if (origin == null) {
            findNavController().navigate(dest, bundle, options)
        } else if (findNavController().currentDestination?.id == origin) {
            findNavController().navigate(dest, bundle, options)
        }
    }
}

fun Fragment.navigateBackTo(dest: Int, inclusive: Boolean = false) {
    findNavController().popBackStack(dest, inclusive)
}