package io.github.ypsitau.examplecutomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int id : new int[]{R.id.customView1, R.id.customView2, R.id.customView3}){
			CustomView customView = findViewById(id);
			customView.start();
		}
	}
}
