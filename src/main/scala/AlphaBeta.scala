package com.tictactoe

class AlphaBeta(playerSymbol: String) {
    
    private[this] val player = playerSymbol
    
    private[this] val MaxScore = 20
    private[this] val MinScore = -20
    private[this] val DrawScore = 0
    private[this] val InProgressScore = -1
    
    private[this] var current = new Board("X", "O")
    private[this] var moveScores = new Array[Int](9)
    
    def getMove(board: Board): Int = {
        current = board
        scoreMoves
        moveWithHighestScore
    }
    
    def scoreMoves {
        var potential = current.dup
        val opponent = potential.opponentOf(player)
        for (i <- 0 to 8) { moveScores(i) = InProgressScore }
        
        for (square <- 0 to 8) {
            if (potential.isSquareUnoccupied(square)) {
                potential.makeMove(square, player)
                moveScores(square) = alphabeta(potential, opponent, 0)
                potential.undoMove(square)
            }
        }
        
    }
    
    def moveWithHighestScore: Int = {
        var bestScore = -100
        var bestMove = -1
        
        for (square <- 0 to 8) {
            if ( moveScores(square) != -1 ) {
                if ( moveScores(square) > bestScore) {
                    bestScore = moveScores(square)
                    bestMove = square
                }
            }
        }
        
        bestMove
    }
    
    def alphabeta(board: Board, currentPlayer: String, depth: Int): Int = {
        if ( board.isGameOver ) {
            return heuristicValueOfLastMove(board, depth)
        }
        
        var potential = board.dup
        val opponent = potential.opponentOf(currentPlayer)
        var bestScore = startingBestScore(currentPlayer)
        
        for (square <- 0 to 8) {
            if ( potential.isSquareUnoccupied(square) ) {
                potential.makeMove(square, currentPlayer)
                var newScore = alphabeta(potential, opponent, depth + 1)
                if ( isBetterMove(currentPlayer, newScore, bestScore)) {
                    bestScore = newScore
                }
            potential.undoMove(square)
            }
        }
        
        return bestScore
    }
    
    def heuristicValueOfLastMove(board: Board, depth: Int): Int = {
        var score = InProgressScore

        if ( board.isPlayerWinner(player) ) {
            score = MaxScore - depth
        }
        else if ( board.isPlayerWinner(board.opponentOf(player)) ) {
            score = MinScore + depth
        }
        else if ( board.isAtDraw ) {
            score = DrawScore
        }
        
        score
    }
    
    def isBetterMove(currentPlayer:String, newScore:Int, bestScore:Int): Boolean = {
        if (currentPlayer == player) {
            return ( newScore > bestScore )
        }
        return ( newScore < bestScore )
    }
    
    def startingBestScore(currentPlayer: String): Int = {
        if (currentPlayer == player) {
            return -100
        }
        return 100
    }
    
}