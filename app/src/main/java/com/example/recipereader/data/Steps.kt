package com.example.recipereader.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Steps : Parcelable {
    val list: MutableList<Step> = ArrayList()

    fun addStep(step: Step) {
        list.add(step)
    }

    private fun createPlaceholderStep(position: Int): Step {
        return Step(
            id = position.toString(),
            stepInfo = generateStepsList(position).toString(),
        )
    }

    private fun generateStepsList(position: Int): List<String> {
        val stepsList = mutableListOf<String>()
        for (i in 1..position) {
            stepsList.add("Step $i")
        }
        return stepsList
    }

}
