package com.informatique.tawsekmisr.ui.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.informatique.tawsekmisr.data.datastorehelper.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for LoginWebView Screen
 * Handles authentication code extraction and storage
 */
@HiltViewModel
class LoginWebViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _authCode = MutableStateFlow<String?>(null)
    val authCode: StateFlow<String?> = _authCode.asStateFlow()

    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()

    /**
     * Save the authentication code when extracted from URL
     */
    fun saveAuthCode(code: String) {
        viewModelScope.launch {
            try {
                // Save to DataStore
                TokenManager.saveToken(context, code)
                _authCode.value = code
            } catch (e: Exception) {
                setError("Failed to save authentication code: ${e.message}")
            }
        }
    }

    /**
     * Handle navigation based on the intent extras
     */
    fun handleNavigation(reserveOrReservations: Int, office: Any?) {
        _navigationEvent.value = if (reserveOrReservations == 0) {
            NavigationEvent.ToReservations
        } else {
            NavigationEvent.ToReservationWithQueue(office)
        }
    }

    /**
     * Clear the navigation event after handling
     */
    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }

    /**
     * Clear all data
     */
    fun clearData() {
        _authCode.value = null
        clearError()
        clearNavigationEvent()
    }
}

/**
 * Sealed class for navigation events
 */
sealed class NavigationEvent {
    data object ToReservations : NavigationEvent()
    data class ToReservationWithQueue(val office: Any?) : NavigationEvent()
}

