package Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.navtest.*


class Downloaded : Fragment() {
    lateinit var database: NewsDatabase
    lateinit var adapter: DownloadedNewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DownloadedNewsAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_downloaded, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getroomdata(view)

    }

    private fun getroomdata(view: View) {

//        val db: AppDatabase = Room.databaseBuilder(
//            ApplicationProvider.getApplicationContext<Context>(),
//            AppDatabase::class.java, "database-name"
//        ).build()

       // val db: NewsDatabase = Room.databaseBuilder(applicationContext)

        //instantiation of database created

        val instance = Room.databaseBuilder(
            requireContext().applicationContext,
            NewsDatabase::class.java,
            "news_database"
        ).build()

        val userDao = instance.newsTableDao()

        val recyclerViewTwo: RecyclerView = view.findViewById(R.id.downloadedRcv)

        adapter = DownloadedNewsAdapter(requireContext())
        recyclerViewTwo.adapter = adapter
        recyclerViewTwo.layoutManager = LinearLayoutManager(requireContext())

        //val users: LiveData<List<NewsTable>> = userDao.getAllNews()
    userDao.getAllNews().observe(viewLifecycleOwner, Observer{
        adapter.setDataItems(it)
    })
//     adapter = DownloadedNewsAdapter(requireContext())





        //NewsTableDAO.getAllNews()
        //val database = NewsDatabase.getDatabase(requireContext())

        //NewsTableDAO newstabledao = instance.NewsTableDAO()
       //List<NewsTable> newstable = newstabledao.getallnews()

       //recyler view referencing

    }


}