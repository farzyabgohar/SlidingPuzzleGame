// Farzyab Gohar
// 101021301
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame model;
    private SliderPuzzleView view;
    private GamePiece selectedPiece;
    private boolean justGrabbed;
    private int lastX;
    private int lastY;
    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);
        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                int wh = w;
                int fh = h;
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("Pressed");
                        lastX = wh;
                        lastY = fh;
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionMove(mouseEvent);
                    }
                });
            }
        }
        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });
        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, -10+SliderPuzzleView.GRID_UNIT_SIZE *
                (GameBoard.WIDTH+2),45 + SliderPuzzleView.GRID_UNIT_SIZE *
                (GameBoard.HEIGHT+2)));
        primaryStage.show();
        // Update the view upon startup
        view.update();
    }
    private void handleGridSectionSelection(MouseEvent mouseEvent) {
        selectedPiece = model.getCurrentBoard().pieceAt(lastX - 1, lastY - 1);
        lastX = (int)mouseEvent.getX();
        lastY = (int)mouseEvent.getY();
    }

    private void handleGridSectionMove(MouseEvent mouseEvent) {
        int currentGridX = (int)mouseEvent.getX();
        int currentGridY = (int)mouseEvent.getY();
        if (currentGridX - lastX > 0 && currentGridX - lastX > currentGridY - lastY && currentGridX - lastX >= 40){
            if ((selectedPiece instanceof HorizontalGamePiece) && (selectedPiece.canMoveRightIn(model.getCurrentBoard())) && selectedPiece instanceof GoalPiece){
                selectedPiece.moveRight();
                model.makeAMove();
                if (model.getCurrentBoard().checkCompletion(selectedPiece)){
                    model.completeBoard();
                }
            } else if (selectedPiece instanceof HorizontalGamePiece && selectedPiece.canMoveRightIn(model.getCurrentBoard())){
                selectedPiece.moveRight();
                model.makeAMove();
            }
        } else if (currentGridX - lastX < 0 && (-1 * (currentGridX - lastX) > currentGridY - lastY) && (-1 * (currentGridX - lastX) >= 40)){
            if (selectedPiece instanceof HorizontalGamePiece && selectedPiece.canMoveLeftIn(model.getCurrentBoard())) {
                selectedPiece.moveLeft();
                model.makeAMove();
            }
        }else if (currentGridY - lastY > currentGridX - lastX && currentGridY - lastY >= 40 && currentGridY - lastY > 0){
            if (selectedPiece instanceof VerticalGamePiece && selectedPiece.canMoveUpIn(model.getCurrentBoard())){
                selectedPiece.moveUp();
                model.makeAMove();
            }
        }else if (currentGridY - lastY < 0 && -1 * (currentGridY - lastY) > currentGridX - lastX && -1 * (currentGridY - lastY)>=40 ){
            if (selectedPiece instanceof VerticalGamePiece && selectedPiece.canMoveDownIn(model.getCurrentBoard())){
                selectedPiece.moveDown();
                model.makeAMove();
            }
        }
        view.update();
    }

    public static void main(String[] args) { launch(args); }
} 