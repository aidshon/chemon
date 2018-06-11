package chemon;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
public class ChemON extends Application{
    @Override
    public void start(Stage primaryStage) {
    GridPane pane1=new GridPane();
    pane1.setAlignment(Pos.CENTER);
    pane1.setStyle("-fx-background-color: white");
    
    ProgressIndicator p1 = new ProgressIndicator();
    p1.setPrefSize(40, 40);
    pane1.add(p1, 0, 2);
    
    ImageView welcome=new ImageView(new Image("welcome.png"));
    pane1.getChildren().add(welcome);
    
    FadeTransition ft=new FadeTransition(Duration.millis(10000), welcome);
    ft.setFromValue(1.0);
    ft.setToValue(0.1);
    ft.setCycleCount(1);
    ft.play();
    
    Scene scene1=new Scene(pane1, 1200, 600);
    primaryStage.setTitle("ChemON");
    primaryStage.setScene(scene1);
    primaryStage.setResizable(false);
    primaryStage.show();
    
    pane1.setOnMouseClicked(e -> {
    GridPane pane2=new GridPane();
    pane2.setAlignment(Pos.BOTTOM_RIGHT);
    pane2.setStyle("-fx-background-color: MintCream");
    
    BorderPane pane4=new BorderPane();
    pane4.setPrefSize(1200, 600);
    ImageView compound=new ImageView(new Image("compound.png", 800, 500, false, false));
    Button btStart=new Button("START", new ImageView(new Image("play.png")));
    pane2.add(compound, 1, 1);
    pane4.setCenter(btStart);
    pane2.add(pane4, 0, 1);
    
    btStart.setStyle("-fx-font: 34 arial; -fx-background-color: null;");
    DropShadow shadow = new DropShadow();
    btStart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btStart.setEffect(shadow);
        }
    });
    btStart.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btStart.setEffect(null);
        }
    });
    
    Scene scene2=new Scene(pane2, 1200, 600);
    primaryStage.setScene(scene2);
    primaryStage.setResizable(false);
    primaryStage.show();
    
    btStart.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
        Pane pane3=new Pane();
        pane3.setStyle("-fx-background-color: MintCream");
    
        Label lbGrams=new Label("How many grams should take part in the reaction?");
        lbGrams.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
        lbGrams.setTextFill(Color.INDIANRED);
        TextField tfGrams=new TextField();
        tfGrams.setPrefColumnCount(1);
        tfGrams.setStyle("-fx-border-color: Navy");
        tfGrams.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                tfGrams.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });     
        BorderPane labels=new BorderPane();
        labels.setTop(lbGrams);
        labels.setBottom(tfGrams);
        
        String[] r={"H", "Li", "Be", "B", "C", "N", "O", "F", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "K",
        "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Br", "I", "Ag", "Au", 
        "OH(-) ions", "NO3(-) ions", "Cl(-) ions", "S(2-) ions", "SO3(2-) ions", "SO4(2-) ions", "CO3(2-) ions", "SiO3(2-) ions", 
        "PO4(3-) ions", "H(+) ions", "K(+) ions", "Na(+) ions", "Ag(+) ions", "Ba(2+) ions", "Ca(2+) ions", "Mg(2+) ions",
        "Zn(2+) ions", "Cu(2+) ions" ,"Hg(2+) ions", "Pb(2+) ions", "Fe(2+) ions", "Fe(3+) ions", "Al(3+) ions", "NH4(+) ions"};
        
        ComboBox<String> cbr1=new ComboBox<>();
        ComboBox<String> cbr2=new ComboBox<>();

        HBox hbComboBox=new HBox();
        hbComboBox.setSpacing(85);
        hbComboBox.getChildren().addAll(cbr1, labels, cbr2);
        
        Tooltip toolTip=new Tooltip();
        toolTip.setText("Select two different reagents from each table of reagents if\n"
                + "you want to get basic reactions and their structures.\n"+
                "Select the same reagent from each table of reagents if\n"+
                "you want to get more complex reactions.\n");
        Image image = new Image("hint.png", 50, 50, false, false);
        toolTip.setGraphic(new ImageView(image));
        toolTip.setStyle("-fx-background-color: Indigo; -fx-font: 13 arial;");
        
        cbr1.setPrefWidth(250);
        cbr1.setStyle("-fx-background-color: MintCream; -fx-font: 21 arial; -fx-font-weight: 800;");
        cbr1.setValue("Select reagent");
        cbr1.setTooltip(toolTip);
        
        cbr2.setPrefWidth(250);
        cbr2.setStyle("-fx-background-color: MintCream; -fx-font: 21 arial; -fx-font-weight: 800; ");
        cbr2.setValue("Select reagent");
        cbr2.setTooltip(toolTip);
        
        ObservableList<String> items=FXCollections.observableArrayList(r);
        cbr1.getItems().addAll(items);
        cbr2.getItems().addAll(items);
        Button btMix=new Button("MIX", new ImageView(new Image("mix.png", 75, 75, false, false)));
        btMix.setStyle("-fx-font: 34 arial; -fx-font-weight: 800; -fx-background-color: null");
        btMix.setTextFill(Color.BLACK);
        DropShadow shadow1=new DropShadow();
        btMix.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btMix.setEffect(shadow1);
        }
        });
        btMix.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btMix.setEffect(null);
        }
        });
        BorderPane borderPane=new BorderPane();
        borderPane.setPrefSize(1200, 600); 
        
        btMix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ImageView reaction;
                ImageView structure;
                GridPane gridPane=new GridPane();
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setPrefSize(1200, 600);
                borderPane.setCenter(gridPane);
                VBox vbMix=new VBox();
                vbMix.setSpacing(75);
                vbMix.setAlignment(Pos.CENTER);
                gridPane.getChildren().add(vbMix);
                String selected1=cbr1.getValue().toString();
                String selected2=cbr2.getValue().toString();
                if ((selected1=="H" && selected2=="Li") || (selected1=="Li" && selected2=="H")) {
                    reaction=new ImageView(new Image("r1.png"));
                    structure=new ImageView(new Image("lih.png", 250, 250, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="O") || (selected1=="O" && selected2=="H")) {
                    reaction=new ImageView(new Image("r2.png"));
                    structure=new ImageView(new Image("h2o.png", 300, 275, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="F") || (selected1=="F" && selected2=="H")) {
                    reaction=new ImageView(new Image("r3.png"));
                    structure=new ImageView(new Image("hf.png", 453, 208, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="Cl") || (selected1=="Cl" && selected2=="H")) {
                    reaction=new ImageView(new Image("r4.png"));
                    structure=new ImageView(new Image("hcl.png", 465, 198, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="Br") || (selected1=="Br" && selected2=="H")) {
                    reaction=new ImageView(new Image("r5.png"));
                    structure=new ImageView(new Image("hbr.png", 488, 205, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="I") || (selected1=="I" && selected2=="H")) {
                    reaction=new ImageView(new Image("r6.png"));
                    structure=new ImageView(new Image("hi.png", 507, 204, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="O") || (selected1=="O" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r7.png"));
                    structure=new ImageView(new Image("li2o.png", 400, 260, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="N") || (selected1=="N" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r8.png"));
                    structure=new ImageView(new Image("li3n.png", 350, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="F") || (selected1=="F" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r9.png"));
                    structure=new ImageView(new Image("lif.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="Br") || (selected1=="Br" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r10.png"));
                    structure=new ImageView(new Image("libr.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="Cl") || (selected1=="Cl" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r11.png"));
                    structure=new ImageView(new Image("licl.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Li" && selected2=="I") || (selected1=="I" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r12.png"));
                    structure=new ImageView(new Image("lii.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Be" && selected2=="O") || (selected1=="O" && selected2=="Be")) {
                    reaction=new ImageView(new Image("r13.png"));
                    structure=new ImageView(new Image("beo.png", 350, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Be" && selected2=="Br") || (selected1=="Br" && selected2=="Be")) {
                    reaction=new ImageView(new Image("r14.png"));
                    structure=new ImageView(new Image("becl.png", 600, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Be" && selected2=="Cl") || (selected1=="Cl" && selected2=="Be")) {
                    reaction=new ImageView(new Image("r15.png"));
                    structure=new ImageView(new Image("becl.png", 600, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="O") || (selected1=="O" && selected2=="B")) {
                    reaction=new ImageView(new Image("r16.png"));
                    structure=new ImageView(new Image("b2O3.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="F") || (selected1=="F" && selected2=="B")) {
                    reaction=new ImageView(new Image("r17.png"));
                    structure=new ImageView(new Image("bf3.png"));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="Br") || (selected1=="Br" && selected2=="B")) {
                    reaction=new ImageView(new Image("r18.png"));
                    structure=new ImageView(new Image("bbr3.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="Cl") || (selected1=="Cl" && selected2=="B")) {
                    reaction=new ImageView(new Image("r19.png"));
                    structure=new ImageView(new Image("bcl3.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="C" && selected2=="O") || (selected1=="O" && selected2=="C")) {
                    reaction=new ImageView(new Image("r20.png"));
                    structure=new ImageView(new Image("co2.png", 600, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="C" && selected2=="F") || (selected1=="F" && selected2=="C")) {
                    reaction=new ImageView(new Image("r21.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="O" && selected2=="F") || (selected1=="F" && selected2=="O")) {
                    reaction=new ImageView(new Image("r22.png"));
                    structure=new ImageView(new Image("f2o2.png", 500, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="F" && selected2=="Cl") || (selected1=="Cl" && selected2=="F")) {
                    reaction=new ImageView(new Image("r23.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView(new Image("r24.png")), new ImageView(new Image("r25.png")));
                    btMix.setDisable(true);
                }
                else if ((selected1=="F" && selected2=="Br") || (selected1=="Br" && selected2=="F")) {
                    reaction=new ImageView(new Image("r26.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView(new Image("r27.png")));
                    btMix.setDisable(true);
                }
                else if ((selected1=="F" && selected2=="I") || (selected1=="I" && selected2=="F")) {
                    reaction=new ImageView(new Image("r28.png"));
                    structure=new ImageView(new Image("if.png"));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Na" && selected2=="O") || (selected1=="O" && selected2=="Na")) {
                    reaction=new ImageView(new Image("r29.png"));
                    structure=new ImageView(new Image("na2o2.png", 500, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Na" && selected2=="F") || (selected1=="F" && selected2=="Na")) {
                    reaction=new ImageView(new Image("r30.png"));
                    structure=new ImageView(new Image("naf.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Na" && selected2=="Cl") || (selected1=="Cl" && selected2=="Na")) {
                    reaction=new ImageView(new Image("r31.png"));
                    structure=new ImageView(new Image("nacl.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Na" && selected2=="Br") || (selected1=="Br" && selected2=="Na")) {
                    reaction=new ImageView(new Image("r32.png"));
                    structure=new ImageView(new Image("nabr.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Na" && selected2=="I") || (selected1=="I" && selected2=="Na")) {
                    reaction=new ImageView(new Image("r33.png"));
                    structure=new ImageView(new Image("nai.png", 320, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Mg" && selected2=="O") || (selected1=="O" && selected2=="Mg")) {
                    reaction=new ImageView(new Image("r34.png"));
                    structure=new ImageView(new Image("mgo.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Mg" && selected2=="N") || (selected1=="N" && selected2=="Mg")) {
                    reaction=new ImageView(new Image("r35.png"));
                    structure=new ImageView(new Image("mg3n2.png"));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Mg" && selected2=="Cl") || (selected1=="Cl" && selected2=="Mg")) {
                    reaction=new ImageView(new Image("r36.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Mg" && selected2=="Br") || (selected1=="Br" && selected2=="Mg")) {
                    reaction=new ImageView(new Image("r37.png"));
                    structure=new ImageView(new Image("mgbr.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Mg" && selected2=="H") || (selected1=="H" && selected2=="Mg")) {
                    reaction=new ImageView(new Image("r38.png"));
                    structure=new ImageView(new Image("mgh2.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="H" && selected2=="OH(-) ions") || (selected1=="OH(-) ions" && selected2=="H")) {
                    reaction=new ImageView(new Image("r39.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="O") || (selected1=="O" && selected2=="Al")) {
                    reaction=new ImageView(new Image("r40.png"));
                    structure=new ImageView(new Image("al2o3.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="Cl") || (selected1=="Cl" && selected2=="Al")) {
                    reaction=new ImageView(new Image("r41.png"));
                    structure=new ImageView(new Image("alcl3.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="Br") || (selected1=="Br" && selected2=="Al")) {
                    reaction=new ImageView(new Image("r42.png"));
                    structure=new ImageView(new Image("albr3.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="I") || (selected1=="I" && selected2=="Al")) {
                    reaction=new ImageView(new Image("r43.png"));
                    structure=new ImageView(new Image("ali3.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="SO4(2-) ions") || (selected1=="SO4(2-) ions" && selected2=="H")) {
                    reaction=new ImageView(new Image("r44.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r45.png"));
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="Cl(-) ions") || (selected1=="Cl(-) ions" && selected2=="H")) {
                    reaction=new ImageView(new Image("r46.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Al" && selected2=="OH(-) ions") || (selected1=="OH(-) ions" && selected2=="Al")) {
                    reaction=new ImageView(new Image("r47.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Au" && selected2=="Cl") || (selected1=="Cl" && selected2=="Au")) {
                    reaction=new ImageView(new Image("r48.png"));
                    structure=new ImageView(new Image("aucl.png", 300, 132, false, false));
                    vbMix.getChildren().addAll(reaction, new ImageView("r49.png"), structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Au" && selected2=="Br") || (selected1=="Br" && selected2=="Au")) {
                    reaction=new ImageView(new Image("r50.png"));
                    structure=new ImageView(new Image("aubr.png", 550, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Au" && selected2=="I") || (selected1=="I" && selected2=="Au")) {
                    reaction=new ImageView(new Image("r51.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Au" && selected2=="Cl(-) ions") || (selected1=="Cl(-) ions" && selected2=="Au")) {
                    reaction=new ImageView(new Image("r52.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ag" && selected2=="Cl") || (selected1=="Cl" && selected2=="Ag")) {
                    reaction=new ImageView(new Image("r53.png"));
                    structure=new ImageView(new Image("agcl.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ag" && selected2=="Br") || (selected1=="Br" && selected2=="Ag")) {
                    reaction=new ImageView(new Image("r54.png"));
                    structure=new ImageView(new Image("agbr.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ag" && selected2=="F") || (selected1=="F" && selected2=="Ag")) {
                    reaction=new ImageView(new Image("r55.png"));
                    structure=new ImageView(new Image("agf2.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ag" && selected2=="I") || (selected1=="I" && selected2=="Ag")) {
                    reaction=new ImageView(new Image("r56.png"));
                    structure=new ImageView(new Image("agi.png", 300, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }   
                else if ((selected1=="Ca" && selected2=="Ca")) {
                    reaction=new ImageView(new Image("r57.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r58.png"), new ImageView("r59.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Sc" && selected2=="Sc")) {
                    reaction=new ImageView(new Image("r60.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Ni" && selected2=="Ni")) {
                    reaction=new ImageView(new Image("r61.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r62.png"), new ImageView("r63.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Mn" && selected2=="Mn")) {
                    reaction=new ImageView(new Image("r64.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r65.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Zn" && selected2=="Zn")) {
                    reaction=new ImageView(new Image("r66.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r67.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Fe" && selected2=="Fe")) {
                    reaction=new ImageView(new Image("r68.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r69.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="V" && selected2=="V")) {
                    reaction=new ImageView(new Image("r70.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r71.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Ti" && selected2=="Ti")) {
                    reaction=new ImageView(new Image("r72.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r73.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Cr" && selected2=="Cr")) {
                    reaction=new ImageView(new Image("r74.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                } 
                else if ((selected1=="I" && selected2=="I")) {
                    reaction=new ImageView(new Image("r75.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                } 
                else if ((selected1=="H" && selected2=="H")) {
                    reaction=new ImageView(new Image("r76.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r77.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Li" && selected2=="Li")) {
                    reaction=new ImageView(new Image("r78.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r79.png"), new ImageView("r80.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Au" && selected2=="Au")) {
                    reaction=new ImageView(new Image("r81.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r82.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Ag" && selected2=="Ag")) {
                    reaction=new ImageView(new Image("r83.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r84.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Br" && selected2=="Br")) {
                    reaction=new ImageView(new Image("r85.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r86.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="K" && selected2=="K")) {
                    reaction=new ImageView(new Image("r87.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r88.png"));
                    btMix.setDisable(true);
                } 
                else if ((selected1=="Zn" && selected2=="NO3(-) ions") || (selected1=="NO3(-) ions" && selected2=="Zn")) {
                    reaction=new ImageView(new Image("r89.png"));
                    vbMix.getChildren().addAll(reaction, new ImageView("r90.png"));
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ti" && selected2=="NO3(-) ions") || (selected1=="NO3(-) ions" && selected2=="Ti")) {
                    reaction=new ImageView(new Image("r91.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Cr" && selected2=="Cl(-) ions") || (selected1=="Cl(-) ions" && selected2=="Cr")) {
                    reaction=new ImageView(new Image("r92.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Cr" && selected2=="SO4(2-) ions") || (selected1=="SO4(2-) ions" && selected2=="Cr")) {
                    reaction=new ImageView(new Image("r93.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="S" && selected2=="H") || (selected1=="H" && selected2=="S")) {
                    reaction=new ImageView(new Image("r94.png"));
                    structure=new ImageView(new Image("h2s.png", 512, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="S" && selected2=="S(2-) ions") || (selected1=="S(2-) ions" && selected2=="S")) {
                    reaction=new ImageView(new Image("r95.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ni" && selected2=="SO4(2-) ions") || (selected1=="SO4(2-) ions" && selected2=="Ni")) {
                    reaction=new ImageView(new Image("r96.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Ni" && selected2=="Cl(-) ions") || (selected1=="Cl(-) ions" && selected2=="Ni")) {
                    reaction=new ImageView(new Image("r97.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="Sc" && selected2=="NO3(-) ions") || (selected1=="NO3(-) ions" && selected2=="Sc")) {
                    reaction=new ImageView(new Image("r98.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="C") || (selected1=="C" && selected2=="B")) {
                    reaction=new ImageView(new Image("r99.png"));
                    structure=new ImageView(new Image("b4c.png", 325, 300, false, false));
                    vbMix.getChildren().addAll(reaction, structure);
                    btMix.setDisable(true);
                }
                else if ((selected1=="B" && selected2=="NO3(-) ions") || (selected1=="NO3(-) ions" && selected2=="B")) {
                    reaction=new ImageView(new Image("r100.png"));
                    vbMix.getChildren().addAll(reaction);
                    btMix.setDisable(true);
                }
                else if (selected1=="Select reagent" || selected2=="Select reagent") {
                    Label lb=new Label("You haven't chosen anything");
                    lb.setStyle("-fx-font: 30 arial; -fx-font-weight: 800");
                    vbMix.getChildren().add(lb);
                }
                else {
                    Label lbNo=new Label("There is no such reaction under consideration in chemistry. \nTry again with other reagents.");
                    lbNo.setStyle("-fx-font: 30 arial; -fx-font-weight: 800");
                    vbMix.getChildren().add(lbNo);
                }
            }
        });
        VBox vbDetails=new VBox();
        vbDetails.setSpacing(10);
        vbDetails.setAlignment(Pos.TOP_CENTER);
                
        Button btHeat=new Button("HEAT", new ImageView(new Image("heat.png", 75, 75, false, false)));
        btHeat.setStyle("-fx-font: 34 arial; -fx-font-weight: 800; -fx-background-color: null");
        btHeat.setTextFill(Color.ORANGE);
        btHeat.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btHeat.setEffect(shadow1);
        }
        });
        btHeat.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btHeat.setEffect(null);
        }
        });
        btHeat.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
               final Stage stage=new Stage(StageStyle.TRANSPARENT);
               BorderPane pane=new BorderPane();
               Scene scene=new Scene(pane, 400, 300, Color.TRANSPARENT);
               stage.setScene(scene);
               stage.show();
            
               Rectangle dragger=new Rectangle(0, 0, 500, 500);
               dragger.setFill(Color.CORNSILK);
               
               Label lbHeat=new Label("Write temperature in Celsius: ");
               lbHeat.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
               lbHeat.setTextFill(Color.INDIANRED);
               
               TextField tfHeat=new TextField();
               tfHeat.setPrefColumnCount(1);
               tfHeat.setStyle("-fx-border-color: Navy");
               tfHeat.textProperty().addListener(new ChangeListener<String>() {
               @Override
               public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                tfHeat.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
               Label lbSelect=new Label("Select state of reagents: ");
               lbSelect.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
               lbSelect.setTextFill(Color.INDIANRED);
               
               RadioButton rbLiquid = new RadioButton("Liquid");
               rbLiquid.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
               rbLiquid.setTextFill(Color.CORNFLOWERBLUE);
               
               RadioButton rbSolid = new RadioButton("Solid");
               rbSolid.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
               rbSolid.setTextFill(Color.CORNFLOWERBLUE);
               
               ToggleGroup group = new ToggleGroup();
               rbLiquid.setToggleGroup(group);
               rbSolid.setToggleGroup(group);
               
               rbLiquid.setOnAction(r -> {
                   if (rbLiquid.isSelected()) {
                       Label lbLiquid=new Label("Reagents are liquid");
                       lbLiquid.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
                       lbLiquid.setTextFill(Color.DARKMAGENTA);
                       rbSolid.setDisable(true);
                       vbDetails.getChildren().add(lbLiquid);
                   }
                   });
                    rbSolid.setOnAction(r -> {
                   if (rbSolid.isSelected()) {
                       Label lbSolid=new Label("Reagents are solid");
                       lbSolid.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
                       lbSolid.setTextFill(Color.DARKMAGENTA);
                       rbLiquid.setDisable(true);
                       vbDetails.getChildren().add(lbSolid);
                   }
                   });
                    
               Button btAccept=new Button("ACCEPT", new ImageView(new Image("accept.png", 50, 50, false, false)));
               btAccept.setStyle("-fx-font: 22 arial; -fx-font-weight: 800; -fx-background-color: null");
               btAccept.setTextFill(Color.GREY);
               btAccept.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
               @Override public void handle(MouseEvent e) {
               btAccept.setEffect(shadow1);
               }
               });
               btAccept.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
               @Override public void handle(MouseEvent e) {
               btAccept.setEffect(null);
               }
               });
               btAccept.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                    public void handle(ActionEvent e) {
                    String textField1=tfHeat.getText().toString();
                    tfGrams.setText("");
                    Label tfLabel1=new Label("Reaction has been taken under "+textField1+" Celsius degrees");
                    tfLabel1.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
                    tfLabel1.setTextFill(Color.DARKMAGENTA);
                    tfGrams.requestFocus();
                    
                    vbDetails.getChildren().add(tfLabel1);
                    
                    stage.close();
                    }
                   });
               
                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setAlignment(Pos.TOP_CENTER);
                vBox.getChildren().addAll(lbHeat, tfHeat, lbSelect, rbLiquid, rbSolid, btAccept);
                pane.getChildren().add(dragger);
                pane.setCenter(vBox);
           }
        });
        
        Button btDetails=new Button("DETAILS", new ImageView(new Image("details.png", 75, 75, false, false)));
        btDetails.setStyle("-fx-font: 34 arial; -fx-font-weight: 800; -fx-background-color: null");
        btDetails.setTextFill(Color.ORANGE);
        btDetails.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btDetails.setEffect(shadow1);
        }
        });
        btDetails.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btDetails.setEffect(null);
        }
        });
        btDetails.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
           @Override
          public void handle(MouseEvent e) {
              btDetails.setCancelButton(true);
          }
        });
         tfGrams.setOnKeyPressed(k -> {
            if (k.getCode()==KeyCode.ENTER) {
             String textField2=tfGrams.getText().toString();
             tfGrams.setText("");
             tfGrams.setDisable(true);
             Label tfLabel2=new Label(textField2+" grams of each reagent are used");
             tfLabel2.setStyle("-fx-font: 22 arial; -fx-font-weight: 800");
             tfLabel2.setTextFill(Color.DARKMAGENTA);
             vbDetails.getChildren().add(tfLabel2);
            }
            });
            tfGrams.requestFocus();
            
        btDetails.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
            final Stage stage=new Stage(StageStyle.TRANSPARENT);
               btHeat.setDisable(true);
               BorderPane pane=new BorderPane();
               Scene scene=new Scene(pane, 600, 150, Color.CORNSILK);
               stage.setScene(scene);
               stage.show();
               
               Button btClose=new Button("CLOSE", new ImageView(new Image("close.png", 30, 30, false, false)));
               btClose.setStyle("-fx-font: 22 arial; -fx-font-weight: 800; -fx-background-color: null");
               btClose.setTextFill(Color.DARKMAGENTA);
               btClose.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
               @Override public void handle(MouseEvent e) {
               btClose.setEffect(shadow1);
               }
               });
               btClose.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
               @Override public void handle(MouseEvent e) {
               btClose.setEffect(null);
               }
               });
               btClose.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                    public void handle(ActionEvent event) {
                    stage.close();
                    }
                   });
               vbDetails.getChildren().add(btClose);
               pane.setCenter(vbDetails);
           }
        });
        
        Button btAgain=new Button("AGAIN", new ImageView(new Image("again.png", 75, 75, false, false)));
        btAgain.setStyle("-fx-font: 34 arial; -fx-font-weight: 800; -fx-background-color: null");
        btAgain.setTextFill(Color.RED);
        btAgain.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btAgain.setEffect(shadow1);
        }
        });
        btAgain.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btAgain.setEffect(null);
        }
        });
        btAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            primaryStage.setScene(scene2);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        });
        
        HBox hbButtons=new HBox();
        hbButtons.setSpacing(95);
        hbButtons.getChildren().addAll(btMix, btHeat, btDetails, btAgain);
        
        borderPane.setTop(hbComboBox);
        borderPane.setBottom(hbButtons);
        pane3.getChildren().add(borderPane);
        
        Scene scene3=new Scene(pane3, 1200, 600);
        primaryStage.setScene(scene3);
        primaryStage.show();
        }
    });
    });
    }
    public static void main(String[] args) {
        launch(args);
    }
}