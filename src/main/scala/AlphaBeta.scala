package com.tictactoe

class AlphaBeta(playerSymbol: String) {
    
    private[this] val player = playerSymbol
    
    def getMove(board: Board): Int = {
        2
    }
    
    def heuristicValueOfLastMove(board: Board, depth: Int): Int = {
        var score = -1

        if ( board.isPlayerWinner(player) ) {
            score = 20 - depth
        }
        else if ( board.isPlayerWinner(board.opponentOf(player)) ) {
            score = -20 + depth
        }
        else if ( board.isAtDraw ) {
            score = 0
        }
        
        score
    }
    
}