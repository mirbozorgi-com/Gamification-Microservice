package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.marketverification.VerifyResponse;

public interface MarketVerificationService {

  VerifyResponse verify(
      String market,
      String packageName,
      String sku,
      String token
  );


  VerifyResponse hillaVerify(
      String market,
      String packageName,
      String sku,
      String orderId,
      String transactionId,
      String env);
}
