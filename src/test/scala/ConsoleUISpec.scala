import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import java.io.PrintStream
import java.io.BufferedReader

import com.tictactoe.ConsoleUI


class ConsoleUISpec extends Spec with BeforeAndAfter {
    
    var ui = new ConsoleUI()
    var mockOut = mock(classOf[PrintStream])
    var mockIn = mock(classOf[BufferedReader])
    
    before {
        ui = new ConsoleUI()
        mockOut = mock(classOf[PrintStream])
        mockIn = mock(classOf[BufferedReader])
        ui.io.setOut(mockOut)
        ui.in = mockIn
    }
    
    describe("prints to user") {
        var ui = new ConsoleUI()
        var mockOut = mock(classOf[PrintStream])
        ui.io.setOut(mockOut)
        
        ui.welcomeUser
        
        verify(mockOut).println("Welcome to Tic Tac Toe in Scala")
    }
    
    describe("input") {
        it("removes whitespace") {
            when(mockIn.readLine).thenReturn("    foo     ")
            
            expect("foo") { ui.getInput }
        }
    }
    
}