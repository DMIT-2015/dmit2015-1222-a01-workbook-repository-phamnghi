package dmit2015.model;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class CanadianIncomeTaxManager {
    //Define a private constructor to implement Single pattern
    private CanadianIncomeTaxManager(){
        loadDataFromFile();
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

    public int[] availableTaxYears(){
        return incomeTaxRates.stream()
                .map(item -> item.getTaxYear())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .mapToInt(item -> item)
                .toArray();
    }
//    public List<String> fetchIncomeTaxData(){
//        List<String> incomeTaxData = null;
//        try{
//            Path csvPath = Path.of(
//                    getClass()
//                            .getClassLoader()
//                            .getResource("data/CanadianPersonalIncomeTaxRates.csv").toURI());
//            incomeTaxData = Files.readAllLines(csvPath);
//
//        } catch (URISyntaxException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        return incomeTaxData;
//    }

    public void loadDataFromFile(){
        try{
            try(var reader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/data/csv/CanadianPersonalIncomeTaxRates.csv")))){
                // Skip the first line as it contains headers
                reader.readLine();
                String line;
                incomeTaxRates = new ArrayList<>();
                while ( (line = reader.readLine()) != null) {
                    var optionalCanadianIncomeTaxRate = CanadianPersonalIncomeTaxRate.parseCsv(line);
                    if(optionalCanadianIncomeTaxRate.isPresent()){
                        var currentCanadianIncomeTaxRate = optionalCanadianIncomeTaxRate.orElseThrow();
                        incomeTaxRates.add(currentCanadianIncomeTaxRate);
                    }
                }
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
