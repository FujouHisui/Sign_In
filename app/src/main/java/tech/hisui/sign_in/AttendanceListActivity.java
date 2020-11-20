package tech.hisui.sign_in;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AttendanceListActivity extends AppCompatActivity {

    private static final String CLASS_TITLE = "class_title";
    private static final String TEACHER = "teacher";
    public static final String CLASS_ID = "class_id";
    private List<Map<String, String>> dataList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private ListView lvAttendance;
    private String[] stu_str = null;
    private String[] time_str = null;
    private String[] position_str = null;
    private String class_id;

    private SwipeRefreshLayout swipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_list);
        lvAttendance = findViewById(R.id.lv_attendance);

        Intent a = getIntent();
        class_id = a.getStringExtra("data");


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }

        initData();


        simpleAdapter = new SimpleAdapter(
                AttendanceListActivity.this, dataList,
                android.R.layout.simple_list_item_2,
                new String[]{"stu_id", "time"},
                new int[]{android.R.id.text1,
                        android.R.id.text2});

        lvAttendance.setAdapter(simpleAdapter);

        lvAttendance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AttendanceListActivity.this,
                        position_str[position], Toast.LENGTH_SHORT).show();
            }
        });


 /*         swipe = findViewById(R.id.swipe);

        swipe.setOnRefreshListener(
              new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshData();
                    }
                }
        );
*/
    }


    private void initData() {
        int length;
        String[][] str;
        String[] stu_id = new String[50];
        String[] time = new String[50];
        String[] position = new String[50];
        MysqlAttendanceList msa = new MysqlAttendanceList();
        try {
            str = msa.execute(class_id).get();
            stu_id = str[0];
            time = str[1];
            position = str[2];
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        stu_str = stu_id;
        time_str= time;
        position_str = position;

        //class_titles = getResources().getStringArray(R.array.class_titles);
        //class_id=getResources().getStringArray(R.array.class_id);
        //teachers = getResources().getStringArray(R.array.teacher);
        //images = getResources().obtainTypedArray(R.array.images);
        if(stu_str.length > time_str.length){
            length = stu_str.length;
        }else {
            length = time_str.length;
        }
        for (int i = 0; i < length; i++){
            Map map = new HashMap();
            map.put("stu_id", stu_str[i]);
            map.put("time", time_str[i]);
            dataList.add(map);
        }

    }
/*
    private void refreshData() {
        Random random = new Random();
        int index = random.nextInt(19);

        Attendancelist attendancelist = new Attendancelist();

        attendancelist.setCLASS_Title(class_titles[index]);
        attendancelist.setClass_id(class_id[index]);
        attendancelist.setmTeacher(teachers[index]);
        attendancelist.setmImageId(images.getResourceId(index, -1));


        attendancelistAdapter .insert(attendancelist, 0);
        attendancelistAdapter .notifyDataSetChanged();
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

