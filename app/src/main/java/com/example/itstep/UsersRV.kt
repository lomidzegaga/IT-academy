package com.example.itstep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itstep.databinding.SingleItemBinding

class UsersRV(): RecyclerView.Adapter<UsersRV.UserViewHolder>() {

    var itemCallback: ((String) -> Unit)? = null

    private var noteList = emptyList<Note>()

    inner class UserViewHolder(
        private val binding: SingleItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)

            binding.apply {
                titleTV.text = item.title
                noteTV.text = item.note

                root.setOnClickListener {
                    itemCallback?.invoke(item.title)
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

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    private fun getItem(position: Int): Note {
        return noteList[position]
    }

    fun updateList(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }
}