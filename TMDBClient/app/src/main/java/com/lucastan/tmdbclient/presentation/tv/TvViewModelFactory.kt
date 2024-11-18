package com.lucastan.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucastan.tmdbclient.domain.usecase.GetTvsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateTvsUseCase

class TvViewModelFactory(
    private val getTvsUseCase: GetTvsUseCase,
    private val updateTvsUseCase: UpdateTvsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvViewModel(getTvsUseCase, updateTvsUseCase) as T
    }
}