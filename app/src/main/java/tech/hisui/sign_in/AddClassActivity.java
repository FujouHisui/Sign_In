package tech.hisui.sign_in;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class AddClassActivity extends AppCompatActivity {

    private EditText et_course_id;
    private EditText et_course_name;
    private EditText et_teacher_name;
    private Button bt_submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);//返回
        }
        et_course_id = findViewById(R.id.et_class_id);
        et_course_name = findViewById(R.id.et_class_name);
        et_teacher_name = findViewById(R.id.et_teacher_name);
        bt_submit = findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((et_course_id.getText().toString().equals("") ||
                        et_course_name.getText().toString().equals("")) ||
                        et_teacher_name.getText().toString().equals(""))
                    Toast.makeText(AddClassActivity.this, "不得为空",
                            Toast.LENGTH_SHORT).show();
                else {
                    MysqlAddCourse msa = new MysqlAddCourse();
                    boolean result = false;
                    try {
                        result = msa.execute(et_course_id.getText().toString(),
                                et_course_name.getText().toString(),
                                et_teacher_name.getText().toString()).get();
                    }catch (ExecutionException e){
                        e.printStackTrace();
                        }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (result)
                        AddClassActivity.this.finish();
                    else
                        Toast.makeText(AddClassActivity.this,
                                "Error", Toast.LENGTH_SHORT).show();
                }
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

}
