package ua.hyperbeard.idlepocketplanel.ui.theme

sealed class Targets(val dest: String){
    object FirstScreen : Targets(dest = "first")
    object SecondScreen : Targets(dest = "second")
    object ThirdScreen : Targets(dest = "third")
    object FourthScreen : Targets(dest = "fourth")
    object FifthScreen : Targets(dest = "fifth")

    companion object{
        const val SHARED_P = "SHARED_P"
        const val PLAYER_NAME = "PLAYER_NAME"
        const val CURRENT_DATA = "live_data"
        const val CLEAR = "clear"
        const val ADVERTISING_ID = "ADVERTISING_ID"
        const val ONE_DEV_KEY = " f45efb30-2619-4e13-be33-171ff2a19627"
        const val ONE_PUSH_TOKEN = "OTMxYmQ2MzQtNGEwOC00ZGE1LWJiNDgtMzFhYjE3OTBjMWJl"
        const val SCAM = "SCAM"
        const val AIM = "AIM"   //link
        const val MODE = "MODE"   //link
        const val CHOOSER = "image/*"   //choose photos
        const val isRegisteredInSegments = "isRegisteredInSegments"   //is registered
        const val POLICY_LINK = "https://telegra.ph/Privacy-Policy-of-Gates-of-Luky-Olympus-09-06"
    }
}
