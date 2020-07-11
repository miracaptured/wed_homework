package com.example.wed_homework

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.activity_filter_.*
import kotlinx.android.synthetic.main.item_filter.*

class Filter_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_)

        var yearsAdapter = ListDelegationAdapter(yearsAdapterDelegate())

        val intent = intent
        val bundleGet:IntArray? = intent.getIntArrayExtra("my years")
        var bundleList = bundleGet?.asList()?.map{ BundleGet(it)
        }

        var distList: List<BundleGet>? = bundleList?.distinct()

        if (bundleGet != null) {
            yearsAdapter.items = distList
            with(checkbox_list) {
                adapter = yearsAdapter
                layoutManager = LinearLayoutManager(context)
            }

            setButton.setOnClickListener() {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            if  (check_all.isChecked) {
                item_check.setChecked(true) }




        }
    }

    private fun yearsAdapterDelegate() = adapterDelegateLayoutContainer<BundleGet, BundleGet>(R.layout.item_filter) {
        bind {

            if (item.year == 0) {  skillExp.text = " <1 year"}
            if (item.year == 1 ) {  skillExp.text = " 1 year"} else {
            skillExp.text = item.year.toString() + " years" }

        }
    }
}