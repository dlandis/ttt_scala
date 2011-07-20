package com.tictactoe

import scala.util.control.Breaks._

class AlphaBeta(playerSymbol: String) {
    
    private[this] val maxPlayer = playerSymbol
    
    private[this] val MaxScore = 20
    private[this] val MinScore = -20
    private[this] val DrawScore = 0
    private[this] val InProgressScore = -1
    private[this] val MaxStartValue = -100
    private[this] val MinStartValue = 100
    private[this] val DepthStartValue = 0 
    
        
    def getMove(board: Board): Int = {
        val potential = board.dup
        val moveScores = scoreMoves(potential)
        return moveWithHighestScore( moveScores )
    }
    
    def scoreMoves(board: Board): IndexedSeq[Tuple2[Int, Int]] = {
        val opponent = board.opponentOf(maxPlayer)
        
        board.unoccupiedSquares.map ( square => { 
            ( square, scoreMove(square, board, opponent) )
        })
    }
    
    def scoreMove(square: Int, board: Board, opponent: String): Int = {
        board.makeMove(square, maxPlayer)
        val score = alphabeta(board, opponent, MaxStartValue, MinStartValue, DepthStartValue)
        board.undoMove(square)
        
        score
    }
    
    def moveWithHighestScore(moveScores: IndexedSeq[Tuple2[Int, Int]]) = {
        var bestScore = -100
        var bestMove = -1
        
        moveScores.foreach { 
            case (square, value) => {
                if ( value > bestScore) {
                    bestScore = value
                    bestMove = square
                }
            }
        }
        
        bestMove
    }
    
    def alphabeta(board: Board, currentPlayer: String, a:Int, b:Int, depth: Int): Int = {
        if ( board.isGameOver ) {
            return heuristicValueOfLastMove(board, depth)
        }

        val opponent = board.opponentOf(currentPlayer)
        var bestScore = startingBestScore(currentPlayer)
        var alpha = a
        var beta = b
        
        breakable {
            board.unoccupiedSquares.foreach ( square => {
                board.makeMove(square, currentPlayer)
                var newScore = alphabeta(board, opponent, alpha, beta, depth + 1)
                board.undoMove(square)
                
                if ( isBetterMove(currentPlayer, newScore, bestScore)) {
                    bestScore = newScore
                }
                
                if (currentPlayer == maxPlayer) {
                    alpha = bestScore
                }
                else {
                    beta = bestScore
                }

                if ( beta <= alpha ) { break }
                
            })
        }
        return bestScore
    }
    
    def heuristicValueOfLastMove(board: Board, depth: Int): Int = {
        var score = InProgressScore

        if ( board.isPlayerWinner(maxPlayer) ) {
            score = MaxScore - depth
        }
        else if ( board.isPlayerWinner(board.opponentOf(maxPlayer)) ) {
            score = MinScore + depth
        }
        else if ( board.isAtDraw ) {
            score = DrawScore
        }
        
        return score
    }
    
    def isBetterMove(currentPlayer:String, newScore:Int, bestScore:Int): Boolean = {
        if (currentPlayer == maxPlayer) {
            return ( newScore > bestScore )
        }
        return ( newScore < bestScore )
    }
    
    def startingBestScore(currentPlayer: String): Int = {
        if (currentPlayer == maxPlayer) {
            return MaxStartValue
        }
        return MinStartValue
    }
    
}