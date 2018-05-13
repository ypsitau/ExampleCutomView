package io.github.ypsitau.examplecutomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		for (int id : new int[]{ R.id.customView1, R.id.customView2, R.id.customView3 }){
			CustomView customView = findViewById(id);
			customView.startPeriodicJob();
		}
		App.setLogEditText((EditText)findViewById(R.id.editText_log), true);
	}
}
