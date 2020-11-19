package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlUpload extends AsyncTask<String, Void, Boolean> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String stu_id = null;
    String course_id = null;
    String position = null;

    @Override
    protected Boolean doInBackground(String... strings) {
        stu_id = strings[0];
        course_id = strings[1];
        position = strings[2];
        boolean aBoolean = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username,db_password);
            Statement st = con.createStatement();
            if ((stu_id.equals("") || course_id.equals("")) || position.equals("")){
                return aBoolean;
            }
            String sql = "INSERT INTO sign_log(stu_id, course_id, position) " +
                    "VALUES ('"+ stu_id +"' , '" + course_id +"', '"+ position +"');";
            st.execute(sql);
            st.close();
            con.close();
            aBoolean = true;
            return aBoolean;

        }catch(SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        aBoolean = false;
        return aBoolean;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
