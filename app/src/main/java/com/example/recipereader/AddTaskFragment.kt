package com.example.recipereader

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipereader.data.Step
import com.example.recipereader.data.Steps
import com.example.recipereader.data.Tasks
import com.example.recipereader.databinding.FragmentAddTaskBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    val args: AddTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.titleInput.setText(args.taskToEdit?.title)
//        binding.descriptionInput.setText(args.taskToEdit?.description)
        return binding.root
    }


//    private fun handleDesc(description: String): List <String> {
//        val steps = description.split(";").map { it.trim() }
//        val bakingInstructions = mutableListOf<String>()
//
//        for (i in steps.indices) {
//            val step = steps[i]
//            val parts = step.split(":", limit = 2)
//
//            val ingredientPart: String
//            val stepPart: String
//
//            if (parts.size == 2) {
//                stepPart = parts[0].trim()
//                ingredientPart = parts[1].trim()
//            } else {
//                ingredientPart = step.trim()
//                stepPart = ""
//            }
//
//            val stepText = if (stepPart.isNotEmpty()) {
//                "${i + 1}. $stepPart: $ingredientPart"
//            } else {
//                "${i + 1}. $ingredientPart"
//            }
//
//            if (stepText.isNotEmpty()) {
//                bakingInstructions.add(stepText)
//            }
//        }
//
//        return bakingInstructions
//    }

    private fun handleDesc(description: String): Steps {
        val stepsObject = Steps
        val steps = description.split(";").map { it.trim() }

        for ((index, stepContent) in steps.withIndex()) {
            val step = Step(
                id = index.toString(),
                stepInfo = stepContent,
            )
            println("step: $step")
            stepsObject.addStep(step)
        }
        return stepsObject
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener { saveTask() }
    }
    private fun saveTask() {
        // Get the values from data fields on the screen
        var title: String = binding.titleInput.text.toString()
        var description: String = binding.descriptionInput.text.toString()

        val stepsList = handleDesc(description)

        // Handle missing EditText input
        if(title.isEmpty())
            title = "Recipe: " + "${stepsList}"
        if(description.isEmpty())
            description = "No recipe here"
        // Create a new Task item based on input values
        val taskItem = Task(
            {title + description}.hashCode().toString(),
            title,
            stepsList,
        )
        if(!args.edit) {
            Tasks.addTask(taskItem)
        }else {
            Tasks.updateTask(oldTask = args.taskToEdit, newTask = taskItem)
        }
        // Hide the software keyboard with InputMethodManager
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        findNavController().popBackStack(R.id.taskListFragment, false)
    }

}
