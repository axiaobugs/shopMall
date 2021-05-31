package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: Order of item to return
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 17:21
 */
@Data
@Entity
@Table (name = "oms_order_return_apply")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderReturnApply implements Serializable {

	private static final long serialVersionUID =  7727367172154993759L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "order_id" )
	private Integer orderId = null;

   	@Column(name = "company_address_id" )
	private Integer companyAddressId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

	/**
	 * order num (not order_id)
	 * 订单编号
	 * */
   	@Column(name = "order_sn" )
	private String orderSerialNum = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "member_username" )
	private String memberUsername = null;

   	@Column(name = "return_amount" )
	private BigDecimal returnAmount = null;

   	@Column(name = "return_name" )
	private String returnName = null;

   	@Column(name = "return_phone" )
	private String returnPhone = null;

	/**
	 * 0: pending to resolve 1: resolving 2: Done 3: reject
	 * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
	 * */
	private Integer status = null;

	/**处理时间*/
   	@Column(name = "handle_time" )
	private Date handleTime  = null;

	/**商品图片*/
   	@Column(name = "product_pic" )
	private String productPic = null;

	/**商品名称*/
   	@Column(name = "product_name" )
	private String productName = null;

	/**商品品牌*/
   	@Column(name = "product_brand" )
	private String productBrand = null;

	/**商品销售属性：颜色：红色；尺码：xl;*/
   	@Column(name = "product_attr" )
	private String productAttr = null;

	/**退货数量*/
   	@Column(name = "product_count" )
	private Integer productCount = null;

	/**
	 * product price in each (not total)
	 * 商品单价
	 * */
   	@Column(name = "product_price" )
	private BigDecimal productPrice = null;

	/**
	 * actual payment from pocket(exclude promote,coupon,credit etc)
	 * 商品实际支付单价
	 * */
   	@Column(name = "product_real_price" )
	private BigDecimal productRealPrice = null;

   	@Column(name = "reason" )
	private String reason = null;

   	@Column(name = "description" )
	private String description = null;

	/**凭证图片，以逗号隔开*/
   	@Column(name = "proof_pics" )
	private String proofPics = null;

	/**处理备注*/
   	@Column(name = "handle_note" )
	private String handleNote = null;

	/**
	 * operator name
	 * 处理人员
	 * */
   	@Column(name = "handle_man" )
	private String handleMan = null;

	/**
	 * consignee name
	 * 收货人*/
   	@Column(name = "receive_man" )
	private String receiveMan = null;

	/**
	 * consignee time
	 * 收货时间
	 */
   	@Column(name = "receive_time" )
	private Date receiveTime = null;

	/**
	* receive note
	* 收货备注*/
   	@Column(name = "receive_note" )
	private String receiveNote = null;

	@Override
	public String toString() {
		return "OmsOrderReturnApply{" +
				"id=" + id +
				", orderId=" + orderId +
				", companyAddressId=" + companyAddressId +
				", productId=" + productId +
				", orderSerialNum='" + orderSerialNum + '\'' +
				", createTime=" + createTime +
				", memberUsername='" + memberUsername + '\'' +
				", returnAmount=" + returnAmount +
				", returnName='" + returnName + '\'' +
				", returnPhone='" + returnPhone + '\'' +
				", status=" + status +
				", handleTime=" + handleTime +
				", productPic='" + productPic + '\'' +
				", productName='" + productName + '\'' +
				", productBrand='" + productBrand + '\'' +
				", productAttr='" + productAttr + '\'' +
				", productCount=" + productCount +
				", productPrice=" + productPrice +
				", productRealPrice=" + productRealPrice +
				", reason='" + reason + '\'' +
				", description='" + description + '\'' +
				", proofPics='" + proofPics + '\'' +
				", handleNote='" + handleNote + '\'' +
				", handleMan='" + handleMan + '\'' +
				", receiveMan='" + receiveMan + '\'' +
				", receiveTime=" + receiveTime +
				", receiveNote='" + receiveNote + '\'' +
				'}';
	}

	/*********************************************************/

//	@ManyToOne
//	@JoinColumn(name = "order_id",insertable = false,updatable = false)
//	private OmsOrder omsOrder;
//
//	@ManyToOne
//	@JoinColumn(name = "company_address_id",insertable = false,updatable = false)
//	private OmsCompanyAddress omsCompanyAddress;
}
