import java.util.Scanner;

public class TicTacToe{
    public static final Scanner INPUT = new Scanner(System.in);
    private char[][] squares;
    char player;
    public TicTacToe(){
        squares = new char[][]
        {
            {'.','.','.'},
            {'.','.','.'},
            {'.','.','.'}
        };
    }

    public Boolean isGameOver(){
        if(calcScore()!=0) return true;
        for(int row =0; row<3;row++){
            for(int column = 0; column<3; column++){
                if(squares[row][column] == '.')
                    return false;
            }
        }
        return true;
    }
    
    protected int minimazForO(){
        int score = calcScore();
        if(isGameOver()) return  score;
        int bestScore = 2;
        for(int row = 0; row<3;row++){
            for (int column =0; column <3; column++){
                if (squares[row][column]=='.'){
                    squares[row][column] = 'O';
                    score = minimazForX();
                    if (score < bestScore) bestScore = score;
                    squares[row][column] = '.';
                }
            }
        }
        return bestScore;
    }
    protected int minimazForX(){
        int score = calcScore();
        if (isGameOver()) return score;
        int bestScore = -2;
        for (int row=0; row<3;row++){
            for (int column =0; column <3;column++){
                if (squares[row][column]=='.'){
                    squares[row][column] = 'X';
                    score = minimazForO();
                    if (score>bestScore) bestScore = score;
                    squares[row][column] = '.';
                }
            }
        }
        return bestScore;
    }
    public void play(){
        player = 'X';
        for (int move = 0; move<9;move++){
            if (isGameOver()) return;
            if (player=='X'){
                playBestMove();
                player='O';
            }else {
                System.out.println(this);
                System.out.println("Enter row: ");
                int row = INPUT.nextInt();
                System.out.println("Enter column: ");
                int column = INPUT.nextInt();
                squares[row][column] = 'O';
                player ='X';
            }
        }
    }
    
    protected void playBestMove(){
        int score;
        int bestScore = -2;
        int bestRow =-1;
        int bestColumn = -1;
        for (int row =0; row<3; row++){
            for (int column =0; column <3;column++){
                if (squares[row][column] == '.'){
                    squares[row][column]='X';
                    score = minimazForO();
                    if (score>bestScore){
                        bestScore = score;
                        bestRow = row;
                        bestColumn = column;
                    }
                    squares[row][column] = '.';
                }
            }
        }
        squares[bestRow][bestColumn] = 'X';
    }
    public int calcScore(){
        int lineScore;
        for (int i=0;i<3;i++){
            lineScore = scoreLine(
                    squares[i][0],
                    squares[i][1],
                    squares[i][2]
            );
            if (lineScore!=0) return lineScore;
            lineScore = scoreLine(
                    squares[0][i],
                    squares[1][i],
                    squares[2][i]
            );
            if (lineScore!=0) return lineScore;
        }
        lineScore = scoreLine(
          squares[0][0],
          squares[1][1],
          squares[2][2]
        );
        if (lineScore!=0) return lineScore;
        return scoreLine(
          squares[0][2],
          squares[1][1],
          squares[2][0]
        );
    }
    
    protected int scoreLine(char a, char b, char c){
        if((a=='X') && (b == 'X') && (c=='X')) return 1;
        if((a=='O') && (b == 'O') && (c=='O')) return -1;
        return 0;
    }
    
    public String toString(){
        String result = "";
        for (int row =0; row <3; row++){
            for (int column = 0; column <3; column++){
                result += squares[row][column];
            }
            result += "\n";
        }
        return result;
    }
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Welcome.\n");
        game.play();
        System.out.println(game);
        System.out.println("Game over.");
    }
}
// public class TicTacToe
// {
//     public static void main(String[] args)
//     {
//         System.out.println("Hello, World!");
//     }
// }