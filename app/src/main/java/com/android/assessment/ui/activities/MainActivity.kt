package com.android.assessment.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.assessment.ui.adapter.HomeAdapter
import com.android.assessment.R
import com.android.assessment.ui.viewmodels.VenueViewModel
import com.android.assessment.models.Venue
import com.android.assessment.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<VenueViewModel>()
    private lateinit var adapter: HomeAdapter
    private var searchData: List<Venue>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.main_search)

        viewModel.getData(
            city = Constants.city,
            date = Constants.date
        )

        viewModel.data
            .observe(this, Observer {

                it.let {
                    initAdapter()
                    rv_home.visibility = View.VISIBLE
                    if (it != null) {
                        adapter.setData(it.response.venues)
                        searchData = it.response.venues
                    }
                }
            })

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                filter(s.toString().toLowerCase());
            }
        })
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun filter(text: String) {
        val searchedItems: ArrayList<Venue> = ArrayList()
        for (s in this.searchData!!) {
            println(searchData)
            if (s.name.toLowerCase().contains(text)) {
                searchedItems.add(s)
            }
        }
        adapter.filterList(searchedItems)
    }

    private fun initAdapter() {
        adapter = HomeAdapter()
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
        adapter.setOnItemClickListener(object :
            HomeAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(
                    this@MainActivity,
                    DetailActivity::class.java
                )
                intent.putExtra("VenueID", searchData?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

}


