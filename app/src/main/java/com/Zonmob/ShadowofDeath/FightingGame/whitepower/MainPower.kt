package com.Zonmob.ShadowofDeath.FightingGame.whitepower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.Zonmob.ShadowofDeath.FightingGame.databinding.ActivityMainPowerBinding
import com.google.android.flexbox.*

class MainPower : AppCompatActivity() {
    // values of the draggable views (usually this should come from a data source)
    private val words = mutableListOf(
        "It's",
        "a",
        "historical",
        "mystery",
        "story",
        "about",
        "corruption",
        "and",
        "power.",
        "It",
        "kicks off",
        "at",
        "a hotel",
        "with",
        "the",
        "discovery",
        "of",
        "a mass",
        "grave."
    )
    // last selected word
    private var selectedWord = ""

    private val binding by lazy { ActivityMainPowerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sentenceAdapter = SentenceAdapter()
        val wordsAdapter = WordsAdapter {
            selectedWord = it
        }.apply {
            submitList(words)
        }

        val llm = GridLayoutManager(this, 4)
        binding.rvSentence.layoutManager = llm
        binding.rvSentence.adapter = sentenceAdapter

        binding.rvSentence.setOnDragListener(
            DropListener {
                wordsAdapter.removeItem(selectedWord)
                sentenceAdapter.addItem(selectedWord)
            }
        )

        binding.rvWords.layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP).apply {
            justifyContent = JustifyContent.SPACE_EVENLY
            alignItems = AlignItems.CENTER
        }

        binding.rvWords.adapter = wordsAdapter

        binding.recreateIcon.setOnClickListener {
            recreate()
        }
    }
}