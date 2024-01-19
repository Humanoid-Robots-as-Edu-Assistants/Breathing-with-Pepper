package com.example.movie

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class FiveMinutes: RobotActivity(), RobotLifecycleCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.movie.R.layout.layout)
        QiSDK.register(this, this)
        // Build an action synchronously.
        val simpleVideoView = findViewById<View>(R.id.simpleVideoView) as VideoView
        var mediaControls = MediaController(this)
        mediaControls.setAnchorView(simpleVideoView)

        simpleVideoView.setMediaController(mediaControls)
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.fuenfkom))
        simpleVideoView.start()

        simpleVideoView.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            //Toast.makeText(applicationContext, "Thank You...!!!", Toast.LENGTH_LONG)
            //    .show() // display a toast when an video is completed
            val main = Intent(this, End::class.java)
            startActivity(main)
        })
        simpleVideoView.setOnErrorListener(MediaPlayer.OnErrorListener { mp, what, extra ->
            Toast.makeText(
                applicationContext,
                "Oops An Error Occur While Playing Video...!!!",
                Toast.LENGTH_LONG
            ).show() // display a toast when an error is occured while playing an video
            false
        })
    }
    override fun onRobotFocusGained(qiContext: QiContext?) {
        TODO("Not yet implemented")
    }

    override fun onRobotFocusLost() {
        TODO("Not yet implemented")
    }

    override fun onRobotFocusRefused(reason: String?) {
        TODO("Not yet implemented")
    }
}