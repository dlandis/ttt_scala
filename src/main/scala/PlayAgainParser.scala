package com.tictactoe

class PlayAgainParser extends InputParser {
    type ParsedValue = Boolean
    def parsedInput(input: String): ParsedValue = {
        return input.matches("^[yY]")
    }
    
    def isValid(input: String): Boolean = {
        return true
    }
    
}