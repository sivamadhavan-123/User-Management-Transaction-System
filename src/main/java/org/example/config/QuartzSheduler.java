package org.example.config;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuartzSheduler implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        try {
            Connection connection=DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement=connection.prepareStatement("update amount set balance=balance+(balance*0.58)");
             statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
