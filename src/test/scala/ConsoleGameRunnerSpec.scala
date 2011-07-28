import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.InOrder
import org.mockito.Matchers._


import com.tictactoe.ConsoleGameRunner
import com.tictactoe.Game
import com.tictactoe.ConsoleUI

class ConsoleGameRunnerSpec extends Spec with BeforeAndAfter {
    var mockGame = createMockGame
    var mockUI = createMockUI
    var gameRunner = new ConsoleGameRunner()
    
    def createMockGame = mock(classOf[Game])
    def createMockUI = mock(classOf[ConsoleUI])
    

    
    before {
        mockGame = createMockGame
        gameRunner.game = mockGame
        
        mockUI = createMockUI
        gameRunner.ui = mockUI
    }
    
    describe("running the game") {
        
        it("runs the game loop") {
            setUpGameLoopMocks
                
            gameRunner.gameLoop
                    
            verifyOrderOfCalls
        }
        
        def setUpGameLoopMocks {
            when(mockUI.getMoveFromUser).thenReturn(4)
            when(mockGame.isCurrentPlayerHuman)
                .thenReturn(true)
                .thenReturn(false)
            when(mockGame.isGameOver)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)
        }

        def verifyOrderOfCalls {
            val order: InOrder = inOrder(
                mockGame,
                mockGame,
                mockUI, 
                mockUI, 
                mockGame,
                mockGame,
                mockUI,
                mockGame,
                mockGame
            )
            
            order.verify(mockGame).restart
            order.verify(mockGame).isGameOver
            order.verify(mockUI).displayBoard
            order.verify(mockUI).getMoveFromUser
            order.verify(mockGame).makeMove(4)
            order.verify(mockGame).isGameOver
            order.verify(mockUI).displayBoard
            order.verify(mockGame).makeComputerMove
            order.verify(mockGame).isGameOver
        }

    }
       
    describe("end game") {
        it("displays the game status") {
            when(mockGame.getStatus).thenReturn("Some Game Status")
            
            gameRunner.endGame
            
            val order: InOrder = inOrder(mockUI, mockUI)
            
            order.verify(mockUI).displayBoard
            order.verify(mockUI).gameOver
        }
    }
    
    describe("playing again") {
        it("restarts game if user wants to play again") {
            when(mockUI.askUserToPlayAgain).thenReturn(true)
            
            expect(true) { gameRunner.playAgain }
        }
        
        it("does not restart game if user does not want to play") {
            when(mockUI.askUserToPlayAgain).thenReturn(false)
            
            expect(false) { gameRunner.playAgain }           
        }
    }
    
    describe("moves") {
        
        it("makes next move from ui if player is human") {
            when(mockGame.isCurrentPlayerHuman).thenReturn(true)
            when(mockUI.getMoveFromUser).thenReturn(0)
            
            gameRunner.makeNextMove
            
            verify(mockGame).makeMove(0)
        }
        
        it("makes next move from game if player is computer") {
            when(mockGame.isCurrentPlayerHuman).thenReturn(false)
            
            gameRunner.makeNextMove
            
            val order: InOrder = inOrder(mockUI, mockGame)
            
            order.verify(mockUI).computerIsMakingMove
            order.verify(mockGame).makeComputerMove
        }
    }
    
    describe("run") {
        it("welcomes the user") {
            setUpMainLoopMocks
            
            gameRunner.run
            
            verify(mockUI).welcomeUser
        }
        
        it("loops until user doesn't want to play again") {
           setUpMainLoopMocks

           gameRunner.run

           verifyOrderOfCalls
        }

        def setUpMainLoopMocks {
           when(mockGame.isGameOver)
               .thenReturn(true)
               .thenReturn(true)

           when(mockUI.askUserToPlayAgain)
               .thenReturn(true)
               .thenReturn(false)
        }

        def verifyOrderOfCalls {
           val order: InOrder = 
               inOrder(
                   mockGame, 
                   mockUI,
                   mockUI, 
                   mockGame, 
                   mockGame,
                   mockUI,
                   mockUI
               )

           order.verify(mockGame).isGameOver
           order.verify(mockUI).gameOver
           order.verify(mockUI).askUserToPlayAgain
           order.verify(mockGame).restart
           order.verify(mockGame).isGameOver
           order.verify(mockUI).gameOver
           order.verify(mockUI).askUserToPlayAgain
        }
    }
}