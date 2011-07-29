package com.tictactoe

object Board {
	private val InProgressStatus = "Game In Progress"
	private val DrawStatus = "Draw"
	private val EmptySquare = "-"

	def apply(pOne: String, pTwo: String): Board = new Board(pOne, pTwo)
}

class Board(pOne: String, pTwo: String) {
	import Board._

	def playerOne: String = pOne
	def playerTwo: String = pTwo

	val currentPlayer = playerOne

	private var boardStatus = InProgressStatus
	private val boardValues = new Array[String](9)

	clear

	def status: String = boardStatus

	def makeMove(square: Int, symbol: String) = {
		setSquareValue(square, symbol)
		updateStatus
	}

	private def setSquareValue(square: Int, symbol: String) = { boardValues(square) = symbol }

	def updateStatus = {
		boardStatus =
			if (isPlayerWinner("O")) playerTwo + " wins!"
			else if (isPlayerWinner("X")) playerOne + " wins!"
			else if (allSquaresOccupied) DrawStatus
			else boardStatus
	}

	def undoMove(square: Int) = {
		setSquareValue(square, EmptySquare)
		boardStatus = InProgressStatus
	}

	def dup: Board = {
		val duplicatedBoard = Board(playerOne, playerTwo)
		for (i <- 0 to 8) duplicatedBoard.makeMove(i, getSquareValue(i))
		duplicatedBoard
	}

	def opponentOf(symbol: String): String = if (symbol == playerOne) playerTwo else playerOne

	def allSquaresOccupied = boardValues.count(_ != EmptySquare) == 9

	def isSquareUnoccupied(square: Int) = boardValues(square) == EmptySquare

	def unoccupiedSquares =
		for {
			square <- 0 to 8
			if isSquareUnoccupied(square)
		} yield square

	def getSquareValue(square: Int) = boardValues(square)

	def isPlayerWinner(symbol: String) = rowsVictory(symbol) || columnsVictory(symbol) || diagonalsVictory(symbol)

	private def rowsVictory(symbol: String) = threeInARow(symbol, (0, 1, 2)) || threeInARow(symbol, (3, 4, 5)) || threeInARow(symbol, (6, 7, 8))
	private def columnsVictory(symbol: String) = threeInARow(symbol, (0, 3, 6)) || threeInARow(symbol, (1, 4, 7)) || threeInARow(symbol, (2, 5, 8))
	private def diagonalsVictory(symbol: String) = threeInARow(symbol, (0, 4, 8)) || threeInARow(symbol, (2, 4, 6))
	private def threeInARow(symbol: String, combo: Tuple3[Int, Int, Int]) =
		(boardValues(combo._1) == symbol && boardValues(combo._2) == symbol && boardValues(combo._3) == symbol)

	def isAtDraw: Boolean = boardStatus == DrawStatus
	def isGameOver: Boolean = boardStatus != InProgressStatus

	def clear = {
		for (i <- 0 to 8) { boardValues(i) = EmptySquare }
		boardStatus = InProgressStatus
	}
}