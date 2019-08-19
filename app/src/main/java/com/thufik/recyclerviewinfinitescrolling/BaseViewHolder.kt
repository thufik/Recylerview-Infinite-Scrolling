package com.thufik.recyclerviewinfinitescrolling

import android.support.v7.widget.RecyclerView
import android.view.View

public abstract class BaseViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    var currentPosition : Int = 0
        get() = currentPosition

    protected abstract fun clear()

    public open fun onBind(position : Int){
        currentPosition = position
        clear()
    }
}

