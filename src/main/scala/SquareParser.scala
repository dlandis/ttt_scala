package com.tictactoe

class SquareParser(game: Game) {
    def isValid(input: String): Boolean = {
        val validInputs = List("1", "2", "3", "4", "5", "6", "7", "8", "9")

        return ( validInputs.contains(input) && game.isSquareUnoccupied(input.toInt - 1) )
    }
}