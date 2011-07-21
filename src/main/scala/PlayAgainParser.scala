package com.tictactoe

class PlayAgainParser extends InputParser {
    def parsedInput(input: String): String = {
        return input.matches("^[yY]").toString
    }
    
    def isValid(input: String): Boolean = {
        return true
    }
    
}