import org.scalatest.Spec

import com.tictactoe.Board

class BoardSpec extends Spec {

    def createBoard = new Board()
    
    def draw(board:Board) = {
        board.setSquareValue(0, "O")
        board.setSquareValue(1, "O")
        board.setSquareValue(2, "X")
        board.setSquareValue(3, "X")
        board.setSquareValue(4, "X")
        board.setSquareValue(5, "O")
        board.setSquareValue(6, "O")
        board.setSquareValue(7, "X")
        board.setSquareValue(8, "X")
    }
    
    def setWinner(board:Board, symbol:String) = {
        board.setSquareValue(0, symbol)
        board.setSquareValue(1, symbol)
        board.setSquareValue(2, symbol)
    }
    
    describe("square values") {
        it("gets an empty square") {
            var board = createBoard
            expect("-") { board.getSquareValue(0)}
        }
        
        it("sets a square") {
            var board = createBoard
            board.setSquareValue(0, "X")
            
            expect("X") { board.getSquareValue(0)}
        }
        
        it("returns true if all squares occupied") {
            var board = createBoard
            
            draw(board)
            
            expect(true) { board.allSquaresOccupied }
        }
        
        it("returns false if not all squares occupied") {
            var board = createBoard
            
            expect(false) { board.allSquaresOccupied }
        }
    }
    
    describe("Status") {
        it("has a default status of 'Game In Progress'") {
            var board = createBoard
            expect("Game In Progress") { board.status }
        }
        
        it("X wins based on board values") {
            var board = createBoard
            setWinner(board, "X")
            
            board.updateStatus()
            
            expect("X wins!") { board.status }
        }
        
        it("O wins based on board values") {
            var board = createBoard
            setWinner(board, "O")
            
            board.updateStatus()
            
            expect("O wins!") { board.status }
        }
        
        it("finds draw if all squares are occupied with no winner") {
            var board = createBoard
            draw(board)
            
            board.updateStatus()
            
            expect("Draw") { board.status }
            
        } 
        
    }
    
}