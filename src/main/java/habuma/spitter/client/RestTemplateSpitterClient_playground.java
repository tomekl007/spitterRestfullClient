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
  private RestTemplate restTemplate = new RestTemplate();
  
  public Spittle[] retrieveSpittlesForSpitter(String username) {
    ResponseEntity<Spittle[]> response = 
      new RestTemplate().getForEntity(
        "http://localhost:8080/mainP/spitters/{spitter}/spittles", 
        Spittle[].class, username);
    
    return response.getBody();
  }

  public String postSpitter(Spitter spitter) {
	  RestTemplate rest = new RestTemplate();
    return rest.postForLocation("http://localhost:8080/mainP/Spitter/spitters", 
              spitter, Spitter.class).toString();
  
  }
  
  public void updateSpittle(Spittle spittle) throws SpitterException {
      restTemplate.put("http://localhost:8080/mainP/spittles/{id}", 
                       spittle,  spittle.getId());
      
  }
  
  public void deleteSpittle(long id) {
	  restTemplate.delete("http://localhost:8080/Spitter/spittles/{id}",id);
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
    
    
  public Spitter postSpitterForObject(Spitter spitter) { 
  RestTemplate rest = new RestTemplate();
  return rest.postForObject("http://localhost:8080/mainP/spitters", 
          spitter, Spitter.class);
  
  //or if i want get location
  //RestTemplate rest=new RestTemplate();
  //ResponseEntity<Spitter>response=rest.postForEntity(
  //"http://localhost:8080/mainP/spitters",spitter,Spitter.class);
  //Spitter spitter=response.getBody();
  //URI url=response.getHeaders().getLocation();
}
  
  public static void main(String[] args) {
    RestTemplate restTemplate = new RestTemplate();  
    
    
    RestTemplateSpitterClient_playground rsc = new RestTemplateSpitterClient_playground();
    Spitter habuma = rsc.getSpitter("habuma");
    
    System.out.println("retrive spitter from service: " + habuma);
    
    Spittle [] spittles = rsc.retrieveSpittlesForSpitter("habuma");
    for(Spittle s : spittles){
    	System.out.println("spittle : " +  s);
    	
    }
    
    spittles[0].setText("modyfied spittle text");
  //  try {
    	spittles[0].setSpitter(habuma);
    	System.out.println(spittles[0].getSpitter());
    	
		//rsc.updateSpittle(spittles[0]);
	//} catch (SpitterException e) {
		
	//	e.printStackTrace();
	//}
    
    
    spittles = rsc.retrieveSpittlesForSpitter("habuma");
    for(Spittle s : spittles){
    	System.out.println("spittle : " +  s);
    	
    }
    
    //rsc.deleteSpittle(1);
    
    
    Spitter newSpitter = new Spitter();
    	newSpitter.setEmail("email@as.asc");
        newSpitter.setFullName("fullName");
        newSpitter.setId((long)33);
        newSpitter.setPassword("password");
        newSpitter.setUsername("usetrnace");
        
    System.out.println(rsc.postSpitterForObject(newSpitter));
    
    
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
