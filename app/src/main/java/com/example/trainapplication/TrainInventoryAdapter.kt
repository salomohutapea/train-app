package com.example.trainapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainapplication.databinding.ItemRowTrainBinding
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

class TrainInventoryAdapter(private val trainInventory: List<TrainModel>) :
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
        val train = trainInventory[position]
        with(holder.binding) {
            textviewTrainName.text = train.name
            textviewTrainArriveIn.text = train.arrivingIn
            textviewTrainDepartFrom.text = train.departingFrom
            textviewTrainArriveTime.text = train.arrivingTime.substring(11, 16)
            textviewTrainDepartTime.text = train.departingTime.substring(11, 16)
            textviewTrainPrice.text = StringBuilder(
                "IDR " + NumberFormat.getNumberInstance(Locale.getDefault()).format(train.price)
            )
            textviewTrainSeat.text = train.availableSeats.toString()
        }
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