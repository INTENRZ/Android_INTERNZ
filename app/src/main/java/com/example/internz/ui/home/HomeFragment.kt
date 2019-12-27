package com.example.internz.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.RecommData

class HomeFragment : Fragment() {

    private lateinit var rv_recomm_profile: RecyclerView
    private lateinit var adapter_recomm_profile: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
    }

    /* home 화면 "추천 프로필" 리사이클러뷰 init */
    fun rvInit(){
        rv_recomm_profile = activity!!.findViewById(R.id.rv_home_recommProfile)
        adapter_recomm_profile = HomeAdapter(context!!)
        rv_recomm_profile.adapter = adapter_recomm_profile
        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter_recomm_profile.data = listOf(
            RecommData(
                img_thumb = "ddd",
                txt_name = "김초희",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "최은지",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "지현이",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "윤주연",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            )
        )
        adapter_recomm_profile.notifyDataSetChanged()
    }
}