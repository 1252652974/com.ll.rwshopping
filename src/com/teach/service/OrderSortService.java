package com.teach.service;

import java.util.List;

import com.teach.entity.OrderInfo;

public class OrderSortService {
	public List<OrderInfo> orderSort(List<OrderInfo> list) {
		for(int i=0;i<list.size()-1;i++) {
			for(int j=0;j<list.size()-i-1;j++) {
				if(list.get(j).getId()<list.get(j+1).getId()) {
					int replaceNum1 = j;
					int replaceNum2 = j+1;

					list.add(replaceNum1, list.get(replaceNum2));
					list.add(replaceNum2+1, list.get(replaceNum1+1));

					list.remove(replaceNum1+1);
					list.remove(replaceNum2+1);
				}
			}
		}
		return list;
	}
}
