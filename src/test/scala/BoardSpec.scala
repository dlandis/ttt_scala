import org.scalatest.Spec
import org.scalatest.BeforeAndAfter

import com.tictactoe.Board

class BoardSpec extends Spec with BeforeAndAfter {
    
    var board = createBoard
    def createBoard = new Board("X", "O")
    
    def draw(board:Board) = {
        board.makeMove(0, "O")
        board.makeMove(1, "O")
        board.makeMove(2, "X")
        board.makeMove(3, "X")
        board.makeMove(4, "X")
        board.makeMove(5, "O")
        board.makeMove(6, "O")
        board.makeMove(7, "X")
        board.makeMove(8, "X")
    }
    
    def setWinner(board:Board, symbol:String) = {
        board.makeMove(0, symbol)
        board.makeMove(4, symbol)
        board.makeMove(8, symbol)
    }
    
    before {
        board = createBoard
    }
    
    describe("square values") {
        it("gets an empty square") {
            expect("-") { board.getSquareValue(0)}
        }
        
        it("gets an occupied square") {
            board.makeMove(0, "X")
            
            expect("X") { board.getSquareValue(0)}
        }
        
        it("returns true if square unoccupied") {        
            expect(true) { board.isSquareUnoccupied(0)}    
        }
        it("returns true if all squares occupied") {        
            draw(board)
            
            expect(true) { board.allSquaresOccupied }
        }
        
        it("returns false if not all squares occupied") {        
            expect(false) { board.allSquaresOccupied }
        }
        
        it("gives a sequence of unoccupied squares") {
            board.unoccupiedSquares.foreach (
                square => {
                    expect(true) { board.isSquareUnoccupied(square) }
                }
            )
        }
        
    }
    
    describe("Status") {
        it("has a default status of 'Game In Progress'") {
            expect("Game In Progress") { board.status }
        }
        
        it("X wins based on board values") {
            setWinner(board, "X")
            
            board.updateStatus
            
            expect("X wins!") { board.status }
        }
        
        it("O wins based on board values") {
            setWinner(board, "O")
            
            board.updateStatus
            
            expect("O wins!") { board.status }
        }
        
        it("finds draw if all squares are occupied with no winner") {
            draw(board)
            
            board.updateStatus
            
            expect("Draw") { board.status }
        } 
        
        it("returns true if player is winner") {
            setWinner(board, "O")
            
            expect(true) { board.isPlayerWinner("O") }
        }
        
        it("returns false if player has not won the game") {            
            expect(false) { board.isPlayerWinner("O") }
            expect(false) { board.isPlayerWinner("X") }
        }
        
        it("returns true if game at draw") {            
            draw(board)
            board.updateStatus
            
            expect(true) { board.isAtDraw }
        }
        
        it("returns true if game over") {            
            draw(board)
            
            expect(true) { board.isGameOver }
        }
    }
    
    describe("Players") {
        it("requires players in constructor") {            
            expect("X") { board.playerOne }
            expect("O") { board.playerTwo }
        }
        
        it("sets current player to player One on initialization") {            
            expect("X") { board.currentPlayer }
        }
        
        it("returns opponent of a player") {            
            expect("O") { board.opponentOf("X") }
            expect("X") { board.opponentOf("O") }
        }
        
    }
    
    describe("Making Moves") {
        it("updates a square") {            
            board.makeMove(1, "X")
            
            expect("X") { board.getSquareValue(1) }
        }
        
        it("calls updateStatus") {            
            for (square <- 0 to 2) { board.makeMove(square, "X") }
            
            expect("X wins!") { board.status }
        }
        
        it("undoes a move") {
            board.makeMove(1, "X")
            
            board.undoMove(1)
            
            expect(true) { board.isSquareUnoccupied(1) }
        }
        
        it("undoing move sets game status to in progress") {
            setWinner(board, "X")
            
            board.undoMove(0)
            
            expect(false) { board.isGameOver }
        }
    }
    
    describe("duplicating") {
        it("creates a copy of itself") {
            board.makeMove(1, "X")

            var duplicatedBoard = board.dup
            
            expect("X") { duplicatedBoard.playerOne }
            expect("O") { duplicatedBoard.playerTwo }
            expect("X") { duplicatedBoard.getSquareValue(1) }
        }
    }
    
    describe("clearing") {
        it("clears all squares and sets game status to in progress") {
            draw(board)
            
            board.clear
            
            expect("Game In Progress") { board.status }
        }
    }
}