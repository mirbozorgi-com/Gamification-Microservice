package com.mirbozorgi.business.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.mirbozorgi.business.service.FCMGameService;
import com.mirbozorgi.core.entity.FCMGame;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FCMInitializer {

  @Autowired
  private FCMGameService fcmGameService;


  @PostConstruct
  public void initialize() {

    for (FCMGame fcmGame : fcmGameService.findAll()) {
      String key = String.format("fcm_%s_%s_%s",
          fcmGame.getGamePackageName(),
          fcmGame.getEnv(),
          fcmGame.getMarketName());

      try {

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(
                GoogleCredentials.fromStream(new ClassPathResource(fcmGame.getPathOfJson())
                    .getInputStream())).build();

        FirebaseApp.initializeApp(options, key);

        if (FirebaseApp.getApps().isEmpty()) {
          FirebaseApp.initializeApp(options);
        }
      } catch (IOException ignored) {
      }


    }

  }
}