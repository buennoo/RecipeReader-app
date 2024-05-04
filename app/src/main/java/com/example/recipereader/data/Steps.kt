package com.example.recipereader.data

import com.example.recipereader.Task

object Steps {
    val list: MutableList<Step> = ArrayList()
    private val COUNT = 10

    init {
        for (i in 1..COUNT) {
            addStep(createPlaceholderStep(i))
        }
    }

    fun addStep(step: Step) {
        list.add(step)
    }

    private fun createPlaceholderStep(position: Int): Step {
        return Step(position.toString(), "Step $position", showSteps(position))
    }

    private fun showSteps(position: Int): String {
        val builder = StringBuilder()
        for(i in 0 .. position) {
            builder.append("\nStep")
        }
        return builder.toString()
    }
}
