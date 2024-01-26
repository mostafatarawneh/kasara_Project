package com.mostafa.dab.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mostafa.dab.Databases.DatabaseHelper
import com.mostafa.dab.models.Project
import com.mostafa.dab.R
import com.mostafa.dab.Adaptors.RecyclerAdabtor
import com.mostafa.dab.databinding.FragmentActiveProjectHomeBinding

class ActiveProjectHomeFragment : Fragment() {

    private var _binding: FragmentActiveProjectHomeBinding? = null
    private val binding get() = _binding!!
//    var listener: ((project: Project) -> Unit)? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adabtor: RecyclerAdabtor? = null
    val  TAG="MY_TAG"
    var project: Project? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActiveProjectHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHandler = DatabaseHelper(view.context)
        val allProjects = dbHandler.getAllProjects()
        dbHandler.close()



        adabtor = RecyclerAdabtor(allProjects, view.context){
        val bundle=Bundle()
            bundle.putString("name",it.name)
            bundle.putString("des",it.destination)
            bundle.putString("num",it.destinationPhoneNumber.toString())
            val secFragment = ProjectShowFragment()
            val orderFragment=AddOrderFragment()
            orderFragment.arguments=bundle
            secFragment.arguments=bundle
            fragmentManager?.beginTransaction()?.replace(R.id.main_farmelayout,secFragment)?.commit()




        }
        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerActiveProject.layoutManager = linearLayoutManager
        binding.recyclerActiveProject.adapter = adabtor
        adabtor!!.notifyDataSetChanged()

    }















}