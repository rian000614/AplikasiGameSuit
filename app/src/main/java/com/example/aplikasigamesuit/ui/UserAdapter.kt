package com.example.aplikasigamesuit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.gamedesignbinarcp6.database.Users
import com.example.aplikasigamesuit.R
import com.example.aplikasigamesuit.databinding.UserItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHoler>() {

    private val mData = ArrayList<Users>()

    fun setData(items: ArrayList<Users>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHoler(view)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ViewHoler(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)
        fun bind(users: Users) {
            binding.tvName.text = users.name
            binding.tvFlName.text = users.fullname
            binding.tvNumber.text = users.number
        }
    }
}