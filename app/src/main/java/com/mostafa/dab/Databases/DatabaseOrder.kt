package com.mostafa.dab.Databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mostafa.dab.models.Order

class DatabaseOrder(context: Context) : SQLiteOpenHelper(context, DBname, null, DBversion) {

    companion object {
        private val DBname = "DBorder"
        private val DBversion = 1
        private val TABLE_NAME = "orders"
        private val ID = "id"
        private val DRIVER = "driver"
        private val HEATDEGREE = "tempreture"
        private val WEIGHT = "weight"
        private val PAID_MONEY = "paidmoney"
        private val NOTE = "note"
        private val MIXTURE_TYPE = "mixture"
        private val PROJECT_NAME="projectName"


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$DRIVER TEXT,$HEATDEGREE TEXT ,$WEIGHT TEXT,$PAID_MONEY TEXT,$NOTE TEXT,$MIXTURE_TYPE TEXT,$PROJECT_NAME TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE  IF EXISTS  $TABLE_NAME")
        onCreate(p0)

    }

    fun InsertInfoOrder(order: Order,projectName:String): Boolean {
        val db = writableDatabase
        var cv = ContentValues()
        cv.put(DRIVER, order.driver)
        cv.put(HEATDEGREE, order.heatDgree)
        cv.put(WEIGHT, order.weight)
        cv.put(PAID_MONEY, order.paidMoney)
        cv.put(NOTE, order.note)
        cv.put(MIXTURE_TYPE, order.mixtureType)
        cv.put(PROJECT_NAME,projectName)
        val Success = db.insert(TABLE_NAME, null, cv)
        return Integer.parseInt(Success.toString()) != -1


    }


    fun getAllInfoOrder(): ArrayList<Order> {
        var orders: ArrayList<Order> = ArrayList()
        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cusor != null && cusor.moveToFirst()) {
            do {
                var id = cusor.getInt(cusor.getColumnIndexOrThrow(ID))
                var driverw = cusor.getString(cusor.getColumnIndexOrThrow(DRIVER))
                var heatdgree = cusor.getString(cusor.getColumnIndexOrThrow(HEATDEGREE))
                var wegiht = cusor.getString(cusor.getColumnIndexOrThrow(WEIGHT))
                var paidmoney = cusor.getString(cusor.getColumnIndexOrThrow(PAID_MONEY))
                var note = cusor.getString(cusor.getColumnIndexOrThrow(PAID_MONEY))
                var mixtype = cusor.getString(cusor.getColumnIndexOrThrow(MIXTURE_TYPE))
                var projectName = cusor.getString(cusor.getColumnIndexOrThrow(PROJECT_NAME))


                var order = Order(driverw, heatdgree, wegiht, paidmoney, note, mixtype,projectName)

                orders.add(order)


            } while (cusor.moveToNext())

            cusor.close()
        }


        return orders
    }


    fun getAllInfoOrderByProjectName(projectName:String): ArrayList<Order> {
        var orders: ArrayList<Order> = ArrayList()
        var db: SQLiteDatabase = readableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $PROJECT_NAME=?", arrayOf(projectName))

        if (cursor != null && cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                var driverw = cursor.getString(cursor.getColumnIndexOrThrow(DRIVER))
                var heatdgree = cursor.getString(cursor.getColumnIndexOrThrow(HEATDEGREE))
                var wegiht = cursor.getString(cursor.getColumnIndexOrThrow(WEIGHT))
                var paidmoney = cursor.getString(cursor.getColumnIndexOrThrow(PAID_MONEY))
                var note = cursor.getString(cursor.getColumnIndexOrThrow(PAID_MONEY))
                var mixtype = cursor.getString(cursor.getColumnIndexOrThrow(MIXTURE_TYPE))
                var projectName = cursor.getString(cursor.getColumnIndexOrThrow(PROJECT_NAME))


                var order = Order(driverw, heatdgree, wegiht, paidmoney, note, mixtype,projectName)

                orders.add(order)


            } while (cursor.moveToNext())

            cursor.close()
        }


        return orders
    }

    fun getOneInfo(InfoID: Int): Order? {

        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $ID =?",
            arrayOf(InfoID.toString())
        )

        if (cusor != null && cusor.moveToFirst()) {

            var id = cusor.getInt(cusor.getColumnIndexOrThrow(ID))
            var driverw = cusor.getString(cusor.getColumnIndexOrThrow(DRIVER))
            var heatdgree = cusor.getString(cusor.getColumnIndexOrThrow(HEATDEGREE))
            var wegiht = cusor.getString(cusor.getColumnIndexOrThrow(WEIGHT))
            var paidmoney = cusor.getString(cusor.getColumnIndexOrThrow(PAID_MONEY))
            var note = cusor.getString(cusor.getColumnIndexOrThrow(PAID_MONEY))
            var mixtype = cusor.getString(cusor.getColumnIndexOrThrow(MIXTURE_TYPE))
            var projectName = cusor.getString(cusor.getColumnIndexOrThrow(PROJECT_NAME))


            var order = Order(driverw,heatdgree,wegiht,paidmoney,note,mixtype,projectName)
            return order
            cusor.close()
        }


        return null
    }


}