package com.mostafa.dab.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mostafa.dab.Databases.DatabaseOrder
import com.mostafa.dab.R
import com.mostafa.dab.Adaptors.RVInfoAdaptor
import com.mostafa.dab.databinding.FragmentProjectShowBinding


class ProjectShowFragment : Fragment() {
    lateinit var tv_destnationProject: TextView
    lateinit var tv_phonenumProject: TextView
    lateinit var tv_projectName: TextView
    private var _binding: FragmentProjectShowBinding? = null
    private val binding get() = _binding!!
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adabtor: RVInfoAdaptor?  = null
    public var NameProject :String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectShowBinding.inflate(inflater, container, false)



        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val name = args?.get("name")
        val des = args?.get("des")
        val num = args?.get("num")

        binding.tvProjectActivtyProjectName.text = name.toString()
        binding.tvProjectactitvtyPhonenumber.text = num.toString()
        binding.tvProjectactivtyDestnation.text = des.toString()

        binding.btnProjectShowNeworder.setOnClickListener {




            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_farmelayout, AddOrderFragment()).commit()



        }
        try {

            val dbHandler = DatabaseOrder(view.context)
            val allProjectsByProjectName = dbHandler.getAllInfoOrderByProjectName(binding.tvProjectActivtyProjectName.text as String)
            dbHandler.close()







            adabtor = RVInfoAdaptor(allProjectsByProjectName, requireContext())
            linearLayoutManager = LinearLayoutManager(context)
            binding.rvProjectShow.layoutManager = linearLayoutManager
            binding.rvProjectShow.adapter = adabtor
            adabtor!!.notifyDataSetChanged()

        }catch (e:Exception){
            Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
        }




    }


}