package com.miu.mdp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentSplashBinding
import com.miu.mdp.ui.splash.state.SplashUIState
import com.miu.mdp.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkOnboardingDone()
        val slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide)
        binding.logo.animation = slideAnimation

        lifecycleScope.launchWhenStarted {
            viewModel.splashUIState.collect { uiState ->
                when (uiState) {
                    is SplashUIState.OnBoardingDone -> {
                        // wait 2 seconds
                        delay(2000)
                        val directions =
                            SplashFragmentDirections.actionSplashFragmentToQuizFragment()
                        findNavController().navigate(directions)
                    }

                    else -> {
                        // wait 2 seconds
                        delay(2000)
                        val directions =
                            SplashFragmentDirections.actionSplashFragmentToOnboardingFragment()
                        findNavController().navigate(directions)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}