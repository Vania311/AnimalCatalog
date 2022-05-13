package com.animalcatalog.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.animalcatalog.R
import com.animalcatalog.databinding.CustomviewLayoutBinding
import com.animalcatalog.presentation.extension.dp2px
import com.google.android.material.shape.RelativeCornerSize

@SuppressLint("CustomViewStyleable")
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CustomviewLayoutBinding.inflate(LayoutInflater.from(context), this)

    private val circleSize by lazy { context.dp2px(200f) }

    init {
        with(binding) {
            context.obtainStyledAttributes(attrs, R.styleable.ShapeView).use { typedArray ->
                if (typedArray.getInt(R.styleable.ShapeView_shape, 0) == 0) {
                    apiIv.shapeAppearanceModel =
                        apiIv.shapeAppearanceModel.withCornerSize(RelativeCornerSize(0.5f))
                    apiIv.layoutParams.apply {
                        width = circleSize
                        height = circleSize
                    }
                }
            }
        }
    }
}