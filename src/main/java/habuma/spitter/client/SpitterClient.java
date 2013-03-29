package habuma.spitter.client;

import my.spitterP.mainP.Spitter;
import my.spitterP.mainP.Spittle;



public interface SpitterClient {
  Spittle[] retrieveSpittlesForSpitter(String username);
  String postSpitter(Spitter spitter);
}
