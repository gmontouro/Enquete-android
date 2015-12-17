package com.teste.service;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

import com.teste.entity.Enquete;
import com.teste.entity.resposta.Resposta;
import com.teste.entity.resultado.Resultado;

public interface APITesteServiceInterface {
  @GET("/connecta-speaknow/poll")
  Call<List<Enquete>> listarEnquetes();
  @GET("/connecta-speaknow/poll/byLink")
  Call<Enquete> enqueteCompleta(@Query("link") String link);
  @GET("/connecta-speaknow/poll/{id}/answers")
  Call<Resultado> resultadoEnquete(@Path("id") int id);
  @Headers("Content-Type: application/json" )
  @POST("/connecta-speaknow/answers")
  Call<List<Resposta>> responderEnquete(@Body List<Resposta> respostas);
}
