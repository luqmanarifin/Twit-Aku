/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twit;

import twitter4j.*;
import twitter4j.conf.*;
import java.util.List;

/**
 *
 * @author Devina
 */
public class TwitterConnector {
  private Query query;
  private Twitter twitter;
  List<Status> tweets;
  
  /**
   * Default konstruktor
   */
  public TwitterConnector() {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("lrKsDayVG1ZiwzF7DB4W61aAe")
      .setOAuthConsumerSecret("VUpnh7yO78xxybxZy5aAciqLXG6AOIYlEhtC3RSd6Tm0GXN2l4")
      .setOAuthAccessToken("356761119-u2CK51Z3T9EZxKe3NyYNPIfJwGU6Z07Dwr6E5YQS")
      .setOAuthAccessTokenSecret("oVRZMwFOIYV7Dzz0SFEgInVKQJr7jEi5DSnv0GDFVJI9t");
    TwitterFactory tf = new TwitterFactory(cb.build());
    twitter = tf.getInstance();
  }
  
  public String getTweetText(int i) {
    return tweets.get(i).getText();
  }
  
  public String getTweetUrl(int i) {
    String url = "https://twitter.com/" + tweets.get(i).getUser().getScreenName() + "/status/" + tweets.get(i).getId();
    return url;
  }
  
  /**
   * Mencari tweet yang mengandung keyword tertentu
   * @param n merupakan jumlah tweet terbaru
   * @param keyword merupakan kata kunci yang ingin dicari
   * @return List yang mengandung keyword
   */
  public List<Status> searchKeyword(int n, String keyword) {
    try {
      query = new Query(keyword);
      query.setCount(n);
      QueryResult result;
      result = twitter.search(query);
      tweets = result.getTweets();
      
      
    } catch (TwitterException te) {
        te.printStackTrace();
        System.out.println("Failed to search tweets: " + te.getMessage());
    }
    return tweets;
  }
}