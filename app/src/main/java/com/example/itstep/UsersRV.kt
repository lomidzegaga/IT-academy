package com.example.itstep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itstep.databinding.SingleItemBinding

class UsersRV(
    private val list: MutableList<NameModel>,
    private val iconList: List<Int>
): RecyclerView.Adapter<UsersRV.UserViewHolder>() {

    var itemCallback: ((Int, String) -> Unit)? = null

    inner class UserViewHolder(
        private val binding: SingleItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            val randomIcon = iconList.random()

            binding.apply {
                textView.text = item.name
                imageView.setImageResource(randomIcon)

                root.setOnClickListener {
                    itemCallback?.invoke(randomIcon, item.name)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    private fun getItem(position: Int): NameModel {
        return list[position]
    }

}