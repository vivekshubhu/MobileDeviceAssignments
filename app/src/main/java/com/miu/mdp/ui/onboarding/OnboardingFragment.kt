package com.miu.mdp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.mdp.databinding.FragmentOnboardingBinding
import com.miu.mdp.ui.onboarding.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveOnboardingDone(false)
        binding.apply {
            viewPager.adapter =
                OnboardingViewPagerAdapter(this@OnboardingFragment, requireContext())
            viewPager.offscreenPageLimit = 1
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 2) {
                        btnNextStep.text = "Get Started"
                    } else {
                        btnNextStep.text = "Next"
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
                override fun onPageScrollStateChanged(arg0: Int) {}
            })

            textSkip.setOnClickListener {
                nextpage()
            }


            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    nextpage()
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }
        }
    }

    private fun nextpage() {
        // go to next page
        viewModel.saveOnboardingDone(true)
        val directions =
            OnboardingFragmentDirections.actionOnboardingFragmentToQuizFragment()
        findNavController().navigate(directions)
    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}