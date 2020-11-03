package tech.hisui.sign_in;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ClassListActivity extends AppCompatActivity {

    private List<GetClass> classList=new ArrayList<GetClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }


        initClass();
        ClassAdapterActivity adapter = new ClassAdapterActivity(ClassListActivity.this,R.layout.activity_class_1,classList);
        ListView listView = (ListView) findViewById(R.id.all_class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long id) {
                GetClass getClass=classList.get(positon);
                Toast.makeText(ClassListActivity.this,getClass.getName(),Toast.LENGTH_SHORT).show();//课程列表
            }
        });


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


    private void initClass(){
        for (int i=0;i<2;i++){
            GetClass math=new GetClass("Math",R.mipmap.ic_class);
            GetClass english=new GetClass("English",R.mipmap.ic_class);//class_list
        }
    }


}
