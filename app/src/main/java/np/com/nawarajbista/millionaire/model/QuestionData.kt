package np.com.nawarajbista.millionaire.model

class QuestionData {

    private val thousandDollar = arrayOf(
        mapOf(
            "q" to "What is your name?",
            "optionCorrect" to "Nawaraj",
            "option2" to "Ranju",
            "option3" to "Nikesh",
            "option4" to "Jayenta"
        ),
        mapOf(
            "q" to "What is your age?",
            "optionCorrect" to "31",
            "option2" to "30",
            "option3" to "35",
            "option4" to "28"
        )
    )

    private val millionDollar = arrayOf(
        mapOf(
            "q" to "What are your doing?",
            "optionCorrect" to "working",
            "option2" to "studying",
            "option3" to "sleeping",
            "option4" to "driving"
        ),
        mapOf(
            "q" to "When you come to USA?",
            "optionCorrect" to "2016",
            "option2" to "2000",
            "option3" to "2015",
            "option4" to "2008"
        )
    )

    val data = arrayOf(
        thousandDollar,
        millionDollar
    )


}