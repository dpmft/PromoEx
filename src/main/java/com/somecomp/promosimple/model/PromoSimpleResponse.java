package com.somecomp.promosimple.model;

public class PromoSimpleResponse {
	private String promoComponentId;
	private String promoHdrId;
	
	public PromoSimpleResponse() {		
	}
	
	public PromoSimpleResponse(String promoHdrId,String promoComponentId) {	
		super();
		this.promoHdrId = promoHdrId;
		this.promoComponentId = promoComponentId;
	}
	
	public String getPromoComponentId() {
		return promoComponentId;
	}

	public void setPromoComponentId(String promoComponentId) {
		this.promoComponentId = promoComponentId;
	}

	public String getPromoHdrId() {
		return promoHdrId;
	}

	public void setPromoHdrId(String promoHdrId) {
		this.promoHdrId = promoHdrId;
	}

	@Override
	public String toString() {
		return "PromoSimpleResponse [promoComponentId=" + promoComponentId + ", promoHdrId=" + promoHdrId + "]";
	}
}
