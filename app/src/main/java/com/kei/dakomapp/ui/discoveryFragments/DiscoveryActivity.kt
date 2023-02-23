package com.kei.dakomapp.ui.discoveryFragments

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.kei.dakomapp.R
import com.kei.dakomapp.adapter.DiscoveryAdapter
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class DiscoveryActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var vpDiscovery: ViewPager2? = null
    private var adapter: DiscoveryAdapter? = null

    companion object {
        fun getLaunchService (from: Context) = Intent(from, DiscoveryActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_discovery)
        tabLayout = findViewById(R.id.tabLayoutDiscovery)
        vpDiscovery = findViewById(R.id.vpDiscovery)
        tabLayout?.newTab()?.let { tabLayout?.addTab(it.setText("Events")) }
        tabLayout?.newTab()?.let { tabLayout?.addTab(it.setText("Activities")) }
        val fragmentManager = supportFragmentManager
        adapter = DiscoveryAdapter(fragmentManager, lifecycle)
        vpDiscovery?.adapter = adapter
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vpDiscovery?.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        vpDiscovery?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout?.selectTab(tabLayout?.getTabAt(position))
            }
        })
    }

}