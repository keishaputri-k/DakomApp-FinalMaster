package com.kei.dakomapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kei.dakomapp.ui.discoveryFragments.ActivitiesFragment
import com.kei.dakomapp.ui.discoveryFragments.EventsFragment


class DiscoveryAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            ActivitiesFragment()
        } else EventsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}