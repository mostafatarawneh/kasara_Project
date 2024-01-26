package com.mostafa.dab.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mostafa.dab.Databases.DatabaseDriver
import com.mostafa.dab.models.Driver
import com.mostafa.dab.R
import com.mostafa.dab.databinding.FragmentAddDriverBinding


class AddDriverFragment : Fragment() {

    var _binding: FragmentAddDriverBinding? = null
    private val binding get() = _binding!!
    var driver: Driver? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDriverBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddfragDriverSave.setOnClickListener {
            try {
                var name: String = binding.etAddDriverName.text.toString()
                var vehicleNumber: String = binding.etAddDriverVechleNum.text.toString()
                var phone: Int = binding.etAddDriverDriverPhoneNumber.text.toString().toInt()
                var vehicleType: String = binding.etAddDriverVehicleType.text.toString()

                driver = Driver(name, vehicleNumber, phone, vehicleType)
                var db = DatabaseDriver(this.requireContext())
                var _sucsess = db.insertDriver(driver!!)
                db.close()
                if (_sucsess) {
                    Toast.makeText(
                        this.requireContext().applicationContext,
                        "Saved done !",
                        Toast.LENGTH_SHORT
                    ).show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_farmelayout, DriverFragment()).commit()
                } else {
                    var e=Exception()
                    Toast.makeText(
                        this.requireContext().applicationContext,
                        "not Done ! , ${e.message} ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Snackbar.make(binding.btnAddfragDriverSave, "${e.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

    }
}