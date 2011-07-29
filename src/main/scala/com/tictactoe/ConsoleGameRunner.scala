package com.tictactoe

object Main extends App {
	private val QuitMessage = "Quitting. Thanks for playing."

	val game = Game()
	val ui = ConsoleUI(game)

	def gameLoop {
		def moveLoop {
			def makeNextMove {
				if (game.isCurrentPlayerHuman) {
					game.makeMove(ui.getMoveFromUser)
				} else {
					ui.computerIsMakingMove
					game.makeComputerMove
				}
			}

			if (!game.isGameOver) {
				ui.displayBoard
				makeNextMove
				moveLoop
			}
		}

		game.restart
		moveLoop
		ui.displayBoard
		ui.gameOver

		if (ui.askUserToPlayAgain)
			gameLoop
	}

	try {
		ui.welcomeUser
		gameLoop
	} catch {
		case e: java.io.IOException => println(QuitMessage)
	}
}