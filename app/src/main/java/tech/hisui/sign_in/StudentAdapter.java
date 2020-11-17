package tech.hisui.sign_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Classes>  {
    private List<Classes> mClassData;
    private Context mContext;
    private  int resourceId;
    public StudentAdapter(Context context, int resourceId, List<Classes> data) {
        super(context, resourceId, data);
        this.mContext=context;
        this.mClassData=data;
        this.resourceId=resourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Classes classes=getItem(position);
        View view;

        view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView tv_class_Title=view.findViewById(R.id.class_title);
        TextView tv_teacher=view.findViewById(R.id.tv_subtitle);
        TextView tv_class_id=view.findViewById(R.id.class_id);
        ImageView ivImage=view.findViewById(R.id.iv_image);

        tv_class_Title.setText(classes.getCLASS_Title());
        tv_class_id.setText(classes.getClass_id());
        tv_teacher.setText(classes.getmTeacher());
        ivImage.setImageResource(classes.getmImageId());
        return view;
    }
}
