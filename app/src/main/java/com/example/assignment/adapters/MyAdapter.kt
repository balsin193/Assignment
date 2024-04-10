package com.example.assignment.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.Model.Data
import com.example.assignment.View.RepoDetailsActivity
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val context: Context, private val dataList: List<Data>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val circleImageView: CircleImageView = itemView.findViewById(R.id.circleImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val contributor: TextView = itemView.findViewById(R.id.contributor)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val link: TextView = itemView.findViewById(R.id.link)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.circleImageView.setImageResource(currentItem.imageResource)
        holder.nameTextView.text = currentItem.name
        holder.contributor.text = currentItem.contributor
        holder.addressTextView.text = currentItem.description
        holder.link.text = "Link: " + currentItem.link

        holder.itemView.setOnClickListener {
            val clickedItem = dataList[position]
//            val json = Gson().toJson(clickedItem)

            // Start new activity and pass JSON data
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra("name", currentItem.name)
            intent.putExtra("description", currentItem.description)
            intent.putExtra("link", currentItem.link)
            intent.putExtra("image", currentItem.imageResource)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size
}
