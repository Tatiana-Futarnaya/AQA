package ru.astondevs.lab6;

/**
 * @author Tatiana Futarnaya
 */
public class Park {
    private String name;
    private int maxAttractions;
    private int attractionCount;
    private Attraction[] attractions;


    public Park(String name, int maxAttractions) {
        this.name = name;
        this.maxAttractions = maxAttractions;
        this.attractions = new Attraction[maxAttractions];
        this.attractionCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAttractions() {
        return maxAttractions;
    }

    public void setMaxAttractions(int maxAttractions) {
        this.maxAttractions = maxAttractions;
    }

    public int getAttractionCount() {
        return attractionCount;
    }

    public void setAttractionCount(int attractionCount) {
        this.attractionCount = attractionCount;
    }

    public Attraction[] getAttractions() {
        return attractions;
    }

    public void setAttractions(Attraction[] attractions) {
        this.attractions = attractions;
    }


    public void addAttraction(String name, String operatingHours, double cost) {
        if (attractionCount < maxAttractions) {
            attractions[attractionCount++] = new Attraction(name, operatingHours, cost);
        } else {
            System.out.println("Cannot add more attractions, limit reached.");
        }
    }


    public void displayInfo() {
        System.out.println("Park Name: " + name);
        System.out.println("Attractions:");
        for (int i = 0; i < attractionCount; i++) {
            System.out.println(attractions[i].getInfo());
        }
    }


    private class Attraction {
        private String name;
        private String operatingHours;
        private double cost;


        public Attraction(String name, String operatingHours, double cost) {
            this.name = name;
            this.operatingHours = operatingHours;
            this.cost = cost;
        }


        public String getInfo() {
            return String.format("Attraction: %s, Operating Hours: %s, Cost: %.2f rub.", name, operatingHours, cost);
        }
    }

}
