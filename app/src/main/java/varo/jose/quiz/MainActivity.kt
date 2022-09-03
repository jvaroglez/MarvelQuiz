package varo.jose.quiz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import varo.jose.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var trueButton : Button
    //private lateinit var falseButton : Button
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

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //trueButton = findViewById(R.id.true_button)
        //falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener { view: View ->
            //Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show()
            val snackBar = Snackbar.make(view,R.string.correct_toast,Snackbar.LENGTH_SHORT)
            snackBar.setBackgroundTint(resources.getColor(R.color.green))
            snackBar.show()
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            //Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show()
            val snackBar = Snackbar.make(view,R.string.incorrect_toast,Snackbar.LENGTH_SHORT)
            snackBar.setBackgroundTint(resources.getColor(R.color.red))
            snackBar.show()
            checkAnswer(false)
        }

        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        //val questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
    }

}