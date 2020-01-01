package com.example.internz.ui.story

object StoryHelper {
    private lateinit var storyIndex : String

    fun getStoryIndex() : String {
        return storyIndex
    }

    fun setStoryIndex(storyIndex : String) {
        this.storyIndex = storyIndex
    }
}