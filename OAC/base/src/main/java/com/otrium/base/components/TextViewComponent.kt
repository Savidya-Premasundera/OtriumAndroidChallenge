package com.otrium.base.components

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.otrium.base.R
import com.otrium.base.databinding.ComponentTextviewBinding
import com.otrium.base.enums.TextAlignment
import com.otrium.base.enums.TextColor
import com.otrium.base.enums.TextSize
import com.otrium.base.enums.TextStyle
import com.otrium.base.helpers.setFontSizeToHeight
import com.otrium.base.helpers.setFontSizeToWidth

class TextViewComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var textViewComponent: TextView

    init {

        val componentTextViewBinding: ComponentTextviewBinding =
            ComponentTextviewBinding.inflate(LayoutInflater.from(context), this, true)

        textViewComponent = componentTextViewBinding.componentTv

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(it, R.styleable.TextViewComponent, 0, 0)

            if (typedArray.hasValue(R.styleable.TextViewComponent_textStyle)) {
                val textStyle = TextStyle.values()[typedArray.getInt(
                    R.styleable.TextViewComponent_textStyle
                    , 0
                )]
                setTextViewStyle(textStyle)
            }

            if (typedArray.hasValue(R.styleable.TextViewComponent_textColor)) {
                val componentColor = TextColor.values()[typedArray.getInt(
                    R.styleable.TextViewComponent_textColor
                    , 0
                )]
                setTextViewColor(componentColor)
            }

            if (typedArray.hasValue(R.styleable.TextViewComponent_tv_text)) {
                val textViewText = typedArray.getString(
                    R.styleable.TextViewComponent_tv_text
                )
                setTextViewText(textViewText)
            }

            if (typedArray.hasValue(R.styleable.TextViewComponent_textAlignment)) {
                val textAlignment = TextAlignment.values()[typedArray.getInt(
                    R.styleable.TextViewComponent_textAlignment
                    , 0
                )]
                setTextAlignment(textAlignment)
            }

            if (typedArray.hasValue(R.styleable.TextViewComponent_tv_maxLines)) {
                val textViewLines = typedArray.getInt(
                    R.styleable.TextViewComponent_tv_maxLines, 0
                )
                setTextViewNoOfLines(textViewLines)
            }

            if (typedArray.hasValue(R.styleable.TextViewComponent_tv_letter_space)) {
                val textViewSpace = typedArray.getFloat(
                    R.styleable.TextViewComponent_tv_letter_space, 0f
                )
                setTextViewLetterSpacing(textViewSpace)
            }

        }

    }

    /**
     * Set font size to device width
     *
     * @param fontWeight    Required size
     * @return Scaled font size
     */
    private fun setWidthFontSize(fontWeight: Float): Float {

        return setFontSizeToWidth(fontWeight, context)

    }

    /**
     * Set font size to device height
     *
     * @param fontWeight    Required size
     * @return Scaled font size
     */
    private fun setHeightFontSize(fontWeight: Float): Float {

        return setFontSizeToHeight(fontWeight, context)

    }

    /**
     * Set font typeface
     *
     * @param font  Font face
     */
    fun setTypeFace(font: Typeface) {

        textViewComponent.typeface = font

    }

    /**
     * Set text style
     *
     * @param style Required text style
     */
    fun setTextViewStyle(style: TextStyle?) {

        when (style) {
            TextStyle.TITLE01H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.TITLE01H.textSize)
                setTextViewLetterSpacing(0.15f)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_bold)
                    ?.let { setTypeFace(it) }
            }
            TextStyle.TITLE02H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.TITLE02H.textSize)
                setTextViewLetterSpacing(0.1f)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_bold)
                    ?.let { setTypeFace(it) }
            }
            TextStyle.BODY01H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.BODY01H.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_semi_bold)?.let { setTypeFace(it) }
            }
            TextStyle.BODY02H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.BODY02H.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            TextStyle.BODY03H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.BODY03H.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            TextStyle.BTN01H -> {
                textViewComponent.textSize = setHeightFontSize(TextSize.BTN01H.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            TextStyle.TITLE01W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.TITLE01W.textSize)
                setTextViewLetterSpacing(0.15f)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_bold)
                    ?.let { setTypeFace(it) }
            }
            TextStyle.TITLE02W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.TITLE02W.textSize)
                setTextViewLetterSpacing(0.1f)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_bold)
                    ?.let { setTypeFace(it) }
            }
            TextStyle.BODY01W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.BODY01W.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro_semi_bold)?.let { setTypeFace(it) }
            }
            TextStyle.BODY02W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.BODY02W.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            TextStyle.BODY03W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.BODY03W.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            TextStyle.BTN01W -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.BTN01W.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
            else -> {
                textViewComponent.textSize = setWidthFontSize(TextSize.BODY01W.textSize)
                ResourcesCompat.getFont(context, R.font.source_sans_pro)?.let { setTypeFace(it) }
            }
        }

    }

    /**
     * Set text color
     *
     * @param textColor Required text color
     */
    fun setTextViewColor(textColor: TextColor?) {

        when (textColor) {
            TextColor.DARK_TV -> {
                textViewComponent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorTxtDark
                    )
                )
            }
            else -> {
                textViewComponent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorTxtDark
                    )
                )
            }
        }

    }

    /**
     * Set text
     *
     * @param text Required text
     */
    fun setTextViewText(text: String?) {

        textViewComponent.text = text

    }

    /**
     * Set text
     *
     * @param text Required text
     */
    fun setTextViewText(text: CharSequence?) {

        textViewComponent.text = text

    }

    /**
     * Get text
     *
     * @return     Textview text
     */
    fun getTextViewText(): String? {

        return textViewComponent.text.toString()

    }

    /**
     * Set text alignment
     *
     * @param textAlignment Text alignment
     */
    fun setTextAlignment(textAlignment: TextAlignment) {

        when (textAlignment) {
            TextAlignment.LEFT -> {
                textViewComponent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                textViewComponent.gravity = Gravity.START
            }
            TextAlignment.RIGHT -> {
                textViewComponent.gravity = Gravity.END
                textViewComponent.gravity = Gravity.RIGHT
                textViewComponent.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            }
            TextAlignment.CENTER -> {
                textViewComponent.gravity = Gravity.CENTER_HORIZONTAL
            }
        }

    }

    /**
     * Set text lines
     *
     * @param noOfLines Number of lines
     */
    fun setTextViewNoOfLines(noOfLines: Int) {

        textViewComponent.minLines = noOfLines
        textViewComponent.maxLines = noOfLines
        textViewComponent.isSingleLine = false
        textViewComponent.ellipsize = TextUtils.TruncateAt.END
        textViewComponent.setLines(noOfLines)

    }

    /**
     * Set letter space
     *
     * @param space     Letter space
     */
    fun setTextViewLetterSpacing(space: Float) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textViewComponent.letterSpacing = space
        } else {
            textViewComponent.textScaleX = space
        }

    }

}