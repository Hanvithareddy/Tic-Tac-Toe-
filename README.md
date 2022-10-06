# Tic-Tac-Toe



This is implementation of the famous tic tac toe game using basics of inter-process communication, socket programming along with some exception handling.

This model is peer to peer. Here the peers (the two players here) act as both server as well as client.


*The peer-to-peer model*

Each peer has a ServerSocket and two socket variables.
The ServerSocket accepts a connection from the other peer and initializes one of the socket variables, used for sending messages to it.
Another socket variable connects to the ServerSocket of the other peer and is used for receiving messages from it.

There are two files Client.java and Board.java


## **Board.java**

The board is a 3x3 array with indices and values both as 0,1,2. The 2 clients have different board variables so after sharing moves both become identical. 
There is a printBoard function to print the board.
public Boolean available: returns whether a position is available to be filled or not.
public void updateBoard: Make updates in the board after receiving a move.
public int checkBoard: calls the printBoard function and returns different integers depending on the status of the game.


## **Client.java**

Here implementation of running of the game as well as inter process communication is done.



**Command to run the game and play:**

javac filename/*.java for compilation
Now, open two terminal instances

java filename/Client 1 5000 8000   and  java filename/Client 2 8000 5000 on each terminal.
1,2 specifies the players and who is playing first and second respectively (both cannot be the same).

                  5000,8000 specifies the ports, use any other if the port is unavailable.


**Command to run the game from testcase:**

After compiling as above run both the player simultaneously using

java filename.Client 1 5000 8000 < in1.txt > out1.txt & java filename.Client 2 8000 5000 < in2.txt > out2.txt

Use diff to compare your output with given out*.txt. (Should match exactly)


## **Output:**

There will be 2 output files. The output file contains the final results of the game for the corresponding players



->It does not matter which player you start first but the ID of both should not be the same.
