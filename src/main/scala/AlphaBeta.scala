package com.tictactoe

class AlphaBeta(playerSymbol: String) {
    
    private val maxPlayer = playerSymbol
    
    private val MaxScore = 20
    private val MinScore = -20
    private val DrawScore = 0
    private val InProgressScore = -1
    private val MaxStartValue = -100
    private val MinStartValue = 100
    private val DepthStartValue = 0 
    
        
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
                if ( value > bestScore ) {
                    bestScore = value
                    bestMove = square
                }
            }
        }
        
        bestMove
    }
    
    def alphabeta(board: Board, currentPlayer: String, a:Int, b:Int, depth: Int): Int = {
        if ( board.isGameOver ) {
            return scoreOfLastMove(board, depth)
        }

        val opponent = board.opponentOf(currentPlayer)
        var bestScore = startingBestScore(currentPlayer)
        var alpha = a
        var beta = b

        board.unoccupiedSquares.foreach ( square => {
            board.makeMove(square, currentPlayer)
            var newScore = alphabeta(board, opponent, alpha, beta, depth + 1)
            board.undoMove(square)
            
            if ( isBetterMove(currentPlayer, newScore, bestScore) ) {
                bestScore = newScore
            }
            
            if ( inMaxSearchLevel(currentPlayer) ) { alpha = bestScore } 
            else { beta = bestScore }
            
            if ( shouldPrune(alpha, beta) ) { return bestScore }
        })

        return bestScore
    }
    
    def scoreOfLastMove(board: Board, depth: Int): Int = {
        if ( board.isPlayerWinner(maxPlayer) ) {
            return MaxScore - depth
        }
        else if ( board.isPlayerWinner(board.opponentOf(maxPlayer)) ) {
           return MinScore + depth
        }
        else if ( board.isAtDraw ) {
            return DrawScore
        }
        
        return InProgressScore
    }
    
    private def isBetterMove(currentPlayer:String, newScore:Int, bestScore:Int): Boolean = {
        if (currentPlayer == maxPlayer) {
            return ( newScore > bestScore )
        }
        return ( newScore < bestScore )
    }
    
    private def inMaxSearchLevel(currentPlayer: String): Boolean = {
        return currentPlayer == maxPlayer
    }
    
    private def startingBestScore(currentPlayer: String): Int = {
        if (currentPlayer == maxPlayer) {
            return MaxStartValue
        }
        return MinStartValue
    }
    
    private def shouldPrune(alpha: Int, beta: Int): Boolean = {
         return beta <= alpha 
    }
    
}