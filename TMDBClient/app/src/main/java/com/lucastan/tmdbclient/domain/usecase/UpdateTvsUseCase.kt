package com.lucastan.tmdbclient.domain.usecase

import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.domain.repository.TvRepository

class UpdateTvsUseCase(private val tvRepository: TvRepository) {
    suspend fun execute(): List<Tv>? = tvRepository.updateTvs()
}