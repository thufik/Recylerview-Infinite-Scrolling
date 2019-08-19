package com.thufik.recyclerviewinfinitescrolling

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

public class PostRecyclerAdapter(open var postItems : ArrayList<PostItem>) : RecyclerView.Adapter<BaseViewHolder>() {

    val VIEW_TYPE_LOADING : Int = 0
    val VIEW_TYPE_NORMAL : Int = 1

    private var isLoaderVisible : Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when(viewType){
            VIEW_TYPE_NORMAL -> return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
            VIEW_TYPE_LOADING -> return FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading,parent,false))
        }
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int {
        return postItems.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoaderVisible){
            if (position == postItems.size - 1){
                return VIEW_TYPE_LOADING
            }else{
                return VIEW_TYPE_NORMAL
            }
        }else{
            return VIEW_TYPE_NORMAL
        }
    }

    fun add(response : PostItem){
        postItems.add(response)
        notifyItemInserted(postItems.size - 1)
    }

    fun addAll(postItems : ArrayList<PostItem>){
        for (item in postItems){
            add(item)
        }
    }

    fun addLoading(){
        isLoaderVisible = true
        add(PostItem("","",""))
    }

    fun getItem(position : Int) : PostItem{
        return postItems.get(position)
    }

    private fun remove(item : PostItem){
        val position = postItems.indexOf(item)

        if (position > -1){
            postItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeLoading(){
        isLoaderVisible = false
        val position = postItems.size - 1

        val item = getItem(position)

        if (item != null){
            postItems.remove(item)
            notifyItemRemoved(position)
        }
    }

    fun clear(){
        while (itemCount > 0){
            remove(getItem(0))
        }
    }


     inner class PostViewHolder(view : View) : BaseViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.textViewTitle)

        override fun clear() {

        }

        override  fun  onBind(position : Int){
            super.onBind(position)

            val item = postItems[position]

            title.text = item.title
        }
    }
}