package dmit2015.batch;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.api.BatchProperty;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.BatchStatus;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Batchlets are task oriented step that is called once.
 * It either succeeds or fails. If it fails, it CAN be restarted and it runs again.
 */
@Named
@Dependent
public class HelloBatchlet extends AbstractBatchlet {

    @Inject
    private JobContext _jobContext;

    private Logger _logger = Logger.getLogger(HelloBatchlet.class.getName());

    @Inject
    @BatchProperty(name = "course_name")
    private String courseName;

    /**
     * Perform a task and return "COMPLETED" if the job has successfully completed
     * otherwise return "FAILED" to indicate the job failed to complete.
     */
    @Transactional
    @Override
    public String process() throws Exception {
        String batchStatus = BatchStatus.COMPLETED.toString();

        Properties jobParameters = _jobContext.getProperties();
        String username = jobParameters.getProperty("username");

        String message = String.format("Hello %s from a Batchlet taking %s", username, courseName);
        _logger.info(message);

        return batchStatus;
    }
}