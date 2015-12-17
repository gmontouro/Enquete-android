package com.teste.utils;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.teste.entity.resultado.Resultado;
import com.teste.entity.resultado.ResultadoItem;
import com.teste.entity.resultado.ResultadoItems;

public class GerarLayoutResultado {
	
	public static LinearLayout gerar(Context context,Resultado resultado)
	{
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		TextView txt = new TextView(context);
		txt.setText("Total de respostas: " + resultado.getTotal());
		txt.setTypeface(null, Typeface.BOLD);
		linearLayout.addView(txt);
		
		for (ResultadoItems resultadoItems : resultado.getQuestions()) {
			BarChart graph = new BarChart(context);
			
			ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
			ArrayList<String> labels = new ArrayList<String>();
			int i=0;
			int maxString = 0;
			for (ResultadoItem resultadoItem : resultadoItems.getValue()) {
				entries.add(new BarEntry(resultadoItem.getTotal(), i));
				labels.add(resultadoItem.getName());
				if (resultadoItem.getName().length() > maxString) {
					maxString = resultadoItem.getName().length();
				}
				i++;
			}
			BarDataSet dataSet = new BarDataSet(entries, resultadoItems.getKey());
			BarData barData = new BarData(labels,dataSet);
			graph.getXAxis().setLabelsToSkip(0);
			graph.getXAxis().setLabelRotationAngle(90);
			graph.getXAxis().setTextSize(10);
			graph.setData(barData);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				     LinearLayout.LayoutParams.MATCH_PARENT, 500 + (maxString * 15));
			params.topMargin = 50;
			graph.setLayoutParams(params);
			linearLayout.addView(graph);
			
		}
		
		return linearLayout;
	}
}
