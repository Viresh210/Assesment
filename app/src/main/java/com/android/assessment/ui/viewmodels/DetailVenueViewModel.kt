package com.android.assessment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.assessment.models.DetailVenueMainResponse
import com.android.assessment.repositories.DetailVenueRepository
import kotlinx.coroutines.launch

class DetailVenueViewModel : ViewModel() {

    private val repository = DetailVenueRepository()
    val data: LiveData<DetailVenueMainResponse?> = repository.data
    fun getDetailData(venueID: String, date: String) {
        viewModelScope.launch {
            repository.fetch(venueID, date)
        }
    }

}