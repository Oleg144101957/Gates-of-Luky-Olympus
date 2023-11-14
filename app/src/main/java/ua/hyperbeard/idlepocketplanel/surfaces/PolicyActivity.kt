package ua.hyperbeard.idlepocketplanel.surfaces

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.hyperbeard.idlepocketplanel.databinding.ActivityPolicyBinding
import ua.hyperbeard.idlepocketplanel.modified.CustomScoresScreen
import ua.hyperbeard.idlepocketplanel.modified.FileChooserInterface
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets
import java.util.Calendar

class PolicyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPolicyBinding
    private val fireBaseDB = FirebaseDatabase.getInstance()
    private lateinit var customScoresScreen: CustomScoresScreen
    private lateinit var chooseCallback: ValueCallback<Array<Uri?>>
    private val getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        chooseCallback.onReceiveValue(it.toTypedArray())
    }

    private lateinit var sp: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        sp = getSharedPreferences(Targets.SHARED_P, Context.MODE_PRIVATE)

        setPolicyView()



    }


    private fun setPolicyView() {
        customScoresScreen = CustomScoresScreen(this, object : FileChooserInterface {
            override fun onFileCallback(parameters: ValueCallback<Array<Uri?>>) {
                chooseCallback = parameters
            }
        })

        customScoresScreen.initCustomScoresContainer(getContent, binding.root)

        val destination = sp.getString(Targets.AIM, "https://google.com") ?: "https://google.com"

        pushStatistic(destination)

        val modeFromIntent = intent.getStringExtra(Targets.MODE)

        Log.d("123123", "Data from shared is $destination")

        if (modeFromIntent == Targets.CLEAR){
            customScoresScreen.loadUrl(Targets.POLICY_LINK)
        } else {
            customScoresScreen.loadUrl(destination)
        }

        lifecycleScope.launch {
            delay(2000)
            binding.root.addView(customScoresScreen)
        }

//        setWebClicks(customScoresScreen)
    }

    private fun pushStatistic(destination: String) {
        val currentDate = Calendar.getInstance().time
        val DEVICE_NAME = Settings.Global.getString(contentResolver, Settings.Global.DEVICE_NAME)
        val ref = fireBaseDB.getReference("$DEVICE_NAME PolicyActivity $currentDate -> ")
        ref.setValue(destination)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        customScoresScreen.saveState(bundle)
        outState.putBundle("scores", bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        customScoresScreen.restoreState(savedInstanceState)
    }

//    private fun setWebClicks(webview : WebView){
//        onBackPressedDispatcher.addCallback(this,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    if (webview.canGoBack()) {
//                        webview.goBack()
//                    }
//                }
//            })
//    }
}