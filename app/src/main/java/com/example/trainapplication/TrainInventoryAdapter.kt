package com.example.trainapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainapplication.databinding.ItemRowTrainBinding

class TrainInventoryAdapter(private val trainInventory: ArrayList<TrainModel>) :
    RecyclerView.Adapter<TrainInventoryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_train, viewGroup, false)
        val holder = ListViewHolder(view)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(trainInventory[holder.absoluteAdapterPosition]) }
        return holder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val user = listUser[position]
//        Glide.with(holder.itemView.context)
//            .load(user.avatar)
//            .into(holder.binding.singleImgUser)
//        holder.binding.singleUsernameUser.text = user.username
//        holder.binding.singleRepoUser.text = user.public_repos
//        holder.binding.singleTvFollowers.text = user.followers
//        holder.binding.singleTvFollowing.text = user.following
    }

    override fun getItemCount(): Int {
        return trainInventory.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var binding = ItemRowTrainBinding.bind(itemView)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TrainModel)
    }
}