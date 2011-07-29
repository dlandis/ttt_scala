package com.tictactoe

object Main extends App {
	private val QuitMessage = "Quitting. Thanks for playing."

	val game = Game()
	val ui = ConsoleUI(game)

	def makeNextMove {
		if (game.isCurrentPlayerHuman) {
			game.makeMove(ui.getMoveFromUser)
		} else {
			ui.computerIsMakingMove
			game.makeComputerMove
		}
	}

	def gameLoop {
		game.restart
		while (!game.isGameOver) {
			ui.displayBoard
			makeNextMove
		}
	}

	def endGame {
		ui.displayBoard
		ui.gameOver
	}

	def playAgain: Boolean = ui.askUserToPlayAgain

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