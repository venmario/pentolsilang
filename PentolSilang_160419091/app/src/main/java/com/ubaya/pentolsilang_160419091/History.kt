package com.ubaya.pentolsilang_160419091

data class History(val id:Int, val p1Name:String, val p2Name: String,val p1Color:String, val p2Color:String, val result:String, val dateTime:String)
{
    override fun toString(): String {
        return "$id;$p1Name;$p2Name;$p1Color;$p2Color;$result;$dateTime"
    }
}