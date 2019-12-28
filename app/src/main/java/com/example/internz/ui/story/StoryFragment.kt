
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryData
import com.example.internz.ui.home.HomeAdapter
import com.example.internz.ui.story.DetailStoryFragment
import kotlinx.android.synthetic.*

class StoryFragment : Fragment() {

    private val adapter: StoryAdapter = StoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        storyFunction(view)
        return view
    }

    private fun storyFunction(view : View) {
        val spinner = view.findViewById<Spinner>(R.id.spinnerStory)
        val arrayAdapter = ArrayAdapter.createFromResource(view.context, R.array.spinner, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initStoryList()
    }

    private fun initStoryList() {



    }

    private inner class StoryAdapter() : RecyclerView.Adapter<StoryViewHolder>() {
        var data = listOf<StoryData>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rv_story_item, parent, false)
            return StoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    private inner class StoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val view : View = view.findViewById(R.id.rvStoryItem)

        private val title : TextView = view.findViewById(R.id.txtStoryTitle)
        private val nickname : TextView = view.findViewById(R.id.txtStoryNick)
        private val date : TextView = view.findViewById(R.id.txtStoryDate)

        fun bind(data : StoryData) {
            title.text = data.title
            nickname.text = data.nickname
            date.text = data.date

            //DetailStoryFragment 보여주기
            view.setOnClickListener {
                //TODO! DetailStoryFragment 액티비티로 교체 요구
//            MainHelper.getFT().hide(StoryFragment()).show(DetailStoryFragment()).commit()
                activity?.supportFragmentManager?.let {
                    it.beginTransaction().apply {
                        show(DetailStoryFragment())
                    }.commit()
                }
            }
        }
    }
}