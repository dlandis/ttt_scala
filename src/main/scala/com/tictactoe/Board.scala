package com.tictactoe

object Board {
	private val InProgressStatus = "Game In Progress"
	private val DrawStatus = "Draw"

	def apply(pOne: String, pTwo: String): Board = new Board(pOne, pTwo)
}

class Board(PlayerOne: String, PlayerTwo: String) {
	import Board._

	private var boardStatus = InProgressStatus	
	private val boardValues: Array[Option[String]] = Array.fill(9)(None)	
		
	def status: String = boardStatus

	def makeMove(square: Int, symbol: Option[String]) = {
		setSquareValue(square, symbol)
		updateStatus
	}

	private def setSquareValue(square: Int, symbol: Option[String]) = { boardValues(square) = symbol }

	def updateStatus = {
		boardStatus =
			if (isPlayerWinner("O")) PlayerTwo + " wins!"
			else if (isPlayerWinner("X")) PlayerOne + " wins!"
			else if (allSquaresOccupied) DrawStatus
			else boardStatus
	}

	def undoMove(square: Int) = {
		setSquareValue(square, None)
		boardStatus = InProgressStatus
	}

	def dup: Board = {
		val duplicate = Board(PlayerOne, PlayerTwo)
		(0 to boardValues.length - 1).foreach(i => duplicate.makeMove(i, getSquareValue(i)))		
		duplicate
	}

	def opponentOf(symbol: String): String =
		symbol match {
			case PlayerOne => PlayerTwo
			case _ => PlayerOne
		}

	def allSquaresOccupied = !boardValues.contains(None)

	def isSquareUnoccupied(square: Int) = boardValues(square) == None

	def unoccupiedSquares = (0 to boardValues.length - 1).filter(isSquareUnoccupied)

	def getSquareValue(square: Int): Option[String] = boardValues(square)

	def isPlayerWinner(symbol: String) = {
		def rows = allMatchSymbol(0, 1, 2) || allMatchSymbol(3, 4, 5) || allMatchSymbol(6, 7, 8)
		def columns = allMatchSymbol(0, 3, 6) || allMatchSymbol(1, 4, 7) || allMatchSymbol(2, 5, 8)
		def diagonals = allMatchSymbol(0, 4, 8) || allMatchSymbol(2, 4, 6)
		def allMatchSymbol(combo: Int*) = combo.forall(boardValues(_) == Some(symbol))
				
		rows || columns || diagonals
	}

	def isAtDraw: Boolean = boardStatus == DrawStatus
	def isGameOver: Boolean = boardStatus != InProgressStatus

	def clear = {
		boardValues.map(_ => None)
		boardStatus = InProgressStatus
	}
}