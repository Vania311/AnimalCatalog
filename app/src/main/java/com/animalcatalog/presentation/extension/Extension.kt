package com.animalcatalog.presentation.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import com.animalcatalog.presentation.MenuActivity
import kotlin.math.roundToInt

fun Activity.showActivityMenu () {
    this.startActivity(Intent(this, MenuActivity::class.java))
}

fun Context.dp2pxFloat(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.dp2px(dp: Float): Int = dp2pxFloat(dp).roundToInt()