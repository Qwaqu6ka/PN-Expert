package ru.fefu.photo_tests_impl.presentation.models

import android.net.Uri

class UserAnswer() {
    private val answers: MutableList<Uri> = mutableListOf()

    fun getAnswers():List<Uri>{
        return answers
    }

    fun addAnswer(photoUri: Uri){
        answers.add(photoUri)
    }

    fun setAnswer(photoUri: Uri, answerNumber: Int){
        if (answerNumber < 1 || answerNumber > answers.size)
            throw Exception(message = "answerNumber must be in 1..ansvers.size diapason")

        answers[answerNumber] = photoUri
    }

    companion object{
        fun toUri(photoUri: Uri):UserAnswer{
            val obj = UserAnswer()
            obj.answers.add(photoUri)

            return obj
        }

        fun toUriList(photoUriList:List<Uri>): UserAnswer{
            val obj = UserAnswer()
            photoUriList.map { it-> obj.answers.add(it) }

            return obj
        }
    }
}