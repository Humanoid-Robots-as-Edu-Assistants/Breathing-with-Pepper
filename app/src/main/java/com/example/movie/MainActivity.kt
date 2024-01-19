package com.example.movie


//import android.R
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.ListenBuilder
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.`object`.conversation.Listen
import com.aldebaran.qi.sdk.`object`.conversation.ListenResult
import com.aldebaran.qi.sdk.`object`.conversation.PhraseSet
import com.aldebaran.qi.sdk.util.PhraseSetUtil
import java.util.Timer
import java.util.concurrent.TimeUnit


lateinit var talk: TextToSpeech

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oneminute)
        QiSDK.register(this, this)
    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {

        var min = 0

        val phraseSetEins: PhraseSet =
            PhraseSetBuilder.with(qiContext) // Create the builder using the QiContext.
                .withTexts("eine") // Add the phrases Pepper will listen to.
                .build() // Build the PhraseSet.

        val phraseSetDrei: PhraseSet =
            PhraseSetBuilder.with(qiContext) // Create the builder using the QiContext.
                .withTexts("drei") // Add the phrases Pepper will listen to.
                .build() // Build the PhraseSet.

        val phraseSetFuenf: PhraseSet = PhraseSetBuilder.with(qiContext)
            .withTexts("fünf")
            .build()

        val phraseSetYes: PhraseSet = PhraseSetBuilder.with(qiContext)
            .withTexts("ja")
            .build()

        val listen: Listen = ListenBuilder.with(qiContext) // Create the builder with the QiContext.
            .withPhraseSets(
                phraseSetEins,
                phraseSetDrei,
                phraseSetFuenf
            ) // Set the PhraseSets to listen to.
            .build() // Build the listen action

        val listen_2: Listen = ListenBuilder.with(qiContext)
            .withPhraseSets(
                phraseSetYes
            )
            .build()

        var main = Intent(this, OneMinute::class.java)

        //val phrase = Phrase("\\rspd=70\\ Das ist langsam \\rspd=200\\ und das hier schnell!")
        val say_1 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Hallo und willkommen zu Atmen und Entspannen mit Pepper!")
            .build()
        val say_2 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Schön euch heute wieder zu sehen!")
            .build()
        val say_3 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Wir machen heute eine ganz simple Atemübung, die euch helfen soll, langsam und gleichmäßig zu atmen...")
            .build()
        val say_4 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Dabei sollt ihr vier Sekunden einatmen und sechs Sekunden ausatmen.")
            .build()
        val say_5 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Ihr müsst auch nicht sehr tief einatmen, wichtiger ist die Gleichmäßigkeit.")
            .build()
        val say_6 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Am besten folgt ihr dabei den Klängen, die ich vorspiele.")
            .build()
        val say_7 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Beim höheren Klang atmet ihr durch die Nase ein, beim tieferen durch den Mund oder die Nase aus")
            .build()
        val say_8 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Ihr könnt gerne die Augen zumachen.")
            .build()
        val say_9 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Oder ihr könnt euch zu Unterstützung die Animation auf meinem Display ansehen.")
            .build()
        val say_10 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Setzt euch jetzt schonmal gerade aber gemütlich hin und entspannt euer Gesicht.")
            .build()
        val say_11 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Ihr hört ein Klingeln, wenn die Übung zu Ende ist.")
            .build()
        val say_12 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Ihr könnt entscheiden, wie lange ihr die Übung machen wollt.")
            .build()
        val say_13 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Soll die Übung eine, drei oder fünf Minuten lang sein?")
            .build()
        val say_14_1 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Alles klar! Wir machen die Übung eine Minute lang.")
            .build()
        val say_14_3 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Alles klar! Wir machen die Übung drei Minuten lang.")
            .build()
        val say_14_5 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Alles klar! Wir machen die Übung fünf Minuten lang.")
            .build()
        val say_15 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Seid ihr alle bereit?")
            .build()
        val say_16 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Sehr gut!")
            .build()
        val say_17 = SayBuilder.with(qiContext)
            .withText("\\rspd=80\\ Atmet jetzt entspannt ein.")
            .build()


        val first1: Future<Void> = say_1.async().run()
        say_1.async().run()
        val ballet: Future<Void> = first1.thenCompose {
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
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_6.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_7.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_8.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_9.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_10.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_11.async().run()
        }.thenCompose {
            //mediaPlayerEnd.start()
            TimeUnit.SECONDS.sleep(1L)
            say_12.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            say_13.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)

            var listenResult: ListenResult = listen.run()
            var understood = false
            while (!understood) {
                val matchedPhraseSet: PhraseSet = listenResult.matchedPhraseSet
                if (PhraseSetUtil.equals(matchedPhraseSet, phraseSetEins)) {
                    say_14_1.async().run()
                    min = 1
                    understood = true
                } else if (PhraseSetUtil.equals(matchedPhraseSet, phraseSetDrei)) {
                    say_14_3.async().run()
                    min = 3
                    understood = true
                } else if (PhraseSetUtil.equals(matchedPhraseSet, phraseSetFuenf)) {
                    say_14_5.async().run()
                    min = 5
                    understood = true
                } else {
                    listenResult = listen.run()
                }
            }
            TimeUnit.SECONDS.sleep(1L)
            say_15.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(1L)
            var listenResult: ListenResult = listen_2.run()
            var understood = false
            while (!understood) {
                val matchedPhraseSet: PhraseSet = listenResult.matchedPhraseSet
                if (PhraseSetUtil.equals(matchedPhraseSet, phraseSetYes)) {
                    say_16.async().run()
                    understood = true
                } else {
                    listenResult = listen.run()
                }
            }
            TimeUnit.SECONDS.sleep(1L)
            say_17.async().run()
        }.thenCompose {
            TimeUnit.SECONDS.sleep(4L)
            when (min) {
                1 -> {
                    main = Intent(this, OneMinute::class.java)
                    startActivity(main)
                }
                3 -> {
                    main = Intent(this, ThreeMinutes::class.java)
                    startActivity(main)
                }
                else -> {
                    main = Intent(this, FiveMinutes::class.java)
                    startActivity(main)
                }
            }
            TimeUnit.SECONDS.sleep(2L)
            say_1.async().run()}

    }
    override fun onRobotFocusLost() {
        //robot focus is lost
        // Remove on started listeners from the GoTo action
    }

    override fun onRobotFocusRefused(reason: String) {
        // The robot focus is refused.
    }

}