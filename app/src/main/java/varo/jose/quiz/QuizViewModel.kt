package varo.jose.quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

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

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

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