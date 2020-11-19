package tech.hisui.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {

    private ImageButton ib_class_list;
    private ImageButton ib_add_class;
    private ImageButton ib_list;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局文件activity_main.xml文件
        setContentView(R.layout.activity_teacher);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }

        Intent i = getIntent();
        String account = i.getStringExtra("Account");


        ib_class_list=(ImageButton) findViewById(R.id.teacher_class_list);
        ib_add_class=(ImageButton) findViewById( R.id.add_class);
        ib_list=(ImageButton)findViewById(R.id.list_sign_in);
        ib_class_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //对于下面的两个参数，分别代表本页面和跳转页面的参数，不过要注意本页面的是用this，要跳转到的页面是class
                if(v==ib_class_list)
                {
                    Intent intent=new Intent(   TeacherActivity.this,ClassListActivity.class);
                    startActivity(intent);
                }
            }
        });

        ib_add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==ib_add_class)
                {
                    Intent intent=new Intent(   TeacherActivity.this,AddClassActivity.class);
                    startActivity(intent);
                }
            }
        });

        ib_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==ib_list)
                {
                    Intent intent=new Intent(   TeacherActivity.this,AttendanceListActivity.class);
                    startActivity(intent);
                }
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);//return

    }

}
