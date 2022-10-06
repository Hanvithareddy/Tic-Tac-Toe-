package Q2 ;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {  

    private ServerSocket server = null;
    private Socket socket1 = null;
    private Socket socket2 = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Integer ID = null;
    private Board board = null ;
    private Scanner sc = new Scanner(System.in);


    /*
     * server socket allows this player to accept connection from other player
     * socket1 is the accepted connection from server socket
     * socket2 is the socket that connects to the server of the other player
     * NOTE: Socket(HOST, port) throws ConnectException if no server is listening on port
     *       handle this while creating socket2
     *       Since server.accept is blocking, so order in which sockets are initialised must be considered appropriately.
     */


    /*
     * TODO: Add any variables you need
     */

    private void printResult(int winner, int ID) {
        /*
         * Don't change this function
         */
        if(winner == 0) {
            System.out.println("DRAW") ;
        } 
        else if(winner == ID) {
            System.out.println("YOU WON") ;
        }
        else System.out.println("YOU LOSE") ;
    }

    private Integer[] takeInput(){
        /*
         * TODO: Take input from the user from STDIN and return the position as an array
         * 
         */
        System.out.print("Enter the position: ");

        /* take x and y as space separated integer*/

        String in = sc.nextLine();
        String[] input = new String[2];
        input = in.split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        Integer  a[] = {x,y};
        if(board.available(x,y)){
                return a;
        }
                
        else{
        System.out.print("INVALID INPUT! Enter again");
        sc = new Scanner(System.in);
        return takeInput();}
        }       
        

       
    private void sendMove(Integer[] pos){
        /*
         * TODO: Send the move to other player by writing to the appropriate socket
         * sent string format: "x y" (space separated)
         */
          String x=Integer.toString(pos[0]);
        String y=Integer.toString(pos[1]);
        String move= x+" "+y+"\n";
        out.write(move);
        out.flush();
    }

    private Integer[] recieveMove(){
        /*
         * TODO: Recieve the move from the server by reading from the appropriate socket
         * return the move as an array of two integers
         * recieve format: "x y" (space separated)
         */
        Integer[] a= new Integer[2];
        try{String input = in.readLine();
            String[] move=new String[2];
            move=input.split(" ");

         int x=Integer.parseInt(move[0]);
         int y=Integer.parseInt(move[1]);
         a[0]=x;
         a[1]=y;
        }catch (IOException e) {
         }
        return a; 
    }
    public Client(Integer ID, Integer port1, Integer port2){
        /*
        * port1 is the port on which server is running and 
        * this player will accept connection from other player
        * 
        * port2 is the port on which the server of the other player is running
        */     

        /*
        * TODO: Initialize ID
        * Also initialize the board
        */
        this.ID=ID;  
        board=new Board();
        try{
            /*
            * INITIALIZE THE ServerSocket 
            */
        ServerSocket server = new ServerSocket(port1);
         System.out.println("Server started");
         System.out.println("Waiting for a client ...");



          

     

 while (socket2 == null) {
                try {
                    socket2 = new Socket("localhost", port2);
                } catch (IOException e) {
                } }
               
           /*
             * initialize the input and output streams appropriately
             */
            socket1=server.accept();
            out = new PrintWriter( socket1.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        }
        catch(IOException e){
            e.printStackTrace();
        }

       
    }

    public void runClient () {
        Boolean myturn = ID==1;
        while(true) {
                /*
                * Here we would have the game logic
                * You can use the functions defined above
                * NOTE: for player with ID 1, it is your turn initially
                *       for player with ID 2, it is the other player's turn initially
                * We have implemented the game using a logic which utilizes myturn bool. You are welcome to try any other logic.
                */
                
            if(myturn) {
                /* your turn */
                Integer [] m=takeInput(); 
                sendMove(m);
                board.updateBoard(m,ID);
                 int result=board.checkBoard();
                 if(result==0){printResult(0,1);break;}
                 if(result==1 || result==2){printResult(1,1);break;}
            } else {
                /* other player's turn */
                 Integer [] o=recieveMove();
                board.updateBoard(o,3-ID);
                int result=board.checkBoard();
                if(result==0){printResult(0,1);break;}
                if(result==1 || result==2){printResult(1,2);break;}
            }
            myturn = !myturn;
        }
    }

    public static void main(String args[]) {
        
        Integer ID = Integer.parseInt(args[0]) ;
        System.out.println(ID);
        if(ID != 1 && ID != 2) {
            System.out.println("Incorrect Player ID\n");
            return ;
        }
        Integer port1 = Integer.parseInt(args[1]) ;
        Integer port2 = Integer.parseInt(args[2]) ;
        try {
            Client client = new Client(ID, port1, port2) ;
            client.runClient() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

