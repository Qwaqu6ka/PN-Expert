package ru.fefu.photo_tests_impl.domain.models

import android.net.Uri


data class PhotoTestAnswer(
    val userAnswer:MutableList<UserAnswerItem> = mutableListOf()
){
    fun addNewAnswer(testTask: String, photo:Uri, answerNumber: Int){
        if ((answerNumber > userAnswer.size + 1) || (answerNumber <= 0)){
            println("user answer is $userAnswer, answer number is $answerNumber")
            throw IllegalArgumentException()
        }

        if (userAnswer.size + 1 == answerNumber){
            println("userAnswer before $userAnswer")
            userAnswer.add(UserAnswerItem(testTask, photo))
            println("userAnswer after $userAnswer")
        }
        else
            userAnswer[answerNumber - 1] = UserAnswerItem(testTask, photo)
    }

    fun toReadingModel():PhotoTestAnswerForReading{
        return PhotoTestAnswerForReading(userAnswer)
    }
}

data class PhotoTestAnswerForReading(
    val userAnswer: List<UserAnswerItem>
)

data class UserAnswerItem(
    val testTask: String,
    val testAnswer:Uri
)