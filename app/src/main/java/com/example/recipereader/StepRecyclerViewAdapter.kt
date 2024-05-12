package com.example.recipereader

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipereader.data.Step
import com.example.recipereader.databinding.FragmentSingleStepBinding

class StepRecyclerViewAdapter(
    private val values: List<Step>,
    private val eventListener: DisplayRecipeFragment,
    private var lastClickedPos: Int? = null
) : RecyclerView.Adapter<StepRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentSingleStepBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = values[position]
        holder.contentView.text = step.stepInfo

        val stepClicked = holder.stepItem.background

        if(position == lastClickedPos) {
            stepClicked.setTint(Color.parseColor("#E3CFE5"))
        }
        else {
            stepClicked.setTint(Color.parseColor("#FDE6FF"))
        }

        holder.stepItem.setOnClickListener {
            val previousClicked = lastClickedPos
            lastClickedPos = position

            if(previousClicked != null) {
                notifyItemChanged(previousClicked)
            }
            // Refresh element (render)
            notifyItemChanged(position)
        }


        holder.itemContainer.setOnClickListener {
            //eventListener.onTaskClick(position)
        }

        holder.itemContainer.setOnLongClickListener {
            eventListener.onTaskLongClick(position)
            true // return true to indicate the event was handled
        }
    }

    override fun getItemCount(): Int = values.size

    class ViewHolder(binding: FragmentSingleStepBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.step
        val itemContainer: View = binding.root
        val stepItem: TextView = binding.step
        override fun toString(): String {
            return "${super.toString()} '${contentView.text}'"
        }
    }
}
