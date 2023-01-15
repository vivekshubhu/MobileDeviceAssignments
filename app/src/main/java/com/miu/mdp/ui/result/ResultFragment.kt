package com.miu.mdp.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.miu.mdp.R
import com.miu.mdp.databinding.FragmentResultBinding
import com.miu.mdp.ui.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textCorrectAnswersValue.text = quizViewModel.correctAnswerIndexList.size.toString()
            textWrongAnswersValue.text = quizViewModel.incorrectAnswerIndexList.size.toString()
            textTotalQuestionsValue.text = quizViewModel.allQuestionList.size.toString()
            textYourAnswerValue.text =
                "${quizViewModel.correctAnswerIndexList.size}/${quizViewModel.allQuestionList.size}"
        }

        binding.buttonResultAnalysis.setOnClickListener {
            val directions = ResultFragmentDirections.actionResultFragmentToResultAnalysisFragment()
            findNavController().navigate(directions)
        }

        binding.buttonTryAgain.setOnClickListener {
            quizViewModel.resetQuiz()
            val directions = ResultFragmentDirections.actionResultFragmentToQuizFragment()
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}