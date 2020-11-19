package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlStuList extends AsyncTask<String, Void, String[][]> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String uname = null;
    String upwd = null;

    @Override
    protected String[][] doInBackground(String... strings) {

        String[][] str = new String[3][50];
        int i = 0, j = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username,db_password);
            Statement st = con.createStatement();
            String sql = "SELECT * FROM `course`;";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                str[i][j] = rs.getString("course_id");
                i++;
                str[i][j] = rs.getString("course_name");
                i++;
                str[i][j] = rs.getString("teacher_name");
                j++;
                i = 0;
            }

            rs.close();
            st.close();
            con.close();
        }catch(SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onPostExecute(String[][] str) {
        super.onPostExecute(str);
    }
}
