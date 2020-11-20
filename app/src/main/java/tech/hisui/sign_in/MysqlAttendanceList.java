package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlAttendanceList extends AsyncTask<String, Void, String[][]> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String course_id = null;

    @Override
    protected String[][] doInBackground(String... strings) {

        String[][] str = new String[50][3];
        int i = 0, j = 0;
        course_id = strings[0];
        if (course_id.equals(""))
            return null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username, db_password);
            Statement st = con.createStatement();
            String sql = "SELECT stu_id, time, position FROM `sign_log` " +
                    "WHERE sign_log.course_id='" + course_id + "';";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                str[i][j] = rs.getString("stu_id");
                i++;
                str[i][j] = rs.getString("time");
                i++;
                str[i][j] = rs.getString("position");
                j++;
                i = 0;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onPostExecute(String[][] str) {
        super.onPostExecute(str);
    }
}