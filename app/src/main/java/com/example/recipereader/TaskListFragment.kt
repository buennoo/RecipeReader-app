package com.example.recipereader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipereader.data.Tasks
import com.example.recipereader.databinding.FragmentTaskListBinding
import com.google.android.material.snackbar.Snackbar

class TaskListFragment: Fragment(), ToDoListListener {
    private lateinit var binding: FragmentTaskListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
    // Set the adapter and layout manager for the RecyclerView
    // "with" is a Kotlin extension function that allows you to call
    // the methods of an object without explicitly calling the object itself
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyTaskRecyclerViewAdapter(
                Tasks.list,
                this@TaskListFragment
            ) // adapter is responsible for displaying the data
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the action for the FAB
        binding.addButton.setOnClickListener {
            // Navigate to the AddTaskFragment with action id
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }
    }

    override fun onTaskClick(taskPosition: Int) {
        val actionTaskListFragmentToDisplayTaskFragment =
            TaskListFragmentDirections.actionTaskListFragmentToDisplayTaskFragment(
                Tasks.list[taskPosition])
        findNavController().navigate(actionTaskListFragmentToDisplayTaskFragment)
    }

    override fun onTaskLongClick(taskPosition: Int) {
        showDeleteDialog(taskPosition)
    }

    private fun deleteDialogPositiveClick(taskPosition: Int) {
        Tasks.list.removeAt(taskPosition)
        Snackbar.make(binding.root, "Task deleted", Snackbar.LENGTH_SHORT).show()
        binding.list.adapter?.notifyItemRemoved(taskPosition)
    }

    private fun deleteDialogNegativeClick(taskPosition: Int) {
        Snackbar.make(binding.root, "Deletion cancelled", Snackbar.LENGTH_SHORT)
            .setAction("Redo") {
                showDeleteDialog(taskPosition)
            }.show()
    }

    private fun showDeleteDialog(taskPosition: Int) {
        val taskToDelete = Tasks.list[taskPosition]
        val dialogBuilder = android.app.AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Delete Task?")
        dialogBuilder.setMessage(taskToDelete.title)
            .setPositiveButton("Confirm") { _, _ ->
                deleteDialogPositiveClick(taskPosition)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                deleteDialogNegativeClick(taskPosition)
            }

        val alert = dialogBuilder.create()
        alert.show()
    }
}
