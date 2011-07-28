package com.tictactoe

class ConsoleUI(game: Game) {
    val WelcomeMessage = "\nWelcome to Tic Tac Toe in Scala!"
    val InstructionsMessage = "You are 'X', and X goes first.\n"
    val SquareSelectPrompt = "Please select a square (1-9) - "
    val PlayAgainPrompt = "Play again? (Y/N) - "
    val ComputerMoveMessage = "Computer's turn...\n"
    
    val io = Console
    var in = io.in
    
    var squareParser = new SquareParser(game)
    var playAgainParser = new PlayAgainParser()
    
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
            "  " + game.getSquareValue(first) + 
            "  |  " + game.getSquareValue(second) + 
            "  |  " + game.getSquareValue(third) 
        )
    }
    
    def welcomeUser {
        println(WelcomeMessage)
        println(InstructionsMessage)
    }
    def gameOver {
        println(game.getStatus)
    }
    
    def computerIsMakingMove {
        println(ComputerMoveMessage)
    }
    
    def getMoveFromUser: Int = {
        val validate = (input:String) => squareParser.isValid(input)

        return squareParser.parsedInput(getValidInput(validate, SquareSelectPrompt))
    }
    
    def askUserToPlayAgain: Boolean = {
        val validate = (input:String) => playAgainParser.isValid(input)

        return playAgainParser.parsedInput(getValidInput(validate, PlayAgainPrompt))
    }
    
    def getValidInput(validate: Function[String, Boolean], prompt:String): String = {
        var input = ""
        do {
            input = getInputWithPrompt(prompt)
        } while (!validate(input))
        return input
    }
    
    def getInputWithPrompt(prompt: String): String = {
        inputPrompt(prompt)
        return getInput
    }
    
    def inputPrompt(string: String) {
        printf(string)
    }
    
    def getInput: String = {
        val input = readLine.trim
        println("")
        return input
    }
    
    private def readLine: String = {
        return in.readLine
    }
    
    private def printf(output:String ) {
        io.out.printf(output)
    }
    
    private def println(output:String ) {
        io.out.println(output)
    }
}