package dmit2015.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class CanadianIncomeTaxManager {
    public List<String> fetchIncomeTaxData(){
        List<String> incomeTaxData = null;
        try{
            Path csvPath = Path.of(
                    getClass()
                            .getClassLoader()
                            .getResource("data/CanadianPersonalIncomeTaxRates.csv").toURI());
            incomeTaxData = Files.readAllLines(csvPath);
        }catch(){
            throw new Exception()
        }

    }
}
