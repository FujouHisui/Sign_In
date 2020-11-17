package tech.hisui.sign_in;

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

public class StudentActivity extends AppCompatActivity {

    private static final String CLASS_TITLE = "class_title";
    private static final String TEACHER = "teacher";
    public static final String CLASS_ID = "class_id";
    private List<Map<String, String>> dataList = new ArrayList<>();
    private List<Classes> class_List = new ArrayList<>();
    private ClassAdapter classAdapter = null;
    private ListView lvClassList;
    private String[] class_titles = null;
    private String[] class_id = null;
    private String[] teachers = null;
    private TypedArray images = null;

    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }

        initDate();


        classAdapter = new ClassAdapter(StudentActivity.this, R.layout.activity_student_list_item, class_List);
        ListView lvClassList = findViewById(R.id.lv_class_list);
        lvClassList.setAdapter(classAdapter);

        swipe = findViewById(R.id.swipe);

        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshData();
                    }
                }
        );

    }

    private void initDate() {
        int length;

        class_titles = getResources().getStringArray(R.array.class_titles);
        class_id=getResources().getStringArray(R.array.class_id);
        teachers = getResources().getStringArray(R.array.teacher);
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
    }

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

