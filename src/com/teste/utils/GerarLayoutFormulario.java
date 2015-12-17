package com.teste.utils;

import java.util.List;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.teste.entity.Enquete;
import com.teste.entity.Item;
import com.teste.entity.Questao;
import com.teste.entity.resposta.RespostaItem;
import com.teste.entity.resposta.RespostaText;

public class GerarLayoutFormulario {
	
	public static LinearLayout gerar(Context context, Enquete enquete)
	{
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		List<Questao> questoes = enquete.getQuestions();
		
		for (Questao quest : questoes) {
			if (quest.getType().equals("TEXT")) {
				EditText editText = new EditText(context);
				RespostaText resposta = new RespostaText();
				resposta.setQuestionId(quest.getId());
				editText.setTag(resposta);
				TextView label = new TextView(context);
				label.setText(quest.getDescription());
				linearLayout.addView(label);
				linearLayout.addView(editText);
				
			} else if (quest.getType().equals("SINGLE")) {
				TextView label = new TextView(context);
				label.setText(quest.getDescription());
				linearLayout.addView(label);
				RadioGroup group = new RadioGroup(context);
				group.setId(quest.getId());
				for (Item item : quest.getItems()) {
					RadioButton radio = new RadioButton(context);
					RespostaItem resposta = new RespostaItem();
					resposta.setQuestionItem(item.getId());
					resposta.setQuestionId(quest.getId());
					radio.setTag(resposta);
					radio.setText(item.getValue());
					group.addView(radio);
				}
				linearLayout.addView(group);
			} else if (quest.getType().equals("MULTIPLE")) {
				TextView label = new TextView(context);
				label.setText(quest.getDescription());
				linearLayout.addView(label);
				for (Item item : quest.getItems()) {
					CheckBox check = new CheckBox(context);
					check.setId(item.getId());
					RespostaItem resposta = new RespostaItem();
					resposta.setQuestionItem(item.getId());
					resposta.setQuestionId(quest.getId());
					check.setTag(resposta);
					check.setText(item.getValue());
					linearLayout.addView(check);
				}
			}
		}
		return linearLayout;
	}
}
