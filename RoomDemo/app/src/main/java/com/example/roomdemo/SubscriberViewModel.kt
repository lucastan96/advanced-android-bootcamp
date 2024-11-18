package com.example.roomdemo

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel() {
    val subscribers = subscriberRepository.subscribers

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow: SharedFlow<String> = _sharedFlow

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    private fun initSaveAndClearAll() {
        inputName.value = ""
        inputEmail.value = ""

        isUpdateOrDelete = false

        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email

        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun saveOrUpdate() {
        if (validateInput()) {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!

                update(subscriberToUpdateOrDelete)
            } else {
                save(Subscriber(0, inputName.value!!, inputEmail.value!!))
            }
        }
    }

    private fun validateInput(): Boolean {
        var message: String? = null

        if (inputName.value.isNullOrEmpty()) {
            message = "Name cannot be empty."
        } else if (inputEmail.value.isNullOrEmpty()) {
            message = "Email cannot be empty."
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            message = "Email invalid."
        }

        if (message != null) {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    _sharedFlow.emit(message)
                }
            }

            return false;
        }

        return true;
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun save(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = subscriberRepository.insert(subscriber)

        withContext(Dispatchers.Main) {
            if (newRowId > -1) {
                inputName.value = ""
                inputEmail.value = ""

                _sharedFlow.emit("Subscriber ID: $newRowId saved successfully")
            } else {
                _sharedFlow.emit("Failed to save subscriber")
            }
        }
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val rowsUpdated = subscriberRepository.update(subscriber)

        withContext(Dispatchers.Main) {
            if (rowsUpdated > 0) {
                initSaveAndClearAll()

                _sharedFlow.emit("$rowsUpdated subscriber updated successfully")
            } else {
                _sharedFlow.emit("Failed to update subscriber")
            }
        }
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val rowsDeleted = subscriberRepository.delete(subscriber)

        withContext(Dispatchers.Main) {
            if (rowsDeleted > 0) {
                initSaveAndClearAll()

                _sharedFlow.emit("$rowsDeleted subscriber deleted successfully")
            } else {
                _sharedFlow.emit("Failed to delete subscriber")
            }
        }
    }

    private fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        val rowsDeleted = subscriberRepository.deleteAll()

        withContext(Dispatchers.Main) {
            if (rowsDeleted > 0) {
                _sharedFlow.emit("$rowsDeleted subscriber(s) deleted successfully")
            } else {
                _sharedFlow.emit("Failed to delete subscribers")
            }
        }
    }
}