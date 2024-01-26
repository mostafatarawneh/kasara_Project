package com.mostafa.dab.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mostafa.dab.Databases.DatabaseHelper
import com.mostafa.dab.models.Project
import com.mostafa.dab.R
import com.mostafa.dab.databinding.FragmentAddProjectBinding


class AddProjectFragment : Fragment() {

    var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!
    var project: Project? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddfragSave.setOnClickListener {
            try {
                var name: String = binding.etAddfragNameProject.text.toString()
                var desntiaon: String = binding.etAddfragDestnation.text.toString()
                var phone: Int = binding.etAddfragDestinationPhoneNumber.text.toString().toInt()
                project = Project(name, desntiaon, phone)
                var db = DatabaseHelper(this.requireContext().applicationContext)
                var _sucsess = db.insertProject(project!!)
                db.close()
                if (_sucsess) {
                    Toast.makeText(
                        this.requireContext().applicationContext,
                        "Saved done !",
                        Toast.LENGTH_SHORT
                    ).show()
                    requireActivity().supportFragmentManager.beginTransaction().replace(
                        R.id.main_farmelayout,
                        ActiveProjectHomeFragment()
                    ).commit()
                } else {
                    Toast.makeText(
                        this.requireContext().applicationContext,
                        "erorr Not saved !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }catch (e:Exception){
                Snackbar.make(binding.btnAddfragSave,"${e.message}",Snackbar.LENGTH_SHORT).show()
            }
        }


    }


}