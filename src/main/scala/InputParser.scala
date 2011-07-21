package com.tictactoe

abstract class InputParser {
    type ParsedValue
    def parsedInput(input: String): ParsedValue
    
    def isValid(input: String): Boolean
    
}