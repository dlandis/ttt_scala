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
        gameLoop
        endGame
    }
    
    def gameLoop {
        while (!game.isGameOver) {
            ui.displayBoard
            var move = ui.getMoveFromUser
            game.makeMove(move.toInt)
            game.makeComputerMove
            ui.displayBoard
        }
    }
    
    def endGame {
        ui.gameOver
    }
}