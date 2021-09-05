package com.example.trainapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainapplication.databinding.ActivityMainBinding
import java.util.*

class TrainActivity : AppCompatActivity() {
    private var searchQuery = ""
    private var tmpSearchQuery = ""

    private lateinit var binding: ActivityMainBinding

    private lateinit var trainPresenter: TrainPresenter
    private lateinit var trainViewModel: TrainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViewModel()
        initializePresenter()
        initializeViews()
    }

    private fun initializePresenter() {
        trainPresenter = TrainPresenter(trainViewModel)
        trainPresenter.getSearchResult(searchQuery)
        trainPresenter.getSortType()
    }

    private fun initializeViewModel() {
        trainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TrainViewModel::class.java)

        trainViewModel.trainSearchResult.observe(this) {
            updateRecyclerView(it)
        }
    }

    private fun updateRecyclerView(list: List<TrainModel>) {
        val trainInventoryAdapter = TrainInventoryAdapter(list)
        with(binding.recyclerviewSearchResult) {
            adapter = trainInventoryAdapter
            this.layoutManager = LinearLayoutManager(context)
        }

        trainInventoryAdapter.setOnItemClickCallback(object :
            TrainInventoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TrainModel) {
                Toast.makeText(this@TrainActivity, data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initializeViews() {
        with(binding) {
            edittextSearchTrain.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchQuery = p0.toString()
//                    trainPresenter.getSearchResult(searchQuery)
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

            trainViewModel.sortType.observe(this@TrainActivity) {
                spinnerSortSearchResult.setSelection(it.type)
            }

            spinnerSortSearchResult.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>?,
                        view: View,
                        i: Int,
                        l: Long
                    ) {
                        trainPresenter.setSortType(SortTypeModel(type = i))
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>?) {
                        return
                    }
                }

            cardSortSearchResult.setOnClickListener {
                spinnerSortSearchResult.performClick()
            }

            buttonSearchTrain.setOnClickListener {
                if(searchQuery != tmpSearchQuery) {
                    trainPresenter.getSearchResult(searchQuery)
                }
                tmpSearchQuery = searchQuery
            }
        }
    }
}