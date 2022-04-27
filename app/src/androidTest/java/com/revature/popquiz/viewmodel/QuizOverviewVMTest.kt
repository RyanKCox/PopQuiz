package com.revature.popquiz.viewmodel

import android.content.Context
import android.content.Intent
import junit.framework.TestCase
import org.junit.Test
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class QuizOverviewVMTest : TestCase() {

    val viewModel = QuizOverviewVM()

    @Test
    fun testLoadWebpage() {

        val link = "https://developer.android.com/"
        val context = mock(Context::class.java)



        viewModel.loadWebpage(context,link)
        verify(context).startActivity(
            argThat{

                it.action == Intent.ACTION_VIEW &&
                        it.dataString!! == link
            }
        )

    }
}