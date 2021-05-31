package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 17:42
 */
@Data
@Entity
@Table (name = "oms_company_address")
@NoArgsConstructor
@AllArgsConstructor
public class OmsCompanyAddress implements Serializable {

	private static final long serialVersionUID =  1565784036608788765L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	/**
	 * name of the this address
	 * 地址名称
	 * */
   	@Column(name = "address_name" )
	private String addressName;

	/**
	 * Default dispatch address
	 * 0: no
	 * 1: yes
	 * 默认发货地址：0->否；1->是
	 * */
   	@Column(name = "send_status" )
	private Integer sendStatus;

	/**
	 * Default receive address
	 * 0: no
	 * 1: yes
	 * 是否默认收货地址：0->否；1->是
	 * */
   	@Column(name = "receive_status" )
	private Integer receiveStatus;

	/**
	 * consignee name
	 * 收发货人姓名
	 * */
   	@Column(name = "name" )
	private String name;

	/**
	 * consignee phone
	 * 收货人电话
	 * */
   	@Column(name = "phone" )
	private String phone;

	/**
	 * state
	 * 省/直辖市
	 * */
   	@Column(name = "province" )
	private String province;

	/**
	 * city
	 * 市
	 * */
   	@Column(name = "city" )
	private String city;

	/**
	 * suburb
	 * 区
	 * */
   	@Column(name = "region" )
	private String suburb;

	/**
	 * address
	 * 详细地址
	 * */
   	@Column(name = "detail_address" )
	private String detailAddress;

	@Override
	public String toString() {
		return "OmsCompanyAddress{" +
				"id=" + id +
				", addressName='" + addressName + '\'' +
				", sendStatus=" + sendStatus +
				", receiveStatus=" + receiveStatus +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", suburb='" + suburb + '\'' +
				", detailAddress='" + detailAddress + '\'' +
				'}';
	}

//	@OneToMany(mappedBy = "omsCompanyAddress")
//	private List<OmsOrderReturnApply> orderReturnApplies = new ArrayList<>();

}
