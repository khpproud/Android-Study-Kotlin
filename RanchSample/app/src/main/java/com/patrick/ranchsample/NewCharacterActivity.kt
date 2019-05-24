package com.patrick.ranchsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_new_character.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val CHARACTER_DATA_KEY = "CHARACTER_DATA_KEY"

private var Bundle.characterData
    get() = getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
    set(value) = putSerializable(CHARACTER_DATA_KEY, value)

class NewCharacterActivity : AppCompatActivity() {

    private lateinit var characterData: CharacterGenerator.CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

//        characterData = savedInstanceState?.characterData ?: CharacterGenerator.generate()
//        displayCharacterData()
        savedInstanceState?.let {
            characterData = it.characterData
            displayCharacterData()
        } ?: getCharacterDataWithApi()

        generateButton.setOnClickListener {
            getCharacterDataWithApi()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.characterData = characterData
    }

    private fun displayCharacterData() {
        characterData.run {
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }

    private fun getCharacterDataWithApi() {
        GlobalScope.launch(Dispatchers.Main) {
            characterData = fetchCharacterData().await()
            displayCharacterData()
        }
    }
}
