package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlReg extends AsyncTask<String, Void, Boolean> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String uname = null;
    String upwd = null;
    int ujob;

    @Override
    protected Boolean doInBackground(String... strings) {
        uname = strings[0];
        upwd = strings[1];
        ujob = Integer.parseInt(strings[2]);

        boolean aBoolean = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username,db_password);
            Statement st = con.createStatement();
            if (uname.equals("") || st.executeQuery("SELECT `user`.username, " +
                    "`user`.`password` FROM `user` WHERE user.username='" + uname + "';").next()){
                return aBoolean;
            }
            String sql = "INSERT INTO user(username, `password`, job) VALUES ('"
                    + uname + "' , '" + upwd +"' , '" + ujob + "');";
            st.execute(sql);
            sql = "SELECT `user`.username, `user`.`password` " +
                    "FROM `user` WHERE user.username='" + uname + "';";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                if (rs.getString("username").equals(uname) &&
                        rs.getString("password").equals(upwd)) {
                    rs.close();
                    st.close();
                    con.close();
                    aBoolean = true;
                    return aBoolean;
                }
            }
            rs.close();
            st.close();
            con.close();
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
