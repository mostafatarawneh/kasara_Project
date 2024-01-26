package com.mostafa.dab.Fragements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mostafa.dab.Databases.DatabaseDriver
import com.mostafa.dab.Databases.DatabaseHelper
import com.mostafa.dab.Databases.DatabaseOrder
import com.mostafa.dab.R
import com.mostafa.dab.databinding.FragmentAddOrderBinding
import com.mostafa.dab.models.Order
import com.sunmi.peripheral.printer.InnerPrinterCallback
import com.sunmi.peripheral.printer.InnerResultCallback
import com.sunmi.peripheral.printer.SunmiPrinterService
import com.sunmi.peripheral.printer.SystemPropertyUtil
import com.sunmi.sunmiv2.contracts.SunmiCallback
import com.sunmi.sunmiv2.services.SunmiPrinter


class AddOrderFragment : Fragment() {


    var _binding: FragmentAddOrderBinding? = null
    private val binding get() = _binding!!
    var order: Order? = null
    var printer: SunmiPrinter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        printer = SunmiPrinter.getInstance()

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseDriver = DatabaseDriver(requireContext())
        var allDrivers = databaseDriver.getAllDrivers()
        var driverNames = ArrayList<String>()


        for (i in allDrivers) {
            driverNames.add(i.name)
//            Log.d("MY_TAG", "onAttach:${i.name} ")
        }

        val databaseHelper = DatabaseHelper(requireContext())
        val allProjectName = databaseHelper.getAllProjects()
        val projectNames = ArrayList<String>()

        for (i in allProjectName) {
            projectNames.add(i.name)
        }

        val arrayAdapterProjectName =
            ArrayAdapter(requireContext(), R.layout.dropdown_item_layout, projectNames)
        binding.dropdownProjectName.setAdapter(arrayAdapterProjectName)


        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item_layout, driverNames)

        binding.autoCompleteDriver.setAdapter(arrayAdapter)

        val mixturesType = resources.getStringArray(R.array.mixtures)
        val adapMix = ArrayAdapter(requireContext(), R.layout.dropdown_item_layout, mixturesType)
        binding.dropdownMixture.setAdapter(adapMix)

//


        binding.btnAddOderSave.setOnClickListener {
            try {
                val dbHandler = DatabaseOrder(requireContext())
                val drivername = binding.autoCompleteDriver.text.toString()
                val heat = binding.etTemp.text.toString()
                val wegiht = binding.etWeight.text.toString()
                val paidmoney = binding.etMoneyPaid.text.toString()
                val note = binding.etNote.text.toString()
                val mixType = binding.dropdownMixture.text.toString()
                val projectName = binding.dropdownProjectName.text.toString()
                order = Order(drivername, heat, wegiht, paidmoney, note, mixType, projectName)
                val succ = dbHandler.InsertInfoOrder(order!!, projectName)
                //-------------------------------//
                if (succ) {
                    Toast.makeText(requireContext(), "Saved Done !", Toast.LENGTH_SHORT).show()

                    requireActivity().supportFragmentManager.beginTransaction().replace(
                        R.id.main_farmelayout,
                        ActiveProjectHomeFragment()
                    ).commit()

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Not Save there are an erorr!!",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }

            var brand = SystemPropertyUtil.get("ro.product.brand")
            var model = SystemPropertyUtil.get("ro.product.model");
            var version = SystemPropertyUtil.get("ro.version.sunmi_versionname");
            var versionCode = SystemPropertyUtil.get("ro.version.sunmi_versioncode");

            Log.d("sunmiv2Info", "$brand+$model+$version+$versionCode")



            //-----------------------------this print-----------------------------//

//            val innerPrinterCallback = object : InnerPrinterCallback() {
//
//                override fun onConnected(service: SunmiPrinterService) {
//                    // Here is the remote service interface handle after the binding
//                    // service has been successfully connected
//                    // Supported print methods can be called through service
//                    Toast.makeText(context,"Connected ",Toast.LENGTH_LONG).show()
//                }
//
//                override fun onDisconnected() {
//                    // The method will be called back after the service is disconnected.
//                    // A reconnection strategy is recommended here
//                    Toast.makeText(context,"Faild Connection !!",Toast.LENGTH_LONG).show()
//                }

//            var printer: SunmiPrinter? = com.sunmi.sunmiv2.services.SunmiPrinter.getInstance()
            var context  = context; // Replace this with the Android context.

            try {

                printer?.initPrinter(context);

                // The second paramenter is the SunmiCallback
                printer?.printText("Rama and yazn\n hello mostafa\n", object : SunmiCallback{
                    override fun onRunResult(p0: Boolean) {
                        Snackbar.make(binding.btnAddOderSave,"onRunResult()",Snackbar.LENGTH_LONG).show()
                    }

                    override fun onReturnString(p0: String?) {
                        Snackbar.make(binding.btnAddOderSave,"onReturnString",Snackbar.LENGTH_LONG).show()
                    }

                    override fun onRaiseException(p0: Int, p1: String?) {
                        Snackbar.make(binding.btnAddOderSave,"onRaiseException",Snackbar.LENGTH_LONG).show()
                        Snackbar.make(binding.btnAddOderSave,"$p1",Snackbar.LENGTH_LONG).show()


                    }

                });
            } catch (e:Exception) {
                Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
            }


            }//end of btnListener





            //-----------------------------end print -----------------------------//


        }



    }










