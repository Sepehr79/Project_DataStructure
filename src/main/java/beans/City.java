package beans;

import exceptions.WrongInputException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(exclude = {"population"})
public class City {

    private String name;

    private int population;

    public void setPopulation(int population){
        if (population < 0)
            throw new WrongInputException("population must be grater than zero");
        this.population = population;
    }

    public void setName(String name){
        if (name.matches(".*[^A-Z^a-z].*"))
            throw new WrongInputException("Wrong city name format");
        this.name = name;
    }
}
