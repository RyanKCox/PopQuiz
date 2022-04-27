package com.revature.popquiz.viewmodel

import junit.framework.TestCase
import org.junit.Test

class ProfileViewModelTest : TestCase() {

    val viewModel = ProfileViewModel()
    @Test
    fun testGetAverageScore() {


        var list = mutableListOf<Float>(100.0f,0.0f)
        var result = viewModel.getAverageScore(list)

        assertEquals("50",result)

    }

    fun testGetMostTakenQuiz() {

        var list = mutableListOf<String>("Apple","Pear","Apple","Pear","Apple")
        var result = viewModel.getMostTakenQuiz(list)

        assertEquals("Apple",result)
    }
}