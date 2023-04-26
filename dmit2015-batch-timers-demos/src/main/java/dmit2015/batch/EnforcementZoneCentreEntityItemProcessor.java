package dmit2015.batch;

import dmit2015.entity.EnforcementZoneCentre;
import dmit2015.repository.EnforcementZoneCentreRepository;
import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * An ItemProcessor is executed after an ItemReader has finished.
 */
@Named
@Dependent
public class EnforcementZoneCentreEntityItemProcessor implements ItemProcessor {

    /**
     * Change the return type of this method to the type of object (JsonObject, String, etc) you are processing
     * Process one item returned from an ItemReader
     */
    @Override
    public EnforcementZoneCentre processItem(Object item) throws Exception {
        String line = (String) item;
        final String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] values = line.split(delimiter);

        EnforcementZoneCentre model = new EnforcementZoneCentre();
        model.setSiteId(Short.parseShort(values[0]));
        model.setLocationDescription(values[1]);
        model.setSpeedLimit(Short.parseShort(values[2]));
        model.setReasonCodes(values[3].replaceAll("[\"()]", ""));
        model.setLatitude(Double.valueOf(values[4]));
        model.setLongitude(Double.valueOf(values[5]));
        return null;
    }

}