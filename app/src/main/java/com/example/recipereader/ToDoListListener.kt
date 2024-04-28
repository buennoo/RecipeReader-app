package com.example.recipereader

interface ToDoListListener {
    fun onTaskClick(taskPosition: Int)
    fun onTaskLongClick(taskPosition: Int)
}