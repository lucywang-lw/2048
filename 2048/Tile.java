import java.awt.Color;

public class Tile {

    private int val;
    private Color col; // color

    public Tile() {
        this.val = 0;
    }

    public Tile(int val) {
        this.val = val;
    }

    // getter to return the tile's value
    public int getTile() {
        return this.val;
    }

    public Tile[] getSegment(Tile[][] board, int x) {
        return board[x];
    }

    // setters
    // set the tile's value
    public void setTile(int val) {
        this.val = val;
    }

    // set the tile's color
    public void setCol() {
        int val = this.val;
        switch (val) {
            case 2:
                this.col = new Color(183, 192, 238);
                break;
            case 4:
                this.col = new Color(136, 151, 227);
                break;
            case 8:
                this.col = new Color(116, 133, 219);
                break;
            case 16:
                this.col = new Color(76, 66, 218);
                break;
            case 32:
                this.col = new Color(213, 156, 214);
                break;
            case 64:
                this.col = new Color(208, 123, 209);
                break;
            case 128:
                this.col = new Color(182, 70, 184);
                break;
            case 256:
                this.col = new Color(138, 31, 140);
                break;
            case 512:
                this.col = new Color(82, 20, 75);
                break;
            case 1024:
                this.col = new Color(75, 156, 196);
                break;
            case 2048:
                this.col = new Color(38, 106, 140);
                break;
            default:
                this.col = new Color(181, 181, 181);
        }
    }

    // get the color
    public Color getCol() {
        this.setCol();
        return this.col;
    }

}
