package Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navtest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_home, container, false)
        getNews(view)
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = NewsDatabase.getDatabase(requireContext())
        //observer Added to chk the data inserted in database in logcat
        database.newsTableDao().getNews().observe(viewLifecycleOwner, Observer {
            Log.d("HomeFragment", "onViewCreated:"+it)
        })
    }
    private fun getNews(view:View){
        val recyclerViewOne: RecyclerView = view.findViewById(R.id.newsList)
        val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News>{

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("call", "onFailure: Error in fetching news", t)

            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news= response.body()
                if (news!=null)
                {
                    Log.d("call",news.toString())
                    adapter = NewsAdapter(requireContext(), news.articles)
                    recyclerViewOne.adapter=adapter
                    recyclerViewOne.adapter = adapter
                    recyclerViewOne.layoutManager = LinearLayoutManager(requireContext())
                }

            }
        })
    }


}//