package com.tictactoe

class PlayAgainParser {
    def parsedInput(input: String): Boolean = {
        return input.matches("[Yy].*")
    }
    
    def isValid(input: String): Boolean = {
        return true
    }
    
}