package com.example.advanceandroid.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.example.advanceandroid.Fragment.DashboardFragment
import com.example.advanceandroid.Fragment.FavouriteFragment
import com.example.advanceandroid.R

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : android.support.v4.widget.DrawerLayout
    lateinit var coordinatorLayout: android.support.design.widget.CoordinatorLayout
    lateinit var toolbar: android.support.v7.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: android.support.design.widget.NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()

        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        navigationView.setNavigationItemSelectedListener {

            if (previousMenuItem != null){
               previousMenuItem?.isCheckable = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it


            when(it.itemId){
                R.id.dashboard -> {
                    openDashboard()
                    drawerLayout.closeDrawers()

                }
                R.id.profile -> {
                    Toast.makeText(this@MainActivity, "Clicked on profile", Toast.LENGTH_SHORT).show()
                }
                R.id.favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FavouriteFragment())

                        .commit()
                    supportActionBar?.title = "Favourite"
                    drawerLayout.closeDrawers()

                }
                R.id.about -> {
                    Toast.makeText(this@MainActivity, "Clicked on about", Toast.LENGTH_SHORT).show()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }



}