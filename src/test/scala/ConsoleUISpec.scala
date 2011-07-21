import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import java.io.PrintStream

import com.tictactoe.ConsoleUI


class ConsoleUISpec extends Spec with BeforeAndAfter {
    
    describe("prints to user") {
        var ui = new ConsoleUI()
        var mockOut = mock(classOf[PrintStream])
        ui.io.setOut(mockOut)
        
        ui.welcomeUser
        
        verify(mockOut).println("Welcome to Tic Tac Toe in Scala")
    }
}