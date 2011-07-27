package com.tictactoe

class SquareParser(game: Game) {
 
    def parsedInput(input: String): Int = {
        return inputMinusOne(input)
    }
    
    def isValid(input: String): Boolean = {
        val validInputs = List("1", "2", "3", "4", "5", "6", "7", "8", "9")

        return (
                validInputs.contains(input) && 
                game.isSquareUnoccupied(inputMinusOne(input)) 
        )
    }
    
    private def inputMinusOne(input: String): Int = input.toInt - 1
}