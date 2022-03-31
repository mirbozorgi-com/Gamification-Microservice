package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.marketverification.VerifyResponse;
import mirbozorgi.base.feignService.MarketVerificationService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.MarketVerificationFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketVerificationServiceImpl implements MarketVerificationService {

  @Autowired
  private MarketVerificationFeign marketVerificationFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public VerifyResponse verify(
      String market,
      String packageName,
      String sku,
      String token) {
    VerifyResponse verifyResponse = null;
    try {
      Object o = marketVerificationFeign.verifyPath(
          market,
          packageName,
          sku,
          token);
      verifyResponse = serializerFeignService
          .toObjectFromFeign(o, VerifyResponse.class);
    } catch (RuntimeException ex) {
      return new VerifyResponse(
          false,
          token,
          0,
          0,
          ""
      );


    }

    return verifyResponse;
  }

  @Override
  public VerifyResponse hillaVerify(
      String market,
      String packageName,
      String sku,
      String orderId,
      String transactionId,
      String env) {
    VerifyResponse verifyResponse = null;
    try {
      Object o = marketVerificationFeign.traceHilla(
          packageName,
          market,
          env,
          orderId,
          transactionId,
          sku
      );
      verifyResponse = serializerFeignService
          .toObjectFromFeign(o, VerifyResponse.class);
    } catch (RuntimeException ex) {
      return new VerifyResponse(
          false,
          orderId,
          0,
          0,
          ""
      );

    }

    return verifyResponse;
  }
}
