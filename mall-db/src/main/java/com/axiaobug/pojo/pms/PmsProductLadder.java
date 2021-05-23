package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 09:18
 * TODO: 没有和product关联,需要添加关联为 @ManyToOne
 */
@Data
@Entity
@Table (name = "pms_product_ladder")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductLadder implements Serializable {

	private static final long serialVersionUID =  4512176485336738903L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

	/**
	 * limit of products
	 * 满足的商品数量
	 * */
	private Integer count = 1;

	private BigDecimal discount = BigDecimal.valueOf(1);

	/**
	 * price after discount
	 * 折后价格
	 * */

	private BigDecimal price = BigDecimal.valueOf(0);



}

