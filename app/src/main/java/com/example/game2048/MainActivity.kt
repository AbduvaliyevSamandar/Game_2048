package com.example.game2048

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.game2048.controller.AppController
import com.example.game2048.data.SideEnum
import com.example.game2048.databinding.ActivityMainBinding
import com.example.game2048.utils.MyBackgroundGenerator
import com.example.game2048.utils.MyTouchListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val controller = AppController()
    private val views = ArrayList<AppCompatTextView>(16)
    private val backgroundGenerator = MyBackgroundGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadViews()
        attachTouchListener()
        describeMatrix()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun attachTouchListener() {
        val myTouchListener = MyTouchListener(this)
        binding.mainView.setOnTouchListener(myTouchListener)
        myTouchListener.setDetectSideListener {
            when(it) {
                SideEnum.DOWN -> {
                    controller.moveDown()
                    describeMatrix()
                }
                // @Ikromjonov_Marufjon
                SideEnum.UP -> {
                    controller.moveUp()
                    describeMatrix()
                }
                SideEnum.LEFT -> {
                    controller.moveLeft()
                    describeMatrix()
                }

                SideEnum.RIGHT -> {
                    controller.moveRight()
                    describeMatrix()
                }
            }
        }
    }

    private fun loadViews() {
        for (i in 0 until binding.mainView.childCount) {
            val linear = binding.mainView.getChildAt(i) as LinearLayoutCompat
            for (j in  0 until linear.childCount) {
                views.add(linear.getChildAt(j) as AppCompatTextView)
            }
        }
    }

    private fun describeMatrix() {
        if (!controller.hasWay())
            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()

        val matrix = controller.getMatrix()
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                views[i*4 + j].apply {
                    text = if (matrix[i][j] == 0) "" else "${matrix[i][j]}"
                    setBackgroundResource(backgroundGenerator.backgroundByAmount(matrix[i][j]))
                }
            }
        }
    }
}

