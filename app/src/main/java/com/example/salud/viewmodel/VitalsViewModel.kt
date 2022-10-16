package com.example.salud.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salud.model.User
import com.example.salud.model.Vitals
import com.example.salud.repository.VitalsRepository
import com.google.type.DateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class VitalsViewModel @Inject constructor(private val repo: VitalsRepository):ViewModel() {
    var vitals by mutableStateOf(emptyList<Vitals>())
    var vital by mutableStateOf<Vitals?>(Vitals())

    fun getVitals() {
        viewModelScope.launch {
            repo.getVitalsFromRoom().collect { response ->
                vitals = response
            }
        }
    }

    fun getVitals(dateTime: String) {
        viewModelScope.launch {
            repo.getVitalFromRoom(dateTime).collect { response ->
                vital = response
            }

        }
    }


    fun addVital(vitals: Vitals) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addVitalToRoom(vitals)
        }
    }

    fun updateVital(vitals: Vitals) {
        Log.d("Hey",vitals.steps.toString())
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateVitalInRoom(vitals)
        }
    }

    fun deleteVitals(vitals: Vitals) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteVitalFromRoom(vitals)
        }
    }
}