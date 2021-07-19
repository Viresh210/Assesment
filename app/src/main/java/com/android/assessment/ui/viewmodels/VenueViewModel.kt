package com.android.assessment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.assessment.models.VenuesMainResponse
import com.android.assessment.repositories.VenuesRepository
import kotlinx.coroutines.launch

class VenueViewModel() : ViewModel() {

    private val repository = VenuesRepository()
    val data: LiveData<VenuesMainResponse?> = repository.data
    fun getData(city: String, date: String) {

        viewModelScope.launch {
            repository.fetch(city, date)
        }
    }

}