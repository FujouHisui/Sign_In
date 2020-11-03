package tech.hisui.sign_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClassAdapterActivity extends ArrayAdapter<GetClass> {
    private int resourceId;
    public ClassAdapterActivity(Context context, int textViewResourceId,
                                List<GetClass> objects) {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        GetClass getClass =getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView classImage=(ImageView)view.findViewById(R.id.class_image);
        TextView className=(TextView)view.findViewById(R.id.class_name);
        classImage.setImageResource(getClass.getImageId());
        className.setText(getClass.getName());
        return view;

    }

}
