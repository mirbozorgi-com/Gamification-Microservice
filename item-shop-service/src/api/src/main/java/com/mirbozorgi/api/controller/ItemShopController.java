package com.mirbozorgi.api.controller;

import com.mirbozorgi.api.model.DeleteItemShopModel;
import com.mirbozorgi.api.model.HillaBuyModel;
import com.mirbozorgi.api.model.PurchaseHillaConsumeModel;
import com.mirbozorgi.api.model.BuyModel;
import com.mirbozorgi.api.model.ItemShopModel;
import com.mirbozorgi.api.model.PurchaseConsumeModel;
import com.mirbozorgi.api.util.helper.ResponseHelper;
import com.mirbozorgi.business.service.ItemShopService;
import com.mirbozorgi.business.service.SerializerService;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.aop.anotions.GameProperties;
import mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuId;
import mirbozorgi.base.context.aop.anotions.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/item-shop")
public class ItemShopController {

  @Autowired
  private ItemShopService itemShopService;

  @Autowired
  private CurrentContextService currentContextService;

  @Autowired
  private SerializerService serializerService;

  @SuperAdmin
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@Validated @RequestBody ItemShopModel model) {
    return ResponseHelper.response(itemShopService.save(
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getType(),
        model.getVipPeriodTime(),
        model.getFileUrl(),
        model.getXp(),
        model.getGold(),
        model.getGem(),
        (short) model.getLevel(),
        model.getAvatarIds()

    ));
  }

  @GameProperties
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(
        itemShopService.findAll(
            currentContextService.getCurrentGamePackageName(),
            currentContextService.getCurrentEnv(),
            currentContextService.getCurrentMarket()
        )
    );
  }

  @SuperAdmin
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseEntity update(@RequestBody ItemShopModel model) {
    return ResponseHelper.response(itemShopService.update(
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        StringUtils.isEmpty(model.getConfig())
            ? null
            : serializerService.toObj(model.getConfig(), Object.class),
        model.getType(),
        model.getVipPeriodTime(),
        model.getGold(),
        model.getGem(),
        model.getLevel(),
        model.getXp(),
        model.getChance(),
        model.getAvatarIds(),
        model.getFileUrl()
    ));
  }

  @SuperAdmin
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@Validated @RequestBody DeleteItemShopModel model) {
    itemShopService.delete(
        model.getName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket()
    );
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuId
  @RequestMapping(value = "/buy", method = RequestMethod.POST)
  public ResponseEntity buy(@RequestBody BuyModel model) {
    return ResponseHelper.response(itemShopService.buy(
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getMarketToken(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()
    ));
  }


  @GamePropertiesWithUuId
  @RequestMapping(value = "/myket-buy", method = RequestMethod.POST)
  public ResponseEntity myketBuy(@RequestBody BuyModel model) {
    return ResponseHelper.response(itemShopService.buyMyket(
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getMarketToken(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime()
    ));
  }


  @GamePropertiesWithUuId
  @RequestMapping(value = "/hilla-buy", method = RequestMethod.POST)
  public ResponseEntity hillaBuy(@RequestBody HillaBuyModel model) {
    return ResponseHelper.response(itemShopService.hillaBuy(
        currentContextService.getCurrentUuId(),
        model.getUsername(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getCurrentLevel(),
        model.getCurrentAvatarIds(),
        model.getEndVipTime(),
        model.getOrderId(),
        model.getTransactionId()
    ));
  }


  @GamePropertiesWithUuId
  @RequestMapping(value = "/myket-purchase-consume", method = RequestMethod.POST)
  public ResponseEntity myketPurchaseConsume(@RequestBody PurchaseConsumeModel model) {
    itemShopService.consumePurchaseMyket(
        currentContextService.getCurrentUuId(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getMarketToken()
    );
    return ResponseHelper.response(true);
  }


  @GamePropertiesWithUuId
  @RequestMapping(value = "/hilla-purchase-consume", method = RequestMethod.POST)
  public ResponseEntity preBuyHilla(@RequestBody PurchaseHillaConsumeModel model) {
    itemShopService.consumeHillaPurchase(
        currentContextService.getCurrentUuId(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getOrderId(),
        model.getTransactionId()
    );
    return ResponseHelper.response(true);
  }

  @GamePropertiesWithUuId
  @RequestMapping(value = "/purchase-consume", method = RequestMethod.POST)
  public ResponseEntity preBuy(@RequestBody PurchaseConsumeModel model) {
    itemShopService.consumePurchase(
        currentContextService.getCurrentUuId(),
        model.getSkuName(),
        currentContextService.getCurrentGamePackageName(),
        currentContextService.getCurrentEnv(),
        currentContextService.getCurrentMarket(),
        model.getMarketToken()
    );
    return ResponseHelper.response(true);
  }


}
