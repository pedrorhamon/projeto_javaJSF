package com.starking.cerveja.controllers.page;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageWrapper<T> {

	private Page<T> page;
	
	public PageWrapper(Page<T> page) {
		this.page = page;
	}

	public List<T> getConteudo() {
		return page.getContent();
	}
	
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	public int getAtual() {
		return page.getNumber();
	}
	
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
}
