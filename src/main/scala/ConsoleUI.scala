package com.tictactoe

class ConsoleUI {
    var io = Console
    var out = io.out
    var in = io.in

    def welcomeUser {
        println("Welcome to Tic Tac Toe in Scala")
    }
    
    def println(output:String ) {
        io.out.println(output)
    }
}