package com.example.circlecalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculations = Mockito.mock(Calculations::class.java)

        Mockito.`when`(calculations.calculateCircumference(2.1)).thenReturn(13.188)
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)

        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        calcViewModel.calculateCircumference(2.1)
        val result = calcViewModel.circumference.value
        assertThat(result).isEqualTo("13.188")
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)
        val result = calcViewModel.area.value
        assertThat(result).isEqualTo("13.8474")
    }
}