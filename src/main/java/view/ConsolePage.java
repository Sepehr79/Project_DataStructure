package view;

import algorithm.dijkstra.Dijkstra;
import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;
import database.api.DataBaseAPI;
import helper.CityAdder;
import exceptions.EmptyTreeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolePage {

    static Scanner scanner = new Scanner(System.in);

    static DataBaseAPI dataBaseAPI = new DataBaseAPI();

    static DijkstraTree<City> tree = dataBaseAPI.getTreeFromDataBase();

    public static void main(String[] args) {

        String input = "";

        while (!input.equals("6")){
            System.out.println("Main page");
            System.out.println("1- Routing");
            System.out.println("2- Range Query");
            System.out.println("3- Insert a new city");
            System.out.println("4- Manage city neighbors");
            System.out.println("5- Show cities");
            System.out.println("6- Save & exit");
            System.out.println("7- Reset database");
            System.out.println("8- Add graph cities");
            System.out.print("Your input: ");

            input = scanner.nextLine();

            switch (input){
                case "1":
                    routing();
                    break;
                case "2":
                    rangeQuery();
                    break;
                case "3":
                    insertCities();
                    break;
                case "4":
                    manageNeighbors();
                    break;
                case "5":
                    showCities();
                    break;
                case "6":
                    break;
                case "7":
                    resetDataBase();
                    break;
                case "8":
                    addGraphCities();
                    break;
                default:
                    System.out.println("Wrong input!");
                    sleepThread(2);
                    System.out.println(System.lineSeparator());
            }
        }

        System.out.println("Writing data on database please wait...");
        dataBaseAPI.insertTree(tree);
        System.out.println("Data successfully wrote!");
        System.gc();
    }

    private static void addGraphCities() {
        CityAdder cityAdder = new CityAdder();

        try {
            cityAdder.addCities(tree);
        } catch (EmptyTreeException e) {
            System.out.println("Tree must be empty!");
            sleepThread(2);
        }
    }

    /**
     * resetting database and tree
     */
    private static void resetDataBase() {
        System.out.println("Are you sure? y/n");
        String input2 = scanner.nextLine();

        switch (input2){
            case "y":
                dataBaseAPI.resetDataBase();
                tree = new DijkstraTree<>();
                System.out.println("Database successfully deleted!");
                sleepThread(2);
                break;
            case "n":
                break;
            default:
                System.out.println("Wrong input!");
                sleepThread(2);
        }

        System.out.println(System.lineSeparator());
    }

    /**
     * show all cities in the current tree
     */
    private static void showCities() {
        if (tree.getNodes().size() == 0){
            System.out.print("There is no any cities on this country...");
            sleepThread(1.5);
        }

        else {
            String input = "";
            while (!input.equals("y")){
                System.out.println();
                System.out.println("List of the cities:\n");
                for (DijkstraNode<City> city: tree.getNodes())
                    System.out.println(city);
                System.out.print("Enter y to exit: ");
                input = scanner.nextLine();
            }
        }
        System.out.println(System.lineSeparator());
    }

    /**
     * insert current cities as neighbor of other cities
     */
    private static void manageNeighbors() {
        List<DijkstraNode<City>> list = new ArrayList<>(tree.getNodes());

        if (list.size() > 1){
            System.out.println("Select editing city: ");
            for (int i = 0; i < list.size(); i++){
                System.out.println(i+1 + "- " + list.get(i));
            }

            System.out.print("Your input: ");
            String input1 = scanner.nextLine();

            DijkstraNode<City> editingCity = list.get(Integer.parseInt(input1)-1);
            list.remove(Integer.parseInt(input1)-1);

            System.out.print("Select neighbor city: ");
            for (int i = 0; i < list.size(); i++){
                System.out.println(i+1 + "- " + list.get(i));
            }

            System.out.println("Your input: ");
            String input2 = scanner.nextLine();

            DijkstraNode<City> neighborCity = list.get(Integer.parseInt(input2) - 1);

            System.out.println("Enter distance between cities: ");
            String distance = scanner.nextLine();

            editingCity.addTwoRoadAdjacent(neighborCity, Float.parseFloat(distance));

            System.out.println("Mission successfully completed");
        }else {
            System.out.print("Number of cities is not enough");
        }
        sleepThread(2);
        System.out.println(System.lineSeparator());
    }

    /**
     * insert new city
     */
    private static void insertCities() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();

        DijkstraNode<City> insertingCity = new DijkstraNode<>(new City(cityName));

        if (tree.getNodes().contains(insertingCity)){
            System.out.println("City already exists!");
            sleepThread(2);
            return;
        }

        System.out.print("Enter city population: ");
        String population = scanner.nextLine();

        insertingCity.getValue().setPopulation(Integer.parseInt(population));

        List<DijkstraNode<City>> cities = new ArrayList<>(tree.getNodes());
        if (cities.size() != 0){
            System.out.println("Which cities do you want to be a neighbor of?");
            for (int i = 0; i < cities.size() ; i++){
                System.out.println(i+1 + "- " + cities.get(i).getValue().getName());
            }
            System.out.println("N- no neighboring");
            System.out.print("Your input: ");
            String inputNum = scanner.nextLine();

            if (!inputNum.equalsIgnoreCase("N")){
                DijkstraNode<City> neighbouringNode = cities.get(Integer.parseInt(inputNum) - 1);

                System.out.print("Enter distance between cities: ");
                String distance = scanner.nextLine();

                insertingCity.addTwoRoadAdjacent(neighbouringNode, Float.parseFloat(distance));
            }
        }

        tree.addNode(insertingCity);
        System.out.println("City successfully added");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.lineSeparator());
    }

    /**
     * range of population
     */
    private static void rangeQuery() {
        List<DijkstraNode<City>> dijkstraNodes = new ArrayList<>();

        System.out.print("Enter first range: ");
        String firstRange = scanner.nextLine();

        System.out.print("Enter second range: ");
        String secondRange = scanner.nextLine();

        for (DijkstraNode<City> dijkstraNode: tree.getNodes())
            if (dijkstraNode.getValue().getPopulation() >= Integer.parseInt(firstRange) &&
                    dijkstraNode.getValue().getPopulation() <= Integer.parseInt(secondRange))
                dijkstraNodes.add(dijkstraNode);

        if (dijkstraNodes.size() != 0){
            System.out.println("Cities with input range: ");
            for (DijkstraNode<City> dijkstraNode: dijkstraNodes)
                System.out.println(dijkstraNode.getValue().getName());
        }else {
            System.out.println("No result found!");
        }

        sleepThread(2);
        System.out.println(System.lineSeparator());
    }

    /**
     * finding the shortest way
     */
    private static void routing() {
        List<DijkstraNode<City>> cities = new ArrayList<>(tree.getNodes());

        for (int i = 0; i < cities.size(); i++){
            System.out.println(i+1 + "- " + cities.get(i).getValue().getName());
        }

        System.out.print("Enter origin city number: ");
        String origin = scanner.nextLine();

        System.out.print("Enter destination city number: ");
        String destination = scanner.nextLine();

        DijkstraNode<City> originCity = cities.get(Integer.parseInt(origin)-1);
        DijkstraNode<City> destinationCityExample = cities.get(Integer.parseInt(destination)-1);

        new Dijkstra<City>().calculateShortestPathFromSource(tree, originCity);
        DijkstraNode<City> destinationCity = tree.getNode(destinationCityExample);

        System.out.println("Way: ");
        StringBuilder way = new StringBuilder();
        for (DijkstraNode<City> ways: destinationCity.getShortestPath())
            way.append(ways.getValue().getName()).append(" -> ");
        way.append(destinationCity.getValue().getName());

        System.out.println(way);

        System.out.println("Distance: " + destinationCity.getDistance());
        sleepThread(2);
        System.out.println(System.lineSeparator());
    }

    /**
     * method for managing thread
     * @param seconds stopped thread
     */
    private static void sleepThread(double seconds){
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
