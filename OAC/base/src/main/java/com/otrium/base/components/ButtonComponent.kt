package com.otrium.base.components

import android.R.attr.button
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.otrium.base.R
import com.otrium.base.databinding.ComponentButtonBinding
import com.otrium.base.enums.ButtonSize
import com.otrium.base.enums.ButtonType
import com.otrium.base.enums.TextSize
import com.otrium.base.helpers.setFontSizeToWidth


class ButtonComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var buttonComponent: MaterialButton

    init {

        val componentButtonBinding: ComponentButtonBinding =
            ComponentButtonBinding.inflate(LayoutInflater.from(context), this, true)

        buttonComponent = componentButtonBinding.componentBtn

        attrs?.let {

            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.ButtonComponent, 0, 0)

            if (typedArray.hasValue(R.styleable.ButtonComponent_buttonType)) {
                val componentType = ButtonType.values()[typedArray.getInt(
                    R.styleable.ButtonComponent_buttonType
                    , 0
                )]
                setButtonType(componentType)
            }

            if (typedArray.hasValue(R.styleable.ButtonComponent_buttonScaleSize)) {
                val componentSize = ButtonSize.values()[typedArray.getInt(
                    R.styleable.ButtonComponent_buttonScaleSize
                    , 0
                )]
                setButtonSize(componentSize)
            }

            if (typedArray.hasValue(R.styleable.ButtonComponent_btn_text)) {
                val buttonText = typedArray.getString(
                    R.styleable.ButtonComponent_btn_text
                )
                setButtonText(buttonText)
            }

            if (typedArray.hasValue(R.styleable.ButtonComponent_is_btn_enabled)) {
                val isEnabled = typedArray.getBoolean(
                    R.styleable.ButtonComponent_is_btn_enabled,
                    false
                )
                enableButton(isEnabled)
            }

        }

    }

    /**
     * Set button type
     *
     * @param buttonType Button type
     */
    fun setButtonType(buttonType: ButtonType) {

        when (buttonType) {
            ButtonType.TEXT_THEME -> {
                buttonComponent.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorBtnTheme
                    )
                )
                buttonComponent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorTxtDark
                    )
                )
                buttonComponent.rippleColor =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBtnTheme
                        )
                    )
            }
            ButtonType.TEXT_UNDERLINED -> {
                buttonComponent.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorBtnTheme
                    )
                )
                buttonComponent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorTxtDark
                    )
                )
                buttonComponent.paintFlags = buttonComponent.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                buttonComponent.rippleColor =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBtnTheme
                        )
                    )
            }
        }

    }

    /**
     * Set button text style for phone
     *
     * @param buttonSize Button size
     */
    fun setButtonSize(buttonSize: ButtonSize?) {

        when (buttonSize) {
            ButtonSize.REGULAR_BTN -> {
                buttonComponent.textSize =
                    setButtonFontSize(TextSize.BTN01W.textSize)
                buttonComponent.typeface =
                    ResourcesCompat.getFont(context, R.font.source_sans_pro_semi_bold)
            }
            ButtonSize.LARGE_BTN -> {
            }
            else -> {
                buttonComponent.textSize =
                    setButtonFontSize(TextSize.BTN01W.textSize)
                buttonComponent.typeface =
                    ResourcesCompat.getFont(context, R.font.source_sans_pro_semi_bold)
            }
        }

    }

    /**
     * Set font size to device width
     *
     * @param weight    Required size
     */
    private fun setButtonFontSize(weight: Float): Float {

        return setFontSizeToWidth(weight, context)

    }

    /**
     * Set button text
     *
     * @param text  Button text
     */
    fun setButtonText(text: String?) {

        buttonComponent.text = text

    }

    /**
     * Set onclick listener
     *
     * @param onClick   OnClick listener
     */
    fun setListener(onClick: () -> Unit) {

        buttonComponent.setOnClickListener {
            onClick()
        }

    }

    /**
     * Enable button click
     *
     * @param isEnabled Enable or disable the button
     */
    fun enableButton(isEnabled: Boolean) {

        buttonComponent.isEnabled = isEnabled

    }

}