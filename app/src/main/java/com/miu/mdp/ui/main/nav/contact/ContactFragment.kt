package com.miu.mdp.ui.main.nav.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentContactBinding
import com.miu.mdp.ui.main.nav.contact.adapter.ContactAdapter
import com.miu.mdp.ui.main.nav.profile.AddItemConstants
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }


    private lateinit var viewModel: ContactViewModel
    private lateinit var binding: FragmentContactBinding
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        initRecyclerView()
        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        val isLoggedIn:Boolean? = SharedPrefsUtil.isUserLoggedIn(requireContext(),
            SharedPrefConstants.IS_LOGGED_IN)
        isLoggedIn?.let {
            when(it){
                true ->fabAdd?.visibility = View.VISIBLE
                false ->fabAdd?.visibility = View.INVISIBLE
            }
        }
        fabAdd?.setOnClickListener {
            val directions = ContactFragmentDirections.actionContactFragmentToAddProfileFragment(
                AddItemConstants.CONTACT)
            findNavController().navigate(directions)
        }
    }

    private fun initRecyclerView() {
        contactAdapter = ContactAdapter {  }
        contactAdapter.submitList(viewModel.contactList)
        with(binding.rvContact){
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}