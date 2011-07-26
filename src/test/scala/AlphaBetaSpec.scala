import org.scalatest.Spec
import org.scalatest.BeforeAndAfter

import java.lang.System

import com.tictactoe.Board
import com.tictactoe.AlphaBeta

class AlphaBetaSpec extends Spec with BeforeAndAfter {
    val AlphaBetaPlayer = "O"
    val Opponent = "X"
    
    var alphaBeta = new AlphaBeta(AlphaBetaPlayer)
    var board = new Board(Opponent, AlphaBetaPlayer)
    
    def createFixtures = (
                            new AlphaBeta(AlphaBetaPlayer), 
                            new Board(Opponent, AlphaBetaPlayer)
                           )

    def draw(board:Board) = {
       board.makeMove(0, AlphaBetaPlayer)
       board.makeMove(1, AlphaBetaPlayer)
       board.makeMove(2, Opponent)
       board.makeMove(3, Opponent)
       board.makeMove(4, Opponent)
       board.makeMove(5, AlphaBetaPlayer)
       board.makeMove(6, AlphaBetaPlayer)
       board.makeMove(7, Opponent)
       board.makeMove(8, Opponent)
    }

    def win(symbol: String, board: Board) = {
        board.makeMove(0, symbol)
        board.makeMove(1, symbol)
        board.makeMove(2, symbol)
    }
    
    before {
        alphaBeta = new AlphaBeta(AlphaBetaPlayer)
        board = new Board(Opponent, AlphaBetaPlayer)
    }
    
    // describe("benchmark") {
    //     it("finds the first move on empty board in < 300 ms") {
    //     
    //         val startTime = System.currentTimeMillis()
    //         
    //         expect(0) { alphaBeta.getMove(board) }
    //         
    //         val endTime = System.currentTimeMillis()
    //         val benchmark = endTime - startTime
    //         
    //         expect(true) { benchmark < 300 }
    //     }
    // }
    
    describe("get moves") {
        
        it("take top left corner if board is blank") {
            expect(0) { alphaBeta.getMove(board) }
        }
        
        describe("block winning moves") {
            it("blocks upper right win") {
                board.makeMove(0, Opponent)
                board.makeMove(4, AlphaBetaPlayer)
                board.makeMove(1, Opponent)
            
                expect(2) { alphaBeta.getMove(board) }
            }
        
            it("blocks bottom left win")  {
                board.makeMove(0, Opponent)
                board.makeMove(4, AlphaBetaPlayer)
                board.makeMove(3, Opponent)
            
                expect(6) { alphaBeta.getMove(board) }
            }
        
            it("blocks middle if corner taken") {
                board.makeMove(0, "X")
            
                expect(4) { alphaBeta.getMove(board) }
            }
        }
        
        describe("winning moves") {
            
            it("takes bottom left to win") {
                board.makeMove(0, "X")
                board.makeMove(4, "O")
                board.makeMove(1, "X")
                board.makeMove(2, "O")
                board.makeMove(8, "X")
                board.makeMove(6, "X")
            }
            
            it("ignores opportunity to fork at 2 and takes bottom left to win") {
                board.makeMove(0, "X")
                board.makeMove(4, "O")
                board.makeMove(8, "X")
                board.makeMove(2, "O")
                board.makeMove(5, "X")
                
                expect(6) { alphaBeta.getMove(board) }
            }
            
        }
    }
    
    describe("scoring moves") {
        it("scores draws as 0") {
            draw(board)

            expect(0) { alphaBeta.scoreOfLastMove(board, 0) }
        }
        
        it("scores games in progress as -1") {
            board.makeMove(0, "X")
            
            expect(-1) { alphaBeta.scoreOfLastMove(board, 0) }
        }
        
        it("scores wins for alphaBeta player as 20 for depth of 0") {
            win(AlphaBetaPlayer, board)
            
            expect(20) { alphaBeta.scoreOfLastMove(board, 0) }
        }
        
        it("subtracts move depth from max score of 20 for alphabeta player") {
            win(AlphaBetaPlayer, board)
            
            expect(19) { alphaBeta.scoreOfLastMove(board, 1) }
            expect(18) { alphaBeta.scoreOfLastMove(board, 2) }
        }
        
        it("scores wins for opponent as -20 for depth of 0") {
            win(Opponent, board)
            
            expect(-20) { alphaBeta.scoreOfLastMove(board, 0) }
        }
        
        it("subtracts move depth from max score of -20 for opponent") {
            win(Opponent, board)
            
            expect(-19) { alphaBeta.scoreOfLastMove(board, 1) }
            expect(-18) { alphaBeta.scoreOfLastMove(board, 2) }
        }
        
    }
}