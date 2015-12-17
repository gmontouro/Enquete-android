package com.teste.enquete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.teste.service.QuestoesService;

public class QuestoesActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questoes);
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.enquete_questoes);
		
		Intent intent = getIntent();
		String link = intent.getStringExtra("ID_ENQUETE");
		QuestoesService service = new QuestoesService(QuestoesActivity.this, link, layout);
		service.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
