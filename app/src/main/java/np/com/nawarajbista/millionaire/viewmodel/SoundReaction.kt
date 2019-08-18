package np.com.nawarajbista.millionaire.viewmodel

import android.content.Context
import android.media.MediaPlayer
import np.com.nawarajbista.millionaire.R

class SoundReaction(private val context: Context) {
    lateinit var clap: MediaPlayer
    lateinit var aww: MediaPlayer

    fun play(reaction: String){
        if(reaction == "clap") {
            clap = MediaPlayer.create(context, R.raw.clapping)
            clap.start()
            clap.setOnCompletionListener {
                it.release()
            }
        }
        else {
            aww = MediaPlayer.create(context, R.raw.aww)
            aww.start()
            aww.setOnCompletionListener {
                it.release()
            }
        }
    }
}
