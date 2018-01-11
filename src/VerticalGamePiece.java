// Farzyab Gohar
// 101021301
import javafx.scene.paint.Color;
public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }
    public boolean canMoveDownIn(GameBoard b) {
        int closestx = 6;
        int closesty = 6;
        for (int i =0; i<b.getNumGamePieces();i++){
            if (b.getGamePieces()[i].getTopLeftY()<closesty && b.getGamePieces()[i].getTopLeftX() == topLeftX && b.getGamePieces()[i].getTopLeftY()>=(topLeftY+height)){
                closestx = b.getGamePieces()[i].getTopLeftX();
                closesty = b.getGamePieces()[i].getTopLeftY();
            }

        }
        if (topLeftY + height == closesty && topLeftX == closestx) {
            return false;
        } else if(topLeftY + height > 5) {
            return false;
        } else {
            return true;
        }
    }
    public boolean canMoveUpIn(GameBoard b) {
        int closestx = 0;
        int closesty = 0;
        boolean x = false;
        if (topLeftY == 0){
            x = false;
        }else {
            for (int i =0;i<b.getNumGamePieces();i++) {
                if (b.getGamePieces()[i].getTopLeftX() == topLeftX && ((b.getGamePieces()[i].getTopLeftY() == topLeftY - 1) | (b.getGamePieces()[i].getTopLeftY() + height == topLeftY))){
                    x = false;
                } else {
                    x = true;
                }
            }
        }
        return x;
    }
}
