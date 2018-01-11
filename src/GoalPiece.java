// Farzyab Gohar
// 101021301
import javafx.scene.paint.Color;
public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
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
        if (topLeftX == 4 && topLeftY == 2) {
            return true;
        }
        else if (topLeftX + width > 5){
            return false;
        } else if (closestx == topLeftX + width && closesty <= topLeftY && (closesty + closesth - 1)>=topLeftY){
            return false;
        } else {
            return true;
        }
    }
}
