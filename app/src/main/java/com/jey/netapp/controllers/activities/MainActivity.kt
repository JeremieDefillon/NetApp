package com.jey.netapp.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.jey.netapp.controllers.fragments.MainFragment
import com.jey.netapp.R

class MainActivity : AppCompatActivity() {

    private var mainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureAndShowMainFragment()
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private fun configureAndShowMainFragment() {

        if(supportFragmentManager.findFragmentById(R.id.activity_main_frame_layout) != null)
            mainFragment = supportFragmentManager.findFragmentById(R.id.activity_main_frame_layout) as MainFragment

        if (mainFragment == null) {
            mainFragment = MainFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit()
        }
    }
}
