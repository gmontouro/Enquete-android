package com.teste.service;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.enquete.MainActivity;
import com.teste.entity.resultado.Resultado;
import com.teste.gson.adapter.ResultadoAdapter;
import com.teste.utils.APIConnect;
import com.teste.utils.GerarLayoutResultado;

public class ResultadoService extends AsyncTask<Void, Void, String> {

	private Resultado resultado;
	private int id;
	ProgressDialog progressDialog;
	private Context context;
	RelativeLayout layout;
	
    public ResultadoService (Context context, int id, RelativeLayout layout){
         this.context = context;
         this.id = id;
         this.layout = layout;
    }
	protected void onPreExecute()
	{
		  progressDialog = ProgressDialog.show(this.context, "", "Recuperando resultado");
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		LinearLayout linear = GerarLayoutResultado.gerar(this.context, this.resultado);
		Button submit = new Button(this.context);
		submit.setText("Voltar para enquetes");
		linear.addView(submit);
		this.layout.addView(linear);
		
		
		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
			    context.startActivity(intent);
			}
		});
	}

	@Override
	protected String doInBackground(Void... params) {
		Gson gson = new GsonBuilder()
        .registerTypeAdapter(Resultado.class, new ResultadoAdapter())
        .create();
		GsonConverterFactory converter = GsonConverterFactory.create(gson);
		
		APIConnect apiConnect = new APIConnect(converter);
		APITesteServiceInterface service = apiConnect.getService();
		
		Call<Resultado> call = service.resultadoEnquete(this.id);
		try {
			Response<Resultado> response = call.execute();
			this.resultado = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
