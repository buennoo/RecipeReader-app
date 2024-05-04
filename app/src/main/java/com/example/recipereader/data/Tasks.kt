package com.example.recipereader.data


import com.example.recipereader.Task

object Tasks {
    val list: MutableList<Task> = ArrayList()
    private val COUNT = 5

    init {
        for (i in 1..COUNT) {
            addTask(createPlaceholderTask(i))
        }
    }

    fun addTask(task: Task){
        list.add(task)
    }

    private fun createPlaceholderTask(position: Int): Task {
        return Task(position.toString(), "Task $position", makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Task: ").append(position)
        for(i in 0 .. position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun updateTask(oldTask: Task?, newTask: Task) {
        oldTask?.let { old ->
            val indexOfOld = list.indexOf(old)
            if(indexOfOld != -1) {
                list[indexOfOld] = newTask
            }
        }
    }
}