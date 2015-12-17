package com.teste.entity.resultado;

import java.util.List;

public class Resultado {
	private int total;
	private List<ResultadoItems> questions;

	public List<ResultadoItems> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ResultadoItems> questions) {
		this.questions = questions;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
