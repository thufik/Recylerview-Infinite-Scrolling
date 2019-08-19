package com.thufik.recyclerviewinfinitescrolling

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

public abstract class PaginationScrollListener(var layoutManager : LinearLayoutManager) : RecyclerView.OnScrollListener() {

    val PAGE_SIZE = 10



    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibibleItemCount = layoutManager.childCount

        val totalItemCount = layoutManager.itemCount

        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()){
            if((visibibleItemCount + firstVisiblePosition) >= totalItemCount && firstVisiblePosition >= 0 && totalItemCount >= PAGE_SIZE){
                loadMoreItems()
            }
        }
    }

    public abstract fun isLoading() : Boolean

    public abstract fun isLastPage() : Boolean

    public abstract fun loadMoreItems()

}