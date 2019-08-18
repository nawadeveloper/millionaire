package np.com.nawarajbista.millionaire

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_you_own.*
import np.com.nawarajbista.millionaire.GameActivity.Companion.DATA
import np.com.nawarajbista.millionaire.viewmodel.ClappingSound
import np.com.nawarajbista.millionaire.viewmodel.FinalResult

class YouOwn : AppCompatActivity() {

    companion object {
        const val USERNAME = "USER_NAME"
    }

    lateinit var userName: String
    lateinit var clap: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_own)


        val receiptData: FinalResult = intent.getParcelableExtra(DATA)

        userName = receiptData.username
        supportActionBar?.title = userName

        textView_congratulation.text = receiptData.message
        if(receiptData.message == "Congratulation!!!") {
            val message = "$userName became a millionaire."
            textView_win.text = message

            //sound play
            val clap = ClappingSound(this)
            clap.play()
        }
        else {
            val message = "$userName only manage to win $${receiptData.win}"
            textView_win.text = message
        }

        button_new_game.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(USERNAME, userName)
            startActivity(intent)
            finish()
        }

        button_new_player.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
