package com.tictactoe

object Main {
    def main(args:Array[String]){
        val gameRunner = new GameRunner()
        gameRunner.main
    }
}
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
            game.makeComputerMove
        }
    }
    
    def endGame {
        ui.displayBoard
        ui.gameOver
    }
    
    def playAgain: Boolean = {
        return (ui.askUserToPlayAgain == "true") 
    }
}