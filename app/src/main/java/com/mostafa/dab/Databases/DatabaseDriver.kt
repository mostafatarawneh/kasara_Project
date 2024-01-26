package com.mostafa.dab.Databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mostafa.dab.models.Driver

class DatabaseDriver(context:Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    companion object {
        val DB_NAME = "driver_database"
        val DB_VERSION=1
        val TABLE_NAME="driver"
        val ID = "id"
        val NAME="name"
        val VEHICLE_NUMBER="vehicleNumber"
        val DRIVER_PHONE = "driverPhone"
        val VEHICLE_TYPE="vehicleType"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME TEXT,$VEHICLE_NUMBER TEXT,$DRIVER_PHONE INTEGER,$VEHICLE_TYPE TEXT) ")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun insertDriver(driver: Driver): Boolean {
        var db: SQLiteDatabase = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(NAME, driver.name)
        contentValues.put(VEHICLE_NUMBER, driver.vehicleNumber)
        contentValues.put(DRIVER_PHONE, driver.driverPhoneNumber)
        contentValues.put(VEHICLE_TYPE, driver.vehicleType)


        var sucess = db.insert(TABLE_NAME, null, contentValues)
        return Integer.parseInt(sucess.toString()) != -1
    }


    fun getAllDrivers(): ArrayList<Driver> {
        var drivers: ArrayList<Driver> = ArrayList()
        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cusor != null && cusor.moveToFirst()) {
            do {
                var id = cusor.getInt(cusor.getColumnIndexOrThrow(ID))
                var name = cusor.getString(cusor.getColumnIndexOrThrow(NAME))
                var vehicleType =
                    cusor.getString(cusor.getColumnIndexOrThrow(VEHICLE_TYPE))
                var destinationPhoneNumber =
                    cusor.getInt(cusor.getColumnIndexOrThrow(DRIVER_PHONE))
                var vehicleNumber =
                    cusor.getString(cusor.getColumnIndexOrThrow(VEHICLE_NUMBER))


                var driver = Driver(name, vehicleNumber, destinationPhoneNumber, vehicleType, id)

                drivers.add(driver)


            } while (cusor.moveToNext())

            cusor.close()
        }


        return drivers
    }
}