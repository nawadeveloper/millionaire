package np.com.nawarajbista.millionaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private var selectedOption: Int? = null
    private var userAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val userName = intent.getStringExtra(MainActivity.USERNAME)

        supportActionBar?.title = userName

        button_option_one.setOnClickListener(this)
        button_option_two.setOnClickListener(this)
        button_option_three.setOnClickListener(this)
        button_option_four.setOnClickListener(this)
        button_submit.setOnClickListener(this)
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
                if(selectedOption == null) {
                    Toast.makeText(
                        this,
                        "Please select your answer by tapping given options.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                userAnswer = userAnswer(selectedOption!!)

                Toast.makeText(this, userAnswer, Toast.LENGTH_SHORT).show()
            }
        }
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
        button.setBackgroundResource(R.drawable.btn_background_select)
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
        }
    }


}
