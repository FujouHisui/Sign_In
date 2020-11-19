package tech.hisui.sign_in;

import android.app.AppComponentFactory;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AttendanceListActivity extends AppCompatActivity {

    private static final String CLASS_TITLE = "class_title";
    private static final String TEACHER = "teacher";
    public static final String CLASS_ID = "class_id";
    private List<Map<String, String>> dataList = new ArrayList<>();
    private List<Attendancelist> class_List = new ArrayList<>();
    private AttendancelistAdapter attendancelistAdapter= null;
    private ListView lvClassList;
    private String[] class_titles = null;
    private String[] class_id = null;
    private String[] teachers = null;
    private TypedArray images = null;

    private SwipeRefreshLayout swipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }

        initData();


        attendancelistAdapter = new AttendancelistAdapter(AttendanceListActivity.this, R.layout.attendance_list_item, class_List);
        ListView lvClassList = findViewById(R.id.lv_class_list);
        lvClassList.setAdapter(attendancelistAdapter);

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

        String[][] str = new String[50][50];
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

        class_titles = course_name;
        class_id = course_id;
        teachers = teacher;

        //class_titles = getResources().getStringArray(R.array.class_titles);
        //class_id=getResources().getStringArray(R.array.class_id);
        //teachers = getResources().getStringArray(R.array.teacher);
        //images = getResources().obtainTypedArray(R.array.images);
        if (class_titles.length > teachers.length) {
            length = teachers.length;
        } else {
            length = class_titles.length;
        }
        for (int i = 0; i < length; i++) {
            Attendancelist attendancelist= new Attendancelist();
            attendancelist.setCLASS_Title(class_titles[i]);
            attendancelist.setClass_id(class_id[i]);
            attendancelist.setmTeacher(teachers[i]);
            attendancelist.setmImageId(images.getResourceId(i, 0));
            class_List.add(attendancelist);
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

