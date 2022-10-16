package com.example.salud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.salud.repository.AuthRepository
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor():ViewModel() {
    var activityList:MutableLiveData<MutableMap<String,Float>> = MutableLiveData()
    var message:MutableLiveData<String> = MutableLiveData()

}