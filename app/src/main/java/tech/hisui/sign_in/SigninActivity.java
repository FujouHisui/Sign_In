package tech.hisui.sign_in;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
//import com.cqvie.options.HttpUtilsHttpURLConnection;
import java.util.Date;

public class SigninActivity extends AppCompatActivity implements LocationSource,AMapLocationListener {

    private MapView mapView = null;
    private AMap aMap;
    private Button request;
    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption mLocationOption;
    private OnLocationChangedListener mListener;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    String date = null;


    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_location_signin);

        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.map_view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        //获取签到按钮
        request = (Button) findViewById(R.id.requet);


        init();
        //初始化定位
        AMapLocationClient mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();

        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WiFi
        mLocationOption.setWifiScan(true);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        //设置指南针
        aMap.getUiSettings().setCompassEnabled(true);
        //设置定位监听
        aMap.setLocationSource(this);
        //按钮
        //启动定位
        mLocationClient.startLocation();

    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        //判断对象不为空
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                aMapLocation.getLocationType();
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                Date nowdate = new Date(aMapLocation.getTime());
                date = df.format(nowdate);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getPoiName();
                aMapLocation.getAoiName();

                //设置缩放级别
                aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                //地图移动到定位点
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLatitude())));
                mListener.onLocationChanged(aMapLocation);
            } else {
                //定位失败
                Log.e("HLQ_Struggle", "Location Error,errCode: " + aMapLocation.getErrorCode() + ",errInfo:" + aMapLocation.getErrorInfo());
            }

            request = (Button)findViewById(R.id.request);
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(SigninActivity.this , "" , Toast.LENGTH_SHORT).show();
                }
            });



        }
    }




}

   /* public void signClickListen(final String date,final  String name,final String signaddress) {
        new Thread(new Runnable() {

            @Override
            public void run() {
               String url = HttpUtilsHttpURLConnection.BASE_URL + "/SignServlet";
                 Map<String, String> params = new HashMap<String, String>();
                 Message msg = new Message();
                Bundle data = new Bundle();
               params.put("signtime",date);
               params.put("name", name);
             params.put("signaddress", signaddress);
               String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
              msg.what = 0x12;
                data.putString("result", result);
           msg.setData(data);
               hander.sendMessage(msg);
           }
  Handler hander = new Handler() {
            @Override
             public void handleMessage(Message msg) {
                              if (msg.what == 0x12) {
                                  Bundle data = msg.getData();
                                           String key = data.getString("result");//得到json返回的json
                                                       Toast.makeText(MainActivity.this,key,Toast.LENGTH_LONG).show();
                                      try {
                                               JSONObject json = new JSONObject(key);
                                            String result = (String) json.get("result");
                                              if ("success".equals(result)) {
                                                            Toast.makeText(MainActivity.this,name + "签到成功！", Toast.LENGTH_SHORT).show();

                                                          } else if ("error".equals(result)) {
                                                           Toast.makeText(MainActivity.this,name + "签到失败！", Toast.LENGTH_SHORT).show();
                                                     }
                                              } catch (JSONException e) {
                                                    e.printStackTrace();
                                      }
                                          }
                                }
           };
         }).start();
         }
}

}
*/
