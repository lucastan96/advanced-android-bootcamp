package com.lucastan.tmdbclient.domain.usecase

import com.lucastan.tmdbclient.data.model.tv.Tv
import com.lucastan.tmdbclient.domain.repository.TvRepository

class GetTvsUseCase(private val tvRepository: TvRepository) {
    suspend fun execute(): List<Tv>? = tvRepository.getTvs()
}
