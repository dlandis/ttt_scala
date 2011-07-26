package com.tictactoe


class GameRunner {
    var game = new Game()
    var ui = new ConsoleUI(game)
    
    def main {
        do {
            gameLoop
            endGame 
        } while (playAgain)
        
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
            game.makeMove(ui.getMoveFromUser.toInt)
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