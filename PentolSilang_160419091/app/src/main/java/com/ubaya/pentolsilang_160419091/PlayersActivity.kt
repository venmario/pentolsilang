package com.ubaya.pentolsilang_160419091

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_players.*

class PlayersActivity : AppCompatActivity() {
    companion object {
        val PLAYER_1_NAME = "PLAYER_1_NAME"
        val PLAYER_1_COLOR = "PLAYER_1_COLOR"
        val PLAYER_2_NAME = "PLAYER_2_NAME"
        val PLAYER_2_COLOR = "PLAYER_2_COLOR"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        val colors = arrayOf("Red","Green","Blue","Yellow")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, colors)

        spinnerColorP1.adapter = arrayAdapter
        spinnerColorP2.adapter = arrayAdapter

        spinnerColorP1.onItemSelectedListener = object :

        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var hexa = (
                        if(colors[p2] == "Red") "#db362a"
                        else if(colors[p2] == "Green") "#36ff7c"
                        else if (colors[p2] == "Blue") "#389fff"
                        else "#f0ff4d"
                        )
                cardViewPlayer1.setCardBackgroundColor(Color.parseColor("$hexa"))
                }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        spinnerColorP2.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var hexa = (
                        if(colors[p2] == "Red") "#db362a"
                        else if(colors[p2] == "Green") "#36ff7c"
                        else if (colors[p2] == "Blue") "#389fff"
                        else "#f0ff4d"
                        )
                cardViewPlayer2.setCardBackgroundColor(Color.parseColor("$hexa"))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        buttonPlay.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(PLAYER_1_NAME, txtPlayer1Name.text.toString())
            intent.putExtra(PLAYER_1_COLOR,spinnerColorP1.selectedItem.toString())
            intent.putExtra(PLAYER_2_NAME, txtPlayer2Name.text.toString())
            intent.putExtra(PLAYER_2_COLOR,spinnerColorP2.selectedItem.toString())
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}