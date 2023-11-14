package ua.hyperbeard.idlepocketplanel.modified


import android.net.Uri
import android.webkit.ValueCallback

interface FileChooserInterface {

    fun onFileCallback(parameters: ValueCallback<Array<Uri?>>)

}