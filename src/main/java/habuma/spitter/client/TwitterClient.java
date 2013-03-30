package habuma.spitter.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class TwitterClient {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("twitter-client.xml");
    
    RestTemplate rest = context.getBean(RestTemplate.class);
    
    String result = rest.getForObject("https://api.twitter.com/1/statuses/user_timeline.json?include_entities=true&include_rts=true&trim_user=true&screen_name=tomekl007&page=0&count=100", String.class);
    System.out.println(result);
  }
}
