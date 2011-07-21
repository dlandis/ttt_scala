package com.tictactoe

class ConsoleUI(game: Game) {
    val SquareSelectPrompt = "Please select a square (1-9) - "
    val PlayAgainPrompt = "Play again? (Y/N) - "
    
    var io = Console
    var in = io.in
    
    var squareParser: InputParser = new SquareParser(game)
    var playAgainParser: InputParser = new PlayAgainParser()
    

    def displayMessage(string: String) {
        println(string)
    }
    
    def displayBoard {
        println("1    |2    |3    ")
        printRow(0, 1, 2)
        println("     |     |     ")
        println("-----------------")
        println("4    |5    |6    ")
        printRow(3, 4, 5)
        println("     |     |     ")
        println("-----------------")
        println("7    |8    |9    ")
        printRow(6, 7, 8)
        println("     |     |     ")
        println(" ")
    }
    
    def printRow(first: Int, second: Int, third: Int) {
        println(
            " " + game.getSquareValue(first) + 
            " " + game.getSquareValue(second) + 
            " " + game.getSquareValue(third) 
        )
    }
    
    def gameOver {
        displayMessage(game.getStatus)
    }
    
    def getMoveFromUser: InputParser#ParsedValue = {
        val validInput = getValidInput(squareParser, SquareSelectPrompt)
        val parsedInput = squareParser.parsedInput(validInput)
        return parsedInput
    }
    
    def askUserToPlayAgain: String = {
        return getValueFromUser(playAgainParser, PlayAgainPrompt).toString
    }
    
    def getValueFromUser(parser: InputParser, message: String): Any = {
        val validInput = getValidInput(parser, message)
        return parser.parsedInput(validInput)
    }
    
    def getValidInput(parser: InputParser, message: String): String = {
        var input = ""
        do {
            displayMessage(message)
            input = getInput
        } while (!parser.isValid(input))
        
        return input
    }
    
    def getInput: String = {
        return readLine.trim
    }
    
    private def readLine: String = {
        return in.readLine
    }
    
    private def println(output:String ) {
        io.out.println(output)
    }
}