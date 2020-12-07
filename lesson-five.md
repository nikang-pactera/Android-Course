# 搭建一个mvp模式的框架

mvp 框架的简单结构


![图片1.png](https://cdn.nlark.com/yuque/0/2020/png/2863967/1607301499120-5dc7a644-8559-4885-944f-48a645c7fbff.png#align=left&display=inline&height=557&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%871.png&originHeight=557&originWidth=346&size=50843&status=done&style=none&width=346)


## base包：
#### BaseActivity
```java
package com.dream.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**BaseActivity
 * Created by Administrator 
 */

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //初始化mPresenter
        initPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }

        //初始化
        initView();
    }

    /**
     * 初始化mPresenter
     */
    protected abstract void initPresenter();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getLayoutId();
}
```
#### BasePresenter
```java
package com.dream.mvpdemo.base;

import com.dream.mvpdemo.model.DataManager;
import com.dream.mvpdemo.model.db.AppDbHelper;
import com.dream.mvpdemo.model.db.DbHelper;
import com.dream.mvpdemo.model.http.ApiHelper;
import com.dream.mvpdemo.model.http.AppApiHelper;
import com.dream.mvpdemo.model.preference.AppPreferenceHelper;
import com.dream.mvpdemo.model.preference.PreferenceHelper;

/**BasePresenter
 * Created by Administrator
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected DataManager mDataManager;
    protected V mView;

     public BasePresenter(){
         AppDbHelper appDbHelper = new DbHelper();
         AppPreferenceHelper appPreferenceHelper = new PreferenceHelper();
         AppApiHelper appApiHelper = new ApiHelper();
         mDataManager = new DataManager(appDbHelper, appApiHelper, appPreferenceHelper);
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }
}
```
#### IBasePresenter
```java
package com.dream.mvpdemo.base;

/**
 * Created by Administrator 
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);
}
```
#### IBaseView
```java
package com.dream.mvpdemo.base;

/**
 * Created by Administrator 
 */

public interface IBaseView {

}
```


## contract 包：
#### MainContract
```java
package com.dream.mvpdemo.contract;

import com.dream.mvpdemo.base.IBasePresenter;
import com.dream.mvpdemo.base.IBaseView;

/**MainContract
 * Created by Administrator 
 */

public interface MainContract {

    interface View extends IBaseView{
        void testGetMview();
    }

    interface Presenter extends IBasePresenter<View>{
        void testGetMpresenter();

        void testDb();

        void testRequestNetwork();

        void testPreference();

    }
}
```


## model 包：
#### DataManager
```java
package com.dream.mvpdemo.model;

import com.dream.mvpdemo.model.db.AppDbHelper;
import com.dream.mvpdemo.model.http.AppApiHelper;
import com.dream.mvpdemo.model.preference.AppPreferenceHelper;

/**DataManager
 * Created by Administrator 
 */

public class DataManager implements AppDbHelper,AppApiHelper,AppPreferenceHelper{
    private AppDbHelper mAppDbHelper;
    private AppApiHelper mAppApiHelper;
    private AppPreferenceHelper mAppPreferenceHelper;

    public DataManager(AppDbHelper mAppDbHelper, AppApiHelper appApiHelper, AppPreferenceHelper appPreferenceHelper) {
        this.mAppDbHelper = mAppDbHelper;
        this.mAppApiHelper = appApiHelper;
        this.mAppPreferenceHelper = appPreferenceHelper;
    }


    @Override
    public void testDb() {
        mAppDbHelper.testDb();
    }

    @Override
    public void testRequestNetwork() {
        mAppApiHelper.testRequestNetwork();
    }

    @Override
    public void testPreference() {
        mAppPreferenceHelper.testPreference();
    }
}
```
### db 包
#### AppDbHelper
```java
package com.dream.mvpdemo.model.db;

/**
 * Created by Administrator 
 */

public interface AppDbHelper {
    void testDb();
}
```
#### DbHelper
```java
package com.dream.mvpdemo.model.db;

import android.util.Log;

/**
 * Created by Administrator
 */

public class DbHelper implements AppDbHelper{
    @Override
    public void testDb() {
        Log.d("print", "数据库操作");
    }
}
```
### http 包
#### ApiHelper
```java
package com.dream.mvpdemo.model.http;

import android.util.Log;

/**
 * Created by Administrator 
 */

public class ApiHelper implements AppApiHelper{

    @Override
    public void testRequestNetwork() {
        Log.d("print", "网络请求操作");
    }
}
```
#### AppApiHelper
```java
package com.dream.mvpdemo.model.http;

/**
 * Created by Administrator 
 */

public interface AppApiHelper {
    void testRequestNetwork();
}
```
### preference 包
#### AppPreferenceHelper
```java
package com.dream.mvpdemo.model.preference;

/**
 * Created by Administrator
 */

public interface AppPreferenceHelper {
    void testPreference();
}
```
#### PreferenceHelper
```java
package com.dream.mvpdemo.model.preference;

import android.util.Log;

/**
 * Created by Administrator
 */

public class PreferenceHelper implements AppPreferenceHelper{

    @Override
    public void testPreference() {
        Log.d("print", "共享参数存储操作");
    }
}
```
## presenter 包
#### MainPresenter
```java
package com.dream.mvpdemo.presenter;

import android.util.Log;

import com.dream.mvpdemo.base.BasePresenter;
import com.dream.mvpdemo.contract.MainContract;

/**MainPresenter
 * Created by Administrator 
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{


    @Override
    public void testGetMpresenter() {
        Log.d("print", "我是P层的引用");
        mView.testGetMview();
    }

    @Override
    public void testDb() {
        mDataManager.testDb();
    }

    @Override
    public void testRequestNetwork() {
        mDataManager.testRequestNetwork();
    }

    @Override
    public void testPreference() {
        mDataManager.testPreference();
    }
}
```
## ui 包 --> activity 包
#### MainActivity
```java
package com.dream.mvpdemo.ui.activity;

import android.util.Log;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.MainContract;
import com.dream.mvpdemo.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initView() {
        mPresenter.testGetMpresenter();
        mPresenter.testDb();
        mPresenter.testRequestNetwork();
        mPresenter.testPreference();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void testGetMview() {
        Log.d("print", "我是V层的引用");
    }
}
```


# Message

## handler 处理 message 
```java
Message next() {
        final long ptr = mPtr;
        if (ptr == 0) {
            return null;
        }
        int pendingIdleHandlerCount = -1; // -1 only during first iteration
        int nextPollTimeoutMillis = 0;
        for (;;) {
            nativePollOnce(ptr, nextPollTimeoutMillis);
            synchronized (this) {
                // Try to retrieve the next message.  Return if found.
                final long now = SystemClock.uptimeMillis();
                Message prevMsg = null;
                Message msg = mMessages;
                if (msg != null && msg.target == null) {
                    // Stalled by a barrier.  Find the next asynchronous message in the queue.
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null && !msg.isAsynchronous());
                }
                if (msg != null) {
                    if (now < msg.when) {
                        // Next message is not ready.  Set a timeout to wake up when it is ready.
                        nextPollTimeoutMillis = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
                    } else {
                        // Got a message.
                        mBlocked = false;
                        if (prevMsg != null) {
                            prevMsg.next = msg.next;
                        } else {
                            mMessages = msg.next;
                        }
                        msg.next = null;
                        if (DEBUG) Log.v(TAG, "Returning message: " + msg);
                        msg.markInUse();
                        return msg;
                    }
                } else {
                    // No more messages.
                    nextPollTimeoutMillis = -1;
                }
                // Process the quit message now that all pending messages have been handled.
                if (mQuitting) {
                    dispose();
                    return null;
                }
            }
            pendingIdleHandlerCount = 0;
            nextPollTimeoutMillis = 0;
        }
    }
```


# 蓝牙调用

## 蓝牙的简单调用
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BluetoothManager manager;
    private BluetoothAdapter adapter;

    private MyBroadcastReceiver myBroadcastReceiver;
    ArrayList<BluetoothDevice> list;
    ListView listView;
    MyAdapter myAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    public void initView(){
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);

        list=new ArrayList<>();
        myAdapter=new MyAdapter(list,this);
        listView=findViewById(R.id.listview);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Method createBond = BluetoothDevice.class.getMethod("createBond");
                    createBond.invoke(list.get(position));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initData(){
        manager= (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        adapter=manager.getAdapter();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                boolean enable = adapter.enable();
                if(enable==false){
                    Intent intent = new Intent();
                    intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    intent.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,200);
                    startActivityForResult(intent,100);
                }
                break;
            case R.id.btn_search:
                adapter.startDiscovery();
                break;
            case R.id.btn_close:
                adapter.disable();
                list.clear();
                myAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==RESULT_OK){
            Toast.makeText(this, "开启成功", Toast.LENGTH_SHORT).show();
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    list.add(device);
                    myAdapter.notifyDataSetChanged();
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    break;
                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    BluetoothDevice device1=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    int bondState = device1.getBondState();
                    if(bondState==BluetoothDevice.BOND_NONE){
                        Toast.makeText(context, "未找到设备", Toast.LENGTH_SHORT).show();
                    }else if(bondState==BluetoothDevice.BOND_BONDING){
                        Toast.makeText(context, "匹配中", Toast.LENGTH_SHORT).show();
                    }else if(bondState==BluetoothDevice.BOND_BONDED){
                        Toast.makeText(context, "配对成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
```


# 相机调用

## AndroidManifest 清单配置文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.donkingliang.photograph">

    <uses-permission android:name="android.permission.CAMERA" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```
## MainActivity
```java
package com.donkingliang.photograph;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView ivCamera;
    private ImageView ivPhoto;

    // 拍照的requestCode
    private static final int CAMERA_REQUEST_CODE = 0x00000010;
    // 申请相机权限的requestCode
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 0x00000012;
    /**
     * 用于保存拍照图片的uri
      */
    private Uri mCameraUri;

    /**
     * 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
     */
    private String mCameraImagePath;

    /**
     *  是否是Android 10以上手机
      */
    private boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCamera = findViewById(R.id.ivCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionAndCamera();
            }
        });
    }

    /**
     * 检查权限并拍照。
     * 调用相机前先检查权限。
     */
    private void checkPermissionAndCamera() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CAMERA);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            //有权限，调起相机拍照。
            openCamera();
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                    PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Bitmap bitmap = data.getParcelableExtra("data");
                ivPhoto.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 处理权限申请的回调。
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                openCamera();
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this,"拍照权限被拒绝",Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 调起相机拍照
     */
    private void openCamera() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断是否有相机
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            captureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addCategory(Intent.CATEGORY_DEFAULT);
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
        }
    }
}
```
