package com.example.apkinspector.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apkinspector.data.InstalledAppRepository
import com.example.apkinspector.model.InstalledApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppListViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        InstalledAppRepository(application)

    private val _apps =
        MutableStateFlow<List<InstalledApp>>(emptyList())

    val apps: StateFlow<List<InstalledApp>> = _apps

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _apps.value = withContext(Dispatchers.IO) {
                    repository.getInstalledApps()
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
