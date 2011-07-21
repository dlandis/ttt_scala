package com.tictactoe

class ConsoleUI(game: Game) {
    var io = Console
    var in = io.in

    def displayMessage(string: String) {
        println(string)
    }
    
    def displayBoard {
        println("1    |2    |3    ")
        printRow(0, 1, 2)
        println("     |     |     ")
        println("-----------------")
        println("4    |5    |6    ")
        printRow(3, 4, 5)
        println("     |     |     ")
        println("-----------------")
        println("7    |8    |9    ")
        printRow(6, 7, 8)
        println("     |     |     ")
        println(" ")
    }
    
    def printRow(first: Int, second: Int, third: Int) {
        println(
            " " + game.getSquareValue(first) + 
            " " + game.getSquareValue(second) + 
            " " + game.getSquareValue(third) 
        )
    }
    
    def gameOver {
        displayMessage(game.getStatus)
    }
    
    def getInput: String = {
        return readLine.trim
    }
    
    private def readLine: String = {
        return in.readLine
    }
    
    private def println(output:String ) {
        io.out.println(output)
    }
}