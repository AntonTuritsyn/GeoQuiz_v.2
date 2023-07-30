package com.android.geoquizv2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.android.geoquizv2.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.android.geoquizv2.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.android.geoquizv2.answer_shown"
class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    private val quizViewModel: QuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            /*val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)*/
            textAnswer()
            quizViewModel.wasCheat = true
            setAnswerShownResult(true)
        }
//      Но если повернуть еще раз, то снова ломается. НАДО ИСПРАВЛЯТЬ!!!!
        if (quizViewModel.wasCheat) { textAnswer() }
        setAnswerShownResult(quizViewModel.wasCheat)

    }

    private fun textAnswer() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        binding.answerTextView.setText(answerText)
    }
    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }
/*      Инкапсуляция работы интента в отдельной функции
        Это делается потому что MainActivity не должна знать подробности реализации данных, которые получает CheatActivity
        Ключи для дополнений (EXTRA_ANSWER_IS_TRUE) должны определяться в activity, которые читают и используют их*/
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}