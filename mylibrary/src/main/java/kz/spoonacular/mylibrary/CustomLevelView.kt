package kz.spoonacular.mylibrary

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView

class CustomLevelView : CardView {

    private var paintRate = Paint()
    private var paintBg = Paint()
    private var paintText = Paint()
    private var rate: Float = 50f
    private var overall: Float = 100f
    private var rateWidth = (width * rate / overall).toInt()
    private var text = "${rate}/${overall}"
    @ColorInt
    private var rateColor = Color.YELLOW
    @ColorInt
    private var backColor = Color.GRAY
    @ColorInt
    private var textColor = Color.BLACK
    private var attributeSet: AttributeSet? = null
    private var defStyleAttr: Int = 0
    private var bounds = Rect()
    private var textPositionX = 0f
    private var textPositionY = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        radius = height / 2f
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate}/${overall}"
        paintText.textSize = (height * 0.5).toFloat()
        paintText.getTextBounds(text, 0, text.length, bounds)
        textPositionX = width/2f - bounds.width()/2
        textPositionY = height/2f + bounds.height()/2
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr) {
        this.attributeSet = attributeSet
        this.defStyleAttr = defStyleAttr
        init()
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet) {
        this.attributeSet = attributeSet
        init()
    }

    constructor(
        context: Context
    ) : super(context) {
        init()
    }

    private fun init() {
        val attrs: TypedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomLevelView,
                defStyleAttr,
                0 // R.style.Widget_MaterialComponents_CardView
        )
        try {
            rateColor = attrs.getColor(R.styleable.CustomLevelView_lvl_rate_color, rateColor)
            backColor = attrs.getColor(R.styleable.CustomLevelView_lvl_back_color, backColor)
            textColor = attrs.getColor(R.styleable.CustomLevelView_lvl_num_color, textColor)
        } finally {
            attrs.recycle()
        }
        paintRate.apply {
            style = Paint.Style.FILL
            color = rateColor
        }
        paintBg.apply {
            style = Paint.Style.FILL
            color = backColor
            alpha = 150
        }
        paintText.apply {
            style = Paint.Style.FILL
            color = textColor
            textSize = (height * 0.5).toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { can ->
            can.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBg)
            can.drawRect(0F, 0F, rateWidth.toFloat(), height.toFloat(), paintRate)
            can.drawText(
                text,
                textPositionX,
                textPositionY,
                paintText
            )
        }
    }

    fun setValues(rate: Float, overall: Float) {
        this.rate = rate
        this.overall = overall
        requestLayout()
    }
}