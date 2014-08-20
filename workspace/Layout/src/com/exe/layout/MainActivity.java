package com.exe.layout;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Tạo mảng để chứa String nội dung công việc và giờ
		final ArrayList<String> arrayWork = new ArrayList<String>();
		
		//Adapter dùng để kết nối mảng với List View
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayWork);
		//Các EditText để vào nội dung công việc được lấy về từ XML
		final EditText workEnter = (EditText)findViewById(R.id.workEnter);
		final EditText hourEdit = (EditText)findViewById(R.id.hourEdit);
		final EditText minuteEdit = (EditText)findViewById(R.id.minuteEdit); 
		//Button khi nhấn sẽ thêm công việc vào ListView
		final Button button = (Button)findViewById(R.id.button);
		//ListView chứa sanh sách các công việc
		final ListView list = (ListView)findViewById(R.id.list);
		//Set adapter cho list để biết sẽ lấy nội dung từ mảng arrayWork
		list.setAdapter(arrayAdapter);
		//Định nghĩa listener xử lý sự kiện nhấn vào button
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Nếu 1 trong 3 Edit Text không có nội dung thì hiện lên thông báo
				if((workEnter.getText().toString().equals("")) || (hourEdit.getText().toString().equals("")) || (minuteEdit.getText().toString().equals("")))
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("Info missing");
					builder.setMessage("Please Enter all infomation of the work");
					builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					builder.show();	
				}
				else
				{
					String str = workEnter.getText().toString() + " - " + hourEdit.getText().toString() + " : " + minuteEdit.getText().toString();
					arrayWork.add(0, str);
					arrayAdapter.notifyDataSetChanged();
					workEnter.setText("");
					hourEdit.setText("");
					minuteEdit.setText("");
				}
			}
		});
/*		OnClickListener add = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Nếu 1 trong 3 Edit Text không có nội dung thì hiện lên thông báo
				if((workEnter.getText().toString().equals("")) || (hourEdit.getText().toString().equals("")) || (minuteEdit.getText().toString().equals("")))
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("Info missing");
					builder.setMessage("Please Enter all infomation of the work");
					builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					builder.show();	
				}
				else
				{
					String str = workEnter.getText().toString() + " - " + hourEdit.getText().toString() + " : " + minuteEdit.getText().toString();
					arrayWork.add(0, str);
					arrayAdapter.notifyDataSetChanged();
					workEnter.setText("");
					hourEdit.setText("");
					minuteEdit.setText("");
				}
		}
		};
		button.setOnClickListener(add);*/
		}
	}
