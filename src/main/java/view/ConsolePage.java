package view;

import algorithm.dijkstra.Dijkstra;
import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolePage {

    static Scanner scanner = new Scanner(System.in);

    static DijkstraTree<City> tree;
    static {
        tree = new DijkstraTree<>();
    }

    public static void main(String[] args) {

        String input = "";
        while (!input.equals("7")){
            System.out.println("Main page");
            System.out.println("1- Routing");
            System.out.println("2- Range Query");
            System.out.println("3- Insert a new city");
            System.out.println("4- Insert cities by matrix");
            System.out.println("5- Manage city neighbors");
            System.out.println("6- Show cities");
            System.out.println("7- Exit");
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
                    matrixInsert();
                    break;
                case "5":
                    manageNeighbors();
                    break;
                case "6":
                    showCities();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Wrong input!");
                    System.out.println();
            }
        }
    }

    private static void showCities() {
        if (tree.getNodes().size() == 0){
            System.out.print("There is no any cities on this country...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.lineSeparator());
    }

    private static void matrixInsert() {

        System.out.println(System.lineSeparator());
    }

    private static void insertCities() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();

        System.out.print("Enter city population: ");
        String population = scanner.nextLine();

        DijkstraNode<City> insertingCity = new DijkstraNode<>(new City(cityName, Integer.parseInt(population)));

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

    private static void rangeQuery() {

        System.out.println(System.lineSeparator());
    }

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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.lineSeparator());
    }
}
