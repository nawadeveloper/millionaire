package np.com.nawarajbista.millionaire.viewmodel

import android.content.Context
import android.media.MediaPlayer
import np.com.nawarajbista.millionaire.R

class ClappingSound(context: Context) {
    val clap = MediaPlayer.create(context, R.raw.clapping)

    fun play(){
        clap.start()
        clap.setOnCompletionListener {
            it.release()
        }

    }
}