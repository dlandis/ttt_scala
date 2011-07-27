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
    
    def currentPlayerIsPlayerTwo = game.switchCurrentPlayer
    
    before {
        game = createGame
        mockBoard = createMockBoard
        mockAI = createMockAlphaBeta
        game.ai = mockAI
        game.board = mockBoard
    }
    
    describe("current player") {
        it("current player starts as 'X'") {
            expect(game.playerOne) { game.getCurrentPlayer }
        }
        
        it("switches current player from X to O") {
            currentPlayerIsPlayerTwo            
            
            expect(game.playerTwo) { game.getCurrentPlayer }
        }
        
        it("switches current player when makemove is called") {
            game.makeMove(0)
            
            expect(game.playerTwo) { game.getCurrentPlayer }
        }
        
    }

    describe("human and computer players") {
        it("identifies current player as human") {
            expect(true) { game.isCurrentPlayerHuman }
        }
        
        it("identifies current player as computer") {
            currentPlayerIsPlayerTwo            
            
            expect(false) { game.isCurrentPlayerHuman }
        }
    }
    describe("interact with board") {
        it("sends a move to the board") {
            game.makeMove(0)
            
            verify(mockBoard).makeMove(0, "X")
        }
        
        it("determines if game is over") {
            game.isGameOver
            
            verify(mockBoard).isGameOver
        }
        
        it("gets square value") {
            game.getSquareValue(0)
            
            verify(mockBoard).getSquareValue(0)
        }
        
        it("gets status") {
            game.getStatus
            
            verify(mockBoard).status
        }
        
        it("finds a square is unoccupied") {
            when(mockBoard.isSquareUnoccupied(0)).thenReturn(true)
            
            expect(true) { game.isSquareUnoccupied(0) }
        }
        
        it("finds a square is occupied") {
            when(mockBoard.isSquareUnoccupied(0)).thenReturn(false)
            
            expect(false) { game.isSquareUnoccupied(0) }
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
            expect(game.playerTwo) { game.getCurrentPlayer }
    
            order.verify(mockAI).getMove(mockBoard)
            order.verify(mockBoard).makeMove(0, "X")
        }
    }
    
    describe("restart") {
        it("clears board and resets current player to Player One") {
            currentPlayerIsPlayerTwo
            
            game.restart
            
            expect(game.playerOne) { game.getCurrentPlayer }
            
            verify(mockBoard).clear
        }
    }

}