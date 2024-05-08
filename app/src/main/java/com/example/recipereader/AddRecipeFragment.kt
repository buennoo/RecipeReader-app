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
import com.example.recipereader.data.Recipes
import com.example.recipereader.databinding.FragmentAddTaskBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    val args: AddRecipeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.titleInput.setText(args.taskToEdit?.title)
//        binding.descriptionInput.setText(args.taskToEdit?.description)
        return binding.root
    }

    private fun handleDesc(description: String): Steps {
        val stepsObject = Steps()
        val steps = description.split(";").map { it.trim() }

        // with index tworzy pare (index, stepContent)
        for ((index, stepInfo) in steps.withIndex()) {
            val splitStepInfo = stepInfo.split(":", limit = 2)

            val changedStep = if (splitStepInfo.size == 2) {
                "${splitStepInfo[0]}\n${splitStepInfo[1]}"
            } else {
                stepInfo
            }

            val step = Step(
                id = index.toString(),
                stepInfo = changedStep,
            )
            println("step: $step")
            stepsObject.addStep(step)
        }
        return stepsObject
    }

    private fun handleIngredients(description: String): Triple<String, String, String> {
        val sections = description.split(";")
        val allIngredients = mutableListOf<String>()

        for (section in sections) {
            if (!section.contains(":")){
                continue
            }
            else {
                val parts = section.split(":", limit = 2)
                if (parts.isNotEmpty()) {
                    val ingredientsPart = parts[0].trim()
                    if (ingredientsPart.isNotEmpty() && !ingredientsPart.contains(",")) {
                        allIngredients.add(ingredientsPart)
                    }
                    else if (ingredientsPart.isNotEmpty()) {
                        val newParts = ingredientsPart.split(",")
                        allIngredients.addAll(newParts)
                    }
                }
            }
        }

        val numOfIngredients = allIngredients.size.toString()
        val numOfSteps = sections.size.toString()
        val ingredients = allIngredients.joinToString(", ")

        return Triple(ingredients, numOfIngredients, numOfSteps)
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
        val (ingredients, numOfIngredients, numberOfSteps) = handleIngredients(description)

        // Handle missing EditText input
        if(title.isEmpty())
            title = "Recipe: " + stepsList.list[0].stepInfo
        if(description.isEmpty())
            description = "No recipe here"
        // Create a new Task item based on input values
        val taskItem = Recipe(
            {title + description}.hashCode().toString(),
            title,
            ingredients,
            numOfIngredients,
            numberOfSteps,
            stepsList
        )
        if(!args.edit) {
            Recipes.addTask(taskItem)
        }else {
            Recipes.updateTask(oldTask = args.taskToEdit, newTask = taskItem)
        }
        // Hide the software keyboard with InputMethodManager
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        findNavController().popBackStack(R.id.taskListFragment, false)
    }

}
