package ru.fefu.photo_tests_impl.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.util.Hashtable
import javax.inject.Inject

class PhotoBarcodeScanner @Inject constructor() {
    fun readQRCode(bitmap: Bitmap): String? {
        var result: String? = null
        try {
            val source = RGBLuminanceSource(bitmap.width, bitmap.height, bitmapToIntArray(bitmap))
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
            val reader = MultiFormatReader()
            val decodeHints = Hashtable<DecodeHintType, Any>()
            decodeHints[DecodeHintType.TRY_HARDER] = true
            decodeHints[DecodeHintType.POSSIBLE_FORMATS] = BarcodeFormat.QR_CODE
            decodeHints[DecodeHintType.CHARACTER_SET] = "utf-8"
            result = reader.decode(binaryBitmap, decodeHints).text
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun bitmapToIntArray(bitmap: Bitmap): IntArray {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        return pixels
    }

}