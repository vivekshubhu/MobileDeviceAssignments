package com.miu.mdp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.miu.mdp.R
import com.miu.mdp.databinding.ActivityMainBinding
import com.miu.mdp.ui.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val quizViewModel: QuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onboardingFragment, R.id.splashFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.title = "QUIZ APP"
                }
                R.id.quizFragment -> {
                    //show menu
                    supportActionBar?.show()
                    invalidateOptionsMenu()
                    binding.toolbar.title = "QUIZ APP"
                }
                R.id.resultFragment -> {
                    invalidateOptionsMenu()
                    binding.toolbar.title = "Result"
                }
                R.id.resultAnalysisFragment -> {
                    binding.toolbar.title = "Result Analysis"
                }
                else -> {
                    binding.toolbar.title = ""
                }
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id == R.id.resultAnalysisFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.action_restart)?.isVisible =
            quizViewModel.currentQuestionNumber < quizViewModel.totalQuestionToAnswer
        menu?.findItem(R.id.action_onboarding)?.isVisible =
            quizViewModel.currentQuestionNumber < quizViewModel.totalQuestionToAnswer
        return true
    }

    // menu click listener
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_restart -> {
                quizViewModel.resetQuiz()
            }

            R.id.action_onboarding -> {
                quizViewModel.resetQuiz()
                navController.navigate(R.id.action_global_onboardingFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}