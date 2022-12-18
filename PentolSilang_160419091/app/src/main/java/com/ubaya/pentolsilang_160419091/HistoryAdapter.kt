package com.ubaya.pentolsilang_160419091

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_card.view.*

class HistoryAdapter(val context:Context): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.history_card,parent,false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        var history = GlobalData.history[itemCount - position -1]
        with(holder.view){
            var id = history.id.toString()
            textGameSession.setText("Game Session #$id")
            textDate.setText(history.dateTime)
            if (history.result == "p1Win"){
                textPlayer1Card.setText("Player 1 ( O ) : ${history.p1Name} WIN")
                textPlayer2Card.setText("Player 2 ( X ) : ${history.p2Name}")
            }
            else if(history.result == "p2Win"){
                textPlayer1Card.setText("Player 1 ( O ) : ${history.p1Name}")
                textPlayer2Card.setText("Player 2 ( X ) : ${history.p2Name} WIN")
            }
            else {
                textPlayer1Card.setText("Player 1 ( O ) : ${history.p1Name}")
                textPlayer2Card.setText("Player 2 ( X ) : ${history.p2Name}")
            }
            textPlayer1Card.setBackgroundColor(Color.parseColor(history.p1Color))
            textPlayer2Card.setBackgroundColor(Color.parseColor(history.p2Color))
        }

    }
    override fun getItemCount(): Int = GlobalData.history.size
}