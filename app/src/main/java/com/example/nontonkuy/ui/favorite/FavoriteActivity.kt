package com.example.nontonkuy.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nontonkuy.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        setSupportActionBar(activityFavoriteBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        val sectionFavPagerAdapter = SectionFavPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.favViewPager.adapter = sectionFavPagerAdapter
        activityFavoriteBinding.favTabs.setupWithViewPager(activityFavoriteBinding.favViewPager)
    }
}