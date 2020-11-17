package tech.hisui.sign_in;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {

    private ImageButton ib_student_select;
    private ImageButton ib_teacher_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_select);
        ib_student_select=(ImageButton) findViewById(R.id.student_select);
        ib_teacher_select=(ImageButton) findViewById(R.id.teacher_select);
        ib_student_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对于下面的两个参数，分别代表本页面和跳转页面的参数，不过要注意本页面的是用this，要跳转到的页面是class
                if(v==ib_student_select)
                {
                    Intent intent=new Intent(   LoginActivity.this,StudentActivity.class);
                    startActivity(intent);
                }
            }
        });

        ib_teacher_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对于下面的两个参数，分别代表本页面和跳转页面的参数，不过要注意本页面的是用this，要跳转到的页面是class
                if(v==ib_teacher_select)
                {
                    Intent intent=new Intent(   LoginActivity.this,TeacherActivity.class);
                    startActivity(intent);
                }
            }
        });

    }








}