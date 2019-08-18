package np.com.nawarajbista.millionaire

import android.content.Intent
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import np.com.nawarajbista.millionaire.databinding.ActivityGameBinding
import np.com.nawarajbista.millionaire.model.DataBindingParcelable
import np.com.nawarajbista.millionaire.viewmodel.ClappingSound
import np.com.nawarajbista.millionaire.viewmodel.DataBindingQuestion
import np.com.nawarajbista.millionaire.viewmodel.FinalResult
import np.com.nawarajbista.millionaire.viewmodel.GetQuestion

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private var selectedOption: Int = 0
    private var userAnswer: String? = null
    lateinit var correctAnswer: String
    private var level: Int = 0
    private lateinit var dataBinding: ActivityGameBinding
    private lateinit var askedQuestion: DataBindingQuestion
    private lateinit var userName: String
    private lateinit var dataForNextActivity: FinalResult
    lateinit var clap: ClappingSound

    companion object {
        const val USERNAME = "USER_NAME"
        const val DATA = "DATA_FOR_INTENT"
        const val SAVED_INSTANCE = "SAVED_INSTANCE"
        const val CORRECT_ANSWER = "CORRECT_ANSWER_INSTANCE"
        const val CURRENT_LEVEL = "CURRENT_LEVEL_OF_GAME"
        const val SELECTED_OPTION = "CURRENTLY_SELECTED_OPTION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_game
        )

        if(savedInstanceState == null) {
            askedQuestion = displayQuestion(level)
            Log.d("error", askedQuestion.q)
            dataBinding.setVariable(BR.question, askedQuestion)
            dataBinding.executePendingBindings()
        }


        userName = intent.getStringExtra(USERNAME)

        supportActionBar?.title = userName


        button_option_one.setOnClickListener(this)
        button_option_two.setOnClickListener(this)
        button_option_three.setOnClickListener(this)
        button_option_four.setOnClickListener(this)
        button_submit.setOnClickListener(this)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val correctAns = correctAnswer
        val currentLevel = level
        val highlightedOption = selectedOption
        val saveInstance = DataBindingParcelable(askedQuestion.level, askedQuestion.q, askedQuestion.options)
        outState?.putParcelable(SAVED_INSTANCE, saveInstance)
        outState?.putString(CORRECT_ANSWER,correctAns)
        outState?.putInt(CURRENT_LEVEL, currentLevel)
        outState?.putInt(SELECTED_OPTION, highlightedOption)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedInstance: DataBindingParcelable? = savedInstanceState?.getParcelable(SAVED_INSTANCE)
        correctAnswer = savedInstanceState?.getString(CORRECT_ANSWER)!!
        level = savedInstanceState.getInt(CURRENT_LEVEL)
        selectedOption = savedInstanceState.getInt(SELECTED_OPTION)

        selectPreviouslySelectedOption()

        askedQuestion = DataBindingQuestion(level, savedInstance!!.q, savedInstance.options)
        dataBinding.question = askedQuestion
    }



    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_option_one -> {
                selectAnswer(button_option_one)
            }
            R.id.button_option_two -> {
                selectAnswer(button_option_two)
            }
            R.id.button_option_three -> {
                selectAnswer(button_option_three)
            }
            R.id.button_option_four -> {
                selectAnswer(button_option_four)
            }
            R.id.button_submit -> {
                if(selectedOption == 0) {
                    Toast.makeText(
                        this,
                        "Please select your answer by tapping given options.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                userAnswer = userAnswer(selectedOption)

                if(userAnswer == correctAnswer) {
                    level += 1
                    if(level < GetQuestion().data.size) {
                        selectAnswer(button_submit) //reset everything for next question
                        askedQuestion = displayQuestion(level)
                        questionChangingAnim()
                        dataBinding.question = askedQuestion
                    }
                    else {
                        sendDataToNextActivity("Congratulation!!!")
                    }
                }
                else {
                    sendDataToNextActivity("Sorry!!!")
                }
            }
        }
    }

    private fun questionChangingAnim() {
        val animationOutIn = AnimationUtils.
            loadAnimation(this, R.anim.fade_out_in)

        textView_question.startAnimation(animationOutIn)
        button_option_one.startAnimation(animationOutIn)
        button_option_two.startAnimation(animationOutIn)
        button_option_three.startAnimation(animationOutIn)
        button_option_four.startAnimation(animationOutIn)

        clappingSoundPlay()
    }

    private fun clappingSoundPlay() {
        clap = ClappingSound(this)
        clap.play()
    }


    private fun selectPreviouslySelectedOption() {
        when(selectedOption) {
            1 -> button_option_one.setBackgroundResource(R.drawable.btn_background_select)
            2 -> button_option_two.setBackgroundResource(R.drawable.btn_background_select)
            3 -> button_option_three.setBackgroundResource(R.drawable.btn_background_select)
            4 -> button_option_four.setBackgroundResource(R.drawable.btn_background_select)
            else -> return
        }
    }






    private fun sendDataToNextActivity(message: String) {
        val win = askedQuestion.win
        dataForNextActivity = FinalResult(userName, message, win)
        val intent = Intent(this, YouOwn::class.java)
        intent.putExtra(DATA, dataForNextActivity)
        startActivity(intent)
        finish()
    }





    private fun displayQuestion(level: Int): DataBindingQuestion {
        val data = GetQuestion().getQuestionData(level)
        correctAnswer = data.getValue("optionCorrect")
        val q = data.getValue("q")

        val optionFromData = listOf(
            data.getValue("optionCorrect"),
            data.getValue("option2"),
            data.getValue("option3"),
            data.getValue("option4")
        )

        val optionDisplay: List<String> = optionFromData.shuffled()

        return DataBindingQuestion(level, q, optionDisplay)
    }




    private fun userAnswer(selectedOption: Int): String? {
        var userAns: String? = null
        when(selectedOption) {
            1 -> {
                userAns = button_option_one.text.toString()
            }
            2 -> {
                userAns = button_option_two.text.toString()
            }
            3 -> {
                userAns = button_option_three.text.toString()
            }
            4 -> {
                userAns = button_option_four.text.toString()
            }
        }
        return userAns
    }





    private fun selectAnswer(button: Button) {
        if(button != button_submit) {
            button.setBackgroundResource(R.drawable.btn_background_select)
        }

        when(button) {
            button_option_one -> {
                button_option_two.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_three.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_four.setBackgroundResource(R.drawable.btn_background_normal)
                selectedOption = 1
            }
            button_option_two -> {
                button_option_one.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_three.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_four.setBackgroundResource(R.drawable.btn_background_normal)
                selectedOption = 2
            }
            button_option_three -> {
                button_option_two.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_one.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_four.setBackgroundResource(R.drawable.btn_background_normal)
                selectedOption = 3
            }
            button_option_four -> {
                button_option_two.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_three.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_one.setBackgroundResource(R.drawable.btn_background_normal)
                selectedOption = 4
            }
            button_submit -> {
                button_option_one.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_two.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_three.setBackgroundResource(R.drawable.btn_background_normal)
                button_option_four.setBackgroundResource(R.drawable.btn_background_normal)
                selectedOption = 0
            }
        }
    }


}
