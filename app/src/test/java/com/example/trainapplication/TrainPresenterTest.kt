package com.example.trainapplication

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TrainPresenterTest {

    private var trainInventoryData = GenerateDataHelper.getData()!!

    private var viewModelMock = mockk<TrainViewModel>(relaxed = true)
    private var domainMock = spyk<SortSearchDomain>()
    private var providerMock = spyk<Provider>()

    private lateinit var presenter: TrainPresenter

    private val dummySearchQuery = ""
    private val dummySortType = SortTypeModel(type = 0)

    @Before
    fun setUp() {
        clearAllMocks()

        presenter = TrainPresenter(viewModelMock)
        presenter.sortSearchDomain = domainMock
        presenter.provider = providerMock

        // Mock: everytime Domain's searchAndSort method called, returns trainInventoryData
        every { domainMock.searchAndSort(dummySearchQuery) } returns trainInventoryData
    }


    @Test
    fun `getSearchResult should run domain's searchAndSort and add data to ViewModel`() {

        // Run getSearchResult from Presenter
        presenter.getSearchResult(dummySearchQuery)

        // Verify whether searchAndSort method with parameter query is called
        verify { domainMock.searchAndSort(dummySearchQuery) }

        // Verify whether trainInventoryData is added to trainSearchResult in viewmodel
        verify { viewModelMock.trainSearchResult.onNext(trainInventoryData) }

    }

    @Test
    fun `setSortType should run provider's setSortType and add data to ViewModel`() {

        // Run setSortType from Presenter
        presenter.setSortType(dummySortType)

        // Verify whether setSortType method in provider is called
        verify { providerMock.setSortType(dummySortType) }

        // Verify whether trainInventoryData is added to trainSearchResult in viewmodel
        verify { viewModelMock.trainSearchResult.onNext(trainInventoryData) }

    }

    @Test
    fun `getSortType should run these processes`() {

        // Mock: everytime Provider's getSortType method called, returns dummySortType
        every { providerMock.getSortType() } returns dummySortType

        // Run getSortType from Presenter
        presenter.getSortType()

        // Verify whether setSortType method in provider is called
        verify { providerMock.getSortType() }

        // Check whether Domain's sortType is equal to Provider's
        assertEquals(domainMock.sortType, providerMock.getSortType())

        // Verify whether trainInventoryData is added to trainSearchResult in viewmodel
        verify { viewModelMock.sortType.onNext(dummySortType) }

    }
}