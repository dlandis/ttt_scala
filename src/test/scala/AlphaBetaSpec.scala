import org.scalatest.Spec

import com.tictactoe.Board
import com.tictactoe.AlphaBeta

class AlphaBetaSpec extends Spec {
    val AlphaBetaPlayer = "O"
    val Opponent = "X"
    def createFixtures() = (
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
    
    describe("get moves") {
        
        it("take top left corner if board is blank") {
            var (alphaBeta, board) = createFixtures
            
            expect(0) { alphaBeta.getMove(board) }
        }
        
        describe("block winning moves") {
            it("blocks upper right win") {
                var (alphaBeta, board) = createFixtures
            
                board.makeMove(0, Opponent)
                board.makeMove(4, AlphaBetaPlayer)
                board.makeMove(1, Opponent)
            
                expect(2) { alphaBeta.getMove(board) }
            }
        
            it("blocks bottom left win")  {
                var (alphaBeta, board) = createFixtures
            
                board.makeMove(0, Opponent)
                board.makeMove(4, AlphaBetaPlayer)
                board.makeMove(3, Opponent)
            
                expect(6) { alphaBeta.getMove(board) }
            }
        
            it("blocks middle if corner taken") {
                var (alphaBeta, board) = createFixtures
            
                board.makeMove(0, "X")
            
                expect(4) { alphaBeta.getMove(board) }
            }
        }
        
        describe("winning moves") {
            
            it("takes bottom left to win") {
                var (alphaBeta, board) = createFixtures
                
                board.makeMove(0, "X")
                board.makeMove(4, "O")
                board.makeMove(1, "X")
                board.makeMove(2, "O")
                board.makeMove(8, "X")
                board.makeMove(6, "X")
            }
            
            it("ignores opportunity to fork at 2 and takes bottom left to win") {
                var (alphaBeta, board) = createFixtures
                
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
            var (alphaBeta, board) = createFixtures

            draw(board)

            expect(0) { alphaBeta.heuristicValueOfLastMove(board, 0) }
        }
        
        it("scores games in progress as -1") {
            var (alphaBeta, board) = createFixtures
            
            board.makeMove(0, "X")
            
            expect(-1) { alphaBeta.heuristicValueOfLastMove(board, 0) }
        }
        
        it("scores wins for alphaBeta player as 20 for depth of 0") {
            var (alphaBeta, board) = createFixtures
            
            win(AlphaBetaPlayer, board)
            
            expect(20) { alphaBeta.heuristicValueOfLastMove(board, 0) }
        }
        
        it("subtracts move depth from max score of 20 for alphabeta player") {
            var (alphaBeta, board) = createFixtures
            
            win(AlphaBetaPlayer, board)
            
            expect(19) { alphaBeta.heuristicValueOfLastMove(board, 1) }
            expect(18) { alphaBeta.heuristicValueOfLastMove(board, 2) }
        }
        
        it("scores wins for opponent as -20 for depth of 0") {
            var (alphaBeta, board) = createFixtures
            
            win(Opponent, board)
            
            expect(-20) { alphaBeta.heuristicValueOfLastMove(board, 0) }
        }
        
        it("subtracts move depth from max score of -20 for opponent") {
            var (alphaBeta, board) = createFixtures
            
            win(Opponent, board)
            
            expect(-19) { alphaBeta.heuristicValueOfLastMove(board, 1) }
            expect(-18) { alphaBeta.heuristicValueOfLastMove(board, 2) }
        }
        
    }
}