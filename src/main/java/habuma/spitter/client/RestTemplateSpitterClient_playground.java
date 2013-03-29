package habuma.spitter.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;

import my.spitterP.mainP.Spitter;
import my.spitterP.mainP.Spittle;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;




public class RestTemplateSpitterClient_playground implements SpitterClient {
  private RestTemplate restTemplate;
  
  public Spittle[] retrieveSpittlesForSpitter(String username) {
    ResponseEntity<Spittle[]> response = 
      new RestTemplate().getForEntity(
        "http://localhost:8080/mainP/spitters/{spitter}/spittles", 
        Spittle[].class, username);
    
    return response.getBody();
  }

  public String postSpitter(Spitter spitter) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public void updateSpittle(Spittle spittle) throws SpitterException {
      restTemplate.put("http://localhost:8080/mainP/spittles/{id}", 
                       spittle,  spittle.getId());
  }
  
  public void deleteSpittle(long id) {
    try {
      restTemplate.delete(new URI("http://localhost:8080/Spitter/spittles/54"));
    } catch (URISyntaxException wontHappen) { }
  }
  
  
    public Spitter getSpitter(String username) {
    RestTemplate rest = new RestTemplate();
    
    MultiValueMap<String, String> headers = 
        new LinkedMultiValueMap<String, String>();
    headers.add("Accept", "application/json");
    
    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
    
    ResponseEntity<Spitter> response = rest.exchange(
            "http://localhost:8080/mainP/spitters/{spitter}", 
            HttpMethod.GET, entity, Spitter.class, username);
    
    return response.getBody();
  }
  
  public static void main(String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    RestTemplateSpitterClient_playground rsc = new RestTemplateSpitterClient_playground();
    System.out.println("retrive spitter from service: " + rsc.getSpitter("habuma"));
    rsc.retrieveSpittlesForSpitter("habuma");
    
    
//    HashMap<String, Object> map = new HashMap<String, Object>();
//    map.put("foo", 1234);
//    map.put("bar", Collections.singletonMap("baz", "booger"));
//    Thing thing = new Thing();
//    thing.setFoo("Whazzup");
//    thing.setBar(1234L);
//    thing.setBaz(true);
//    MultiValueMap<String, Object> mvMap = new LinkedMultiValueMap<String, Object>();
//    mvMap.add("xyz", "123");
//    mvMap.add("abc", "AAA");
//    restTemplate.postForObject("http://{host}/index.html", thing, String.class, "localhost:8080");
  }
}
