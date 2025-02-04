# Palestine Route Finder
# Project Overview
The Palestine Route Finder is a graphical application designed to help users find the shortest path between two cities in Palestine based on their geographical locations and distances. The application uses Dijkstra's Algorithm, a famous algorithm for finding the shortest path between nodes in a graph, to calculate the optimal route.

This project models cities as nodes and the roads connecting them as weighted edges, with the weight representing the distance between two cities in kilometers. The program visualizes the cities and the routes between them on a 2D map, allowing users to interactively select the start and end cities to calculate and display the shortest route.

# Key Features
1- Graph Representation of Cities and Routes: The cities are represented as nodes, and the routes between them are represented as weighted edges.

2-Interactive Map: Users can select a start and end city from the map, and the program will calculate the shortest path between them.

3- Dijkstra’s Algorithm: The application implements Dijkstra’s Algorithm to find the shortest route between two cities.

4-Real-time Feedback: The program provides real-time feedback by updating the displayed route and distance when the user selects the cities.

5-Map Visualization: The cities and the calculated route are visualized on a 2D map with interactive tooltips for city names.

# Technologies Used
1-JavaFX: For the graphical user interface (GUI) that renders the cities, routes, and interactive map.

2-Dijkstra’s Algorithm: For computing the shortest path between two cities.

3-File I/O: To read city and route data from input files.

4-Java Collections: For storing the cities, routes, and other data structures such as PriorityQueue for implementing Dijkstra’s Algorithm.

# How It Works
1-City and Route Data: The city and route data is read from a text file. Each city has a name, latitude, longitude, and an associated set of neighboring cities with distances.

2-Map Creation: The cities are displayed as circles on a 2D map with coordinates based on their latitude and longitude. Routes between cities are represented as lines connecting the circles.

3-Dijkstra’s Algorithm: When the user selects a start city and an end city, the program uses Dijkstra's Algorithm to find the shortest path between them based on the distances between the cities.

4-Interactive Interface: The user can interact with the map by clicking on cities to set the start and end points for the route. The shortest path is then displayed with the total distance shown.

# Usage

1-Start the Application: Run the application by launching the Driver class. The main window will open, displaying a map of the cities.

2-Select Start and End Cities: Click on any city to set it as the start or end city for your route.

3-Calculate the Shortest Path: After selecting the cities, click the "Calculate Route" button to find the shortest path between them. The path will be displayed on the map, along with the total distance.

4-Clear the Map: To reset the map and clear the path, use the "Clear" button.

# Data Format
- The first line contains the number of cities and routes.
- The next lines list the cities with their names, latitude, and longitude.
- After that, the routes between cities are listed, with each line containing two cities and the distance between them.
