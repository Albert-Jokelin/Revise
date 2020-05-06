package com.albertjokelin.revise.ui.maths


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertjokelin.revise.MyWebActivity
import com.albertjokelin.revise.R
import kotlinx.android.synthetic.main.content_row.view.*

class MathsAdapter(val feed: HomeFeed): RecyclerView.Adapter<CustomViewHolder> (){

    // Number of items
    override fun getItemCount(): Int {
        return feed.materials.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // Creating a view
        val layoutInflater= LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.content_row, parent, false)
        return  CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val material_var = feed.materials[position]
        holder.view.textView2?.text = material_var.name
    }
}

class CustomViewHolder(val view: View, var lesson: Materials? = null): RecyclerView.ViewHolder (view){

    companion object{
        const val LESSON_LINK_KEY = "LESSON_LINK"
    }
    init{
        view.setOnClickListener {

            val intent = Intent(view.context, MyWebActivity::class.java)
            intent.putExtra(LESSON_LINK_KEY, lesson?.myLink)

            view.context.startActivity(intent)
        }
    }
}