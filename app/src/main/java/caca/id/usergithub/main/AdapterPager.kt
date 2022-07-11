package caca.id.usergithub.main

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import caca.id.usergithub.R

class AdapterPager(private val context : Context, fragment : FragmentManager, file : Bundle) : FragmentPagerAdapter(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var FragmentBundle : Bundle


    init {
        FragmentBundle = file
    }
    @StringRes
    private val TITLES = intArrayOf(R.string.us1, R.string.us2)
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FragmentFollowers()
            1 -> fragment = FragmentFollowing()
        }

        fragment?.arguments = this.FragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TITLES[position])
    }
}