package com.mostafa.dab

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mostafa.dab.Databases.DatabaseHelper
import com.mostafa.dab.Fragements.ActiveProjectHomeFragment
import com.mostafa.dab.Fragements.AddDriverFragment
import com.mostafa.dab.Fragements.AddOrderFragment
import com.mostafa.dab.Fragements.AddProjectFragment
import com.mostafa.dab.Fragements.DriverFragment

class MainActivity : AppCompatActivity() {

   lateinit var ban: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_farmelayout, AddProjectFragment()).commit()
        }

        ban = findViewById(R.id.bottomNavigationView)
        ban.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.active_home_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_farmelayout, ActiveProjectHomeFragment()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.add_menu->{supportFragmentManager.beginTransaction().replace(R.id.main_farmelayout,
                    AddProjectFragment()
                ).commit()
                return@setOnItemSelectedListener true}
                R.id.driver_menu->{supportFragmentManager.beginTransaction().replace(R.id.main_farmelayout,
                    DriverFragment()
                ).commit()
                return@setOnItemSelectedListener true}

               else-> return@setOnItemSelectedListener  false
            }
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu_items,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var dbhandler= DatabaseHelper(this)
        var dialog = AlertDialog.Builder(this).setTitle("info")
            .setMessage("Click ok to delete ${dbhandler.getAllProjects().size} Projects")
            .setPositiveButton("OK!", { dialog, i ->
                dbhandler.deleteAllProjects()
                dbhandler.close()
                var i = Intent(this,MainActivity::class.java)
                startActivity(i)

            }).setNegativeButton("No",{dialog,i->
                dialog.dismiss()
            })
        when(item.itemId){
            R.id.item_optionMenu_del->dialog.show()
            R.id.item_optionMenu_addDriver-> supportFragmentManager.beginTransaction().replace(R.id.main_farmelayout,
                AddDriverFragment()
            ).commit()
        }



        return super.onOptionsItemSelected(item)
    }


}