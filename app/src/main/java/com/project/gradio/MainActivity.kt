package com.project.gradio

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColor
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.color_picker.*
class MainActivity : AppCompatActivity() {
    private var color1 = arrayOf(51,255,221)
    private var color2= arrayOf(162,51,255)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gradient.setOnClickListener(){
            color1 = showColorPicker(gradient)

        }
        gradient2.setOnClickListener(){
            color2 = showColorPicker(gradient2)
        }
        generate.setOnClickListener(){
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        Color.rgb(color1[0], color1[1], color1[2]),
                        Color.rgb(color2[0], color2[1], color2[2])
                    )
                );
                gradientDrawable.cornerRadius = 20f;

                //Set Gradient
                main_layout.setBackground(gradientDrawable);
        }
        up.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(Color.rgb(color1[0],color1[1],color1[2]),
                    Color.rgb(color2[0],color2[1],color2[2]))
            );
            gradientDrawable.cornerRadius = 20f;
            main_layout.setBackground(gradientDrawable);
        }
        down.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(Color.rgb(color1[0],color1[1],color1[2]),
                    Color.rgb(color2[0],color2[1],color2[2]))
            );
            gradientDrawable.cornerRadius = 20f;
            main_layout.setBackground(gradientDrawable);
        }
        left.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(Color.rgb(color1[0],color1[1],color1[2]),
                    Color.rgb(color2[0],color2[1],color2[2]))
            );
            gradientDrawable.cornerRadius = 20f;
            main_layout.setBackground(gradientDrawable);
        }
        right.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT,
                intArrayOf(Color.rgb(color1[0],color1[1],color1[2]),
                    Color.rgb(color2[0],color2[1],color2[2]))
            );
            gradientDrawable.cornerRadius = 20f;
            main_layout.setBackground(gradientDrawable);
        }
    }
    fun showColorPicker(gradient: Button):Array<Int>{
        var color = arrayOf(0,0,0)
        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.color_picker)
        color = colorSelect(dialog)
        dialog.findViewById<TextView>(R.id.positive_button).setOnClickListener {
           // colorSelect(dialog)
            var rhex = Integer.toHexString(color[0])
            var ghex = Integer.toHexString(color[1])
            var bhex = Integer.toHexString(color[2])
            if(rhex.length == 1)rhex = "0" + rhex
            if(ghex.length == 1)ghex = "0" + ghex
            if(bhex.length == 1)bhex = "0" + bhex
            gradient.setText("#$rhex$ghex$bhex")
            // change custom button Color
            val gradientDrawable = (gradient.getBackground() as GradientDrawable).mutate()
            (gradientDrawable as GradientDrawable).setColor(Color.rgb(color[0],color[1],color[2]))
             gradient.setTextColor(Color.rgb(255-color[0],255-color[1],color[2]))
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.negative_button).setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
        return color
    }
    fun colorSelect(dialog: MaterialDialog):Array<Int> {
        var color = arrayOf(0,0,0)
        var boxlay = dialog.findViewById<ImageView>(R.id.colorshow)
        var rseek = dialog.findViewById<SeekBar>(R.id.redPicker)
        var gseek = dialog.findViewById<SeekBar>(R.id.greenPicker)
        var bseek = dialog.findViewById<SeekBar>(R.id.bluePicker)
        var redc = 0
        var greenc = 0
        var bluec = 0
        rseek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //text_view.text = "Progress : $i"
                redc = i
                color[0] = redc;
               var color = Color.rgb(redc,greenc,bluec)
                boxlay.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
        gseek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //text_view.text = "Progress : $i"
                greenc=i
                color[1] = greenc
                var color = Color.rgb(redc,greenc,bluec)
                boxlay.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
        bseek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //text_view.text = "Progress : $i
                bluec = i
                color[2] = bluec
                var color = Color.rgb(redc,greenc,bluec)
                boxlay.setBackgroundColor(color)
//                Toast.makeText(this@MainActivity,
//                    "Progress is: " + rseek.progress + "%",
//                    Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
        return color
    }
}