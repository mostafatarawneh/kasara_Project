package com.mostafa.dab.Fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mostafa.dab.Adaptors.AdapotorRvDiverFragment
import com.mostafa.dab.Databases.DatabaseDriver
import com.mostafa.dab.databinding.FragmentDriverBinding


class DriverFragment : Fragment() {

    private var _binding: FragmentDriverBinding? = null
    private val binding get() = _binding!!
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adabtor: AdapotorRvDiverFragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dbHandler = DatabaseDriver(view.context)
        var drivers = dbHandler.getAllDrivers()
        dbHandler.close()



        adabtor = AdapotorRvDiverFragment(drivers,requireContext())
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvDriverFrag.layoutManager = linearLayoutManager
        binding.rvDriverFrag.adapter = adabtor
        adabtor!!.notifyDataSetChanged()


    }


}