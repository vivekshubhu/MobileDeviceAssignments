package com.miu.mdp.ui.main.nav.work

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
import com.miu.mdp.databinding.FragmentWorkBinding
import com.miu.mdp.ui.main.nav.profile.AddItemConstants
import com.miu.mdp.ui.main.nav.work.adapter.WorkAdapter
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil

class WorkFragment : Fragment() {
    private var workFragment: WorkFragment? = null
    companion object {
        fun newInstance() = WorkFragment()
    }
    fun gg(): WorkFragment? {
        return workFragment
    }
    private lateinit var viewModel: WorkViewModel
    private lateinit var binding: FragmentWorkBinding
    private lateinit var workAdapter: WorkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workFragment = this
        viewModel = ViewModelProvider(this)[WorkViewModel::class.java]
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
            val directions = WorkFragmentDirections.actionWorkFragmentToAddProfileFragment(
                AddItemConstants.WORK_EXPERIENCE)
            findNavController().navigate(directions)
        }
    }

    private fun initRecyclerView() {
        workAdapter = WorkAdapter {  }
        workAdapter.submitList(viewModel.workExperienceList)
        with(binding.rvWork){
            layoutManager = LinearLayoutManager(context)
            adapter = workAdapter
        }
    }


}