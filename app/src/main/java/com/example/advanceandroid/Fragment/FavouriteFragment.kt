package com.example.advanceandroid.Fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.room.Room
import com.example.advanceandroid.Adapter.DashboardRecyclerAdapter
import com.example.advanceandroid.Adapter.FavouriteRecyclerAdapter
import com.example.advanceandroid.R
import com.example.advanceandroid.database.BookDatabase
import com.example.advanceandroid.database.BookEntity
import com.example.advanceandroid.model.Book


class FavouriteFragment : Fragment() {
    lateinit var recyclerFavourite : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var progressLayout : RelativeLayout
    lateinit var progressBar : ProgressBar
    lateinit var recyclerAdapter: FavouriteRecyclerAdapter
    var dbBookList = listOf<BookEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        recyclerFavourite = view.findViewById(R.id.recyclerFavourite)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)
        progressLayout.visibility = View.VISIBLE

        layoutManager = GridLayoutManager(activity as Context,2)
        dbBookList = RetrieveFavourites(activity as Context).execute().get()

        if(dbBookList != null && activity != null){
            progressLayout.visibility = View.GONE
            recyclerAdapter = FavouriteRecyclerAdapter(activity as Context,dbBookList)
            recyclerFavourite.adapter = recyclerAdapter
            recyclerFavourite.layoutManager = layoutManager
        }else {
            Toast.makeText(activity as Context, "Some unexpected error occurred!!", Toast.LENGTH_SHORT).show()
        }
        return view
    }



    class RetrieveFavourites(val context: Context): AsyncTask<Void,Void,List<BookEntity>>(){

        override fun doInBackground(vararg params: Void?): List<BookEntity> {
            val db = Room.databaseBuilder(context,BookDatabase::class.java,"books-db").build()
            return db.bookDao().getAllBooks()
        }
    }


}
