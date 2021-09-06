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
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class TrainActivity : AppCompatActivity() {

    private var searchQuery = ""
    private var tmpSearchQuery = ""
    private var compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivityMainBinding
    private lateinit var trainPresenter: TrainPresenter
    private lateinit var trainViewModel: TrainViewModel
    private lateinit var searchResultDisposable: Disposable
    private lateinit var sortTypeDisposable: Disposable

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

        searchResultDisposable = trainViewModel.trainSearchResult.subscribeOn(Schedulers.io())
            .subscribe {
                updateRecyclerView(it)
            }

        compositeDisposable.add(searchResultDisposable)
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

            sortTypeDisposable = trainViewModel.sortType.subscribeOn(Schedulers.io())
                .subscribe {
                    spinnerSortSearchResult.setSelection(it.type)
                }

            compositeDisposable.add(sortTypeDisposable)

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
                if (searchQuery != tmpSearchQuery) {
                    trainPresenter.getSearchResult(searchQuery)
                }
                tmpSearchQuery = searchQuery
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }
}