package com.example.recipereader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipereader.databinding.FragmentDisplayTaskBinding

// TODO: Rename parameter arguments, choose names that match
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
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the task from the arguments and display the task details
        val task = args.task
        binding.displayTitle.text = task.title
        binding.displayDescription.text = task.description
        // select the drawable resource for the image view based on the importance of the task
//        val importanceDrawable = when(task.importance){
//            IMPORTANCE.LOW -> R.drawable.circle_drawable_green
//            IMPORTANCE.NORMAL -> R.drawable.circle_drawable_orange
//            IMPORTANCE.HIGH -> R.drawable.circle_drawable_red
//        }
//        binding.displayImportance.setImageResource(importanceDrawable)

//        binding.displayEdit.setOnClickListener {
//            val actionEditTask =
//                DisplayTaskFragmentDirections.actionDisplayTaskFragmentToAddTaskFragment()
//            with(actionEditTask) {
//                taskToEdit = task
//                edit = true
//            }
//            findNavController().navigate(actionEditTask)
//        }
    }
}