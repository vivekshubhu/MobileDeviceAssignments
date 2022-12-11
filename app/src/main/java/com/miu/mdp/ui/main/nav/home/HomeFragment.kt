package com.miu.mdp.ui.main.nav.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentHomeBinding
import com.miu.mdp.utils.HomeConstants
import kotlinx.coroutines.flow.update

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavigationView?.visibility = View.VISIBLE

        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        fabAdd?.visibility = View.INVISIBLE

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.introDatas.collect() {
                it.forEach { pair ->
                    when (pair.first) {
                        HomeConstants.FULL_NAME -> {
                            if (pair.second?.isNotBlank() == true)
                                binding.tvProfileName.text = pair.second
                        }
                        HomeConstants.INTRO -> {
                            if (pair.second?.isNotBlank() == true)
                                binding.tvProfileDescription.text = pair.second
                        }
                        HomeConstants.CAREER_NOTE -> {
                            if (pair.second?.isNotBlank() == true)
                                binding.tvCareerNoteDesc.text = pair.second
                        }
                    }
                }
            }

        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.toolsAndTechnology.collect() {
                it.forEachIndexed { index, pair: Pair<String, MutableList<String>> ->
                    if (pair.first.isNotBlank() && pair.second.isNotEmpty())
                        addChips(pair.first, pair.second, it, index)
                }
            }
        }


    }

    private fun addChips(
        constant: String,
        second: MutableList<String>,
        list: MutableList<Pair<String, MutableList<String>>>,
        index: Int
    ) {
        when (constant) {
            HomeConstants.LANGUAGES -> {
                with(binding.cgLanguageDesc) {
                    inflateChipView(second, list, index)
                }
            }
            HomeConstants.FRAMEWORKS_WEB -> {
                with(binding.cgFrameworkDesc) {
                    inflateChipView(second, list, index)
                }
            }
            HomeConstants.MICROSERVICES_CLOUD -> {
                with(binding.cgMicroserviceDesc) {
                    inflateChipView(second, list, index)
                }
            }
            HomeConstants.DATABASES -> {
                with(binding.cgDatabaseDesc) {
                    inflateChipView(second, list, index)
                }
            }
            HomeConstants.TOOLS -> {
                with(binding.cgToolsDesc) {
                    inflateChipView(second, list, index)
                }
            }
        }
    }

    private fun ChipGroup.inflateChipView(
        second: MutableList<String>,
        list: MutableList<Pair<String, MutableList<String>>>,
        index: Int
    ) {
        second.forEach { item ->
            val chip = Chip(context)
            chip.text = item
            addView(chip)
            chip.setOnCloseIconClickListener {
                this.removeView(chip)
                list[index].second.remove(item)
                viewModel.toolsAndTechnology.update { list }
            }
        }
    }
}

