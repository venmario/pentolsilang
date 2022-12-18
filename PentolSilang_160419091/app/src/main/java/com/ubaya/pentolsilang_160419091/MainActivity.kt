package com.ubaya.pentolsilang_160419091

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val PLAYER_1_NAME = "PLAYER_1_NAME"
        val PLAYER_1_COLOR = "PLAYER_1_COLOR"
        val PLAYER_2_NAME = "PLAYER_2_NAME"
        val PLAYER_2_COLOR = "PLAYER_2_COLOR"
        val PLAYER_WINNER = "PLAYER_WINNER"
    }
    var p1Name: String = ""
    var p2Name: String = ""
    var p1Color: String = ""
    var p2Color: String = ""
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quit game")
        builder.setMessage("Are you sure want to quit this game?")
        builder.setPositiveButton("Quit", DialogInterface.OnClickListener { _, _ ->
            finish()
        })
        builder.setNegativeButton("KEEP PLAYING",null)
        builder.create().show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        p1Color = intent.getStringExtra(PlayersActivity.PLAYER_1_COLOR).toString()
        p1Name = intent.getStringExtra(PlayersActivity.PLAYER_1_NAME).toString()
        ChangeColor(p1Color,"1")
        p2Color = intent.getStringExtra(PlayersActivity.PLAYER_2_COLOR).toString()
        p2Name = intent.getStringExtra(PlayersActivity.PLAYER_2_NAME).toString()
        ChangeColor(p2Color,"2")

        textP1Name.setText("$p1Name ( O )")
        textP2Name.setText("$p2Name ( X )")
        textP1Turn.setText("Your Turn!")
        textP2Turn.setText("$p1Name's Turn!")
    }

    fun ChangeColor(color:String, player:String){
        if(player == "1"){
            if(color == "Red"){
                cardViewP1.setBackgroundColor(Color.parseColor("#db362a"))
            }
            else if(color == "Blue"){
                cardViewP1.setBackgroundColor(android.graphics.Color.parseColor("#389fff"))
            }
            else if(color == "Green"){
                cardViewP1.setBackgroundColor(android.graphics.Color.parseColor("#36ff7c"))
            }
            else{
                cardViewP1.setBackgroundColor(android.graphics.Color.parseColor("#f0ff4d"))
            }
        }
        else if(player == "2"){
            if(color == "Red"){
                cardViewP2.setBackgroundColor(Color.parseColor("#db362a"))

            }
            else if(color == "Blue"){
                cardViewP2.setBackgroundColor(android.graphics.Color.parseColor("#389fff"))
            }
            else if(color == "Green"){
                cardViewP2.setBackgroundColor(android.graphics.Color.parseColor("#36ff7c"))
            }
            else{
                cardViewP2.setBackgroundColor(android.graphics.Color.parseColor("#f0ff4d"))
            }
        }
    }

    fun buttonClick(view: View) {
        val selectedButton = view as Button
        var buttonId = 0
        when(selectedButton.id){
            R.id.button1 -> buttonId = 1
            R.id.button2 -> buttonId = 2
            R.id.button3 -> buttonId = 3
            R.id.button4 -> buttonId = 4
            R.id.button5 -> buttonId = 5
            R.id.button6 -> buttonId = 6
            R.id.button7 -> buttonId = 7
            R.id.button8 -> buttonId = 8
            R.id.button9 -> buttonId = 9
        }
        PlayGame(buttonId,selectedButton)
    }

    var playersTurn = 1
    var totalTurns = 0

    var p1Choice= ArrayList<Int>()
    var p2Choice= ArrayList<Int>()

    fun PlayGame(id : Int , selectedButton : Button){
        if(playersTurn == 1){
            p1Choice.add(id)
            selectedButton.setBackgroundResource(R.drawable.pentol)
            textP2Turn.setText("Your Turn!")
            textP1Turn.setText("$p2Name's Turn!")
            playersTurn = 2
            totalTurns++
        }
        else{
            p2Choice.add(id)
            selectedButton.setBackgroundResource(R.drawable.silang)
            textP1Turn.setText("Your Turn!")
            textP2Turn.setText("$p1Name's Turn!")
            playersTurn = 1
            totalTurns++
        }

        selectedButton.isEnabled = false
        CheckWinner()
    }

private fun CheckWinner() {
    var winner = -1
    //baris 1
    if(p1Choice.contains(1) && p1Choice.contains(2) && p1Choice.contains(3)){
        winner = 1
    }
    else if (p2Choice.contains(1) && p2Choice.contains(2) && p2Choice.contains(3)){
        winner = 2
    }
    //baris 2
    else if (p1Choice.contains(4) && p1Choice.contains(5) && p1Choice.contains(6)){
        winner = 1
    }
    else if (p2Choice.contains(4) && p2Choice.contains(5) && p2Choice.contains(6)){
        winner = 2
    }
    // baris 3
    else if (p1Choice.contains(7) && p1Choice.contains(8) && p1Choice.contains(9)){
        winner = 1
    }
    else if (p2Choice.contains(7) && p2Choice.contains(8) && p2Choice.contains(9)){
        winner = 2
    }
    //kolom 1
    else if (p1Choice.contains(1) && p1Choice.contains(4) && p1Choice.contains(7)){
        winner = 1
    }else if (p2Choice.contains(1) && p2Choice.contains(4) && p2Choice.contains(7)){
        winner = 2
    }
    //kolom 2
    else if (p1Choice.contains(2) && p1Choice.contains(5) && p1Choice.contains(8)){
        winner = 1
    }
    else if (p2Choice.contains(2) && p2Choice.contains(5) && p2Choice.contains(8)){
        winner = 2
    }
    //kolom 3
    else if (p1Choice.contains(3) && p1Choice.contains(6) && p1Choice.contains(9)){
        winner = 1
    }
    else if (p2Choice.contains(3) && p2Choice.contains(6) && p2Choice.contains(9)){
        winner = 2
    }
    //diagonal 1
    else if (p1Choice.contains(1) && p1Choice.contains(5) && p1Choice.contains(9)){
        winner = 1
    }
    else if (p2Choice.contains(1) && p2Choice.contains(5) && p2Choice.contains(9)){
        winner = 2
    }
    //diagonal 2
    else if (p1Choice.contains(3) && p1Choice.contains(5) && p1Choice.contains(7)){
        winner = 1
    }
    else if (p2Choice.contains(3) && p2Choice.contains(5) && p2Choice.contains(7)){
        winner = 2
    }
    else{
        if(totalTurns == 9){
            winner = 0
        }
    }


        val builder = AlertDialog.Builder(this)
        when{
            winner == 0 -> {
                builder.setMessage("Game Draw!")
                builder.setTitle("Game Over")
                builder.setPositiveButton("Oh no...", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra(MainActivity.PLAYER_1_NAME, p1Name)
                    intent.putExtra(MainActivity.PLAYER_1_COLOR,p1Color)
                    intent.putExtra(MainActivity.PLAYER_2_NAME, p2Name)
                    intent.putExtra(MainActivity.PLAYER_2_COLOR,p2Color)
                    intent.putExtra(MainActivity.PLAYER_WINNER,winner)
                    finish()
                    startActivity(intent)
                })
                builder.create().show()
            }
            winner == 1 -> {
                builder.setMessage("$p1Name wins!")
                builder.setTitle("Game Over")
                builder.setPositiveButton("HOORAY!", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra(MainActivity.PLAYER_1_NAME, p1Name)
                    intent.putExtra(MainActivity.PLAYER_1_COLOR,p1Color)
                    intent.putExtra(MainActivity.PLAYER_2_NAME, p2Name)
                    intent.putExtra(MainActivity.PLAYER_2_COLOR,p2Color)
                    intent.putExtra(MainActivity.PLAYER_WINNER,winner)
                    finish()
                    startActivity(intent)
                })
                builder.create().show()
            }
            winner == 2 -> {
                builder.setMessage("$p2Name wins!")
                builder.setTitle("Game Over")
                builder.setPositiveButton("HOORAY!", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra(MainActivity.PLAYER_1_NAME, p1Name)
                    intent.putExtra(MainActivity.PLAYER_1_COLOR,p1Color)
                    intent.putExtra(MainActivity.PLAYER_2_NAME, p2Name)
                    intent.putExtra(MainActivity.PLAYER_2_COLOR,p2Color)
                    intent.putExtra(MainActivity.PLAYER_WINNER,winner)
                    finish()
                    startActivity(intent)
                })
                builder.create().show()
            }
        }
    }
}