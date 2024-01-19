package com.example.movie

import android.media.MediaPlayer
import android.os.Bundle
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.`object`.conversation.PhraseSet
import java.util.concurrent.TimeUnit

class End: RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        setContentView(R.layout.oneminute)
    }

    override fun onRobotFocusGained(qiContext: QiContext?){
        val mediaPlayerEnde = MediaPlayer.create(this, R.raw.zapsplat_bell_small_hand_short_ring_005_84224)
        val say_0 = SayBuilder.with(qiContext)
            .withText("")
            .build()
        val say_1 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Sehr gut!")
            .build()
        val say_2 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Also, wenn ihr jetzt nicht total entspannt seid, dann weiß ich auch nicht weiter.")
            .build()
        val say_3 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Danke, dass ihr heute mitgemacht habt.")
            .build()
        val say_4 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Ich würde mich freuen, euch bald wieder zu sehen! Habt noch eine schöne restliche Woche!")
            .build()
        val say_5 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Tschüss, bis bald!")
            .build()

        val first1: Future<Void> = say_0.async().run()
        say_0.async().run()
        val ballet: Future<Void> = first1.thenCompose {
            mediaPlayerEnde.start()
            TimeUnit.SECONDS.sleep(2L)
            say_1.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_2.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_3.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_4.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_5.async().run()
        }
    }

    override fun onRobotFocusLost() {
        TODO("Not yet implemented")
    }

    override fun onRobotFocusRefused(reason: String?) {
        TODO("Not yet implemented")
    }
}