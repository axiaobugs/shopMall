package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 17:06
 */
@Data
@Entity
@Table (name = "oms_order_operate_history")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderOperateHistory implements Serializable {

	private static final long serialVersionUID =  5233647464694414326L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
     * fk
     * 订单id
     * */
   	@Column(name = "order_id" )
	private Integer orderId = null;

	/**
     * operator -> user,system,admin
     * 操作人：用户；系统；后台管理员
     * */
   	@Column(name = "operate_man" )
	private String operateMan = null;

	/**操作时间*/
   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	/**
     * 0: pending payment
     * 1: pending dispatch
     * 2: dispatched
     * 3: done
     * 4: order close
     * 5: invalid order
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     * */
   	@Column(name = "order_status" )
	private Integer orderStatus = null;

   	@Column(name = "note" )
	private String note = null;

    @Override
    public String toString() {
        return "OmsOrderOperateHistory{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", operateMan='" + operateMan + '\'' +
                ", createTime=" + createTime +
                ", orderStatus=" + orderStatus +
                ", note='" + note + '\'' +
                '}';
    }

    /**************************************************************/
//
//    @ManyToOne
//    @JoinColumn(name = "order_id",insertable = false,updatable = false)
//    private OmsOrder omsOrder;


}
