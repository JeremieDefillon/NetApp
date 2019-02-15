package com.jey.netapp.controllers.activities

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import com.jey.netapp.R
import com.jey.netapp.controllers.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    private var mainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        this.configureAndShowMainFragment()
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private fun configureAndShowMainFragment() {

        if (supportFragmentManager.findFragmentById(R.id.activity_main_frame_layout) != null)
            mainFragment = supportFragmentManager.findFragmentById(R.id.activity_main_frame_layout) as MainFragment

        if (mainFragment == null) {
            mainFragment = MainFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit()
        }
    }
}
