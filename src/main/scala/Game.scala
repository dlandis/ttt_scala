package com.tictactoe

class Game {
    val playerOne = new Player("X")
    val playerTwo = new Player("O", false)
    
    private var currentPlayer = playerOne
    var board = new Board(playerOne.symbol, playerTwo.symbol)
    var ai = new AlphaBeta(playerTwo.symbol)
    
    def getCurrentPlayer: Player = currentPlayer
    
    def isCurrentPlayerHuman: Boolean = {
        return currentPlayer.isHuman
    }
    
    def isPlayerHuman(player: Player): Boolean = {
        return player.isHuman
    }
    
    def makeComputerMove {
        val square = getMoveFromAI
        makeMove(square)
    }
    
    def getMoveFromAI: Int = {
        ai.getMove(board)
    }
    
    def makeMove(square: Int) = {
        board.makeMove(square, currentPlayer.symbol)
        switchCurrentPlayer
    }
    
    def switchCurrentPlayer {
        if (currentPlayer == playerOne) { 
            currentPlayer = playerTwo
        }
        else {
            currentPlayer = playerOne
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
        currentPlayer = playerOne
    }
}