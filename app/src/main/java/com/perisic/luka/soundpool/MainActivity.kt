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
        buttonOne.setOnClickListener {
            R.raw.bells.playMusic()
        }
        buttonTwo.setOnClickListener {
            R.raw.car_starting.playMusic()
        }
    }

    private fun loadSounds() {
        soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .build()
                .apply {
                    setOnLoadCompleteListener { _, _, _ ->
                        arrayOf(buttonOne, buttonTwo).forEach { it.isEnabled = true }
                    }
                    arrayOf(R.raw.bells, R.raw.car_starting).forEach {
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
