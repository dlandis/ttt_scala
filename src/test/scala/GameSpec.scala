import org.scalatest.Spec
import org.mockito.Mockito._
import org.mockito.InOrder

import com.tictactoe.Game
import com.tictactoe.Board
import com.tictactoe.AlphaBeta


class GameSpec extends Spec {
    
    def createGame = new Game()
    def createMocks = ( createMockBoard, createMockAlphaBeta )
    def createMockBoard = mock(classOf[Board])
    def createMockAlphaBeta = mock(classOf[AlphaBeta])
    
    describe("current player") {
        it("current player starts as 'X'") {
            var game = createGame
            expect("X") { game.getCurrentPlayer }
        }
        
        it("switches current player from X to O") {
            var game = createGame
            
            game.switchCurrentPlayer
            
            expect("O") { game.getCurrentPlayer }
        }
    }
    

    describe("interact with board") {
        it("sends a move to the board") {
            var game = createGame
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.makeMove(0)
            
            verify(mockBoard).makeMove(0, "X")
        }
        
        it("determines if game is over") {
            var game = createGame
            val mockBoard = createMockBoard
            game.board = mockBoard        
            
            game.isGameOver
            
            verify(mockBoard).isGameOver
        }
        
        it("gets square value") {
            var game = createGame
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.getSquareValue(0)
            
            verify(mockBoard).getSquareValue(0)
        }
        
        it("gets status") {
            var game = createGame
            val mockBoard = createMockBoard
            game.board = mockBoard
            
            game.getStatus
            
            verify(mockBoard).status
        }
    }
    
    describe("interact with AI") {
        it("gets move from Alpha Beta") {
            var game = createGame
            val (mockBoard, mockAI) = createMocks
            game.ai = mockAI
            game.board = mockBoard
            when(mockAI.getMove(mockBoard)).thenReturn(0)
            
            expect(0) { game.getMoveFromAI }
            verify(mockAI).getMove(mockBoard)
        }
    }
    
    describe("updating game") {
        it("sends move from user to the board") {
            var game = createGame
            val (mockBoard, mockAI) = createMocks
            game.ai = mockAI
            game.board = mockBoard
            
            game.updateGameWithMove(0)
            
            verify(mockBoard).makeMove(0, "X")
        } 
        
        it("gets move from AI after move recorded") {
            var game = createGame
            val (mockBoard, mockAI) = createMocks
            game.ai = mockAI
            game.board = mockBoard
            val order: InOrder = inOrder(mockBoard, mockAI)
            game.updateGameWithMove(0)
            
            order.verify(mockBoard).makeMove(0, "X")
            order.verify(mockAI).getMove(mockBoard)
        }
        
        it("switches players appropriately") (pending)
        
    }   
}