package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 12:07
 */
@Data
@Entity
@Table (name = "pms_product_full_reduction")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductFullReduction implements Serializable {

	private static final long serialVersionUID =  5433269407303741326L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "full_price" )
	private BigDecimal fullPrice = null;

   	@Column(name = "reduce_price" )
	private BigDecimal reducePrice = null;

	@Override
	public String toString() {
		return "PmsProductFullReduction{" +
				"id=" + id +
				", productId=" + productId +
				", fullPrice=" + fullPrice +
				", reducePrice=" + reducePrice +
				'}';
	}

	/******************************************************************/

   	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private PmsProduct pmsProduct;

}
