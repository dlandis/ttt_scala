import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.InOrder
import org.mockito.Matchers._


import com.tictactoe.GameRunner
import com.tictactoe.Game
import com.tictactoe.ConsoleUI

class GameRunnerSpec extends Spec with BeforeAndAfter {
    var mockGame = createMockGame
    var mockUI = createMockUI
    var gameRunner = new GameRunner()
    
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
            when(mockUI.getMoveFromUser).thenReturn("4")
            when(mockGame.isGameOver)
                .thenReturn(false)
                .thenReturn(true)
                
            gameRunner.gameLoop
            
            val order: InOrder = 
                inOrder(
                    mockGame,
                    mockUI, 
                    mockUI, 
                    mockGame,
                    mockGame,
                    mockUI,
                    mockGame
                )
            
            order.verify(mockGame).isGameOver
            order.verify(mockUI).displayBoard
            order.verify(mockUI).getMoveFromUser
            order.verify(mockGame).makeMove(4)
            order.verify(mockGame).makeComputerMove
            order.verify(mockUI).displayBoard
            order.verify(mockGame).isGameOver
            
        }
        
        it("displays the game status") {
            when(mockGame.getStatus).thenReturn("Some Game Status")
            
            gameRunner.endGame
            
            verify(mockUI).gameOver
        }
    }
}