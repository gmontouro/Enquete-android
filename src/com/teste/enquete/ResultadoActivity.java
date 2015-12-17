package com.teste.enquete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.teste.service.ResultadoService;

public class ResultadoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultado);
		
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID_ENQUETE",0);
		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.resultado_questoes);
		ResultadoService service = new ResultadoService(ResultadoActivity.this, id, layout);
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
