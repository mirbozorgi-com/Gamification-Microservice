package com.mirbozorgi.api.controller.base;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@ApiIgnore
public class BaseController {

  @Autowired
  private HttpServletRequest request;

  /**
   * getByPackageName player id from session.
   *
   * @return playerid
   */
  public long getPlayerId() {
    try {
      return (long) request.getSession().getAttribute("player-id");
    } catch (Exception ex) {
      return -1;
    }
  }

  public int getClientVersion() {
    try {
      return (int) request.getSession().getAttribute("client-version");
    } catch (Exception ex) {
      return -1;
    }
  }

  public String getMarket() {
    try {
      return (String) request.getSession().getAttribute("market-id");
    } catch (Exception ex) {
      return null;
    }
  }

}
