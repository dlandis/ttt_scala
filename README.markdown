Tic Tac Toe in Scala
====================


### Install Simple Build Tool with homebrew

brew install sbt

### Within Project Directory

#### Install Dependencies

sbt update

#### Run tests

sbt test

#### Play Tic Tac Toe

sbt run


## Notes on Scala and SBT

Programming Scala by Dean Wampler is available online and a great reference for getting to know Scala. Find it at http://ofps.oreilly.com/titles/9780596155957/


* In SBT, your input doesn't seem to display when playing tic tac toe if you use the command "sbt run". If you launch sbt first, though, then type run inside the SBT prompt, your input appears. Beats me why this is.

* Running the tests should go quickly - the first time you run them, compile time is added. Run a second time to see how long the tests take.

