package com.example.salud.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salud.model.DataOrException
import com.example.salud.model.Leaderboard
import com.example.salud.model.User
import com.example.salud.repository.LeaderboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repository: LeaderboardRepository
): ViewModel() {
    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Leaderboard>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )

    init {
        getLeaderboard()
    }

    fun getLeaderboard() {
        Log.d("Hey","Hey")
        viewModelScope.launch {
            loading.value = true
            data.value = repository.getLeaderboardFromFirestore()
            loading.value = false
        }
    }
}