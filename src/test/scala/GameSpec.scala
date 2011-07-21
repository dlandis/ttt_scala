import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.InOrder

import com.tictactoe.Game
import com.tictactoe.Board
import com.tictactoe.AlphaBeta


class GameSpec extends Spec with BeforeAndAfter {
    
    var game = createGame
    var mockBoard = createMockBoard
    var mockAI = createMockAlphaBeta
    
    def createGame = new Game()
    def createMocks = ( createMockBoard, createMockAlphaBeta )
    def createMockBoard = mock(classOf[Board])
    def createMockAlphaBeta = mock(classOf[AlphaBeta])
    
    before {
        game = createGame
        mockBoard = createMockBoard
        mockAI = createMockAlphaBeta
        game.ai = mockAI
        game.board = mockBoard
    }
    
    describe("current player") {
        it("current player starts as 'X'") {
            expect("X") { game.getCurrentPlayer }
        }
        
        it("switches current player from X to O") {
            game.switchCurrentPlayer
            
            expect("O") { game.getCurrentPlayer }
        }
        
        it("switches current player when makemove is called") {
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.makeMove(0)
            
            expect("O") { game.getCurrentPlayer }
        }
    }

    describe("interact with board") {
        it("sends a move to the board") {
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.makeMove(0)
            
            verify(mockBoard).makeMove(0, "X")
        }
        
        
        it("determines if game is over") {
            val mockBoard = createMockBoard
            game.board = mockBoard        
            
            game.isGameOver
            
            verify(mockBoard).isGameOver
        }
        
        it("gets square value") {
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.getSquareValue(0)
            
            verify(mockBoard).getSquareValue(0)
        }
        
        it("gets status") {
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.getStatus
            
            verify(mockBoard).status
        }
    }

    describe("interact with AI") {
        it("gets move from Alpha Beta") {
            when(mockAI.getMove(mockBoard)).thenReturn(0)
            
            expect(0) { game.getMoveFromAI }
            verify(mockAI).getMove(mockBoard)
        }
        
        it("sends AI move to board") {
            when(mockAI.getMove(mockBoard)).thenReturn(0)
            
            val order: InOrder = inOrder(mockBoard, mockAI)
            
            game.makeComputerMove
            
            expect(0) { game.getMoveFromAI }
            expect("O") { game.getCurrentPlayer }
    
            order.verify(mockAI).getMove(mockBoard)
            order.verify(mockBoard).makeMove(0, "X")
        }
    }

}