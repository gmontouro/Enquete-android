package com.teste.gson.adapter;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teste.entity.resposta.Resposta;
import com.teste.entity.resposta.RespostaItem;
import com.teste.entity.resposta.RespostaText;

public class RespostaAdapter  implements JsonSerializer<Resposta> {
	@Override
	public JsonElement serialize(Resposta resposta, Type arg1,
			JsonSerializationContext context) {
			JsonObject item = new JsonObject();
			JsonObject question = new JsonObject();
			
			if (resposta instanceof RespostaText) {
				item.addProperty("value", ((RespostaText) resposta).getValue());
			} else if (resposta instanceof RespostaItem) {
				JsonObject questionItem = new JsonObject();
				questionItem.addProperty("id", ((RespostaItem) resposta).getQuestionItem());
				item.add("questionItem", questionItem);
			}
			
			question.addProperty("id", resposta.getQuestionId());
			item.add("question", question);
			
			
		return item;
	}
}
