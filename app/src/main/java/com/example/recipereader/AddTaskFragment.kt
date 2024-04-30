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
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.titleInput.setText(args.taskToEdit?.title)
        binding.descriptionInput.setText(args.taskToEdit?.description)
//        when(args.taskToEdit?.importance){
//            IMPORTANCE.LOW -> binding.lowRadioButton.isChecked =  true
//            IMPORTANCE.NORMAL -> binding.normalRadioButton.isChecked = true
//            IMPORTANCE.HIGH -> binding.normalRadioButton.isChecked = true
//            else -> binding.normalRadioButton.isChecked = true
//        }
        return binding.root
    }

    private fun handleInput(description: String): List <String> {
        val steps = description.split(";").map { it.trim() }

        val bakingInstructions = mutableListOf<String>()

        for (i in steps.indices) {
            val step = steps[i]
            val (ingredientPart, stepPart) = step.split(":", limit = 2).map { it.trim() }


            val stepText = if (stepPart.isNotEmpty()) {
                "${i + 1}. $stepPart: $ingredientPart"
            } else {
                ingredientPart
            }

            if (stepText.isNotEmpty()) {
                bakingInstructions.add(stepText)
            }
        }
        return bakingInstructions
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener { saveTask() }
    }
    private fun saveTask() {
        // Get the values from data fields on the screen
        var title: String = binding.titleInput.text.toString()
        var description: String = binding.descriptionInput.text.toString()


//        val importance = when(binding.importanceGroup.checkedRadioButtonId){
//            R.id.low_radioButton -> IMPORTANCE.LOW
//            R.id.normal_radioButton -> IMPORTANCE.NORMAL
//            R.id.high_radioButton -> IMPORTANCE.HIGH
//            else -> IMPORTANCE.NORMAL
//        }

        //handle input function
        var recipeDesc : String = handleDesc(description)
        description = recipeDesc
        // Handle missing EditText input
        if(title.isEmpty())
            title = "default_title" + "${Tasks.list.size + 1}"
        if(description.isEmpty())
            description = "no description"
        // Create a new Task item based on input values
        val taskItem = Task(
            {title + description}.hashCode().toString(),
            title,
            description,
//            importance
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

    private fun handleDesc(recipeInput: String) : String{
        var recipeInput = "test"
        return recipeInput
    }

}
