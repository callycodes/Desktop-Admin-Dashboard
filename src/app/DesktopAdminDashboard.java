/*
 * Name: Callum Bass
 * Student ID: w1682693
 * Software Development - Group Project
 */
package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sql.Login;


public class DesktopAdminDashboard extends Application {
    
    public static Profile profile = null;
    Stage stage = null;
    String action = "";
    
    @Override
    public void start(Stage primaryStage) {
        
        
        primaryStage.setScene(mainScene());
        primaryStage.show();
        primaryStage.setTitle("Dashboard");
        stage = primaryStage;
    }

    
    public Scene mainScene() {
        BorderPane border = new BorderPane();
        HBox hbox = createHBox();
        hbox.getStyleClass().add("blue-pane");
        border.setTop(hbox);
        
        if (ProfileManager.isLoggedIn()) {
            if (profile.userType.equalsIgnoreCase("administrator")) {
                border.setBottom(createAdminPanel());
            }
            border.setLeft(createVBox());
        }
        
      
        if (action.equals("home") || action.equals("")) {
            border.setCenter(createMainPane());
        } else if (action.equals("login")) {
            border.setCenter(createLoginPane());
        }
        
        
        border.getStyleClass().add("white-pane");
        //border.setRight(addFlowPane());
        
        Scene scene = new Scene(border, 800, 600);
        
        scene.getStylesheets().add("/style.css");
        
        action = "";
        
        return scene;
    }
    
    public void refresh() {
        stage.setScene(mainScene());
        stage.show();
    }
    
    
    public VBox createVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setMinWidth(150);
        
        
        vbox.getStyleClass().add("blue-pane");

        Text title = new Text("Graphs");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] {
            new Hyperlink("Sales"),
            new Hyperlink("Marketing"),
            new Hyperlink("Distribution"),
            new Hyperlink("Costs")};

        for (int i=0; i<4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

    return vbox;
}
    
    public HBox createAdminPanel() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("blue-pane");
    hbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);

    Button activityBtn = new Button("Activity Log");
    activityBtn.setPrefSize(100, 20);
    activityBtn.getStyleClass().add("blue");
    
    Button dataBtn = new Button("Data Editor");
    dataBtn.setPrefSize(100, 20);
    dataBtn.getStyleClass().add("blue");
            
    Button searchBtn = new Button("Search");
    searchBtn.setPrefSize(100, 20);
    searchBtn.getStyleClass().add("blue");
            
    hbox.getChildren().addAll(activityBtn, dataBtn, searchBtn);

    return hbox;
    }
    
    public HBox createHBox() {
    HBox hbox = new HBox();
    
    Label logo = new Label("DASH");
    logo.getStyleClass().add("logo");
    logo.setFont(new Font("Arial Black", 30));
    
    //Added to space out the logo from the login/logout
    HBox logoBox = new HBox();
    logoBox.setAlignment(Pos.CENTER_LEFT);
    HBox.setHgrow(logoBox, Priority.ALWAYS);
    
    logoBox.getChildren().add(logo);
    
    hbox.getChildren().addAll(logoBox);
    
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);

    
    Button homeBtn = new Button("Home");
    homeBtn.setPrefSize(60, 20);
homeBtn.getStyleClass().add("blue");

    homeBtn.setOnAction((ActionEvent e) -> {
        action = "";
            refresh();
        });
    
    hbox.getChildren().add(homeBtn);
    
    if (ProfileManager.isLoggedIn()) {
        Button buttonCurrent = new Button("Logout");
    buttonCurrent.setPrefSize(100, 20);
buttonCurrent.getStyleClass().add("blue");

    buttonCurrent.setOnAction((ActionEvent e) -> {
        profile = null;
       
            alert("Success", "You've been logged out!");
            refresh();
        });
    
    hbox.getChildren().addAll(buttonCurrent);
    } else {
        Button buttonCurrent = new Button("Login");
    buttonCurrent.setPrefSize(100, 20);
    buttonCurrent.getStyleClass().add("blue");
    
    buttonCurrent.setOnAction((ActionEvent e) -> {
        action = "login";
            refresh();
        });
    
    Button buttonProjected = new Button("Register");
    buttonProjected.setPrefSize(100, 20);
    buttonProjected.getStyleClass().add("blue");
    
    hbox.getChildren().addAll(buttonCurrent, buttonProjected);
    }
    

    return hbox;
}
    public GridPane createLoginPane() {
        GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(12);
    grid.setAlignment(Pos.CENTER);

    HBox hbButtons = new HBox();
    hbButtons.setSpacing(8.0);
    
    



ColumnConstraints column1 = new ColumnConstraints();
column1.setHalignment(HPos.RIGHT);
grid.getColumnConstraints().add(column1); 

ColumnConstraints column2 = new ColumnConstraints();
column2.setHalignment(HPos.LEFT);
grid.getColumnConstraints().add(column2); 


    Button btnSubmit = new Button("Submit");
    Button btnClear = new Button("Clear");
    Button btnExit = new Button("Exit");

    Label lblName = new Label("User name:");
    TextField tfName = new TextField();
    Label lblPwd = new Label("Password:");
    PasswordField pfPwd = new PasswordField();

    hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit);
    grid.add(lblName, 0, 0);
    grid.add(tfName, 1, 0);
    grid.add(lblPwd, 0, 1);
    grid.add(pfPwd, 1, 1);
    grid.add(hbButtons, 0, 2, 2, 3);
    
    btnSubmit.setOnAction((ActionEvent e) -> {
        profile = Login.login(tfName.getText(), pfPwd.getText());
        if (!ProfileManager.isLoggedIn()){
            alert("Error", "Sorry, there was an issue logging you in!");
        } else {
            alert("Success", "You have been logged in!");
            refresh();
        }
        });
    
    
    btnClear.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        tfName.setText("");
        pfPwd.setText("");
       
    }
});
    
    return grid;
    }
    
    
        public GridPane createMainPane() {
        GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(12);
    grid.setAlignment(Pos.CENTER);

    HBox hbButtons = new HBox();
    hbButtons.setSpacing(10.0);
    
    

    

    return grid;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void alert(String title, String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(description);
    alert.showAndWait();
    }
    
    
}
