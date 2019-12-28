package com.example.internz.ui.Story

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.example.internz.R
import com.example.internz.feature.comment.CommentActivity
import kotlinx.android.synthetic.main.fragment_detail_story.*

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
        //TODO! 기능 추가

        view.findViewById<ImageView>(R.id.imgDetailComment).setOnClickListener {
            view.context.startActivity(Intent(view.context, CommentActivity::class.java))
        }
    }
}
