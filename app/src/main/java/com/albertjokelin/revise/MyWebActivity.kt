package com.albertjokelin.revise

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.albertjokelin.revise.ui.maths.CustomViewHolder
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MyWebActivity : AppCompatActivity() {

    lateinit var mAdView: AdView
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_web_activity)

        webView = findViewById(R.id.my_web_view)


        // Google Adview
        MobileAds.initialize(this)
        mAdView = findViewById(R.id.adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Loading the web page
        val webLink = intent.getStringExtra(CustomViewHolder.LESSON_LINK_KEY)

        println(webLink)
        webView.loadUrl(webLink)


    }
}




