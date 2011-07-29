package com.tictactoe

object Game{
	private val playerOne = new Player("X")
	private val playerTwo = new Player("O", false)
	
	def apply() = new Game
}

class Game {
	import Game._
	
	private var currentPlayer = playerOne
	
	var board = Board(playerOne.symbol, playerTwo.symbol)
	var ai = AlphaBeta(playerTwo.symbol)

	def getCurrentPlayer: Player = currentPlayer

	def isCurrentPlayerHuman: Boolean = currentPlayer.isHuman

	def makeComputerMove { makeMove(getMoveFromAI) }

	def getMoveFromAI: Int = ai.getMove(board)

	def makeMove(square: Int) = {
		board.makeMove(square, Some(currentPlayer.symbol))
		switchCurrentPlayer
	}

	def switchCurrentPlayer { currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne }

	def isGameOver: Boolean = board.isGameOver

	def getSquareValue(square: Int): Option[String] = board.getSquareValue(square)		

	def isSquareUnoccupied(square: Int): Boolean = board.isSquareUnoccupied(square)

	def getStatus: String = board.status

	def restart = {
		board.clear
		currentPlayer = playerOne
	}
}