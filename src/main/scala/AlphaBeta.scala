package com.tictactoe

class AlphaBeta(playerSymbol: String) {
    
    private[this] val player = playerSymbol
    
    private[this] val MaxScore = 20
    private[this] val MinScore = -20
    private[this] val DrawScore = 0
    private[this] val InProgressScore = -1
    
    def getMove(board: Board): Int = {
        var current = board
        2
        
    }
    
    def scoreMoves {
        
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
    
}