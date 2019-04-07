package com.teach.entity;

import com.teach.annotations.Id;

public class OrderInfo {
	@Id
	int id;
	int orderId;
	int userId;
	String orderAddress;
	String orderTel;
	String orderRemark;
	int goodsNum;
	int ordersStatus;
	int goodsId;
	String orderTime;
	public OrderInfo() {
		super();
	}
	public OrderInfo(int orderId, int userId, String orderAddress, String orderTel,
 int goodsId, String orderTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderAddress = orderAddress;
		this.orderTel = orderTel;
		this.goodsId = goodsId;
		this.orderTime = orderTime;
	}
	public OrderInfo(int Id) {
		super();
		this.id = Id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderTel() {
		return orderTel;
	}
	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public int getOrdersStatus() {
		return ordersStatus;
	}
	public void setOrdersStatus(int ordersStatus) {
		this.ordersStatus = ordersStatus;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", orderAddresss="
				+ orderAddress + ", orderTel=" + orderTel + ", orderRemark=" + orderRemark + ", goodsNum=" + goodsNum
				+ ", ordersStatus=" + ordersStatus + ", goodsId=" + goodsId + ", orderTime=" + orderTime + "]";
	}
	
	
	
}
