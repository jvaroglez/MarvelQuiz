package varo.jose.quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    //init { Log.d(TAG, "ViewModel instance created") }

    //override fun onCleared() {     super.onCleared() Log.d(TAG, "ViewModel instance about to be destroyed")}

    private val questionBank = listOf(
        Question(R.string.question_pparker,true),
        Question(R.string.question_jarvis,true),
        Question(R.string.question_antman,false),
        Question(R.string.question_wakanda, true),
        Question(R.string.question_guerracap, false),
        Question(R.string.question_drstrange, false),
        Question(R.string.question_daredevil, true),
        Question(R.string.question_kbishop, false),
        Question(R.string.question_nidavellir,true),
        Question(R.string.question_quill, true),
        Question(R.string.question_bucky, false),
        Question(R.string.question_fantastic, false),
        Question(R.string.question_wanda, true),
        Question(R.string.question_xmen, false)
    )

    private var currentIndex: Int
    get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
    set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}