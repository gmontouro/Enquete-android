package com.teste.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.teste.enquete.EnqueteActivity;
import com.teste.entity.Enquete;
import com.teste.utils.APIConnect;

public class EnqueteService extends AsyncTask<Void, Void, String> {

	private List<Enquete> enquetes;
	ProgressDialog progressDialog;
	private Context context;
	private ListView lista;
	
    public EnqueteService (Context context, ListView list){
         this.context = context;
         this.lista = list;
    }
	protected void onPreExecute()
	{
		  progressDialog = ProgressDialog.show(this.context, "", "Carregando enquetes disponíveis");
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		String[] values = new String[this.enquetes.size()];
		String[] links = new String[this.enquetes.size()];
		int[] ids = new int[this.enquetes.size()];
		int i = 0;
		for (Enquete enq : this.enquetes) {
			values[i] = enq.getName();
			links[i] = enq.getLink();
			ids[i] = enq.getId();
			i++;
		}
		
		List<com.teste.data.ListView> output = new ArrayList<com.teste.data.ListView>();
		for (i = 0; i < values.length; i++) {
			com.teste.data.ListView d = new com.teste.data.ListView();
		     d.name = values[i];
		     d.link = links[i];
		     d.id = ids[i];
		     output.add(d);
		}
		
		ArrayAdapter<com.teste.data.ListView> adapter = new ArrayAdapter<com.teste.data.ListView>(this.context,
	              android.R.layout.simple_list_item_1, output);
		
		adapter.notifyDataSetChanged();
		this.lista.setAdapter(adapter);
		
	    this.lista.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						com.teste.data.ListView  item = (com.teste.data.ListView) parent.getItemAtPosition(position);
					    Intent intent = new Intent(view.getContext(), EnqueteActivity.class);
					    intent.putExtra("ID_ENQUETE", item.id);
					    intent.putExtra("LINK_ENQUETE", item.link);
					    view.getContext().startActivity(intent);
					}
		         }); 
	}

	@Override
	protected String doInBackground(Void... params) {
		GsonConverterFactory converter = GsonConverterFactory.create();
		
		APIConnect apiConnect = new APIConnect(converter);
		APITesteServiceInterface service = apiConnect.getService();
		
		Call<List<Enquete>> call = service.listarEnquetes();
		try {
			Response<List<Enquete>> response = call.execute();
			this.enquetes = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
