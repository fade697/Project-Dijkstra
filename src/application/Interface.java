package application;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Interface {

	private static byte selected = 0;

	static TextArea txtArea_result = new TextArea();
	static TextArea txtArea_path = new TextArea();
	static TextField distanceTextField = new TextField();
	static Button browseButton = new Button("Browse");

//    static String dest;
//    static String source;

	static City startCity;
	static City endCity;

	static Button clearBt;

	static Button calcRouteBt;

	static Group mapGroup;

	public static Scene intInterface() {
		HBox mainPane = new HBox();
		mainPane.setPadding(new Insets(10, 20, 10, 20));
		VBox leftPane = new VBox();
		leftPane.setMinWidth(300);
		HBox fileBrowsePane = new HBox();
		fileBrowsePane.setAlignment(Pos.TOP_CENTER);
		HBox calcButtonPane = new HBox();
		calcButtonPane.setMinHeight(300);
		calcButtonPane.setAlignment(Pos.TOP_CENTER);
		StackPane routeLbPane = new StackPane();
		routeLbPane.setAlignment(Pos.CENTER);
		HBox distanceLbPane = new HBox();
		distanceLbPane.setAlignment(Pos.CENTER);
		StackPane tablePane = new StackPane();
		tablePane.setAlignment(Pos.BOTTOM_CENTER);
		tablePane.setMinHeight(300);
		StackPane mapPane = new StackPane();
		mapGroup = new Group();

		Label browseLabel = new Label("Data File: ");
		browseLabel.setDisable(true);
		TextField browseField = new TextField();
		fileBrowsePane.getChildren().addAll(browseLabel, browseField, browseButton);
		fileBrowsePane.setSpacing(10);
		calcRouteBt = new Button("Calculate Route");
		clearBt = new Button("Clear");
		calcRouteBt.setDisable(true);
		clearBt.setDisable(true);
		calcButtonPane.getChildren().addAll(calcRouteBt, clearBt);
		calcButtonPane.setSpacing(10);

		Label routeLabel = new Label();
		Font routeFont = new Font("Arial", 20);
		routeLabel.setFont(routeFont);
		routeLabel.setTextFill(Color.GREEN);
		routeLbPane.getChildren().add(routeLabel);

		Label distanceLb = new Label("Total distance (km): ");
		Label distanceLbVal = new Label();
		distanceLbVal.setTextFill(Color.GREEN);
		distanceLbVal.setFont(routeFont);

		distanceLbPane.getChildren().addAll(distanceLb, distanceTextField, distanceLbVal);
//        distanceLbPane.setTranslateY(-200);

		TableView<String> aStarVbfsTb = new TableView<>();
		aStarVbfsTb.setMaxSize(400, 150);
		TableColumn algorithmCol = new TableColumn<>("Algorithm");
		TableColumn timeCol = new TableColumn<>("Time (ms)");
		TableColumn theoreticalCol = new TableColumn<>("Theoretical");
		TableColumn spaceCol = new TableColumn<>("Space");
		aStarVbfsTb.getColumns().addAll(algorithmCol, timeCol, theoreticalCol, spaceCol);

		tablePane.getChildren().add(aStarVbfsTb);

		leftPane.getChildren().addAll(fileBrowsePane, calcButtonPane, txtArea_path, routeLbPane, distanceLbPane);
		leftPane.setSpacing(40);
		leftPane.setAlignment(Pos.CENTER);

//        mapPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		InputStream imgStream = null;
		try {
			imgStream = new FileInputStream("Palestine.png");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		Image psImg = new Image(imgStream);
		ImageView psImgView = new ImageView(psImg);

   //     psImgView.setFitHeight(900);
//        psImgView.setPreserveRatio(true);
		mapPane.setMaxHeight(536);

		mainPane.setSpacing(40);
		mainPane.getChildren().addAll(leftPane, mapPane);

		mapGroup.getChildren().add(psImgView);
//        mapGroup.getChildren().add(testCircle);
		addCitiesToMap(mapGroup, calcRouteBt);

		mapPane.getChildren().add(mapGroup);

		mapPane.setOnMouseClicked(e -> {
			System.out.println(e.getX() + " " + e.getY());
		});

		calcRouteBt.setOnAction(e -> {
			try {
				Driver.onStart();
				calcRouteBt.setDisable(true);
				clearBt.setDisable(false);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});

		clearBt.setOnAction(e -> {
			Driver.onClear2();
			clearBt.setDisable(true);
			calcRouteBt.setDisable(true);
			txtArea_result.setText("");
			txtArea_path.setText("");
		});

		return new Scene(mainPane, 1200, 800);

	}

	public static void addCitiesToMap(Group mapGroup, Button calcRouteBt) {
		Driver.table = new TableEntry[Driver.citiesMap.size() + 1];
//        for (Map.Entry<String, City> c : Driver.citiesMap.entrySet()) {
//            c.getValue().c.setFill(Color.BLUE);
//            System.out.println(c.getValue());
//            c.getValue().c.setOnMouseClicked(e -> {
//                if (selected == 0) {
//                    c.getValue().c.setFill(Color.GREEN);
//                    c.getValue().c.setRadius(c.getValue().c.getRadius() + 2);
//                    source = c.getValue().getName();
//                    selected++;
//                } else {
//                    c.getValue().c.setFill(Color.ORANGE);
//                    c.getValue().c.setRadius(c.getValue().c.getRadius() + 2);
//                    dest = c.getValue().getName();
//                    calcRouteBt.setDisable(false);
//                    selected = 0;
//                }
//            });
//        }
		for (Map.Entry<String, City> c : Driver.citiesMap.entrySet()) {
			c.getValue().c.setFill(Color.BLUE);
			mapGroup.getChildren().add(c.getValue().c);
			mapGroup.getChildren().add(c.getValue().line);
			c.getValue().line.setVisible(false);
		}
	}

	public static void warning_Message(String x) {
		Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setAlertType(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setContentText(x);
		alert.show();
	}

	public static void makeTransation(City start, City end, TableEntry[] tableEntries) {
		Polygon pathCursor = new Polygon();
		pathCursor.getPoints().setAll(start.c.getCenterX() - 5, start.c.getCenterY() + 10, start.c.getCenterX(),
				start.c.getCenterY(), start.c.getCenterX() + 5, start.c.getCenterY() + 10);
		pathCursor.setFill(Color.RED);
		mapGroup.getChildren().add(pathCursor);
		City next = start;
		do {
			translate(end.c, pathCursor);
			next = tableEntries[next.cityEntry].path;
		} while (next != end);
	}

	private static void translate(Circle c2, Polygon triangle) {
		TranslateTransition tt = new TranslateTransition();
		tt.setNode(triangle);
		tt.setDuration(javafx.util.Duration.seconds(1));
		tt.setByX(c2.getCenterX());
		tt.setByY(c2.getCenterY());
		tt.play();
	}
//    public static void drawLine(Circle s )

}