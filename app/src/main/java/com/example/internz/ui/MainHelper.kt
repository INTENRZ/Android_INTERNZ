package com.example.internz.ui

import androidx.fragment.app.FragmentTransaction

object MainHelper {
    private lateinit var fragmentTransaction : FragmentTransaction

    fun setFT(fragmentTransaction: FragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction
    }

    fun getFT() : FragmentTransaction {
        return fragmentTransaction
    }
}