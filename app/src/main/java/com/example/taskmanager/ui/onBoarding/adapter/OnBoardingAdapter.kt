package com.example.taskmanager.ui.onBoarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemBoardingBinding
import com.example.taskmanager.ui.model.OnBoard
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val context: Context, private val onClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private var data = arrayListOf(
        OnBoard(R.raw.task_anim_1,"Tittle 1"),
        OnBoard(R.raw.task_anim_1,"Tittle 2"),
        OnBoard(R.raw.task_anim_1,"Tittle 3"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


    inner class OnBoardingViewHolder(private val binding: ItemBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            onBoard.anim?.let { binding.ivBoarding.setAnimation(it ) }
            binding.tvTitle.text = onBoard.title
            if (adapterPosition == data.lastIndex) binding.tvSkip.text =
                context.getString(R.string.next) else context.getString(R.string.skip)
            binding.tvSkip.setOnClickListener {
                onClick()
            }
        }
    }
}