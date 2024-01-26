package com.mostafa.dab.Fragements

import com.mostafa.dab.ReceiptPrinter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mostafa.dab.Databases.DatabaseDriver
import com.mostafa.dab.Databases.DatabaseHelper
import com.mostafa.dab.Databases.DatabaseOrder
import com.mostafa.dab.models.Order
import com.mostafa.dab.R
import com.mostafa.dab.databinding.FragmentAddOrderBinding


class AddOrderFragment : Fragment() {


    var _binding: FragmentAddOrderBinding? = null
    private val binding get() = _binding!!
    var order: Order?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseDriver = DatabaseDriver(requireContext())
        var allDrivers=databaseDriver.getAllDrivers()
        var driverNames =ArrayList<String>()


        for (i in allDrivers){
            driverNames.add(i.name)
//            Log.d("MY_TAG", "onAttach:${i.name} ")
        }

        val databaseHelper=DatabaseHelper(requireContext())
        val allProjectName = databaseHelper.getAllProjects()
        val projectNames = ArrayList<String>()

        for (i in allProjectName){
            projectNames.add(i.name)
        }

        val arrayAdapterProjectName = ArrayAdapter(requireContext(),R.layout.dropdown_item_layout,projectNames)
        binding.dropdownProjectName.setAdapter(arrayAdapterProjectName)



        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item_layout,driverNames)

        binding.autoCompleteDriver.setAdapter(arrayAdapter)

        val mixturesType = resources.getStringArray(R.array.mixtures)
        val adapMix=ArrayAdapter(requireContext(), R.layout.dropdown_item_layout,mixturesType)
        binding.dropdownMixture.setAdapter(adapMix)

//






        binding.btnAddOderSave.setOnClickListener {
            try{
            val dbHandler = DatabaseOrder(requireContext())
            val drivername =binding.autoCompleteDriver.text.toString()
            val heat =binding.etTemp.text.toString()
            val wegiht =binding.etWeight.text.toString()
            val paidmoney =binding.etMoneyPaid.text.toString()
            val note =binding.etNote.text.toString()
            val mixType =binding.dropdownMixture.text.toString()
            val projectName =binding.dropdownProjectName.text.toString()
            order= Order(drivername,heat,wegiht,paidmoney,note,mixType,projectName)
            val succ=dbHandler.InsertInfoOrder(order!!,projectName)
            //-------------------------------//
            if (succ){
                Toast.makeText(requireContext(),"Saved Done !",Toast.LENGTH_SHORT).show()

                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.main_farmelayout,
                    ActiveProjectHomeFragment()
                ).commit()

            }else{
                Toast.makeText(requireContext(),"Not Save there are an erorr!!",Toast.LENGTH_SHORT).show()

            }
                }catch (e:Exception){
                Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
                }



            //-----------------------------this print-----------------------------//







    }
    fun getLayoutBitmap(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }



}

    }








