package com.example.kotlinfishermenhandbook

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(listArray: ArrayList<ListItem>, context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var listArrayR = listArray
    private var contextR = context


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var tvTitle: TextView = view.findViewById(R.id.tvTitleShyka)
        private var tvContent: TextView = view.findViewById(R.id.tvContentItem)
        private var im: ImageView = view.findViewById(R.id.ivShuka)


        @SuppressLint("SetTextI18n")
        fun bind(listItem: ListItem, context: Context) {
            tvTitle.text = listItem.titleText
            tvContent.text = listItem.contentText.substring(0, 100) + "..."
            im.setImageResource(listItem.image_id)
            itemView.setOnClickListener {
                Toast.makeText(context, "You clicked: ${tvTitle.text}! ", Toast.LENGTH_SHORT).show()
                val i = Intent(context, ContentActivity::class.java).apply {
                    putExtra("Title", tvTitle.text.toString())
                    putExtra("Content", listItem.contentText)
                    putExtra("Image", listItem.image_id)
                }
                context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listItem = listArrayR[position]
        holder.bind(listItem, contextR)
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listArray: List<ListItem>) {
        listArrayR.clear()
        listArrayR.addAll(listArray)
        notifyDataSetChanged()
    }
}