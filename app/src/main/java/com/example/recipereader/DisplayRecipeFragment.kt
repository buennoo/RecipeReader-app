package com.example.recipereader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipereader.databinding.FragmentDisplayTaskBinding

class DisplayRecipeFragment : Fragment(), ToDoListListener {
    val args: DisplayRecipeFragmentArgs by navArgs()
    private lateinit var binding: FragmentDisplayTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    // Inflate the layout for this fragment
        binding = FragmentDisplayTaskBinding.inflate(inflater, container, false)
        val current = args.task
        with(binding.stepsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = StepRecyclerViewAdapter(
                current.steps.list,
                this@DisplayRecipeFragment
            ) // adapter is responsible for displaying the data
        }
        return binding.root
    }

    override fun onTaskClick(taskPosition: Int) {
        // Abstract fun override
    }

    override fun onTaskLongClick(taskPosition: Int) {
        // Abstract fun override
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the task from the arguments and display the task details
        val task = args.task
        binding.displayTitle.text = task.title
        binding.displayNumIngr.text = task.numOfIngredients
        binding.displayNumSteps.text = task.numOfSteps

        if (task.steps != null) {
            binding.displayDescription.text = task.ingredients
        } else {
            binding.displayDescription.text = "Brak kroków"
        }

        binding.displayGoBack.setOnClickListener {
            // Navigate to the AddTaskFragment with action id
            findNavController().navigate(R.id.action_displayTaskFragment_to_taskListFragment)

        }
    }
}