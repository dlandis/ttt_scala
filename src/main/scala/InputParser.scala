package com.tictactoe

abstract class InputParser {
    
    def parsedInput(input: String): String
    
    def isValid(input: String): Boolean
    
}