package application;
// Note undirected graph

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;



public class Driver extends Application {

    static Map<String, City> citiesMap = new HashMap<>();
    static Map<Pair<String, String>, Double> hueristicMap = new HashMap<>();
    static TableEntry[] table;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene rootScene = Interface.intInterface();
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Palestine Route Finder");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void onStart() throws Exception {
        if (table.length != 0) {
            onClear();
        }
        try {
            initializeTable();
            String end = Interface.endCity.getName();
            String start = Interface.startCity.getName();
            Dijkstra search = new Dijkstra(hueristicMap);
            if (start != null && end != null) {
                table = search.findPath(citiesMap.get(start), citiesMap.get(end), table);
                StringBuilder path = new StringBuilder("");
                printPath(citiesMap.get(end), path);
                Interface.txtArea_path.setText(path.toString());
                Interface.txtArea_result.setText("Distance to go from " + start + " to " + end + "\n="
                        + table[citiesMap.get(end).cityEntry].getDistance() + "km");
//                for (TableEntry t : table) {
//                    System.out.println(t.path.getName());
//                }
//                Interface.makeTransation(Interface.startCity.c, Interface.endCity.c, table);
            }else{
                Interface.warning_Message("Enter starting point and end point");
            }
        } catch (Exception e) {
            throw e;
        }
        
    }

    static void onClear() {
        initializeTable();
        for (int i = 0; i < table.length; i++) {
            table[i].known = false;
            if (table[i].path != null) {
//                System.out.println(table[i].path);
                table[i].path.line.setVisible(false);
                table[i].path.c.setFill(Color.BLUE);
            }
            table[i].path = null;
            table[i].distance = Double.MAX_VALUE;
        }
    }
    static void onClear2() {
//        initializeTable();
        for (int i = 0; i < table.length; i++) {
            table[i].known = false;
            if (table[i].path != null) {
//                System.out.println(table[i].path);
                table[i].path.line.setVisible(false);
                table[i].path.c.setFill(Color.BLUE);
            }
            table[i].path = null;
            table[i].distance = Double.MAX_VALUE;
        }
    }

    static void printPath(City start, StringBuilder s) {//Not to bebo start her is end
        if (table[start.cityEntry].path != null) {
            table[start.cityEntry].path.line.setEndX(start.c.getTranslateX());
            table[start.cityEntry].path.line.setEndY(start.c.getTranslateY());
            table[start.cityEntry].path.line.setVisible(true);
            table[start.cityEntry].path.c.setFill(Color.BLUE);
            printPath(table[start.cityEntry].path, s);
            s.append("to :");
            Interface.txtArea_path.setText(s.toString());
        }
        s.append(start+"\n" );
        Interface.distanceTextField.setText(String.valueOf( table[start.cityEntry].getDistance()));
    }

    static void initializeTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new TableEntry();
            table[i].known = false;
            table[i].path = null;
            table[i].distance = Double.MAX_VALUE;
        }
    }

    private static void readHuersticTable(String fileName) {
        File hFile = new File(fileName);
        try (Scanner input = new Scanner(hFile)) {
            while (input.hasNext()) {
                String data = input.nextLine();
                String[] tok = data.split(" ");//0: first city, 1 second city, 2 is air distance
                Pair<String, String> pair = new Pair<>(tok[0], tok[1]);
                hueristicMap.put(pair, Double.parseDouble(tok[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readData(String fileName) {
        File stdFile = new File(fileName);
        try (Scanner input = new Scanner(stdFile)) {
            String numOfData = input.nextLine();
            String[] str = numOfData.split(" ");
            int countries = Integer.parseInt(str[0]);
            int edges = Integer.parseInt(str[1]);
            int countriesRead = 0;
            while (input.hasNext()) {
                if (countriesRead < countries) {
                    String countryData = input.nextLine();
                    String[] tok = countryData.split(" ");
                    City city = new City(tok[0].strip(), Double.parseDouble(tok[1].strip()),
                            Double.parseDouble(tok[2].strip()));
                    citiesMap.put(city.getName(), city);
                    countriesRead++;
                } else {
                    String edgesData = input.nextLine();
                    String[] tok = edgesData.split(" ");
                    City city1 = citiesMap.get(tok[0]);
                    City city2 = citiesMap.get(tok[1]);
                    if (city1 != null && city2 != null) {
                        city1.adjacent.add(new Adjacent(city2, Float.parseFloat(tok[2])));
                        city2.adjacent.add(new Adjacent(city1, Float.parseFloat(tok[2])));
                    } else {
                        System.out.println("Invalid city name: " + tok[0] + " or " + tok[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Interface.warning_Message(e.toString());
        }
    }


    private static void setLattitudeAndLongtiude(String file) {
        File stdFile = new File(file);
//        try (Scanner input = new Scanner(stdFile)) {
//            while (input.hasNext()) {
//                String countryData = input.nextLine();
//                String[] tok = countryData.split(",");

//                citiesMap.get(tok[0]).lattitude = Double.parseDouble(tok[1]);
//                citiesMap.get(tok[0]).longtidue = Double.parseDouble(tok[2]);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Interface.warning_Message(e.toString());
//        }
    }

    public static void main(String[] args) {
        try {
            readData("cities.txt");
            setLattitudeAndLongtiude("CitiesLongLat.txt");
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
//			warning_Message(e.toString());
        }
    }

	public static List<City> calculatePath(City startCity, City endCity) {
		// TODO Auto-generated method stub
		return null;
	}


}
