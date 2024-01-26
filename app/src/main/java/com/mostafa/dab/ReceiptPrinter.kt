package com.mostafa.dab

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.sunmi.peripheral.printer.InnerPrinterCallback
import com.sunmi.peripheral.printer.InnerPrinterManager
import com.sunmi.peripheral.printer.SunmiPrinterService


class ReceiptPrinter(context: Context) {
    val innerPrinterCallback = object : InnerPrinterCallback() {

        override fun onConnected(service: SunmiPrinterService) {
            // Here is the remote service interface handle after the binding
            // service has been successfully connected
            // Supported print methods can be called through service
            Toast.makeText(context,"Connected ",Toast.LENGTH_LONG).show()
        }

        override fun onDisconnected() {
            // The method will be called back after the service is disconnected.
            // A reconnection strategy is recommended here
            Toast.makeText(context,"Faild Connection !!",Toast.LENGTH_LONG).show()
        }


    }
}