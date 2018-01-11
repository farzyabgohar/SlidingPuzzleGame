// Farzyab Gohar
// 101021301
import javafx.scene.paint.Color;
public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }
    public boolean canMoveLeftIn(GameBoard b) {
        int closestx = -1;
        int closesty = -1;
        int closestw = 0;
        int closesth = 0;
        for (int i =0; i<b.getNumGamePieces();i++){
            if (b.getGamePieces()[i].getTopLeftX()>closestx && b.getGamePieces()[i].getTopLeftX()<topLeftX){
                closestx = b.getGamePieces()[i].getTopLeftX();
                closesty = b.getGamePieces()[i].getTopLeftY();
                closestw = b.getGamePieces()[i].getWidth();
                closesth = b.getGamePieces()[i].getHeight();
            }

        }
        if (topLeftX == 0) {
            return false;
        } else if (topLeftX - 1 == closestx && closesty <= topLeftY && topLeftY<=(closesty + closesth -1)){
            return false;
        } else {
            return true;
        }
    }
    public boolean canMoveRightIn(GameBoard b) {
        int closestx = 6;
        int closesty = 6;
        int closesth = 0;
        for (int i =0; i<b.getNumGamePieces();i++){
            if (b.getGamePieces()[i].getTopLeftX()<closestx && b.getGamePieces()[i].getTopLeftX()>(topLeftX + width - 1)){
               closestx = b.getGamePieces()[i].getTopLeftX();
               closesty = b.getGamePieces()[i].getTopLeftY();
               closesth = b.getGamePieces()[i].getHeight();
            }

        }
        if (topLeftX + width > 5){
            return false;
        } else if (closestx == topLeftX + width && closesty <= topLeftY && (closesty + closesth - 1)>=topLeftY){
            return false;
        } else {
            return true;
        }
    }
}
