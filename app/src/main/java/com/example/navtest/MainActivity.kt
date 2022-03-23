package com.example.navtest

import Fragments.Downloaded
import Fragments.HomeFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private  val homeFragment = HomeFragment()
    private  val downloadFragment = Downloaded()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        replaceFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.downloaded -> replaceFragment(downloadFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}