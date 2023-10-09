package ru.fefu.photo_tests_impl.domain.models

import android.net.Uri


data class PhotoTestAnswer(
    val userAnswer:MutableList<Uri> = mutableListOf()
){
    fun addNewAnswer(photo:Uri, answerNumber: Int){
        if ((answerNumber > userAnswer.size + 1) || (answerNumber <= 0)){
            println("user answer is $userAnswer, answer number is $answerNumber")
            throw IllegalArgumentException()
        }

        if (userAnswer.size + 1 == answerNumber){
            println("userAnswer before $userAnswer")
            userAnswer.add(photo)
            println("userAnswer after $userAnswer")
        }
        else
            userAnswer[answerNumber - 1] = photo
    }

    fun toReadingModel():PhotoTestAnswerForReading{
        return PhotoTestAnswerForReading(userAnswer)
    }
}

data class PhotoTestAnswerForReading(
    val userAnswer: List<Uri>
)