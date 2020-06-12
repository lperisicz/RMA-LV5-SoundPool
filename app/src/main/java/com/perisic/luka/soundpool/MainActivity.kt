package com.perisic.luka.soundpool

import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private var soundMap: HashMap<Int, Int> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        loadSounds()
    }

    private fun setupUi() {
        imageViewOne.setOnClickListener {
            R.raw.bells.playMusic()
        }
        imageViewTwo.setOnClickListener {
            R.raw.car_starting.playMusic()
        }
        imageViewThree.setOnClickListener {
            R.raw.barking.playMusic()
        }
    }

    private fun loadSounds() {
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .build()
            .apply {
                setOnLoadCompleteListener { _, _, _ ->
                    arrayOf(imageViewOne, imageViewTwo, imageViewThree).forEach {
                        it.isClickable = true
                    }
                }
                arrayOf(R.raw.bells, R.raw.car_starting, R.raw.barking).forEach {
                    soundMap[it] = load(this@MainActivity, it, 1)
                }
            }
    }

    private fun Int.playMusic() {
        try {
            soundPool.play(soundMap.getValue(this), 1f, 1f, 1, 0, 1f)
        } catch (e: Exception) {
            return
        }
    }
}
