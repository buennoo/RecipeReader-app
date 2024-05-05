package com.example.recipereader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipereader.data.Step
import com.example.recipereader.data.Steps
import com.example.recipereader.databinding.FragmentDisplayTaskBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayTaskFragment : Fragment() {
    val args: DisplayTaskFragmentArgs by navArgs()
    private lateinit var binding: FragmentDisplayTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    // Inflate the layout for this fragment
        binding = FragmentDisplayTaskBinding.inflate(inflater, container, false)
        with(binding.stepsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = StepRecyclerViewAdapter(
                Steps.list
//                this@DisplayTaskFragment
            ) // adapter is responsible for displaying the data
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the task from the arguments and display the task details
        val task = args.task
        binding.displayTitle.text = task.title
//        binding.displayDescription.text = task.steps

        if (task.steps != null) {
            val stepsDescription = task.steps.list.joinToString(separator = "\n") { step : Step ->
                "${step.id}. ${step.stepInfo}"
            }
            binding.displayDescription.text = stepsDescription
        } else {
            binding.displayDescription.text = "Brak kroków"
        }


//        binding.displayEdit.setOnClickListener {
//            val actionEditTask =
//                DisplayTaskFragmentDirections.actionDisplayTaskFragmentToAddTaskFragment()
//            with(actionEditTask) {
//                taskToEdit = task
//                edit = true
//            }
//            findNavController().navigate(actionEditTask)
//        }

        // added
        binding.displayGoBack.setOnClickListener {
            // Navigate to the AddTaskFragment with action id
            findNavController().navigate(R.id.action_displayTaskFragment_to_taskListFragment)

        }
    }
}