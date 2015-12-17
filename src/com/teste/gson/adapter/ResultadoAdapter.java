package com.teste.gson.adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.teste.entity.resultado.Resultado;
import com.teste.entity.resultado.ResultadoItem;
import com.teste.entity.resultado.ResultadoItems;

public class ResultadoAdapter  implements JsonDeserializer<Resultado> {
	@Override
	public Resultado deserialize(JsonElement json, Type arg1,
			JsonDeserializationContext context) throws JsonParseException {
        Resultado resultado = new Resultado();

        try {
            if (json != null) {
            	JSONObject jsonResponse = new JSONObject(json.toString());
            	resultado.setTotal(jsonResponse.getInt("total"));
            	JSONObject questions = jsonResponse.getJSONObject("questions");
            	Iterator<String> keys = questions.keys();
            	List<ResultadoItems> listResultadoItems = new ArrayList<ResultadoItems>();
				while (keys.hasNext()) {
					String rs = keys.next();
					ResultadoItems resultadoItems = new ResultadoItems();
					List<ResultadoItem> listItems = new ArrayList<ResultadoItem>();
				    resultadoItems.setKey(rs);
				    JSONArray itens = questions.getJSONArray(rs);
	            	for (int i=0; i< itens.length() ; i++) {
				    	JSONObject item = itens.getJSONObject(i);
				    	ResultadoItem resultadoItem = new ResultadoItem();
				    	resultadoItem.setName(item.getString("name"));
				    	resultadoItem.setTotal(item.getInt("total"));
				    	listItems.add(resultadoItem);
				    }
				    resultadoItems.setValue(listItems);
				    listResultadoItems.add(resultadoItems);
				}
				resultado.setQuestions(listResultadoItems);
            }
        } catch (Exception e) {
        	resultado = null;
        }
        return resultado;
	}
}
