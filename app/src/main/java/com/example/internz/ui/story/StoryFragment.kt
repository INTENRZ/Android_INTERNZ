
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.internz.R
import com.example.internz.ui.home.MainHomeAdapter
import com.example.internz.ui.story.StoryViewModel

class StoryFragment : Fragment() {

    private lateinit var storyViewModel: StoryViewModel

    private lateinit var adapter_recomm_profile: MainHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}


//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_main_home, container, false)