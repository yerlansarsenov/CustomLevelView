package kz.spoonacular.customlevelview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.spoonacular.mylibrary.CustomLevelView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customLevelView = findViewById<CustomLevelView>(R.id.custom)
        customLevelView.apply {
            rate = 45.2f
            overall = 100f
        }
    }
}