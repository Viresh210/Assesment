package com.android.assessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.assessment.R
import com.android.assessment.models.Venue
import kotlinx.android.synthetic.main.home_rv_item_view.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var data: List<Venue>? = null
    private lateinit var mListener: onItemClickListener

    fun setData(list: List<Venue>) {
        data = list
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_rv_item_view,
                parent,
                false
            ),
            mListener
        )
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    class HomeViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Venue?) {
            itemView.tv_home_item_title.text = item?.name
            itemView.tv_home_item_body.text = item?.location?.address

        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun filterList(filterdNames: List<Venue>?) {
        this.data = filterdNames
        notifyDataSetChanged()
    }

}