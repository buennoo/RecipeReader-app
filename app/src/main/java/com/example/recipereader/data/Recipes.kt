package com.example.recipereader.data

object Recipes {
    val list: MutableList<Step> = ArrayList()
    private val COUNT = 10

    init {
        for (i in 1..COUNT) {
            addRecipe(createPlaceholderRecipe(i))
        }
    }

    fun addRecipe(recipe: Step) {
        list.add(recipe)
    }

    private fun createPlaceholderRecipe(position: Int): Step {
        return Step(
            position.toString(),
            "Recipe $position",
            listOf("Ingredient 1 for Recipe $position", "Ingredient 2 for Recipe $position"),
            listOf("Step 1 for Recipe $position", "Step 2 for Recipe $position")
        )
    }

    fun updateRecipe(oldRecipe: Step?, newRecipe: Step) {
        oldRecipe?.let { old ->
            val indexOfOld = list.indexOf(old)
            if (indexOfOld != -1) {
                list[indexOfOld] = newRecipe
            }
        }
    }

    fun getRecipeById(id: String): Step? {
        return list.find { it.id == id }
    }
}
