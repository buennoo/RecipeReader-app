package com.example.recipereader.data

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
        return Step(
            id = position.toString(),
            stepInfo = generateStepsList(position).toString(),
        )
    }

    fun generateStepsList(position: Int): List<String> {
        val stepsList = mutableListOf<String>()
        for (i in 1..position) {
            stepsList.add("Step $i")
        }
        return stepsList
    }

}
