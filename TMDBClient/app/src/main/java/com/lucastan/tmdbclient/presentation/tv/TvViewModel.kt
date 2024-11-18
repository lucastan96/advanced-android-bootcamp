package com.lucastan.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lucastan.tmdbclient.domain.usecase.GetTvsUseCase
import com.lucastan.tmdbclient.domain.usecase.UpdateTvsUseCase

class TvViewModel(
    private val getTvUseCase: GetTvsUseCase,
    private val updateTvsUseCase: UpdateTvsUseCase
) : ViewModel() {
    fun getTvs() = liveData {
        val tvList = getTvUseCase.execute()
        emit(tvList)
    }

    fun updateTvs() = liveData {
        val tvList = updateTvsUseCase.execute()
        emit(tvList)
    }
}