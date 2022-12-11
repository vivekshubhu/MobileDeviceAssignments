package com.miu.mdp.ui.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginButton.setOnClickListener {
            val userName = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (userName.isNotBlank() && password.isNotBlank()){
                if (userName.equals("admin",true) &&
                        password.equals("123",true)){
                    navigateToHome(true)
                }else {
                    Toast.makeText(
                        requireContext(),
                        "Wrong credentials please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                Toast.makeText(
                    requireContext(),
                    "Missing information please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.guestButton.setOnClickListener {
            navigateToHome(false)
        }
        val fabAdd = activity?.findViewById<FloatingActionButton>(R.id.fab_action)
        fabAdd?.visibility = View.INVISIBLE
    }

    private fun navigateToHome(isLoggedIn: Boolean) {
        SharedPrefsUtil.saveUserLoggedInStatus(requireContext(),
            SharedPrefConstants.IS_LOGGED_IN,isLoggedIn)
        val directions = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}