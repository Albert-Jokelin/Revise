package com.albertjokelin.revise

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.albertjokelin.revise.databinding.ActivityMainBinding
import com.albertjokelin.revise.ui.home.HomeFragment
import com.albertjokelin.revise.ui.maths.MathsFragment
import com.albertjokelin.revise.ui.science.ScienceFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.view.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var mAdView: AdView
    lateinit var binding: ActivityMainBinding
    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Google Ad-mobs Code

        MobileAds.initialize(this)
       mAdView = findViewById(R.id.adView)
       val adRequest: AdRequest = AdRequest.Builder().build()
       mAdView.loadAd(adRequest)


        // Change the title bar name to Home and stuff

        actionBar?.title = "Home"
        // Code for the menu
        toolbar = binding.drawerLayout.toolbar
        setSupportActionBar(toolbar)


        drawer = binding.drawerLayout
        navigationView = binding.navView

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_science -> {
                addFragment(ScienceFragment())
            }

            R.id.nav_home -> {
                addFragment(HomeFragment())
            }
            R.id.nav_maths-> {
                addFragment(MathsFragment())
            }

        }
        drawer.closeDrawer((GravityCompat.START))
        return true
    }

    fun addFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        ft.replace(R.id.nav_host_fragment, fragment)
        ft.commitAllowingStateLoss()
    }
}






