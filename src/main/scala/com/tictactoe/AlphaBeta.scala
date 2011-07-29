package com.tictactoe

object AlphaBeta{
	private val MaxScore = 20
	private val MinScore = -20
	private val DrawScore = 0
	private val InProgressScore = -1
	private val MaxStartValue = -100
	private val MinStartValue = 100
	private val DepthStartValue = 0	
	
	def apply(playerSymbol: String): AlphaBeta = new AlphaBeta(playerSymbol)
}

class AlphaBeta(playerSymbol: String) {
	import AlphaBeta._
	
	private val maxPlayer = playerSymbol

	def getMove(board: Board): Int = moveWithHighestScore(scoreMoves(board.dup))

	def scoreMoves(board: Board): IndexedSeq[Tuple2[Int, Int]] = {
		def scoreMove(square: Int, opponent: String): Int = {
			board.makeMove(square, Some(maxPlayer))
			val score = alphabeta(board, opponent, MaxStartValue, MinStartValue, DepthStartValue)
			board.undoMove(square)
			score
		}

		val opponent = board.opponentOf(maxPlayer)
		board.unoccupiedSquares.map(square => (square, scoreMove(square, opponent)))
	}

	def moveWithHighestScore(moveScores: IndexedSeq[Tuple2[Int, Int]]) = {
		var bestScore = -100
		var bestMove = -1

		moveScores.foreach {
			case (square, value) => {
				if (value > bestScore) {
					bestScore = value
					bestMove = square
				}
			}
		}

		bestMove
	}

	def alphabeta(board: Board, currentPlayer: String, a: Int, b: Int, depth: Int): Int = {
		def isBetterMove(newScore: Int, bestScore: Int): Boolean = if (currentPlayer == maxPlayer) newScore > bestScore else newScore < bestScore

		def shouldPrune(alpha: Int, beta: Int): Boolean = beta <= alpha

		def inMaxSearchLevel: Boolean = currentPlayer == maxPlayer

		def startingBestScore: Int = if (currentPlayer == maxPlayer) MaxStartValue else MinStartValue

		def scoreOfLastMove: Int =
			if (board.isPlayerWinner(maxPlayer))
				MaxScore - depth
			else if (board.isPlayerWinner(board.opponentOf(maxPlayer)))
				MinScore + depth
			else if (board.isAtDraw)
				DrawScore
			else
				InProgressScore

		if (board.isGameOver) {
			scoreOfLastMove
		} else {
			val opponent = board.opponentOf(currentPlayer)
			var bestScore = startingBestScore
			var alpha = a
			var beta = b

			board.unoccupiedSquares.foreach(square => {
				board.makeMove(square, Some(currentPlayer))
				var newScore = alphabeta(board, opponent, alpha, beta, depth + 1)
				board.undoMove(square)

				if (isBetterMove(newScore, bestScore)) {
					bestScore = newScore
				}

				if (inMaxSearchLevel)
					alpha = bestScore
				else
					beta = bestScore

				if (shouldPrune(alpha, beta))
					return bestScore
			})

			bestScore
		}
	}
}