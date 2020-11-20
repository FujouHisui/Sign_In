package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlAddCourse extends AsyncTask<String, Void, Boolean> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String course_id = null;
    String course_name = null;
    String teacher_name = null;


    @Override
    protected Boolean doInBackground(String... strings) {
        course_id = strings[0];
        course_name = strings[1];
        teacher_name = strings[2];

        boolean aBoolean = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username,db_password);
            Statement st = con.createStatement();
            if (course_id.equals("") || st.executeQuery("SELECT `course`.course_id, " +
                    "`course`.`course_name` FROM `course` " +
                    "WHERE course.course_id='" + course_id + "';").next()){
                return aBoolean;
            }
            String sql = "INSERT INTO course(course_id, course_name, teacher_name) VALUES ('"
                    + course_id + "' , '" + course_name +"' , '" + teacher_name + "');";
            st.execute(sql);
            aBoolean  = true;
            st.close();
            con.close();
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