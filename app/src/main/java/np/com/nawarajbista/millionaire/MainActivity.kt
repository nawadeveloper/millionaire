package np.com.nawarajbista.millionaire

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start.setOnClickListener {
            var userName = editText_name.text.trim().toString()
            if(userName.isEmpty()) {
                userName = "unknown player"
            }
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(USERNAME, userName)
            startActivity(intent)
        }
    }
}
