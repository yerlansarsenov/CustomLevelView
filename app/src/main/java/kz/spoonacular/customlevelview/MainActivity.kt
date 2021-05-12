package kz.spoonacular.customlevelview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kz.spoonacular.mylibrary.CustomLevelView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customLevelView = findViewById<CustomLevelView>(R.id.custom)
        customLevelView.apply {
            setValues(50f, 100f)
        }
        val handler = Handler()
        handler.postDelayed({
            customLevelView.apply {
                setValues(47.2f, 68f)
            }
        }, 2000)
    }
}