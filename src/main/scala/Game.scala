package com.tictactoe

class Game {
    private[this] val PlayerOne = "X"
    private[this] val PlayerTwo = "O"
    
    private[this] var currentPlayer = PlayerOne
    var board = new Board(PlayerOne, PlayerTwo)
    var ai = new AlphaBeta(PlayerTwo)
    
    def getCurrentPlayer: String = currentPlayer
    
    def switchCurrentPlayer {
        if (currentPlayer == PlayerOne) { 
            currentPlayer = PlayerTwo
        }
        else {
            currentPlayer = PlayerOne
        }
    }
    
    def isCurrentPlayerHuman: Boolean = {
        return isPlayerHuman(getCurrentPlayer)
    }
    
    def isPlayerHuman(symbol: String): Boolean = {
        return (symbol == PlayerOne)
    }
    
    def makeMove(square: Int) = {
        board.makeMove(square, currentPlayer)
        switchCurrentPlayer
    }
    
    def makeComputerMove {
        val square = getMoveFromAI
        makeMove(square)
    }
    
    def isGameOver: Boolean = {
        board.isGameOver
    }
    
    def getSquareValue(square: Int): String = {
        return board.getSquareValue(square)
    }
    
    def isSquareUnoccupied(square: Int): Boolean = {
        return board.isSquareUnoccupied(square)
    }
    
    def getStatus: String = {
        board.status
    }
    
    def restart = {
        board.clear
        currentPlayer = PlayerOne
    }

    
    def getMoveFromAI: Int = {
        ai.getMove(board)
    }
}