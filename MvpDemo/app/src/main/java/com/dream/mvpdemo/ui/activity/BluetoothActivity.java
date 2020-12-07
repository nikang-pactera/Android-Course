package com.dream.mvpdemo.ui.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dream.mvpdemo.R;
import com.dream.mvpdemo.base.BaseActivity;
import com.dream.mvpdemo.contract.BluetoothContract;
import com.dream.mvpdemo.presenter.BluetoothPresenter;
import com.dream.mvpdemo.ui.adapter.BluetoothAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class BluetoothActivity extends BaseActivity<BluetoothPresenter> implements BluetoothContract.View, View.OnClickListener {

    private BluetoothAdapter adapter;

    private ArrayList<BluetoothDevice> list;
    private DelegationAdapter delegationAdapter;

    @Override
    protected void initPresenter() {
        mPresenter = new BluetoothPresenter();
    }

    public void initView() {
        mPresenter.getMpresenter();
        mPresenter.db();
        mPresenter.requestNetwork();
        mPresenter.preference();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bluetooth;
    }

    public void onItemClick(int position) {
        try {
            Method createBond = BluetoothDevice.class.getMethod("createBond");
            createBond.invoke(list.get(position));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        BluetoothManager manager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                boolean enable = adapter.enable();
                if (!enable) {
                    Intent intent = new Intent();
                    intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    intent.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200);
                    startActivityForResult(intent, 100);
                }
                break;
            case R.id.btn_search:
                adapter.startDiscovery();
                break;
            case R.id.btn_close:
                adapter.disable();
                list.clear();
                delegationAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Toast.makeText(this, "开启成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getView() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);

        list = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        delegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new BluetoothAdapterDelegate(this));

        //设置Adapter
        recyclerView.setAdapter(delegationAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        delegationAdapter.setDataItems(list);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            assert action != null;
            switch (action) {
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    list.add(device);
                    delegationAdapter.notifyDataSetChanged();
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    break;
                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    BluetoothDevice device1 = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    assert device1 != null;
                    int bondState = device1.getBondState();
                    if (bondState == BluetoothDevice.BOND_NONE) {
                        Toast.makeText(context, "未找到设备", Toast.LENGTH_SHORT).show();
                    } else if (bondState == BluetoothDevice.BOND_BONDING) {
                        Toast.makeText(context, "匹配中", Toast.LENGTH_SHORT).show();
                    } else if (bondState == BluetoothDevice.BOND_BONDED) {
                        Toast.makeText(context, "配对成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}