package varo.jose.quiz

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import varo.jose.quiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()
    //private lateinit var trueButton : Button
    //private lateinit var falseButton : Button

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //Handle the result
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        //trueButton = findViewById(R.id.true_button)
        //falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener { view: View ->
            //Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show()
            //val snackBar = Snackbar.make(view,R.string.correct_toast,Snackbar.LENGTH_SHORT)
            //snackBar.setBackgroundTint(resources.getColor(R.color.green))
            //snackBar.show()
            checkAnswer(true, view)
        }
        binding.falseButton.setOnClickListener { view: View ->
            //Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show()
            //val snackBar = Snackbar.make(view,R.string.incorrect_toast,Snackbar.LENGTH_SHORT)
            //snackBar.setBackgroundTint(resources.getColor(R.color.red))
            //snackBar.show()
            checkAnswer(false, view)
        }

        binding.prevButton.setOnClickListener {
            //currentIndex = (currentIndex - 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.cheatButton?.setOnClickListener {
            //val intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivity(intent)
            cheatLauncher.launch(intent)
        }

        binding.questionTextView.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        //val questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean, view:View) {
        //val correctAnswer = questionBank[currentIndex].answer
        val questionTextResId = quizViewModel.currentQuestionAnswer

        //val messageResId = if (userAnswer == questionTextResId) {
        //    R.string.correct_toast
        //} else {
        //    R.string.incorrect_toast
        //}

        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == questionTextResId -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        val color = if (userAnswer == questionTextResId) {
            R.color.green
        } else {
            R.color.red
        }

        val snackBar = Snackbar.make(view,messageResId,Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(resources.getColor(color))
        snackBar.show()

        //Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                //.show()
    }

}