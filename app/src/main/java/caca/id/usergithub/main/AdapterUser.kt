package caca.id.usergithub.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import caca.id.usergithub.databinding.UseritemBinding
import caca.id.usergithub.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class AdapterUser : RecyclerView.Adapter<AdapterUser.UserViewHolder>() {
    private val listuser = ArrayList<User>()
    private var ClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (ClickCallback : OnItemClickCallback){
        this.ClickCallback = ClickCallback
    }

    fun setList (user: ArrayList<User>){
        listuser.clear()
        listuser.addAll(user)
        notifyDataSetChanged()
    }
    inner class UserViewHolder (val binding: UseritemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.root.setOnClickListener{
                ClickCallback?.OnItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(imgItemPhoto)
                tvItemUsername.text = user.login
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UseritemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listuser[position])
    }

    override fun getItemCount(): Int = listuser.size
    interface OnItemClickCallback {
        fun OnItemClicked (data : User)
    }
}