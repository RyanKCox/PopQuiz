package com.revature.popquiz.viewmodels

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.api.RetrofitHelper
import com.revature.popquiz.model.api.services.questions.QuestionByIDRepo
import com.revature.popquiz.model.api.services.questions.QuestionRepo
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.api.services.quiz.QuizByIDRepo
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.RoomDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchQuizzesOverviewViewModel: ViewModel()
{
    private val quizService = RetrofitHelper.getAllQuizzesServices()
    private lateinit var quizRepo: QuizByIDRepo
    var quiz: MutableLiveData<QuizAPIEntity> = MutableLiveData()
    private lateinit var questionRepo: QuestionByIDRepo
//    var loadedVariable = MutableLiveData(false)

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
            questionRepo = QuestionByIDRepo(quizService)
            quiz.value!!.questionIDs.forEach {
                when (val response = questionRepo.fetchQuestionById(id = it))
                {
                    is QuestionByIDRepo.Result.Success ->
                    {
                        if (response.quiz.value != null)
                        {
                            newQuiz.questionList.add(response.quiz.value!!)
                        }

                    }
                    is QuestionByIDRepo.Result.Failure ->
                    {
                        Log.d("SearchQuizzesOverviewVM", "Loading Failed")
                    }
                }
            }
            RoomDataManager.quizRepository.insertQuiz(newQuiz)
        }
    }
}