package com.example.recipereader


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipereader.databinding.FragmentTaskItemBinding

class MyRecipeRecyclerViewAdapter(
    private val values: List <Recipe>,
    private val  eventListener: ToDoListListener
): RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder>()
{
    // onCreateViewHolder creates the ViewHolder objects
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyRecipeRecyclerViewAdapter.ViewHolder {
        // create the view holders for the recycler view items
        // no data is bound to the views yet
        return ViewHolder(
            FragmentTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // The ViewHolder class is a container for the views in the recycler view item
    class ViewHolder(binding: FragmentTaskItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val contentView: TextView = binding.content
        val itemContainer: View = binding.root
        val imageBackground: ImageView = binding.itemImg
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun onBindViewHolder(holder: MyRecipeRecyclerViewAdapter.ViewHolder, position: Int) {
        val task = values[position]

        holder.imageBackground.background.setTint(Color.parseColor("#FDE6FF"))
        holder.contentView.background.setTint(Color.parseColor("#FDE6FF"))

        holder.contentView.text = task.title

        holder.itemContainer.setOnClickListener {
            eventListener.onTaskClick(position)
        }

        holder.itemContainer.setOnLongClickListener {
            eventListener.onTaskLongClick(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}




