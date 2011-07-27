package com.tictactoe

class Game {
    private val PlayerOne = "X"
    private val PlayerTwo = "O"
    
    private var currentPlayer = PlayerOne
    var board = new Board(PlayerOne, PlayerTwo)
    var ai = new AlphaBeta(PlayerTwo)
    
    def getCurrentPlayer: String = currentPlayer
    
    def isCurrentPlayerHuman: Boolean = {
        return isPlayerHuman(getCurrentPlayer)
    }
    
    def isPlayerHuman(symbol: String): Boolean = {
        return (symbol == PlayerOne)
    }
    
    def makeComputerMove {
        val square = getMoveFromAI
        makeMove(square)
    }
    
    def getMoveFromAI: Int = {
        ai.getMove(board)
    }
    
    def makeMove(square: Int) = {
        board.makeMove(square, currentPlayer)
        switchCurrentPlayer
    }
    
    def switchCurrentPlayer {
        if (currentPlayer == PlayerOne) { 
            currentPlayer = PlayerTwo
        }
        else {
            currentPlayer = PlayerOne
        }
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
}