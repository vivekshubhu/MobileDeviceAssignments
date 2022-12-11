package com.miu.mdp


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.miu.mdp.databinding.ActivityMainBinding
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil
import com.miu.mdp.R


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        setupNavigation()
        binding.materialToolbar.navigationIcon?.setColorFilter(
            resources.getColor(R.color.white), PorterDuff.Mode.LIGHTEN
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.tvTitle.text = destination.label
            if (destination.id == R.id.loginFragment) {
                binding.bottomNavView.visibility = View.GONE
                binding.materialToolbar.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
                binding.materialToolbar.visibility = View.VISIBLE
            }
            binding.fabAction.setImageDrawable(resources?.let {
                ResourcesCompat.getDrawable(
                    it,
                    R.drawable.ic_baseline_create_24,
                    null
                )
            })
        }
        binding.ibShare.setOnClickListener {

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(
                Intent.EXTRA_TITLE,
                "Github:" + " - " + "Please check out the project on github"
            )
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://github.com/vivekshubhu/MobileDeviceAssignments"
            )

            startActivity(Intent.createChooser(sendIntent, null))
        }

        binding.ibLinkedin.setOnClickListener {
            val url = "https://www.linkedin.com/in/vivekkarki247/"
            val bundle = Bundle()
                bundle.putString("url",url)
            navController.navigate(R.id.webViewFragment,bundle)
        }
        binding.ibLogout.setOnClickListener {
            if (SharedPrefsUtil.isUserLoggedIn(this, SharedPrefConstants.IS_LOGGED_IN) == true) {
                SharedPrefsUtil.saveUserLoggedInStatus(
                    this,
                    SharedPrefConstants.IS_LOGGED_IN,
                    false
                )
            }
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.profileFragment, R.id.workFragment, R.id.contactFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (navController.currentBackStackEntry?.destination?.label == "Login") {
            finishAffinity()
        }
    }
}