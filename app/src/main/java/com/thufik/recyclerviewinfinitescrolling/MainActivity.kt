package com.thufik.recyclerviewinfinitescrolling

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var isLoading = false
    private var isLastPage = false

    public var PAGE_START : Int = 1

    private var currentPage = PAGE_START

    private var itemCount = 0

    private var mAdapter = PostRecyclerAdapter(ArrayList())

    private var totalPage  = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val linearLayoutManager = LinearLayoutManager(this)

        infiniteRecyclerView.layoutManager = linearLayoutManager
        infiniteRecyclerView.adapter = mAdapter

        preparedListItem()

        infiniteRecyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItems() {
                isLoading = true
                currentPage++

                preparedListItem()
            }

        })
    }

    private fun preparedListItem(){
        var items : ArrayList<PostItem> = ArrayList()

        Handler().postDelayed(object : Runnable {
            override fun run() {
                for (i in 0 until 10){
                    itemCount++
                    val postItem = PostItem("Fake Android Apps With Over 50,000 " + itemCount,"Fake Android Apps With Over 50,000 Installations Found on Google Play, Quick Heal Claims","hue")
                    items.add(postItem)

                }

                if (currentPage != PAGE_START){
                    mAdapter.removeLoading()
                }

                mAdapter.addAll(items)

                if (currentPage < totalPage){
                    mAdapter.addLoading()
                }else{
                    isLastPage = true
                }

                isLoading = false
            }

        },1500)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
