package com.revature.popquiz.model.room

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.revature.popquiz.model.room.quizroom.QuizDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class AppDataBaseTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var quizDB: AppDataBase
    private lateinit var quizDao: QuizDao


    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        quizDB = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java)
//            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        quizDao = quizDB.quizDao()

//        val quiz = Quiz(title = "Test")
//
//        CoroutineScope(Dispatchers.Main).launch {
//            quizDao.insertQuiz(quiz)
//        }

    }
    @After
    fun closeDB(){
        quizDB.close()
    }

    @Test
    fun testIfEmpty(){
//        val livedata = quizDao.fetchAllQuiz()
//        val items = LiveDataTestUtil.getValue(livedata)
//        assertTrue(items.isEmpty())
    }

    @Test
    fun readWriteTest() = runBlocking{

//        var results = quizDao.fetchAllQuiz()
//        assertEquals("Test",results.value?.first()?.title)
//

    }

}