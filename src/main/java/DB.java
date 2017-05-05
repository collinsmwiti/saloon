// imports
import org.sql2o.*;

// to link with the database
public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do", "collins", "password");
}
