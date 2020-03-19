package com.example.kottry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewPager)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(FirstFragment(), "First")
        adapter.addFrag(FirstFragment(), "Second")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }
}
