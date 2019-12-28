
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryDataTemporal
import com.example.internz.ui.story.DetailStoryActivity
import com.example.internz.ui.story.StoryAdapter

class StoryFragment : Fragment() {


    private lateinit var rvStory: RecyclerView
    private lateinit var storyAdapter : StoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_story, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeStory()

    }


    fun makeStory() {
        rvStory = view!!.findViewById(R.id.rvStory)
        storyAdapter = StoryAdapter(context!!)

        rvStory.apply {
            adapter = storyAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        storyAdapter.data = StoryDataTemporal().getStory()
        storyAdapter.notifyDataSetChanged()

        storyAdapter.setItemClickListener(object : StoryAdapter.ItemClickListener {
            override fun onClick(view: View, position : Int) {
                val intent = Intent(this@StoryFragment.context, DetailStoryActivity::class.java)
                startActivity(intent)
            }
        })
    }
}


//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_main_home, container, false)