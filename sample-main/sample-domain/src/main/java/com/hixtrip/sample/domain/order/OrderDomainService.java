package com.hixtrip.sample.domain.order;

import com.hixtrip.sample.domain.commodity.CommodityDomainService;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.PayDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 订单领域服务
 * todo 只需要实现创建订单即可
 */
@Component
public class OrderDomainService {

    @Autowired
    CommodityDomainService commodityDomainService;
    @Autowired
    InventoryDomainService inventoryDomainService ;
    @Autowired
    PayDomainService payDomainService ;

    @Autowired
    OrderDomainService orderDomainService;

    /**
     * todo 需要实现
     * 创建待付款订单
     */
    public void createOrder(Order order) throws Exception {
        //需要你在infra实现, 自行定义出入参

        // 查询SKU价格
        String skuId = order.getSkuId();
        Integer amount = order.getAmount();
        BigDecimal skuPrice = commodityDomainService.getSkuPrice(skuId);

        // 扣减库存
        Integer sellableQuantity = inventoryDomainService.getInventory(skuId);

        if(sellableQuantity<amount){
            throw new Exception("no more items");
        }
        Boolean isChangedInventory = inventoryDomainService.
                changeInventory(skuId, sellableQuantity.longValue(), amount.longValue(), amount.longValue());

        CommandPay commandPay = new CommandPay();
        commandPay.setOrderId(order.getId());
        if(isChangedInventory){
            commandPay.setPayStatus("1");
            orderPaySuccess(commandPay);
        }else{
            commandPay.setPayStatus("0");
            orderPayFail(commandPay);
        }



    }

    /**
     * todo 需要实现
     * 待付款订单支付成功
     */
    public void orderPaySuccess(CommandPay commandPay) {
        //需要你在infra实现, 自行定义出入参
        // 模拟支付
        payDomainService.payRecord(commandPay);
    }

    /**
     * todo 需要实现
     * 待付款订单支付失败
     */
    public void orderPayFail(CommandPay commandPay) {
        //需要你在infra实现, 自行定义出入参
        payDomainService.payRecord(commandPay);
    }
}
