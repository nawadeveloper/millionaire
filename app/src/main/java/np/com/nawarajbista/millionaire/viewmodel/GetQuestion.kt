package np.com.nawarajbista.millionaire.viewmodel

import np.com.nawarajbista.millionaire.model.QuestionData

class GetQuestion: QuestionData() {
    private val allData = QuestionData().data

    fun getQuestionData(level: Int): Map<String, String> {
        val levelArray = allData[level]
        val randomQuestion = (0 until levelArray.size).random()

        return levelArray[randomQuestion]
    }
}