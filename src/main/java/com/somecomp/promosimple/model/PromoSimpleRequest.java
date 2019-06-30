package com.somecomp.promosimple.model;

public class PromoSimpleRequest {
	String promoId;
	String promoCompId;
	
	public PromoSimpleRequest() {		
	}
	
	public PromoSimpleRequest(String promoId,String promoCompId) {	
		super();
		this.promoId = promoId;
		this.promoCompId = promoCompId;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public String getPromoCompId() {
		return promoCompId;
	}

	public void setPromoCompId(String promoCompId) {
		this.promoCompId = promoCompId;
	}

	@Override
	public String toString() {
		return "PromoSimpleRequest [promoId=" + promoId + ", promoCompId=" + promoCompId + "]";
	}
}
