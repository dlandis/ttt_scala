package com.tictactoe

class PlayAgainParser extends InputParser {
    def parsedInput(input: String): String = {
        return input.matches("[Yy].*").toString
    }
    
    def isValid(input: String): Boolean = {
        return true
    }
    
}