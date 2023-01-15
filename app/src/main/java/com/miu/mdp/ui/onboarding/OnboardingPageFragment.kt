package com.miu.mdp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.mdp.databinding.FragmentOnboardingPageBinding

class OnboardingPageFragment : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private var imageResource = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = requireArguments().getString(TITLE)!!
            description = requireArguments().getString(DESCRIPTION)!!
            imageResource = requireArguments().getInt(image)
        }
    }

    private var _binding: FragmentOnboardingPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textOnboardingTitle.text = title
            textOnboardingDescription.text = description
            imageOnboarding.setAnimation(imageResource)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val image = "image"
        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int,
        ): OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            args.putString(DESCRIPTION, description)
            args.putInt(image, imageResource)
            fragment.arguments = args
            return fragment
        }
    }
}
