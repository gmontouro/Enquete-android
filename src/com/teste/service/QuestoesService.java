package com.teste.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.teste.entity.Enquete;
import com.teste.entity.resposta.Resposta;
import com.teste.entity.resposta.RespostaItem;
import com.teste.entity.resposta.RespostaText;
import com.teste.utils.APIConnect;
import com.teste.utils.GerarLayoutFormulario;

public class QuestoesService extends AsyncTask<Void, Void, String> {

	private Enquete enquete;
	ProgressDialog progressDialog;
	private Context context;
	private String link;
	private RelativeLayout layout;
	private LinearLayout linear;
	
    public QuestoesService (Context context, String link, RelativeLayout layout){
         this.context = context;
         this.link = link;
         this.layout = layout;
    }
	protected void onPreExecute()
	{
		  progressDialog = ProgressDialog.show(this.context, "", "Carregando questões da enquete:"+this.link);
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		linear = GerarLayoutFormulario.gerar(this.context, this.enquete);
		
		Button submit = new Button(this.context);
		submit.setText("Enviar respostas");
		linear.addView(submit);
		
		this.layout.addView(linear);
		
		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				List<Resposta> respostas = new ArrayList<Resposta>();
				
				for( int i = 0; i < linear.getChildCount(); i++ ) {
					  if( linear.getChildAt( i ) instanceof EditText ) {
						  EditText editText = (EditText) linear.getChildAt( i );
						  RespostaText resposta = (RespostaText) editText.getTag();
						  resposta.setValue(editText.getText().toString());
						  respostas.add(resposta);
					  }
					  
					  if( linear.getChildAt( i ) instanceof CheckBox ) {
						  CheckBox checkBox = (CheckBox) linear.getChildAt( i );
						  if (checkBox.isChecked()) {
							  respostas.add((RespostaItem) checkBox.getTag());
						  }
					  }
					  
					  if( linear.getChildAt( i ) instanceof RadioGroup ) {
						  RadioGroup radioGroup = (RadioGroup) linear.getChildAt( i );
						  for( int j = 0; j < radioGroup.getChildCount(); j++ ) {
							  if( radioGroup.getChildAt( j ) instanceof RadioButton ) {
								  RadioButton radio = (RadioButton) radioGroup.getChildAt(j);
								  if (radio.isChecked()) {
									  respostas.add((RespostaItem) radio.getTag());
								  }
							  }
						  }
					  }
				}
				RespostasService serviceResposta = new RespostasService(context, respostas,enquete);
				serviceResposta.execute();
			}
			
		});
	}

	@Override
	protected String doInBackground(Void... params) {
		GsonConverterFactory converter = GsonConverterFactory.create();
		
		APIConnect apiConnect = new APIConnect(converter);
		APITesteServiceInterface service = apiConnect.getService();
		
		Call<Enquete> call = service.enqueteCompleta(this.link);
		try {
			Response<Enquete> response = call.execute();
			this.enquete = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
