package com.example.trainapplication

import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrainPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    var viewModel = TrainViewModel()

    private var trainInventoryData = GenerateDataHelper.getData()
    private var domain: SortSearchDomain? = trainInventoryData?.let { SortSearchDomain(it) }

    private lateinit var presenter: TrainPresenter
    private lateinit var provider: Provider

    @Before
    fun setUp() {
        provider = Provider()
        presenter = TrainPresenter(viewModel)
    }

    @Test
    fun `getSearchResult output in viewmodel must be same with the one from domain`() {
        // Sample search query = tasa
        val query = "tasa"

        // Expected : Search result from SortSearchDomain
        val expected = domain?.searchAndSort(query)

        // Run getSearchResult method from presenter
        presenter.getSearchResult(query)

        // Observe viewmodel trainSearchResult value change
        val observer = TestObserver<List<TrainModel>>()
        viewModel.trainSearchResult.subscribe(observer)
        observer.assertNoErrors()
        observer.assertValueCount(1)

        assertThat(observer.values()[0], `is`(expected))
    }

    @Test
    fun setSortType() {

    }

    @Test
    fun `getSortType output in viewmodel must be same with the one from provider`() {

        // Expected : Sort type from database (provider)
//        val expected = provider.getSortType()

        // Run getSortType method from presenter
        presenter.getSortType()


    }
}