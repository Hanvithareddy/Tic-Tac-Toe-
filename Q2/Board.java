package Q2 ;

public class Board {
    private int [][] board = new int[3][3];
    /* elements of board is either 0, 1 or 2 
     * 0 means empty
     * 1 means player 1's token  (say X)
     * 2 means player 2's token  (say O)
     */
 
    public void printBoard(){
        
        System.out.println("Board:");
        System.out.println("-------------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                if(board[i][j]==0){
                    System.out.print(" ");
                }
                else if(board[i][j]==1){
                    System.out.print("X");
                }
                else if(board[i][j]==2){
                    System.out.print("O");
                }
                System.out.print(" | ");
            }
            System.out.println("\n-------------");   
        }
    }

    public Boolean available(Integer x, Integer y){
        /*
         * TODO: Check if the position (x,y) is available
         * return true if available. 
         * Also return false if (x,y) is not a valid position
         */
        
        if(x >=0 && x <=2)
        {
            if(y >=0 && y <=2)
            {
                if(board[x][y] == 0) return true;
                else return false;
            }
        }
        return false;
    }


    public void updateBoard(Integer[] pos, Integer id){
        /*
         * TODO: Update the board 
         */
        
        if(available(pos[0], pos[1]))
        {
            if(id == 1)
            {
                board[pos[0]][pos[1]] = 1;
                return;
            } 
            if(id==2){
                board[pos[0]][pos[1]] = 2;
                return;
            }
        } else{
            return;
        }

    }

    

    

    public int checkBoard() {

        printBoard();
        

        /*
         * TODO: Check the board and return the status of the game
         * -1 if Game has Not yet Ended
         * 0 if Game has Ended in a Draw
         * 1 if Player 1 has Won
         * 2 if Player 2 has Won
         */

        int h1 = board[0][0] + board[0][1] + board[0][2];
        int h2 = board[1][0] + board[1][1] + board[1][2];
        int h3 = board[2][0] + board[2][1] + board[2][2];
        if((h1== 3 && board[0][0] == board[0][1]) || (h2 == 3 && board[1][0] == board[1][1]) || (h3 == 3 && board[2][0] == board[2][1])) return 1;
        else if((h1 == 6 && board[0][0] == board[0][1]) || (h2 == 6 && board[1][0] == board[1][1]) || (h3 == 6 && board[2][0] == board[2][1])) return 2;

        int v1 = board[0][0] + board[1][0] + board[2][0];
        int v2 = board[0][1] + board[1][1] + board[2][1];
        int v3 = board[0][2] + board[1][2] + board[2][2];
        if((v1 == 3 && board[0][0] == board[1][0]) || (v2 == 3 && board[1][1] == board[2][1]) || (v3 == 3 && board[1][2] == board[2][2])) return 1;
        else if((h1 == 6 && board[0][0] == board[1][0]) || (v2 == 6 && board[1][1] == board[2][1]) || (v3 == 6 && board[1][2] == board[2][2])) return 2;

        int diag1 = board[0][0] + board[1][1] + board[2][2];
        int diag2 = board[2][0] + board[1][1] + board[0][2];
        if((diag1 == 3 && board[0][0] == board[1][1]) || (diag2 == 3 && board[2][0] == board[1][1])) return 1;
        else if((diag1 == 6 && board[0][0] == board[1][1]) || (diag2 == 6 && board[2][0] == board[1][1])) return 2;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 0) return -1;
            }
        }

        return 0; //if nothing above returns that means game is draw
    }
}
