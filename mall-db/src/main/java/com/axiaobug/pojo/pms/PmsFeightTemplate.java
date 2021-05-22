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

	private String name = "";

	/**
	 * shipping fee charge by:
	 * 0: weight 1: qty
	 * 计费类型:0->按重量；1->按件数
	 * */
   	@Column(name = "charge_type" )
	private Integer chargeType = 1;

	/**
	 * first weight
	 * 首重kg
	 * */
   	@Column(name = "first_weight" )
	private BigDecimal firstWeight = BigDecimal.valueOf(1);

	/**
	 * first fee
	 * 首费（元）
	 * */
   	@Column(name = "first_fee" )
	private BigDecimal firstFee = BigDecimal.valueOf(75);

   	@Column(name = "continue_weight" )
	private BigDecimal continueWeight = BigDecimal.valueOf(0);

   	@Column(name = "continme_fee" )
	private BigDecimal continueFee = BigDecimal.valueOf(0);

	/**
	 * destination
	 * 目的地（省、市）
	 * */
	private String dest = "";



   	@OneToMany(mappedBy = "pmsFeightTemplate",cascade = CascadeType.PERSIST)
   	private List<PmsProduct> products = new ArrayList<>();



}
