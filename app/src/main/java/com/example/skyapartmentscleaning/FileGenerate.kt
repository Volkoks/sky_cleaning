package com.example.skycleaning

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

// Создание файла во внутренем хранилище
fun generateFileCSVToInternalStorage(context: Context, fileName: String): File? {
    val csvFile = File(context.filesDir, fileName)
    csvFile.createNewFile()
    return if (csvFile.exists()) {
        csvFile
    } else {
        Toast.makeText(context,"ФАЙЛ CSV НЕ СОЗДАН!", Toast.LENGTH_SHORT).show()
        null
    }
}
// Создание файла в внешенем хранилище
fun generateFileCSVToExternalStorage(context: Context,fileName: String):File?{
    val path: String? = context.getExternalFilesDir(null)?.absolutePath
    val file = File(path,fileName)
    file.createNewFile()
    return if (file.exists()){
        return file
    }else{
        Toast.makeText(context,"ФАЙЛ CSV НЕ СОЗДАН!", Toast.LENGTH_SHORT).show()
        null
    }
}

fun shareFile(context: Context, csvFile: File) {
    val intent = Intent(Intent.ACTION_SEND)
    val contentUri =
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", csvFile)
    val mimeType = context.contentResolver.getType(contentUri)
    if (contentUri != null) {
        intent.putExtra(Intent.EXTRA_STREAM,contentUri)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.setDataAndType(contentUri, mimeType)
        context.startActivity(Intent.createChooser(intent, "Отправить в: "))
    }
}


