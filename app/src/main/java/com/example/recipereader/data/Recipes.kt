package com.example.recipereader.data


import com.example.recipereader.Recipe

object Recipes {
    val list: MutableList<Recipe> = ArrayList()
    private const val COUNT = 5

    init {
        for (i in 1..COUNT) {
            addTask(createPlaceholderTask(i))
        }
    }

    fun addTask(task: Recipe){
        list.add(task)
    }

    private fun createPlaceholderTask(position: Int): Recipe {
       // return Task(position.toString(), "Task $position", makeDetails(position))
        return Recipe(
            id = position.toString(),
            title = "Task $position",
            ingredients = "none",
            numOfIngredients = "0",
            numOfSteps = "0",
            steps = createStepsForTask(position)
        )
    }

    private fun createStepsForTask(position: Int): Steps {
        val stepsObject = Steps().apply { list.clear() }

        for (i in 0..position) {
            val stepInfo = "$i. Random detail"
            val step = Step(
                id = i.toString(),
                stepInfo = stepInfo
            )
            stepsObject.addStep(step)
            println(step)
        }
        return stepsObject
    }


//    private fun makeDetails(position: Int): String {
//        val builder = StringBuilder()
//        builder.append("Details about Task: ").append(position)
//        for(i in 0 .. position) {
//            builder.append("\nMore details information here.")
//        }
//        return builder.toString()
//    }

    fun updateTask(oldTask: Recipe?, newTask: Recipe) {
        oldTask?.let { old ->
            val indexOfOld = list.indexOf(old)
            if(indexOfOld != -1) {
                list[indexOfOld] = newTask
            }
        }
    }
}