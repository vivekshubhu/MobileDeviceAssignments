package com.miu.mdp.ui.main.nav.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.R
import com.miu.mdp.data.Certification
import com.miu.mdp.data.Contact
import com.miu.mdp.data.Education
import com.miu.mdp.data.Work
import com.miu.mdp.databinding.FragmentAddProfileBinding
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil


class AddProfileFragment : Fragment() {
    lateinit var binding: FragmentAddProfileBinding
    lateinit var viewModel: ProfileViewModel

    val args: AddProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        fabAdd?.setImageDrawable(context?.resources?.let {
            ResourcesCompat.getDrawable(
                it,
                R.drawable.ic_baseline_done_24,
                null
            )
        })
        fabAdd?.setOnClickListener {
            handleInputBasedOnUI()
            fabAdd.setImageDrawable(context?.resources?.let {
                ResourcesCompat.getDrawable(
                    it,
                    R.drawable.ic_baseline_create_24,
                    null
                )
            })
            navigateToIncomingScreen()

        }
        handleUIbasedOnScreen()
    }

    private fun navigateToIncomingScreen() {
        var navDirection:NavDirections? = null
        when(args.fromScreen){
            AddItemConstants.EDUCATION, AddItemConstants.CERTIFICATION -> {
                navDirection = AddProfileFragmentDirections.actionAddProfileFragmentToProfileFragment()
            }
            AddItemConstants.WORK_EXPERIENCE -> {
                navDirection = AddProfileFragmentDirections.actionAddProfileFragmentToWorkFragment()
            }
            AddItemConstants.CONTACT -> {
                navDirection = AddProfileFragmentDirections.actionAddProfileFragmentToContactFragment()
            }
        }
        navDirection?.let { findNavController().navigate(it) }
    }

    private fun handleInputBasedOnUI() {
        when (args.fromScreen) {
            AddItemConstants.EDUCATION -> {
                val education = Education(
                    binding.tilItem1.editText?.text.toString(),
                    binding.tilItem2.editText?.text.toString(),
                    binding.tilItem3.editText?.text.toString(),
                )

                //check previous items on sharerpref and update the list
                SharedPrefsUtil.saveListItem(
                    requireContext(),
                    education,
                    SharedPrefConstants.EDUCATIONS
                )

            }
            AddItemConstants.CERTIFICATION -> {
                val certification = Certification(
                    binding.tilItem1.editText?.text.toString(),
                    binding.tilItem2.editText?.text.toString(),
                )
                SharedPrefsUtil.saveListItem(
                    requireContext(),
                    certification,
                    SharedPrefConstants.CERTIFICATIONS
                )

            }
            AddItemConstants.WORK_EXPERIENCE -> {
                val work = Work(
                    binding.tilItem1.editText?.text.toString(),
                    binding.tilItem2.editText?.text.toString(),
                    binding.tilItem3.editText?.text.toString(),
                    binding.tilItem4.editText?.text.toString(),
                    binding.tilItem5.editText?.text.toString(),
                    binding.tilItem6.editText?.text.toString(),
                )
                SharedPrefsUtil.saveListItem(
                    requireContext(),
                    work,
                    SharedPrefConstants.WORK_EXPERIENCES
                )
            }
            AddItemConstants.CONTACT -> {
                val contact = Contact(
                    binding.tilItem1.editText?.text.toString(),
                    binding.tilItem2.editText?.text.toString(),
                    binding.tilItem3.editText?.text.toString(),
                )
                SharedPrefsUtil.saveListItem(
                    requireContext(),
                    contact,
                    SharedPrefConstants.CONTACTS
                )

            }
        }
    }

    private fun handleUIbasedOnScreen() {
        when (args.fromScreen) {
            AddItemConstants.EDUCATION -> {
                binding.tilItem1.hint = "Image Url"
                binding.tilItem2.hint = "University Name"
                binding.tilItem3.hint = "Degree Title"

                mutableListOf(binding.tilItem4, binding.tilItem5, binding.tilItem6).forEach {
                    it.visibility = View.INVISIBLE
                }
            }
            AddItemConstants.CERTIFICATION -> {
                binding.tilItem1.hint = "Image Url"
                binding.tilItem2.hint = "Certification Title"
                mutableListOf(
                    binding.tilItem3,
                    binding.tilItem4,
                    binding.tilItem5,
                    binding.tilItem6
                ).forEach {
                    it.visibility = View.INVISIBLE
                }
            }
            AddItemConstants.WORK_EXPERIENCE -> {
                binding.tilItem1.hint = "Image Url"
                binding.tilItem2.hint = "Position"
                binding.tilItem3.hint = "Organization Name"
                binding.tilItem4.hint = "Duration"
                binding.tilItem5.hint = "Company Address"
                binding.tilItem6.hint = "Work Description"
            }
            AddItemConstants.CONTACT -> {
                binding.tilItem1.hint = "Image Url"
                binding.tilItem2.hint = "contact"
                binding.tilItem3.hint = "Contact Type"
                mutableListOf(binding.tilItem4, binding.tilItem5, binding.tilItem6).forEach {
                    it.visibility = View.INVISIBLE
                }
            }
        }
    }

}

object AddItemConstants {
    const val EDUCATION = "Education"
    const val CERTIFICATION = "Certification"
    const val WORK_EXPERIENCE = "work experience"
    const val CONTACT = "contact"
}