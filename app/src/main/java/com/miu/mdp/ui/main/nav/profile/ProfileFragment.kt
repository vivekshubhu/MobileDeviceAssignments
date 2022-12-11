package com.miu.mdp.ui.main.nav.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentProfileBinding
import com.miu.mdp.ui.main.nav.profile.adapter.CertificationAdapter
import com.miu.mdp.ui.main.nav.profile.adapter.EducationAdapter
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var educationAdapter: EducationAdapter
    private lateinit var certificationAdapter: CertificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        initRecyclerView()
        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        fabAdd?.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_create_24,null))
        val isLoggedIn:Boolean? = SharedPrefsUtil.isUserLoggedIn(requireContext(),
            SharedPrefConstants.IS_LOGGED_IN)
        isLoggedIn?.let {
            when(it){
                true ->fabAdd?.visibility = View.VISIBLE
                false ->fabAdd?.visibility = View.INVISIBLE
            }
        }
        fabAdd?.setOnClickListener {
            createAlertDialogAndNavigateToAddScreen()
        }
    }

    private fun createAlertDialogAndNavigateToAddScreen() {
        var itemClicked: String? = null
        val items = arrayOf(AddItemConstants.EDUCATION, AddItemConstants.CERTIFICATION)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add new")
        builder.setSingleChoiceItems(items, 0) { _, which ->
            // Do something when an item is selected
            itemClicked = items[which]
        }
        val layout = LinearLayout(context)
        layout.gravity = Gravity.CENTER
        layout.addView(Button(context))
        builder.setNeutralButton(
            "ADD"
        ) { _, _ ->
            //navigateTo edit screen and set param based on item clicked
            if (itemClicked == null) {
                itemClicked = items[0]
            }
            val directions =
                ProfileFragmentDirections.actionProfileFragmentToAddProfileFragment(itemClicked)
            findNavController().navigate(directions)
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun initRecyclerView() {
        initEducationRecyclerView()
        initCertificationRecyclerView()
    }

    private fun initEducationRecyclerView() {
        this.educationAdapter = EducationAdapter {}
        educationAdapter.submitList(viewModel.educationList)
        binding.rvCollege.apply {
            this.setHasFixedSize(false)
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = educationAdapter
        }
    }

    private fun initCertificationRecyclerView() {
        this.certificationAdapter = CertificationAdapter { }
        certificationAdapter.submitList(viewModel.certificationList)
        binding.rvCertification.apply {
            this.setHasFixedSize(false)
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = certificationAdapter
        }
    }


}