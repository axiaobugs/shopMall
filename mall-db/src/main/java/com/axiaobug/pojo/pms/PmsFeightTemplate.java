package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/21 20:25
 */
@Data
@Entity
@Table (name = "pms_feight_template")
@NoArgsConstructor
@AllArgsConstructor
public class PmsFeightTemplate implements Serializable {

	private static final long serialVersionUID =  6596081111922189373L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;

	/**
	 * shipping fee charge by:
	 * 0: weight 1: qty
	 * 计费类型:0->按重量；1->按件数
	 * */
   	@Column(name = "charge_type" )
	private Integer chargeType = null;

	/**
	 * first weight
	 * 首重kg
	 * */
   	@Column(name = "first_weight" )
	private BigDecimal firstWeight = null;

	/**
	 * first fee
	 * 首费（元）
	 * */
   	@Column(name = "first_fee" )
	private BigDecimal firstFee = null;

   	@Column(name = "continue_weight" )
	private BigDecimal continueWeight = null;

   	@Column(name = "continme_fee" )
	private BigDecimal continueFee = null;

	/**
	 * destination
	 * 目的地（省、市）
	 * */
	private String dest = null;



   	@OneToMany(mappedBy = "pmsFeightTemplate",cascade = CascadeType.REFRESH)
   	private List<PmsProduct> products = new ArrayList<>();



}
