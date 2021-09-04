package com.example.trainapplication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainapplication.databinding.ActivityMainBinding

class TrainActivity : AppCompatActivity() {
    private var searchInput = ""

    private lateinit var binding: ActivityMainBinding

    private lateinit var trainPresenter: TrainPresenter
    private lateinit var trainViewModel: TrainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TrainViewModel::class.java)

        trainPresenter = TrainPresenter(trainViewModel)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
        initializeViewModel()
    }

    private fun initializeViewModel() {
        trainViewModel.trainSearchResult.observe(this) {
            updateRecyclerView(it)
        }
    }

    private fun updateRecyclerView(list: ArrayList<TrainModel>) {
        val trainInventoryAdapter = TrainInventoryAdapter(list)
        with(binding.recyclerviewSearchResult) {
            adapter = trainInventoryAdapter
            this.itemAnimator = null
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context)
        }

        trainInventoryAdapter.setOnItemClickCallback(object :
            TrainInventoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TrainModel) {
                // CLICKED
            }
        })
    }

    private fun initializeViews() {
        with(binding) {
            edittextSearchTrain.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("TEXT CHANGED", p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {}

            })

            ArrayAdapter.createFromResource(
                this@TrainActivity,
                R.array.sort_type,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerSortSearchResult.adapter = adapter
            }

            spinnerSortSearchResult.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>?,
                        view: View,
                        i: Int,
                        l: Long
                    ) {
                        // Your code here
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>?) {
                        return
                    }
                }
        }
    }
}