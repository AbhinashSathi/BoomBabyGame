import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Random;

public class BoomBabyGame extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
    static int counter=0,flag=0,num,gameincr=0,sum=0;
    static int [] arr;//=new int[100];
    static int [] array=new int[64];
    static int [] scrrepeat=new int[64];
    static int count=1,scorecounting=0,levelcounter=1;
    static String path;
    Stage myStage;
    Stage primaryStage;
    @Override
    public void start(Stage myStage)
    {
        try{
            Button news =new Button("Continue Game");
            Button ad =new Button("Watch Ads");
            Button exit=new Button("ExitGame");
            Button continues=new Button("",new ImageView(new Image("ads copy.jpg",700,650,false,false)));
            Button cont=new Button("",new ImageView(new Image("instruction1.jpg")));
            Rectangle2D bounds=Screen.getPrimary().getVisualBounds();
            int panelHeight=(int)bounds.getHeight()+50,panelWidth=(int)bounds.getWidth();
            int tilewidth=(panelWidth/10),tileheight=(panelHeight/10);
            String vdpath="welcome2.mp4";
            Media vdmedia=new Media(new File(vdpath).toURI().toString());
            MediaPlayer vdmediaplayer=new MediaPlayer(vdmedia);
            MediaView vdmediaview=new MediaView(vdmediaplayer);
            vdmediaview.setFitWidth((double)panelWidth);
            vdmediaview.setFitHeight((double)panelHeight);
            sum=0;
            scorecounting=0;
            arr=new int[levelcounter*8];
            array=new int[64];
            for(int i=0;i<64;i++)
                scrrepeat[i]=0;
            GridPane adroot =new GridPane();
            BorderPane gamePane=new BorderPane();
            StackPane score=new StackPane();
            GridPane rootsound=new GridPane();
            GridPane rootNode=new GridPane();
            StackPane levelRoot=new StackPane();
            FlowPane vdPane=new FlowPane();
            vdPane.getChildren().addAll(vdmediaview);
            vdPane.setAlignment(Pos.CENTER);
            vdPane.setStyle("-fx-background-color:#000");
            VBox rootNode1=new VBox();
            rootNode1.setAlignment(Pos.CENTER);
            Scene myScene2=new Scene(rootNode1,panelWidth,panelHeight,Color.BLACK);
            Scene myScene =new Scene(cont,panelWidth,panelHeight);
            cont.setStyle("-fx-background-color:#000");
            Scene myScene1 =new Scene(gamePane,panelWidth,panelHeight,Color.BLACK);
            Scene adScene1 =new Scene(adroot,panelWidth,panelHeight,Color.BLACK);
            Scene adScene2 =new Scene(continues,panelWidth,panelHeight);
            Scene soundScene =new Scene(rootsound,panelWidth,panelHeight);
            Scene vdScene=new Scene(vdPane,panelWidth,panelHeight);
            Button exits=new Button("Exit");
            adroot.setStyle("-fx-background-color:#000");
            Label adlabel=new Label("you had completed your 3 chances");
            adlabel.setStyle("-fx-text-fill:#fff");
            adroot.setAlignment(Pos.CENTER);
            adroot.add(adlabel,0,0,1,1);
            adroot.add(ad,0,1,1,1);
            adroot.add(exits,0,2,1,1);
            adroot.setVgap(30);
            Label soundsel=new Label("Please Select Sound Effect");
            RadioButton rbsound=new RadioButton("Bomb Sound");
            RadioButton rbsound1=new RadioButton("Telugu funny sound");
            soundsel.setStyle("-fx-text-fill:#fff");
            rbsound.setSelected(true);
            rbsound.setStyle("-fx-text-fill:#fff");
            rbsound1.setStyle("-fx-text-fill:#fff");
            rootsound.setVgap(10);
            rootsound.setAlignment(Pos.CENTER);
            rootsound.add(soundsel,0,0,1,1);
            rootsound.add(rbsound,0,1,1,1);
            rootsound.add(rbsound1,0,2,1,1);
            rootsound.setStyle("-fx-background-color:#000");
            Label rootNode1Label=new Label("GAME PAUSED");
            rootNode1Label.setStyle("-fx-font-size:100px;-fx-text-border:#ffd700;-fx-text-fill:#ff4500");
            rootNode1.getChildren().addAll(rootNode1Label,
                    news,exit);
            rootNode1.setSpacing(10);
            news.setPrefSize(300,150);
            ad.setPrefSize(300,150);
            exit.setPrefSize(300,150);
            Random rand=new Random();
            myStage.setTitle("BoomBabyGame");
            rootNode.setPadding(new Insets(2));
            rootNode.setStyle("-fx-background-color: #000");
            rootNode.setHgap(5);
            rootNode.setVgap(5);
            rootNode1.setStyle("-fx-background-color: #000");
            news.setStyle("-fx-background-color: #00ff00");
            ad.setStyle("-fx-background-color: #ffff00");
            exits.setStyle("-fx-background-color: #f0f0ff");
            exit.setStyle("-fx-background-color: #ffff00");
            if(num==0)
                path="Sound2.m4a";
		    else
                path="Sound1.m4a";
            AudioClip mp=new AudioClip(new File(path).toURI().toString());

            Label label=new Label("",new ImageView(new Image("coverpic.jpg",panelWidth,tileheight,false,false)));
            Label level=new Label(levelcounter+"");
            level.setStyle("-fx-text-fill:#ffff00;-fx-font-size:35;-fx-font-weight: bold");
            label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            Label scoring=new Label(scorecounting+"");
            if(gameincr==0){
                scoring.setText(0+"");
                scorecounting=0;
            }
            scoring.setStyle("-fx-text-fill:#ffff00;-fx-font-size:35;-fx-font-weight: bold");
            ImageView scrimg1= new ImageView(new Image("userlife3.jpg",(tilewidth)-20,(panelHeight-tileheight),false,false));
            ImageView scrimg2= new ImageView(new Image("userlife2.jpg",(tilewidth)-20,(panelHeight-tileheight),false,false));
            ImageView scrimg3= new ImageView(new Image("userlife1.jpg",(tilewidth)-20,(panelHeight-tileheight),false,false));
            if(counter==0)
                score.getChildren().addAll(scrimg1,scoring);
            score.setAlignment(Pos.CENTER);
            gamePane.setTop(label);
            gamePane.setLeft(score);
            levelRoot.getChildren().addAll(new ImageView(new Image("userlevel.jpg",(tilewidth)-20,(panelHeight-tileheight),false,false)),level);
            gamePane.setRight(levelRoot);
            gamePane.setBottom(null);
            gamePane.setCenter(rootNode);
            Button [] bt=new Button[64];
            int k1=0;
            for(int i=0;i<levelcounter*8;i++){
                arr[i]=rand.nextInt(65);
                scrrepeat[i]=0;
            }
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)	{
                    int x=rand.nextInt(5);
                    final int k=k1;
                    k1++;
                    if(!checks(k))
                    {
                        bt[k]=new Button(x+"");
                        bt[k].setText(x+"");
                        bt[k].setMinSize(tilewidth+5,tileheight);
                        array[k]=x;
                        sum+=array[k];
                        rootNode.add(bt[k],i+1,j+1,1,1);
                        bt[k].setOnAction(new EventHandler<ActionEvent>()
                        {
                            public void handle(ActionEvent ae)
                            {
                                bt[k].setText(array[k]+"");
                                bt[k].setStyle("-fx-font-size:20;-fx-text-fill:#dc143c");
                                if(scrrepeat[k]==0)
                                {
                                    scrrepeat[k]=1;
                                    gameincr=1;
                                    scorecounting+=Integer.parseInt(bt[k].getText());
                                    scoring.setText(scorecounting+"");
                                    if(scorecounting>=sum)
                                    {
                                        levelcounter++;
                                        sum=0;
                                        scorecounting=0;
                                        counter=0;
                                        start(myStage);
                                    }
                                }
                            }
                        });
                    }
                    else
                    {
                        array[k]=10;
                        Image image=new Image("bomb.png",tilewidth-15,tileheight-15,false,false);
                        ImageView im=new ImageView(image);
                        bt[k]=new Button();
                        im.setImage(image);
                        bt[k].setGraphic(im);
                        rootNode.add(bt[k],i+1,j+1,1,1);
                        bt[k].setOnAction(new EventHandler<ActionEvent>()
                        {
                            public void handle(ActionEvent ae)
                            {
                                bt[k].setGraphic(im);
                                mp.play();
                                count++;
                                if(count>3)
                                {
                                    count=1;
                                    myStage.setScene(myScene2);
                                    myStage.setMaximized(true);
                                    myStage.setAlwaysOnTop(true);
                                    myStage.show();
                                    // start(myStage);
                                }
                                if(counter>2)
                                {
                                    counter=0;
                                    myStage.setScene(adScene1);
                                    myStage.setMaximized(true);
                                    myStage.setAlwaysOnTop(true);
                                    myStage.show();
                                }
                            }
                        });
                    }
                    bt[k].setDisable(true);
                }
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        counter++;
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    for(int i=0;i<64;i++){
                        Image image1=new Image("cover.jpg",tilewidth-10,tileheight-10,false,false);
                        ImageView im1=new ImageView(image1);
                        bt[i].setDisable(false);
                        if(array[i]<5)
                            bt[i].setText("");
                        else
                        {
                            im1.setImage(image1);
                            bt[i].setGraphic(im1);
                        }
                    }
                }
            });
            Thread t=new Thread(sleeper);
            news.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    if(counter==1)
                    {
                        score.getChildren().remove(scrimg1);
                        score.getChildren().remove(scoring);
                        score.getChildren().addAll(scrimg2,scoring);
                    }
                    if(counter==2)
                    {
                        score.getChildren().remove(scrimg2);
                        score.getChildren().remove(scoring);
                        score.getChildren().addAll(scrimg3,scoring);
                    }
                    gamePane.setLeft(score);
                    counter++;
                    myStage.setScene(myScene1);
                    myStage.setMaximized(true);
                    myStage.setAlwaysOnTop(true);
                    myStage.show();
                }
            });
            exit.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    Platform.exit();
                }
            });
            exits.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    Platform.exit();
                }
            });
            ad.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae)
                {
                    myStage.setScene(adScene2);
                    myStage.setMaximized(true);
                    myStage.setAlwaysOnTop(true);
                    myStage.show();
                }
            });
            continues.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae)
                {
                    gameincr=0;
                    counter=0;flag=0;
                    gameincr=0;
                    sum=0;
                    count=1;
                    scorecounting=0;
                    levelcounter=1;
                    //primaryStage.close();
                    myStage.close();
                    start(new Stage());
                    // Platform.runLater( () -> new BoomBabyGame().start( new Stage() ) );
                    //Platform.setImplicitExit(false);			   Platform.exit();
                }
            });
            cont.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae)
                {
                    myStage.setScene(soundScene);
                    myStage.setMaximized(true);
                    myStage.setAlwaysOnTop(true);
                    myStage.show();
                }
            });
            rbsound.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae)
                {
                    myStage.setScene(myScene1);
                    // myStage.initStyle(StageStyle.UNDECORATED);
                    myStage.setMaximized(true);
                    myStage.setAlwaysOnTop(true);
                    myStage.show();
                    t.start();
                }
            });
            rbsound1.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent ae)
                {
                    myStage.setScene(myScene1);
                    // myStage.initStyle(StageStyle.UNDECORATED);
                    myStage.setMaximized(true);
                    myStage.setAlwaysOnTop(true);
                    myStage.show();
                    t.start();
                }
            });
            if(levelcounter==1)
            {
                myStage.initStyle(StageStyle.UNDECORATED);
                myStage.setAlwaysOnTop(true);
                myStage.setResizable(false);
                myStage.setMaximized(true);
                vdmediaplayer.setAutoPlay(true);
                myStage.setScene(vdScene);
                myStage.show();
                vdScene.setOnMousePressed(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent me)
                    {
                        myStage.setScene(myScene);
                        myStage.show();
                    }
                });
            }
            else if(levelcounter<=7)
            {
                myStage.setScene(myScene1);
                myStage.show();
                t.start();
            }
            else{
                Button ending=new Button("",new ImageView(new Image("congrajulation.jpg")));
                ending.setStyle("-fx-background-color:#000");
                Scene end =new Scene(ending,panelWidth,panelHeight+50);
                myStage.setScene(end);
                myStage.initStyle(StageStyle.UNDECORATED);
                myStage.setAlwaysOnTop(true);
                myStage.setResizable(false);
                myStage.setMaximized(true);
                myStage.show();
            }
            myStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("bomb.ico")));
            myStage.setAlwaysOnTop(true);
            myStage.setResizable(false);
            myStage.setMaximized(true);
            myStage.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public boolean checks(int a)
    {
        for(int i=0;i<levelcounter*8;i++)
            if(arr[i]==a)
                return true;
        return false;
    }
}