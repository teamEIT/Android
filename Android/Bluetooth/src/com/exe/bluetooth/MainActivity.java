package com.exe.bluetooth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.exe.bluetooth.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Button On, Off, Visible, List;
	private BluetoothAdapter bluetoothAdapter;
	private Set<BluetoothDevice> pairedDevices;
	private ListView listView;
	private BluetoothSocket btSocket = null;
	private OutputStream outStream = null;
	
	private static final String TAG = "LEDOnOff";
	// Insert your server's MAC address
	private static String address = "00:00:00:00:00:00";
	// Well known SPP UUID
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		On = (Button)findViewById(R.id.button_on);
		Off = (Button)findViewById(R.id.button_off);
		Visible = (Button)findViewById(R.id.button_get_visible);
		List = (Button)findViewById(R.id.button_list_device);
		
		listView = (ListView)findViewById(R.id.listView);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();	
		//Su kien khi nhan nut on
		On.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				on(v);
			}
		});
		//Su kien khi nhan nut get visible
		Visible.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				visible(v);
			}
		});
	
	}
	public void on(View view) {
		if(!bluetoothAdapter.isEnabled()) {
			Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) ;
			startActivityForResult(turnOn, 0);
			Toast.makeText(getApplicationContext(), "Turned On", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(getApplicationContext(), "Already On", Toast.LENGTH_LONG).show();
		}
	}
	
	public void list(View view) {
		pairedDevices = bluetoothAdapter.getBondedDevices();
		ArrayList<String> list = new ArrayList<String>();
		for(BluetoothDevice bluetooth : pairedDevices)
			list.add(bluetooth.getName());
		Toast.makeText(getApplicationContext(), "Showing paired devices", Toast.LENGTH_LONG).show();
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
	}
	
	public void off(View view) {
		bluetoothAdapter.disable();
		Toast.makeText(getApplicationContext(), "Turned Off", Toast.LENGTH_LONG).show();
	}
	
	public void visible(View view) {
		Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		startActivityForResult(getVisible, 0);
	}
	
}
