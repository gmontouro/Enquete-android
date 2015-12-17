package com.teste.enquete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class EnqueteActivity extends Activity {
	
	private String link;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enquete);
		LinearLayout layout = (LinearLayout)findViewById(R.id.opcoes);
		
		Intent intent = getIntent();
		this.link = intent.getStringExtra("LINK_ENQUETE");
		this.id = intent.getIntExtra("ID_ENQUETE",0);

		Button responder = new Button(this);
		Button resultado = new Button(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		responder.setLayoutParams(params);
		resultado.setLayoutParams(params);
		responder.setText("Responder enquete");
		resultado.setText("Ver resultado");
		layout.addView(responder);
		layout.addView(resultado);
		
		responder.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), QuestoesActivity.class);
			    intent.putExtra("ID_ENQUETE", link);
			    v.getContext().startActivity(intent);
			}
		});
		resultado.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ResultadoActivity.class);
			    intent.putExtra("ID_ENQUETE", id);
			    v.getContext().startActivity(intent);
			}
		});
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
