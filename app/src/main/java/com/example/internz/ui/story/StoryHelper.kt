package com.example.internz.ui.story

object StoryHelper {
    private lateinit var storyIndex : String
    private lateinit var userIndex : String

    fun getStoryIndex() : String {
        return storyIndex
    }

    fun setStoryIndex(storyIndex : String) {
        this.storyIndex = storyIndex
    }


    fun getUserIndex() : String {
        return userIndex
    }

    fun setUserIndex(userIndex : String) {
        this.userIndex = userIndex
    }
}