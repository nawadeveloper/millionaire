package np.com.nawarajbista.millionaire.viewmodel


data class DataBindingQuestion(val level: Int, val q: String?, val options: List<String>) {

    var prize = 0
    var win = 0
    var questionNumber = 1
    init {
        when(this.level) {
            0 -> {
                this.prize = 1000
                this.win = 0
            }
            1 -> {
                this.questionNumber = 2
                this.prize = 10000
                this.win = 1000

            }

            2 -> {
                this.questionNumber = 3
                this.prize = 20000
                this.win = 10000
            }

            3 -> {
                this.questionNumber = 4
                this.prize = 50000
                this.win = 20000
            }

            4 -> {
                this.questionNumber = 5
                this.prize = 100000
                this.win = 50000
            }
        }
    }

    var message = "Question number $questionNumber for $$prize"

}
