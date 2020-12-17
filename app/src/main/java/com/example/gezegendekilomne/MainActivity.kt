package com.example.gezegendekilomne

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , View.OnClickListener {

    val KILO_TO_POUND = 2.2045
    val POUND_TO_KILO = 0.45359237
    val MARS = 0.38
    val VENUS = 0.91
    val JUPITER = 2.34




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            tvSonuc.text = savedInstanceState.getString("result")
        }

        Glide.with(this).load(R.drawable.title).into(appTitle)

        cbMars.setOnClickListener (this)
        cbVenus.setOnClickListener(this)
        cbJupiter.setOnClickListener(this)
        /*btnHesapla.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.e("CALCULATE", "Calculate button triggered")
            }
        })

        btnHesapla.setOnClickListener {
            var pound = kiloToPound(kiloEntry.toString().toDouble())
            var mars = poundToKilo(pound * MARS)

            tvSonuc.text = mars.format(2).toString()
        }*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", tvSonuc.text.toString())
    }

    fun kiloToPound(kilo: Double) : Double {
        return kilo * KILO_TO_POUND
    }

    fun poundToKilo(pound: Double) : Double{
        return pound * POUND_TO_KILO
    }

    fun Double.format(afterDot : Int ) = java.lang.String.format("%.${afterDot}f", this)

    override fun onClick(v: View?) {
        v as CheckBox
        var isChecked : Boolean = v.isChecked
        var result : Double = 0.0

        if(!TextUtils.isEmpty(etKilo.text.toString())){
            var kiloEntry = etKilo.text.toString().toDouble()
            when(v.id){

                R.id.cbMars -> if(isChecked){
                    cbJupiter.isChecked = false
                    cbVenus.isChecked = false
                    result = calculator(kiloEntry,v)
                }
                R.id.cbVenus -> if(isChecked){
                    cbJupiter.isChecked = false
                    cbMars.isChecked = false
                    result =  calculator(kiloEntry,v)
                }
                R.id.cbJupiter -> if(isChecked){
                    cbMars.isChecked = false
                    cbVenus.isChecked = false
                    result = calculator(kiloEntry,v)
                }
            }
            tvSonuc.text = result.format(2).toString()
        }else{
            tvSonuc.text = "Lütfen Kilonuzu Giriniz"
            cbJupiter.isChecked = false
            cbVenus.isChecked = false
            cbMars.isChecked = false
        }

    }

    fun calculator(kilo : Double, cb : CheckBox) : Double{
        var planet : Double = when(cb.id){
            R.id.cbMars -> MARS
            R.id.cbVenus -> VENUS
            R.id.cbJupiter -> JUPITER

            else -> 0.0
        }

        return poundToKilo(kiloToPound(kilo) * planet)
    }

}