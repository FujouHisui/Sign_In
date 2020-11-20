package tech.hisui.sign_in;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class StudentListActivity extends AppCompatActivity {

    private static final String CLASS_TITLE = "class_title";
    private static final String TEACHER = "teacher";
    public static final String CLASS_ID = "class_id";
    private List<Map<String, String>> dataList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    private ListView lvClassList;
    private String[] class_titles = null;
    private String[] class_id = null;
    private String[] teachers = null;
    private String account;
    private SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_item);

        lvClassList = findViewById(R.id.lv_list);
        Intent i = getIntent();
        account = i.getStringExtra("Account");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }


        initData();

        simpleAdapter = new SimpleAdapter(
                StudentListActivity.this, dataList,
                android.R.layout.simple_list_item_2,
                new String[]{"course_name", "teacher"},
                new int[]{android.R.id.text1,
                        android.R.id.text2});

        lvClassList.setAdapter(simpleAdapter);

        lvClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(StudentListActivity.this, "" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentListActivity.this, SigninActivity.class);
                intent.putExtra("stu_id", account);
                intent.putExtra("class_id", class_id[position]);
                startActivity(intent);
            }
        });
        //classAdapter = new ClassAdapter(StudentListActivity.this, R.layout.activity_student_list_item, class_List);
        //ListView lvClassList = findViewById(R.id.lv_class_list);
        //lvClassList.setAdapter(classAdapter);

        //swipe = findViewById(R.id.swipe);
/*
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //refreshData();

                    }
                }
        );
        */

    }


    private void initData() {
        int length;
        String[][] str;
        String[] course_id = new String[50];
        String[] course_name = new String[50];
        String[] teacher = new String[50];
        MysqlStuList msl = new MysqlStuList();
        try {
            str = msl.execute().get();
            course_id = str[0];
            course_name = str[1];
            teacher = str[2];
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        //class_titles = getResources().getStringArray(R.array.class_titles);
        //class_id=getResources().getStringArray(R.array.class_id);
        //teachers = getResources().getStringArray(R.array.teacher);
        //
        class_titles = course_name;
        class_id=course_id;
        teachers = teacher;

        if(class_titles.length > teachers.length){
            length = class_titles.length;
        }else {
            length = teachers.length;
        }
        for (int i = 0; i < length; i++){
            Map map = new HashMap();
            map.put("course_name", class_titles[i]);
            map.put("teacher", teachers[i]);
            dataList.add(map);
        }

        /*
        images = getResources().obtainTypedArray(R.array.images);
        if (class_titles.length > teachers.length) {
            length = teachers.length;
        } else {
            length = class_titles.length;
        }
        for (int i = 0; i < length; i++) {
            Classes classes = new Classes();
            classes.setCLASS_Title(class_titles[i]);
            classes.setClass_id(class_id[i]);
            classes.setmTeacher(teachers[i]);
            classes.setmImageId(images.getResourceId(i, 0));
            class_List.add(classes);
        }
        */
    }

    /*
    private void refreshData() {
        Random random = new Random();
        int index = random.nextInt(19);

        Classes classes = new Classes();

        classes.setCLASS_Title(class_titles[index]);
        classes.setClass_id(class_id[index]);
        classes.setmTeacher(teachers[index]);
        classes.setmImageId(images.getResourceId(index, -1));


        classAdapter.insert(classes, 0);
        classAdapter.notifyDataSetChanged();
        swipe.setRefreshing(false);
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);//return

    }
}

