package dmit2015.model;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class CanadianIncomeTaxManager {
    //Define a private constructor to implement Single pattern
    private CanadianIncomeTaxManager(){

    }

    //Define a Single instance of this class
    private static CanadianIncomeTaxManager INSTANCE;

    // Define a static class-level to access the singleton
    public static CanadianIncomeTaxManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CanadianIncomeTaxManager();
        }
        return INSTANCE;
    }

    @Getter
    private List<CanadianPersonalIncomeTaxRate> incomeTaxRates;  //field
    public List<String> fetchIncomeTaxData(){
        List<String> incomeTaxData = null;
        try{
            Path csvPath = Path.of(
                    getClass()
                            .getClassLoader()
                            .getResource("data/CanadianPersonalIncomeTaxRates.csv").toURI());
            incomeTaxData = Files.readAllLines(csvPath);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return incomeTaxData;
    }
}
