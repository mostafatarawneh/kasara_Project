package com.mostafa.dab

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap


class ReceiptPrinter(context: Context) {

    val context : Context?=null

    fun printReceipt(receiptText: String) {
        val intent = Intent("woyou.aidlservice.jiuv5.IWoyouService")
        intent.setPackage("com.sunmi.sunmiqrcodescanner")

        intent.putExtra("EXTRA_PRINT_DATA", receiptText)
        intent.putExtra("EXTRA_PRINT", true)

       context?.startService(intent)
    }

    fun printBitmap(bitmap: Bitmap) {
        val intent = Intent("woyou.aidlservice.jiuv5.IWoyouService")
        intent.setPackage("com.sunmi.sunmiqrcodescanner")

        intent.putExtra("EXTRA_PRINT_BITMAP", bitmap)
        intent.putExtra("EXTRA_PRINT", true)

        context?.startService(intent)
    }
}
