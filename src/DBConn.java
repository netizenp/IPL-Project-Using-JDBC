import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private Connection con;
    private Statement st;
    private ResultSet rs;
    public ResultSet getExecuteQuery(String query){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ipl_project","root","123456789A");
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
