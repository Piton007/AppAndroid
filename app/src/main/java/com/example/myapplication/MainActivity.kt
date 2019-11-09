package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.driverfeature.BottomDialogParkings
import com.example.driverfeature.GoogleMapFragment
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    private fun navigateTo(item: MenuItem): Boolean {
        item.isChecked = true

        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, getFragmentFor(item))
            .commit() > 0
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when(item.itemId){
            R.id.navigation_home ->GoogleMapFragment()
            R.id.navigation_dashboard->DashboardFragment()
            else->{
                NotificationsFragment()
            }
        }
    }
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomSheetDialogFragment: BottomDialogParkings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener{
            navigateTo(it)
        }
        navigateTo(navView.menu.findItem(R.id.navigation_home))
        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )*/
       // drawerLayout=findViewById(R.id.drawerLayoutCore)

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
        //NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

    }

   /* override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }*/



}
