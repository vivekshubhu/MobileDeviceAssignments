package com.miu.mdp.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.mdp.databinding.FragmentResultAnalysisBinding
import com.miu.mdp.ui.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultAnalysisFragment : Fragment() {

    private var _binding: FragmentResultAnalysisBinding? = null
    private val binding get() = _binding!!

    private val quizViewModel: QuizViewModel by activityViewModels()

    private val resultAdapter = ResultAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            adapter = resultAdapter
        }
        val result = quizViewModel.getResultList()
        resultAdapter.submitList(result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}