package com.ubaya.pentolsilang_160419091

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_players.*
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    var p1Name: String = ""
    var p2Name: String = ""
    val HISTORY = "HISTORY"
    var p1Color: String = ""
    var p2Color: String = ""
    var p1ColorHexa: String =""
    var p2ColorHexa: String =""

    override fun onBackPressed() {
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        p1Color = intent.getStringExtra(MainActivity.PLAYER_1_COLOR).toString()
        p1Name = intent.getStringExtra(MainActivity.PLAYER_1_NAME).toString()
        ChangeColor(p1Color,"1")
        p2Color = intent.getStringExtra(MainActivity.PLAYER_2_COLOR).toString()
        p2Name = intent.getStringExtra(MainActivity.PLAYER_2_NAME).toString()
        ChangeColor(p2Color,"2")

        var winner = intent.getIntExtra(MainActivity.PLAYER_WINNER,0)
        var result:String = ""
        if(winner == 0){
            textP1Result.setText("Draw!")
            textP2Result.setText("Draw!")
            result = "draw"
        }
        else if(winner == 1){
            textP1Result.setText("You Win!")
            textP2Result.setText("You Lose!")
            result = "p1Win"
        }
        else if(winner == 2){
            textP1Result.setText("You Lose!")
            textP2Result.setText("You Win!")
            result = "p2Win"
        }

        textP1NameResult.setText("$p1Name ( O )")
        textP2NameResult.setText("$p2Name ( X )")

        //retrive saved History
        var sharedFile = packageName
        var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var histories = shared.getString(HISTORY, "")

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm")
        val dateNow = formatter.format(date)

        var id = 0
        if (histories.isNullOrBlank()){
            id = 1
        }
        else{
            GlobalData.history.clear()
            var splitHistories = histories.split("|")
            splitHistories.forEach{hs->
                var history = hs.split(";")
                id = history[0].toInt()
                GlobalData.history.add(History(id,history[1],history[2],history[3],history[4],history[5],history[6]))
            }
            id++
        }

        var myHistory = History(id,p1Name,p2Name,p1ColorHexa,p2ColorHexa,result,dateNow)

        GlobalData.history.add(myHistory)
        histories = GlobalData.history.joinToString(separator = "|")
        //save to share preference
        var editor:SharedPreferences.Editor = shared.edit()
        editor.putString(HISTORY,histories)
        editor.apply()

        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(PlayersActivity.PLAYER_1_NAME,p1Name)
            intent.putExtra(PlayersActivity.PLAYER_1_COLOR,p1Color)
            intent.putExtra(PlayersActivity.PLAYER_2_NAME,p2Name)
            intent.putExtra(PlayersActivity.PLAYER_2_COLOR,p2Color)
            finish()
            startActivity(intent)
        }

        buttonHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    fun ChangeColor(color:String, player:String){
        if(player == "1"){
            if(color == "Red"){
                cardViewP1Result.setBackgroundColor(Color.parseColor("#db362a"))
                p1ColorHexa = "#db362a"
            }
            else if(color == "Blue"){
                cardViewP1Result.setBackgroundColor(Color.parseColor("#389fff"))
                p1ColorHexa = "#389fff"
            }
            else if(color == "Green"){
                cardViewP1Result.setBackgroundColor(Color.parseColor("#36ff7c"))
                p1ColorHexa = "#36ff7c"
            }
            else{
                cardViewP1Result.setBackgroundColor(Color.parseColor("#f0ff4d"))
                p1ColorHexa = "#f0ff4d"
            }
        }
        else if(player == "2"){
            if(color == "Red"){
                cardViewP2Result.setBackgroundColor(Color.parseColor("#db362a"))
                p2ColorHexa = "#db362a"
            }
            else if(color == "Blue"){
                cardViewP2Result.setBackgroundColor(Color.parseColor("#389fff"))
                p2ColorHexa = "#389fff"
            }
            else if(color == "Green"){
                cardViewP2Result.setBackgroundColor(Color.parseColor("#36ff7c"))
                p2ColorHexa = "#36ff7c"
            }
            else{
                cardViewP2Result.setBackgroundColor(Color.parseColor("#f0ff4d"))
                p2ColorHexa = "#f0ff4d"
            }
        }
    }
}