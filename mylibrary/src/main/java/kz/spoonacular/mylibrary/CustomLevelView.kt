package kz.spoonacular.mylibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class CustomLevelView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int
) : CardView(context, attributeSet, defStyleAttr){

    private var paintRate = Paint()
    private var paintBg = Paint()
    private var paintText = Paint()
    var rate: Float = 60f
    var overall: Float = 100f
    private var rateWidth = (width * rate / 100.0).toInt()
    private var text = "${rate}/${overall}"

    init {
        paintRate.apply {
            style = Paint.Style.FILL
            color = Color.YELLOW
        }
        paintBg.apply {
            style = Paint.Style.FILL
            color = Color.GRAY
            alpha = 150
        }
        paintText.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            textSize = (height * 0.9).toFloat()
        }
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate}/${overall}"
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rateWidth = (width * rate / 100.0).toInt()
        text = "${rate}/${overall}"
        paintText.textSize = (height * 0.5).toFloat()
        canvas?.let {
            it.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBg)
            it.drawRect(0F, 0F, rateWidth.toFloat(), height.toFloat(), paintRate)
            it.drawText(text, (width/2.5).toFloat(), (height/2).toFloat(), paintText)
        }
    }
}