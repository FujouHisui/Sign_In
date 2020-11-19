package tech.hisui.sign_in;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlLogin extends AsyncTask<String, Void, Boolean> {

    private static final String db_url = "jdbc:mysql://47.111.11.245:3306/sign_in";
    private static final String db_username = "root";
    private static final String db_password = "233cb6177f0d";
    String uname = null;
    String upwd = null;

    @Override
    protected Boolean doInBackground(String... strings) {
        uname = strings[0];
        upwd = strings[1];
        boolean aBoolean;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db_url, db_username,db_password);
            Statement st = con.createStatement();
            String sql = "SELECT password FROM `user` WHERE user.username = \"" + uname + "\";";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                if (rs.getString("password").equals(upwd)) {
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
