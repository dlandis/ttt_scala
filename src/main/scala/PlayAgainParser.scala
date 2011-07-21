package com.tictactoe

class PlayAgainParser extends InputParser {
    type ParsedValue = String
    def parsedInput(input: String): ParsedValue = {
        return "foo"
    }
    
    def isValid(input: String): Boolean = {
        return true
    }
    
}