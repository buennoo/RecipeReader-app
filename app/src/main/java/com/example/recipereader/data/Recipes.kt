package com.example.recipereader.data
import com.example.recipereader.data.Recipe

object Recipes {
    val list: MutableList<Recipe> = ArrayList()
    private val COUNT = 10

    init {
        for (i in 1..COUNT) {
            addRecipe(createPlaceholderRecipe(i))
        }
    }

    fun addRecipe(recipe: Recipe) {
        list.add(recipe)
    }

    private fun createPlaceholderRecipe(position: Int): Recipe {
        return Recipe(
            position.toString(),
            "Recipe $position",
            listOf("Ingredient 1 for Recipe $position", "Ingredient 2 for Recipe $position"),
            listOf("Step 1 for Recipe $position", "Step 2 for Recipe $position")
        )
    }

    fun updateRecipe(oldRecipe: Recipe?, newRecipe: Recipe) {
        oldRecipe?.let { old ->
            val indexOfOld = list.indexOf(old)
            if (indexOfOld != -1) {
                list[indexOfOld] = newRecipe
            }
        }
    }

    fun getRecipeById(id: String): Recipe? {
        return list.find { it.id == id }
    }
}
