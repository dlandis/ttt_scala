import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.Matchers._
import java.io.PrintStream
import java.io.BufferedReader

import com.tictactoe.ConsoleUI
import com.tictactoe.Game

class ConsoleUISpec extends Spec with BeforeAndAfter {
    var mockOut = createMockOut
    var mockIn = createMockIn
    var mockGame = createMockGame
    
    var ui = new ConsoleUI(mockGame)

    
    def createMockOut = mock(classOf[PrintStream])
    def createMockIn = mock(classOf[BufferedReader])
    def createMockGame = mock(classOf[Game])
    
    before {
        mockGame = createMockGame
        ui = new ConsoleUI(mockGame)
        
        mockOut = createMockOut
        ui.io.setOut(mockOut)
        
        mockIn = createMockIn
        ui.in = mockIn
    }
    
    describe("output") {
        
        it("displays a message") {
            ui.displayMessage("Welcome To Tic Tac Toe in Scala")

            verify(mockOut).println("Welcome To Tic Tac Toe in Scala")
        }
        
        it("displays square info from game") {
            ui.displayBoard
            
            verify(mockGame, times(9)).getSquareValue(anyInt())
        }
        
        it("game over message") {
            when(mockGame.getStatus).thenReturn("X wins!")
            
            ui.gameOver
            
            verify(mockOut).println("X wins!")
        }
        
    }
    
    describe("input") {
        it("removes whitespace") {
            when(mockIn.readLine).thenReturn("    foo     ")
            
            expect("foo") { ui.getInput }
        }
        
    }
    
    
}