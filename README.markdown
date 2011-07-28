Tic Tac Toe in Scala
====================


### Install Simple Build Tool with homebrew

brew install sbt

This should grab SBT version 0.7.5. If you have trouble getting a 0.7.x version, see below.

### Start SBT Within Project Directory

sbt

### Within SBT

#### Install Dependencies

update

#### Run tests

test

#### Play Tic Tac Toe

run

## Notes on Scala and SBT

Programming Scala by Dean Wampler is available online and a great reference for getting to know Scala. Find it at http://ofps.oreilly.com/titles/9780596155957/

* Running the tests should go quickly - the first time you run them, compile time is added. Run a second time to see how long the tests take.

### Alternate SBT install instructions:

Download SBT 0.7.7  http://simple-build-tool.googlecode.com/files/sbt-launch-0.7.7.jar

Put the jar in your ~/bin directory. Create a file called sbt, and add:

java -Xmx512M -jar `dirname $0`/sbt-launch.jar "$@"

At the terminal, type:

$ chmod u+x ~/bin/sbt

This allows you to launch sbt in any directory by typing sbt at the command prompt.

