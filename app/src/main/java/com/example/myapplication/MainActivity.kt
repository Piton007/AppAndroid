package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Switch
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.driverfeature.BottomDialogParkings
import com.example.driverfeature.GoogleMapFragment
import com.example.myapplication.ui.Fragments.PerfilFragment
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var name: String?=null
    private  var email: String?=null
    private  var password: String?=null

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val args=intent
        if (args!=null){ this.name= args!!.getStringExtra("name")
            this.email=args!!.getStringExtra("email")
            this.password=args!!.getStringExtra("password")}


        Log.d("Main","on Created name: "+name+" email: "+email+" password: "+password)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerView: NavigationView=findViewById(R.id.navViewCore)
        val bottomBarView: BottomNavigationView = findViewById(R.id.nav_bottom_view)

        navController = findNavController(R.id.nav_host_fragment)
        drawerLayout=findViewById(R.id.drawerLayoutCore)

        Log.d("Main","drawer layout btenido")


        NavigationUI.setupWithNavController(bottomBarView,navController)
        drawerView.setNavigationItemSelectedListener {

            Log.d("Main","Item: "+it.itemId)
            when(it.itemId){
                R.id.navigation_perfil->{
                    val args= Bundle()
                    args.putString("name",name)
                    args.putString("email",email)
                    args.putString("password",password)
                    navController.navigate(R.id.navigation_perfil,args)
                    Log.d("Main","Se pasaron datos ")
                }
                R.id.navigation_bookings->{
                    navController.navigate(R.id.navigation_bookings)
                }
                R.id.navigation_car->{
                    navController.navigate(R.id.navigation_car)
                }
                R.id.navigation_favorites->navController.navigate(R.id.navigation_favorites)
                R.id.navigation_recents->navController.navigate(R.id.navigation_recents)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
             true


        }

        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)


    }

    override fun onPause() {
        super.onPause()
        Log.d("Main","Height: "+(findViewById(R.id.drawerLayoutCore) as View).height)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }


}
