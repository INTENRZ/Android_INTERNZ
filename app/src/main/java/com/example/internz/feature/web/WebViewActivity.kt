package com.example.internz.feature.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.internz.R

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView : WebView
    private lateinit var webSetting : WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webViewFunction()
    }

    private fun webViewFunction() {
        //변수 초기화
        webView = findViewById(R.id.webView)
        webSetting = webView.settings

        webView.webViewClient = WebViewClient() //클릭시 새창 뜨지 않게함
        webView.isHorizontalScrollBarEnabled = true
        webSetting.javaScriptEnabled = true //자바스크립트 허용
        webSetting.useWideViewPort = true //화면 사이즈 맞추기 허용
        webSetting.loadWithOverviewMode = true //컨텐츠 웹뷰보다 클 경우 스크린 크기에 맞게 조정
        webSetting.setSupportZoom(false) //화면 줌 허용 여부
        webSetting.builtInZoomControls = false //화면 두손가락 확대 축소 비허용
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //컨텐츠 사이즈 맞추기

        //웹뷰 시작
        webView.loadUrl(intent.getStringExtra("url"))

    }
}
