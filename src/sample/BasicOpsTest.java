package canvastest;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.animation.PauseTransition;

public class BasicOpsTest extends Application {
    //Creating the main function using javafx
    public static void main(String[] args) { launch(args); }

    int x = 1000; //Using these assigned values to the integers will make building the scene easier.
    int y = 600;

    public Stage window;
    public Scene firstscene, mainscreen; //The two screens of the application.
    Label scenepop, mainTitle; //Scenepop is the instructions that will be portrayed in the first scene
    //And mainTitle is the main title of the game, Memory Game.
    Button proceed;
    Pane lay; //These two panes are for the layouts of the two scenes.
    Pane lay2;
    Line horiz;
    int randval1;
    int randval2;
    int randval3;


    int click1=-1;
    int click2=-1;
    int click3=-1;


    public void onclick(Rectangle r) {
        r.setFill(Color.RED);
        System.out.println("You pressed the " +r);

        if      (click1 == -1) {
            click1 = (int) ((r.getX() - 50) / 250 + 4 * ((r.getY() - 100) / 250));
        }
        else if (click2 == -1) {
            click2 = (int) ((r.getX() - 50) / 250 + 4 * ((r.getY() - 100) / 250));
        }
        else if (click3 == -1) {
            click3 = (int) ((r.getX() - 50) / 250 + 4 * ((r.getY() - 100) / 250));
        }

        if ((click1 != -1) && (click2 != -1) && (click3 != -1)) {
            if ((randval1 == click1) && (randval2 == click2) && (randval3 == click3)) {
                Label win = new Label("Well Played!!!");
                win.setFont(new Font("Comic Sans", 60));
                lay2.getChildren().add(win);
                win.setLayoutX(x/3);
                win.setLayoutY(250);
                System.out.println("You won!");
            } else {
                Label lose = new Label("OOPS, Try again!");
                lose.setFont(new Font("Comic Sans", 60));
                lay2.getChildren().add(lose);
                lose.setLayoutX(x/3);
                lose.setLayoutY(250);
                System.out.println("You lost!");
            }
            click1 =-1;
            click2 =-1;
            click3 =-1;
        }

    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        lay = new Pane();
        lay2 = new Pane();
        //Building the first scene that displays the instructions.
        firstscene = new Scene(lay, 400, 300);
        mainscreen = new Scene(lay2, x, y);

        scenepop = new Label("Welcome to the Memory Game.\nInstructions: Starting with level 1, " +
                "There will be three shapes appearing on the screen in quick secession. Repeat the shapes the computer displayed" +
                "and click them in the order they were displayed in.");
        scenepop.setFont(new Font("Comic Sans", 20));
        scenepop.setMaxWidth(350);
        scenepop.setWrapText(true);
        scenepop.setLayoutX(10);
        scenepop.setLayoutY(10);
        lay.getChildren().add(scenepop);

        proceed = new Button("Start Game");
        proceed.setOnAction(e -> window.setScene(mainscreen));
        proceed.setLayoutX(300);
        proceed.setLayoutY(265);
        lay.getChildren().add(proceed);

        window.setScene(firstscene);
        window.setTitle("Memory Game");
        window.show();

        //Building the main game
        horiz = new Line(x, y / 7, 0, y / 7);
        horiz.setStroke(Color.BLUE);
        horiz.setStrokeWidth(5);
        lay2.getChildren().add(horiz);

        mainTitle = new Label("Memory Game");
        mainTitle.setFont(new Font("Garamond", 42));
        lay2.getChildren().add(mainTitle);

        int h = 50;
        //Draw the 8 squares which will be displayed.

        Rectangle[] r = new Rectangle[8];

        r[0] = new Rectangle(h      , 100, 150, 150);
        r[1] = new Rectangle(h + 250, 100, 150, 150);
        r[2] = new Rectangle(h + 500, 100, 150, 150);
        r[3] = new Rectangle(h + 750, 100, 150, 150);
        r[4] = new Rectangle(h      , 350, 150, 150);
        r[5] = new Rectangle(h + 250, 350, 150, 150);
        r[6] = new Rectangle(h + 500, 350, 150, 150);
        r[7] = new Rectangle(h + 750, 350, 150, 150);

        for (int i = 0; i < 8; i++) {
            r[i].setStroke(Color.RED);
            lay2.getChildren().add(r[i]);
            Rectangle tmp = r[i];
            r[i].setOnMouseClicked(e -> onclick(tmp));
        }
        Random rand = new Random();
        //Creating a pause of 1/2 seconds between the different colored shapes
        int pausenum = 10;
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(pausenum));
        PauseTransition pause2 = new PauseTransition(javafx.util.Duration.seconds(pausenum + 0.4));
        PauseTransition pause3 = new PauseTransition(javafx.util.Duration.seconds(pausenum + 0.8));

        PauseTransition pausefin1 = new PauseTransition(javafx.util.Duration.seconds(pausenum + 1.2));
        PauseTransition pausefin2 = new PauseTransition(javafx.util.Duration.seconds(pausenum + 1.2));
        PauseTransition pausefin3 = new PauseTransition(javafx.util.Duration.seconds(pausenum + 1.2 ));

        randval1 = rand.nextInt(7);
        r[randval1].setFill(Color.BLACK);
        System.out.println(randval1);
        pause.setOnFinished(event -> r[randval1].setFill(Color.SKYBLUE));
        pause.play();


        randval2 = rand.nextInt(7);
        r[randval2].setFill(Color.BLACK);
        System.out.println(randval2);
        pause2.setOnFinished(event -> r[randval2].setFill(Color.MAGENTA));
        pause2.play();


        randval3 = rand.nextInt(7);
        r[randval3].setFill(Color.BLACK);
        System.out.println(randval3);
        pause3.setOnFinished(event -> r[randval3].setFill(Color.DARKGREEN));
        pause3.play();

        //  for(int j = 1; j <= 3; j++) {
        //   j = randval1;
        pausefin1.setOnFinished(event -> r[randval1].setFill(Color.BLACK));
        pausefin1.play();
        pausefin2.setOnFinished(event -> r[randval2].setFill(Color.BLACK));
        pausefin2.play();
        pausefin3.setOnFinished(event -> r[randval3].setFill(Color.BLACK));
        pausefin3.play();
        }





    }


