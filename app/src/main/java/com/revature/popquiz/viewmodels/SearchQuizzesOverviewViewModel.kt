package com.revature.popquiz.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.quizBarSearch
import com.revature.popquiz.model.api.RetrofitHelper
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.api.services.quiz.AllQuizRepo
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.api.services.quiz.QuizByIDRepo
import com.revature.popquiz.model.dataobjects.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchQuizzesOverviewViewModel: ViewModel()
{
    private val quizService = RetrofitHelper.getAllQuizzesServices()
    private lateinit var quizRepo: QuizByIDRepo
    var quiz: MutableLiveData<QuizAPIEntity> = MutableLiveData()

    fun loadQuiz(id: Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            quizRepo = QuizByIDRepo(quizService)

            when (val response = quizRepo.fetchQuizResponse(1))
            {
                is QuizByIDRepo.Result.Success ->
                {
                    quiz.postValue(response.quiz.value)
                }
                is QuizByIDRepo.Result.Failure ->
                {
                    Log.d("SearchQuizzesOverviewVM", "Loading Failed")
                }
            }
        }
    }

    fun createQuiz()
    {
        var newQuiz = Quiz(
            APIid = quiz.value!!.APIid,
            title = quiz.value!!.title,
            shortDescription = quiz.value!!.shortDesc,
            longDescription = quiz.value!!.longDesc
        )
        viewModelScope.launch(Dispatchers.IO)
        {
            quizRepo = QuizByIDRepo(quizService)
            quiz.value!!.questionIDs.forEach {
                quizRepo.
            }
        }
    }
}