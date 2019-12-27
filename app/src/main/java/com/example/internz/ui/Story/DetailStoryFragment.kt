package com.example.internz.ui.Story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.internz.R

class DetailStoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_story, container, false)

        detailStoryFunction(view)
        return view
    }

    private fun detailStoryFunction(view : View) {
        val fragmentManager =
    }
}
