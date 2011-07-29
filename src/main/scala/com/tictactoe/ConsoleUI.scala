package com.tictactoe

object ConsoleUI{
	val WelcomeMessage = "\nWelcome to Tic Tac Toe in Scala!"
	val InstructionsMessage = "You are 'X', and X goes first.\nQuit at any time by typing 'quit'\n"
	val InvalidMessage = "Invalid selection. "
	val SquareSelectPrompt = "Please select an empty square (1-9) - "
	val PlayAgainPrompt = "Play again? (Y/N) - "
	val ComputerMoveMessage = "Computer's turn...\n"
	
	def apply(game: Game): ConsoleUI = new ConsoleUI(game)
}

class ConsoleUI(game: Game) {
	import ConsoleUI._

	val io = Console
	val in = io.in

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
		println("  " + game.getSquareValue(first) + "  |  " + game.getSquareValue(second) + "  |  " + game.getSquareValue(third))
	}

	def welcomeUser {
		println(WelcomeMessage)
		println(InstructionsMessage)
	}

	def gameOver { println(game.getStatus) }

	def computerIsMakingMove { println(ComputerMoveMessage) }

	def getMoveFromUser: Int = SquareParser.parsedInput(getValidInput(input => SquareParser.isValid(input), SquareSelectPrompt))

	def askUserToPlayAgain: Boolean = PlayAgainParser.parsedInput(getValidInput(input => PlayAgainParser.isValid(input), PlayAgainPrompt))

	def getValidInput(validate: Function[String, Boolean], prompt: String): String = {
		var input = ""
		do {
			input = getInputWithPrompt(prompt)
			if (!validate(input)) printf(InvalidMessage)
		} while (!validate(input))
			
		input
	}

	def getInputWithPrompt(prompt: String): String = {
		def getInput: String = {
			val input = readLine.trim
			blankLine
			if (shouldQuit(input)) throw new java.io.IOException()
			return input
		}

		def inputPrompt(string: String) { printf(string) }

		inputPrompt(prompt)
		getInput
	}

	private def shouldQuit(input: String): Boolean = input.matches("quit")
	private def readLine: String = in.readLine

	private def printf(output: String) { io.out.printf(output) }
	private def println(output: String) { io.out.println(output) }
	private def blankLine { io.out.println() }

	object PlayAgainParser {
		def parsedInput(input: String): Boolean = input.matches("[Yy].*")
		def isValid(input: String): Boolean = true
	}

	object SquareParser {
		private val validInputs = List("1", "2", "3", "4", "5", "6", "7", "8", "9")
		def parsedInput(input: String): Int = inputMinusOne(input)
		def isValid(input: String): Boolean = validInputs.contains(input) && game.isSquareUnoccupied(inputMinusOne(input))
		private def inputMinusOne(input: String): Int = input.toInt - 1
	}
}