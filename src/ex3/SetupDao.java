package ex3;

import java.io.File;
import java.sql.*;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

public class SetupDao extends AbstractDao {

    public void createSchema() {
        executeSqlFromFile(getClassPathFile("schema.sql"));
    }
    
    public void createExample() {
        executeSqlFromFile(getClassPathFile("example.sql"));
    }
    
    public void deleteAndmed(){
    	try {
            st = getConnection().createStatement();
            st.executeUpdate("truncate table unit");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }
    
    
    private String getClassPathFile(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getFile();
    }

    private void executeSqlFromFile(String sqlFilePath) {

        Project project = new Project();
        project.init();

        SQLExec e = new SQLExec();
        e.setProject(project);
        e.setTaskType("sql");
        e.setTaskName("sql");
        e.setSrc(new File(sqlFilePath));
        e.setDriver("org.hsqldb.jdbcDriver");
        e.setUserid("sa");
        e.setPassword("");
        e.setUrl(DB_URL);
        e.execute();
    }
}
