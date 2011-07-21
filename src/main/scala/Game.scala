package com.tictactoe

class Game {
    private[this] val PlayerOne = "X"
    private[this] val PlayerTwo = "O"
    
    private[this] var currentPlayer = PlayerOne
    var board = new Board(PlayerOne, PlayerTwo)
    var ai = new AlphaBeta(PlayerTwo)
    
    def getCurrentPlayer = currentPlayer
    
    def switchCurrentPlayer {
        if (currentPlayer == PlayerOne) { 
            currentPlayer = PlayerTwo
        }
        else {
            currentPlayer = PlayerOne
        }
    }
    
    def makeMove(square: Int) = {
        board.makeMove(square, currentPlayer)
        switchCurrentPlayer
    }
    
    def makeComputerMove {
        val square = getMoveFromAI
        makeMove(square)
    }
    
    def isGameOver = {
        board.isGameOver
    }
    
    def getSquareValue(square: Int) = {
        board.getSquareValue(square)
    }
    
    def getStatus = {
        board.status
    }
    

    
    def getMoveFromAI: Int = {
        ai.getMove(board)
    }
}