import java.util.Arrays;
import java.util.Random;

public class Board extends Tile {
    int score = 0;
    public Tile[][] board;
    int times = 4;
    int border = 0;

    public Board() {
        board = new Tile[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    // Getter methods
    public int getScore() {
        return this.score;
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public int highest() {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getTile() > max) {
                    max = board[i][j].getTile();
                }
            }
        }
        return max;
    }

    public void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(board[i][j]);
            }
        }
    }

    // create a new tile with the value 2 or 4
    public void createTile() {
        Random rand = new Random();
        int[] twoFour = new int[] { 2, 4 };
        int randomVal = twoFour[rand.nextInt(twoFour.length)]; // select 2 or 4 randomly
        boolean success = true;
        while (success) {
            int col = (int) (Math.random() * 4);
            int row = (int) (Math.random() * 4);
            if (board[row][col].getTile() == 0) {
                board[row][col].setTile(randomVal);
                success = false;
            }

        }
    }

    // game is done - board is full
    public boolean over() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getTile() == 0) {
                    return false;
                }
                if (i != 3 && board[i][j] == board[i + 1][j]) {
                    return false;
                }
                if (j != 3 && board[i][j] == board[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void up() {
        Tile[] temp = new Tile[4];
        int m = 0;
        for (int i = 0; i < 4; i++) {
            m = 0;
            for (int j = 0; j < 4; j++) {
                temp[m] = board[j][i];
                m++;
            }
            temp = call(temp);
            for (int k = 0; k < 4; k++) {
                board[k][i] = temp[k];
            }
        }
    }

    public void down() {
        Tile[] temp = new Tile[4];
        int m = 0;
        for (int i = 0; i < 4; i++) {
            m = 0;
            for (int j = 3; j >= 0; j--) {
                // temp[m] = board[j][i];
                temp[m] = board[j][i];
                m++;
            }
            temp = call(temp);
            System.out.println("temp: " + Arrays.toString(temp));
            for (int k = 3; k >= 0; k--) {
                board[k][i] = temp[3 - k];
            }
        }
    }

    public void right() {
        for (int i = 0; i < 4; i++) {
            Tile[] temp = reverse(board[i]);
            temp = call(temp);
            board[i] = reverse(temp);
        }
    }

    public void left() {
        for (int i = 0; i < 4; i++) {
            board[i] = call(board[i]);
        }
    }

    public Tile[] reverse(Tile[] col) {
        for (int i = 0; i < col.length / 2; i++) {
            Tile temp = col[i];
            col[i] = col[col.length - i - 1];
            col[col.length - i - 1] = temp;
        }
        return col;
    }

    public Tile[] shift(Tile[] col) {
        Tile[] shifted = { new Tile(), new Tile(), new Tile(), new Tile() };
        int k = 0;
        for (int i = 0; i < 4; i++) {
            if (col[i].getTile() != 0) {
                shifted[k] = col[i];
                k++;
            }
        }
        return shifted;
    }

    public Tile[] join(Tile[] col) {
        for (int i = 0; i < 3; i++) {
            if (col[i].getTile() == col[i + 1].getTile()) {
                score += col[i].getTile();
                col[i].setTile(col[i].getTile() * 2);
                col[i + 1].setTile(0);

            }
        }
        return col;
    }

    public Tile[] call(Tile[] col) {
        col = shift(col);
        col = join(col);
        col = shift(col);
        return col;
    }

}
