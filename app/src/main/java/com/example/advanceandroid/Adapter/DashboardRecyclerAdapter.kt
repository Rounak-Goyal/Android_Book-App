package com.example.advanceandroid.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
//import android.widget.Toast
import com.example.advanceandroid.Activity.DescriptionActivity
import com.example.advanceandroid.R
import com.example.advanceandroid.model.Book
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Book>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.textBookName.text = book.bookName
        holder.textBookAuthor.text = book.bookAuthor
        holder.textBookCost.text = book.bookPrice
        holder.textBookRating.text = book.bookRating
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)
        //holder.imgBookImage.setImageResource(book.bookImage) <<-----not used--


        holder.llContent.setOnClickListener{
            val intent = Intent(context,DescriptionActivity::class.java)
            intent.putExtra("book_id",book.bookId)
            context.startActivity(intent)
        }
    }


    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textBookName : TextView = view.findViewById(R.id.txtBookName)
        val textBookAuthor : TextView = view.findViewById(R.id.txtBookAuthor)
        val textBookCost : TextView = view.findViewById(R.id.txtBookCost)
        val textBookRating : TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage : ImageView = view.findViewById(R.id.imgBookImage)
        val llContent : LinearLayout = view.findViewById(R.id.llContent)
    }


}