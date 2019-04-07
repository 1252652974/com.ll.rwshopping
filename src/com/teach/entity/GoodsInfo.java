package com.teach.entity;

import com.teach.annotations.Id;

public class GoodsInfo {
	@Id
    private int goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsImgs;
    private String goodsPubdate;
    private int goodsSellerId;
    private String goodsDesc;
    private String goodsClass;
    private int goodsTotal;
	public GoodsInfo() {
		super();
	}
	public GoodsInfo(int goodsId) {
		super();
		this.goodsId = goodsId;
	}
	public GoodsInfo(int goodsId, String goodsName) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs, String goodsPubdate) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
		this.goodsPubdate = goodsPubdate;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs, String goodsPubdate,
			int goodsSellerId) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
		this.goodsPubdate = goodsPubdate;
		this.goodsSellerId = goodsSellerId;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs, String goodsPubdate,
			int goodsSellerId, String goodsDesc) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
		this.goodsPubdate = goodsPubdate;
		this.goodsSellerId = goodsSellerId;
		this.goodsDesc = goodsDesc;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs, String goodsPubdate,
			int goodsSellerId, String goodsDesc, String goodsClass) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
		this.goodsPubdate = goodsPubdate;
		this.goodsSellerId = goodsSellerId;
		this.goodsDesc = goodsDesc;
		this.goodsClass = goodsClass;
	}
	public int getGoodsTotal() {
		return goodsTotal;
	}
	public void setGoodsTotal(int goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	public GoodsInfo(int goodsId, String goodsName, String goodsPrice, String goodsImgs, String goodsPubdate,
			int goodsSellerId, String goodsDesc, String goodsClass, int goodsTotal) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsImgs = goodsImgs;
		this.goodsPubdate = goodsPubdate;
		this.goodsSellerId = goodsSellerId;
		this.goodsDesc = goodsDesc;
		this.goodsClass = goodsClass;
		this.goodsTotal = goodsTotal;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsImgs() {
		return goodsImgs;
	}
	public void setGoodsImgs(String goodsImgs) {
		this.goodsImgs = goodsImgs;
	}
	public String getGoodsPubdate() {
		return goodsPubdate;
	}
	public void setGoodsPubdate(String goodsPubdate) {
		this.goodsPubdate = goodsPubdate;
	}
	public int getGoodsSellerId() {
		return goodsSellerId;
	}
	public void setGoodsSellerId(int goodsSellerId) {
		this.goodsSellerId = goodsSellerId;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getGoodsClass() {
		return goodsClass;
	}
	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}
	@Override
	public String toString() {
		return "GoodsInfo [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsImgs=" + goodsImgs + ", goodsPubdate=" + goodsPubdate + ", goodsSellerId=" + goodsSellerId
				+ ", goodsDesc=" + goodsDesc + ", goodsClass=" + goodsClass + ", goodsTotal=" + goodsTotal + "]";
	}
	
    
    
    
    
}
