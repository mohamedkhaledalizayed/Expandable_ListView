package com.itgds.expandablelistview

import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itgds.expandablelistview.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var foodHeader: ArrayList<String>
    lateinit var results: ArrayList<String>
    lateinit var footItems: HashMap<String, List<String>>
    private lateinit var myExpandableAdapter: MyExpandableAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        foodHeader = ArrayList()
        results = ArrayList()
        footItems = HashMap()

        myExpandableAdapter = MyExpandableAdapter(this, foodHeader, footItems)
        binding.expandedList.setOnGroupExpandListener(object : OnGroupExpandListener {
            var previousGroup = -1
            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup) binding.expandedList.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })
        prepareData()

        binding.expandedList.setOnChildClickListener { expandableListView, view, i, i1, l ->
            Toast.makeText(this@MainActivity, myExpandableAdapter.getChild(i, i1) as String, Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.putExtra("data", myExpandableAdapter.getChild(i, i1) as String)
            setResult(RESULT_OK, intent)
            finish()
            false
        }
        binding.expandedList.setAdapter(myExpandableAdapter)

        binding.expandedList.expandGroup(0, true)
    }

    private fun prepareData() {
        foodHeader.clear()
        footItems.clear()
        results.clear()
        foodHeader.add("محافظة القاهرة")
        foodHeader.add("Recent Locations")
        foodHeader.add("Nearby Locations")
        val fruitItems: MutableList<String> = ArrayList()
        fruitItems.add("● حميات العباسية العنوان 1 شارع امتداد رمسيس بجوار المحطة الدولية")
        fruitItems.add("IKEA")
        fruitItems.add("CFC IKEA")
        fruitItems.add("ON the run")
        val juiceItems: MutableList<String> = ArrayList()
        juiceItems.add("Dandy Mega Mall")
        juiceItems.add("Smart Village")
        juiceItems.add("Xceed")
        juiceItems.add("Smart Village Mosque")
        juiceItems.add("Lacoste")
        juiceItems.add("Bien Sport")
        juiceItems.add("Citizen Watches")
        juiceItems.add("Arabian African Bank")
        juiceItems.add("Creative Hub")
        juiceItems.add("Center Point")
        //        eggItems.add("Lacoste");
//        eggItems.add("Bien Sport");
//        eggItems.add("Citizen Watches");
//        eggItems.add("Arabian African Bank");
//        eggItems.add("Creative Hub");
//        eggItems.add("Center Point");
        footItems.put(foodHeader.get(0), fruitItems)
        footItems.put(foodHeader.get(1), juiceItems)
        footItems.put(foodHeader.get(2), juiceItems)
        myExpandableAdapter.notifyDataSetChanged()
    }
}
