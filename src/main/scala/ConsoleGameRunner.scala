package com.tictactoe


class ConsoleGameRunner {
    var game = new Game()
    var ui = new ConsoleUI(game)
    
    val QuitMessage = "Quitting. Thanks for playing."
    
    def run {
        try {
            ui.welcomeUser
            do {
                gameLoop
                endGame 
            } while (playAgain)
        } catch {
            case e: java.io.IOException => println(QuitMessage)            
        }
    }
    
    def gameLoop {
        game.restart
        while (!game.isGameOver) {
            ui.displayBoard
            makeNextMove
        }
    }
    
    def makeNextMove {
        if(game.isCurrentPlayerHuman) {
            game.makeMove(ui.getMoveFromUser)
        }
        else {
            ui.computerIsMakingMove
            game.makeComputerMove
        }
    }
    
    def endGame {
        ui.displayBoard
        ui.gameOver
    }
    
    def playAgain: Boolean = {
        return (ui.askUserToPlayAgain) 
    }
}