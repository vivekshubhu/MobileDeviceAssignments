package com.miu.mdp.ui.main.WebView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentWebViewBinding
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*


class WebViewFragment : Fragment() {
    lateinit var binding: FragmentWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         binding = FragmentWebViewBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        fabAdd?.visibility = View.INVISIBLE
        navArgs<WebViewFragmentArgs>().let {
            with(binding.webview) {
                val settings = this.settings
                settings.javaScriptEnabled = true
                settings.domStorageEnabled =true
                settings.loadWithOverviewMode = true
                webViewClient = getClient()

                loadUrl("https://www.linkedin.com/in/sundarparajuli/")
                webChromeClient = (object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, progress: Int) {
                        binding.progressbar.progress = progress
                        if (progress == 100) {
                            binding.progressbar.visibility = View.GONE
                        } else {
                            binding.progressbar.visibility = View.VISIBLE
                        }
                    }
                })
            }
        }
    }

    private fun getBitmapInputStream(bitmap: Bitmap, compressFormat: Bitmap.CompressFormat): InputStream {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(compressFormat, 80, byteArrayOutputStream)
        val bitmapData: ByteArray = byteArrayOutputStream.toByteArray()
        return ByteArrayInputStream(bitmapData)
    }

    private fun getClient(): WebViewClient {
        return object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
                if(url == null){
                    return super.shouldInterceptRequest(view, url as String)
                }
                return if(url.toLowerCase(Locale.ROOT).contains(".jpg") || url.toLowerCase(Locale.ROOT).contains(".jpeg")){
                    val bitmap = Glide.with(binding.webview).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).load(url).submit().get()
                    WebResourceResponse("image/jpg", "UTF-8",getBitmapInputStream(bitmap,
                        Bitmap.CompressFormat.JPEG))
                }else if(url.toLowerCase(Locale.ROOT).contains(".png")){
                    val bitmap = Glide.with(binding.webview).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).load(url).submit().get()
                    WebResourceResponse("image/png", "UTF-8",getBitmapInputStream(bitmap,
                        Bitmap.CompressFormat.PNG))
                }else{
                    super.shouldInterceptRequest(view, url)
                }

            }
        }
    }
}
