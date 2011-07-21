package com.tictactoe

class SquareParser(game: Game) extends InputParser {
 
    def parsedInput(input: String): String = {
        return inputMinusOne(input).toString
    }
    
    def isValid(input: String): Boolean = {
        val validInputs = List("1", "2", "3", "4", "5", "6", "7", "8", "9")

        return (
                validInputs.contains(input) && 
                game.isSquareUnoccupied(inputMinusOne(input)) 
        )
    }
    
    def inputMinusOne(input: String): Int = input.toInt - 1
}