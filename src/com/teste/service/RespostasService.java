package com.teste.service;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.enquete.ResultadoActivity;
import com.teste.entity.Enquete;
import com.teste.entity.resposta.Resposta;
import com.teste.gson.adapter.RespostaAdapter;
import com.teste.utils.APIConnect;

public class RespostasService extends AsyncTask<Void, Void, String> {

	private List<Resposta> respostas;
	ProgressDialog progressDialog;
	private Context context;
	private Enquete enquete;
	
    public RespostasService (Context context, List<Resposta> respostas, Enquete enquete){
         this.context = context;
         this.respostas = respostas;
         this.enquete = enquete;
    }
	protected void onPreExecute()
	{
		  progressDialog = ProgressDialog.show(this.context, "", "Enviando respostas...");
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		Intent intent = new Intent(this.context, ResultadoActivity.class);
	    intent.putExtra("ID_ENQUETE", this.enquete.getId());
	    this.context.startActivity(intent);
	}

	@Override
	protected String doInBackground(Void... params) {
		Gson gson = new GsonBuilder()
        .registerTypeAdapter(Resposta.class, new RespostaAdapter())
        .create();
		GsonConverterFactory converter = GsonConverterFactory.create(gson);
		
		APIConnect apiConnect = new APIConnect(converter);
		APITesteServiceInterface service = apiConnect.getService();
		Call<List<Resposta>> call = service.responderEnquete(this.respostas);
		try {
			call.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
