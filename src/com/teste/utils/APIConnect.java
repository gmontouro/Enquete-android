package com.teste.utils;

import com.squareup.okhttp.OkHttpClient;
import com.teste.service.APITesteServiceInterface;
import com.teste.service.LoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class APIConnect {
	private String host = "http://167.114.116.94:7019/";
	private GsonConverterFactory converter;
	
	public APIConnect (GsonConverterFactory converter) 
	{
		this.converter = converter;
	}
	
	public APITesteServiceInterface getService()
	{
		OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());
        Retrofit retrofit = new Retrofit
		.Builder()
	    .baseUrl(this.host)
	    .addConverterFactory(this.converter)
	    .client(client)
	    .build();
        APITesteServiceInterface service = retrofit.create(APITesteServiceInterface.class);
		
		return service;
	}
}
