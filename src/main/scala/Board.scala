package com.tictactoe

class Board(pOne:String, pTwo:String) {
    val InProgressStatus = "Game In Progress"
    val DrawStatus = "Draw"
    val EmptySquare = "-"
    
    def playerOne: String = pOne
    def playerTwo: String = pTwo
    
    val currentPlayer = playerOne 
    
    private[this] var boardValues = new Array[String](9)
    for (i <- 0 to 8) { boardValues(i) = EmptySquare }
    
    private[this] var boardStatus = InProgressStatus
    
    def status: String = boardStatus
    
    def makeMove(square:Int, symbol:String) = {
        setSquareValue(square, symbol)
        updateStatus
    }
    
    def setSquareValue(square:Int, symbol:String) = {
        boardValues(square) = symbol
    }
    
    def updateStatus = {
        if ( isPlayerWinner("O") ) {
            boardStatus = "O wins!"
        }
        else if ( isPlayerWinner("X") ) {
            boardStatus = "X wins!"
        }
        else if (allSquaresOccupied) {
            boardStatus = DrawStatus
        }
    }
    
    def undoMove(square:Int) = {
        setSquareValue(square, EmptySquare)
        boardStatus = InProgressStatus
    }

    def dup: Board = {
        var duplicatedBoard = new Board(this.playerOne, this.playerTwo)
        for (i <- 0 to 8) {
            duplicatedBoard.makeMove(i, this.getSquareValue(i))
        }
        
        duplicatedBoard
    }
    
    def opponentOf(symbol: String): String = {
        if (symbol == playerOne) return playerTwo
        return playerOne
    }
    
    def allSquaresOccupied = {
        boardValues.count( _ != EmptySquare ) == 9
    }
    
    def isSquareUnoccupied(square:Int) = {
        boardValues(square) == EmptySquare
    }
    
    def unoccupiedSquares = {
        for { square <- 0 to 8 
            if isSquareUnoccupied(square)
        } yield square
    }
    
    def getSquareValue(square:Int) = { 
        boardValues(square)
    }

    def isPlayerWinner(symbol:String) = {
        rowsVictory(symbol) || columnsVictory(symbol) || diagonalsVictory(symbol)
    }
    
    def rowsVictory(symbol:String) = {
        (
            threeInARow(symbol, (0, 1, 2) ) || 
            threeInARow(symbol, (3, 4, 5) ) || 
            threeInARow(symbol, (6, 7, 8) )
        )
    }
    
    def columnsVictory(symbol:String) = {
        (
            threeInARow(symbol, (0, 3, 6) ) || 
            threeInARow(symbol, (1, 4, 7) ) || 
            threeInARow(symbol, (2, 5, 8) )
        )
    }
    
    def diagonalsVictory(symbol:String) = {
        (
            threeInARow(symbol, (0, 4, 8) ) || 
            threeInARow(symbol, (2, 4, 6) )
        )
    }
    
    def threeInARow(symbol:String, combo:Tuple3[Int, Int, Int]) = {
        (boardValues(combo._1) == symbol && boardValues(combo._2) == symbol && boardValues(combo._3) == symbol)
    }
    
    def isAtDraw = {
        boardStatus == DrawStatus
    }
    
    def isGameOver = {
        boardStatus != InProgressStatus
    }
    

    
    
}