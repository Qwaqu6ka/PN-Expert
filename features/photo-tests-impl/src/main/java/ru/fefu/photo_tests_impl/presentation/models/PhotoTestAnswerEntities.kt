package ru.fefu.photo_tests_impl.presentation.models

import android.net.Uri

class PhotoTestAnswer() {
    private val answers: MutableList<Uri> = mutableListOf()

    fun getAnswers():List<Uri>{
        return answers
    }

    fun addAnswer(photoUri: Uri){
        answers.add(photoUri)
    }

    fun setAnswer(photoUri: Uri, answerNumber: Int){
        if (answerNumber < 1 || answerNumber > answers.size)
            throw Exception(message = "answerNumber must be in 1..answers.size diapason")

        answers[answerNumber] = photoUri
    }

    fun readingModel(): PhotoTestAnswerToReading{
        return PhotoTestAnswerToReading.toUriList(answers)
    }

    companion object{
        fun toUri(photoUri: Uri):PhotoTestAnswer{
            val obj = PhotoTestAnswer()
            obj.answers.add(photoUri)

            return obj
        }

        fun toUriList(photoUriList:List<Uri>): PhotoTestAnswer{
            val obj = PhotoTestAnswer()
            photoUriList.map { it-> obj.answers.add(it) }

            return obj
        }
    }
}


class PhotoTestAnswerToReading private constructor(photoUriList: List<Uri>) {
    private val answers: List<Uri> = photoUriList

    fun getAnswers():List<Uri>{
        return answers
    }

    companion object{
        fun toUriList(photoUriList: List<Uri>):PhotoTestAnswerToReading{
            return PhotoTestAnswerToReading(photoUriList)
        }

        fun createEmpty():PhotoTestAnswerToReading{
            return PhotoTestAnswerToReading(emptyList())
        }
    }
}