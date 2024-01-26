package com.mostafa.dab.Databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mostafa.dab.models.Driver
import com.mostafa.dab.models.Project
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "DBproject"
        private val DB_VERSION = 1
        private val TABLE_NAME = "project"
        private val MEDI_CLN_ID = "id"
        private val MEDI_CLN_NAME = "name"
        private val MEDI_CLN_DESTNAION = "desnation"
        private val MEDI_CLN_PHONENUMBER = "phonenumber"
        private val MEDI_CLN_DATE = "date"

        //-----------------------------------------------------/
        private val TABLE_NAME_DRIVER = "driver"
        private val DRIVER_CLN_ID = "id"
        private val DRIVER_CLN_NAME = "name"
        private val DRIVER_CLN_VehicleNumber = "vehicleNumber"
        private val DRIVER_CLN_DriverPhoneNumber = "driverPhoneNumber"
        private val DRIVER_CLN_VehicleType = "vehicleType"

        var currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME($MEDI_CLN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$MEDI_CLN_NAME TEXT , $MEDI_CLN_DESTNAION TEXT , $MEDI_CLN_PHONENUMBER TEXT , $MEDI_CLN_DATE TEXT)"
        p0?.execSQL(CREATE_TABLE)

        val CREATE_TABLE_DRIVER =
            "CREATE TABLE $TABLE_NAME_DRIVER($DRIVER_CLN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$DRIVER_CLN_NAME TEXT,$DRIVER_CLN_VehicleNumber TEXT ,$DRIVER_CLN_VehicleType TEXT,$DRIVER_CLN_DriverPhoneNumber INTEGER) "
        p0?.execSQL(CREATE_TABLE_DRIVER)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        val DROP_TABLE_DRIVER = "DROP TABLE IF EXISTS $TABLE_NAME_DRIVER"
        p0?.execSQL(DROP_TABLE)
        p0?.execSQL(DROP_TABLE_DRIVER)
        onCreate(p0)

    }


    fun insertProject(project: Project): Boolean {
        var db: SQLiteDatabase = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(MEDI_CLN_NAME, project.name)
        contentValues.put(MEDI_CLN_DATE, currentDate)
        contentValues.put(MEDI_CLN_DESTNAION, project.destination)
        contentValues.put(MEDI_CLN_PHONENUMBER, project.destinationPhoneNumber)
        var sucess = db.insert(TABLE_NAME, null, contentValues)
        return Integer.parseInt(sucess.toString()) != -1
    }




    fun editProject(project: Project): Boolean {

        var db: SQLiteDatabase = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(MEDI_CLN_NAME, project.name)
        contentValues.put(MEDI_CLN_DATE, currentDate)
        contentValues.put(MEDI_CLN_DESTNAION, project.destination)
        contentValues.put(MEDI_CLN_PHONENUMBER, project.destinationPhoneNumber)
        var args = arrayOf(project.name)
        var sucess = db.update(TABLE_NAME, contentValues, "$MEDI_CLN_ID=?", args)
        return sucess > 0

    }




    fun getProjectCount(): Long {
        var db: SQLiteDatabase = readableDatabase
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME)
    }


    fun deleteProject(project: Project): Boolean {
        var db: SQLiteDatabase = writableDatabase
        var args = arrayOf("${project.name}")
        var sucess = db.delete(TABLE_NAME, "$MEDI_CLN_ID=?", args)
        return sucess > 0


    }



    fun deleteAllProjects() {
        var db: SQLiteDatabase = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }


    fun getAllProjects(): ArrayList<Project> {
        var projects: ArrayList<Project> = ArrayList()
        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cusor != null && cusor.moveToFirst()) {
            do {
                var id = cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_ID))
                var name = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_NAME))
                var destination = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DESTNAION))
                var destinationPhoneNumber = cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_PHONENUMBER))
//                var date = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DATE))


                var project = Project(name, destination, destinationPhoneNumber, id)

                projects.add(project)


            } while (cusor.moveToNext())

            cusor.close()
        }


        return projects
    }


    fun getProject(projectID: Int): Project? {

        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $MEDI_CLN_ID =?",
            arrayOf(projectID.toString())
        )

        if (cusor != null && cusor.moveToFirst()) {

            var id = cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_ID))
            var name = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_NAME))
            var destination = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DESTNAION))
            var destinationPhoneNumber =
                cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_PHONENUMBER))
//                var date = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DATE))


            var project = Project(name, destination, destinationPhoneNumber, id)
            return project
            cusor.close()
        }


        return null
    }




    fun getProjectID(driverName:String):Int{
        var db: SQLiteDatabase = readableDatabase
        var cusor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $MEDI_CLN_NAME =?",
            arrayOf(driverName)
        )

        if (cusor != null && cusor.moveToFirst()) {

            var id = cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_ID))
            var name = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_NAME))
            var destination = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DESTNAION))
            var destinationPhoneNumber =
                cusor.getInt(cusor.getColumnIndexOrThrow(MEDI_CLN_PHONENUMBER))
//                var date = cusor.getString(cusor.getColumnIndexOrThrow(MEDI_CLN_DATE))


            var project = Project(name, destination, destinationPhoneNumber, id)
            return id
            cusor.close()
        }


        return null!!
     }


    fun deleteProject(projectId: Int){
        val db= writableDatabase
        db.delete(TABLE_NAME,"$MEDI_CLN_ID =? ", arrayOf(projectId.toString()))

    }



}