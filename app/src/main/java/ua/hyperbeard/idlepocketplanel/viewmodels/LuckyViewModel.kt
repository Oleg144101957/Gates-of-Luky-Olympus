package ua.hyperbeard.idlepocketplanel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.domain.Element
import ua.hyperbeard.idlepocketplanel.domain.Menu

class LuckyViewModel : ViewModel() {

    val existenceScores : MutableStateFlow<Int> = MutableStateFlow(0)
    val existenceLives : MutableStateFlow<Int> = MutableStateFlow(2)
    val existenceTime : MutableStateFlow<Int> = MutableStateFlow(20)

    var choiceImage: Int? = null
    var choicePosition: Int? = null

    val existenceList: MutableStateFlow<List<Element>> = MutableStateFlow(listOf(
        Element(0, frontImage = R.drawable.pieceofluck1, rearImage = R.drawable.pieceofluck7, description = "serendipity", menu = Menu(true)),
        Element(1, frontImage = R.drawable.pieceofluck2, rearImage = R.drawable.pieceofluck7, description = "petrichor", menu = Menu(true)),
        Element(2, frontImage = R.drawable.pieceofluck3, rearImage = R.drawable.pieceofluck7, description = "luminous", menu = Menu(true)),
        Element(3, frontImage = R.drawable.pieceofluck5, rearImage = R.drawable.pieceofluck7, description = "mellifluous", menu = Menu(true)),

        Element(4, frontImage = R.drawable.pieceofluck1, rearImage = R.drawable.pieceofluck7, description = "nebulous", menu = Menu(true)),
        Element(5, frontImage = R.drawable.pieceofluck2, rearImage = R.drawable.pieceofluck7, description = "mellifluous", menu = Menu(true)),
        Element(6, frontImage = R.drawable.pieceofluck3, rearImage = R.drawable.pieceofluck7, description = "petrichor", menu = Menu(true)),
        Element(7, frontImage = R.drawable.pieceofluck5, rearImage = R.drawable.pieceofluck7, description = "petrichor", menu = Menu(true)),

        Element(8, frontImage = R.drawable.pieceofluck1, rearImage = R.drawable.pieceofluck7, description = "onomatopoeia", menu = Menu(true)),
        Element(9, frontImage = R.drawable.pieceofluck2, rearImage = R.drawable.pieceofluck7, description = "cacophony", menu = Menu(true)),
        Element(10, frontImage = R.drawable.pieceofluck3, rearImage = R.drawable.pieceofluck7, description = "petrichor", menu = Menu(true)),
        Element(11, frontImage = R.drawable.pieceofluck5, rearImage = R.drawable.pieceofluck7, description = "onomatopoeia", menu = Menu(true)),

        Element(12, frontImage = R.drawable.pieceofluck1, rearImage = R.drawable.pieceofluck7, description = "cacophony", menu = Menu(true)),
        Element(13, frontImage = R.drawable.pieceofluck2, rearImage = R.drawable.pieceofluck7, description = "ephemeral", menu = Menu(true)),
        Element(14, frontImage = R.drawable.pieceofluck3, rearImage = R.drawable.pieceofluck7, description = "nebulous", menu = Menu(true)),
        Element(15, frontImage = R.drawable.pieceofluck5, rearImage = R.drawable.pieceofluck7, description = "halcyon", menu = Menu(true))
    ))

    suspend fun initialStateOfNewGame(){
        delay(1500)
        val newList = existenceList.value.map {
            it.copy(menu = Menu(false))
        }
        existenceList.value = newList

        while (existenceTime.value>0){
            delay(1000)
            existenceTime.value -= 1
        }
    }

    fun touchElement(position: Int){
        val newList = existenceList.value.toMutableList()
        newList[position] = newList[position].copy(menu = Menu(true))
        existenceList.value = newList
        checkBomb(position)
        checkScore(position)
    }

    private fun checkScore(position: Int) {
        when(existenceList.value[position].frontImage){
            R.drawable.pieceofluck1 -> {
                minusScore(2)
            }

            else -> {
                checkPair(position)
            }
        }
    }

    private fun checkPair(position: Int) {
        if (choiceImage == null){
            choiceImage = existenceList.value[position].frontImage
            choicePosition = position
        } else {
            if (choiceImage == existenceList.value[position].frontImage){
                addScores(position)
                choiceImage = null
                choicePosition = null
            } else {

                viewModelScope.launch {
                    closeElements(position, choicePosition!!)
                }
                choiceImage = null
                choicePosition = null
            }
        }
    }

    private suspend fun closeElements(position: Int, choicePosition: Int) {
        delay(600)
        val newList = existenceList.value.toMutableList()
        newList[position] = newList[position].copy(menu = Menu(false))
        newList[choicePosition] = newList[choicePosition].copy(menu = Menu(false))
        existenceList.value = newList

    }

    private fun addScores(position: Int){
        when(existenceList.value[position].frontImage){
            R.drawable.pieceofluck2 -> {
                val newScore = existenceScores.value + 1
                existenceScores.value = newScore
            }
            R.drawable.pieceofluck3 -> {
                val newScore = existenceScores.value + 2
                existenceScores.value = newScore
            }
            R.drawable.pieceofluck5 -> {
                val newScore = existenceScores.value + 3
                existenceScores.value = newScore
            }
        }
    }

    private fun minusScore(score: Int){
        if (existenceScores.value != 0){
            val newScoresWithMinus = existenceScores.value - score
            existenceScores.value = newScoresWithMinus
        }
    }

    private fun checkBomb(position: Int) {
        if (existenceList.value[position].frontImage == R.drawable.pieceofluck1){
            val newExistLife = existenceLives.value - 1
            existenceLives.value = newExistLife
        }
    }
}