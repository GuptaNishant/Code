package dns_pkg;


import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WebServiceTester  {

   private Client client;
   private String REST_SERVICE_URL = "http://localhost:8080/DnsManagement/rest/DnsService/dnss";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
      WebServiceTester tester = new WebServiceTester();

      tester.init();
      tester.testGetAllDnss();
      tester.testGetDns();
      tester.testUpdateDns();
      tester.testAddDns();
      tester.testDeleteDns();
   }
   //Test: Get list of all dnss
   //Test: Check if list is not empty
   private void testGetAllDnss(){
      GenericType<List<Dns>> list = new GenericType<List<Dns>>() {};
      List<Dns> dnss = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;
      if(dnss.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllDnss, Result: " + result );
   }
   //Test: Get IP of url 
   //Test: Check if ip is same as sample ip
   private void testGetDns(){
      Dns sampleDns = new Dns();
      sampleDns.setUrl("abc1.com");

      Dns dns = client
         .target(REST_SERVICE_URL)
         .path("/{dnsurl}")
         .resolveTemplate("dnsurl", "abc1.com")
         .request(MediaType.APPLICATION_XML)
         .get(Dns.class);
      String result = FAIL;
      if(sampleDns != null && sampleDns.getUrl() == dns.getUrl()){
         result = PASS;
      }
      System.out.println("Test case name: testGetDns, Result: " + result );
   }
   //Test: Update DNS of url abc1.com
   //Test: Check if result is success XML.
   private void testUpdateDns(){
      Form form = new Form();
      form.param("url", "abc2.com");
      form.param("ip", "10.197.159.101");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testUpdateDns, Result: " + result );
   }
   //Test: Add DNS of url abc3.com
   //Test: Check if result is success XML.
   private void testAddDns(){
      Form form = new Form();
      form.param("url", "xyz.com");
      form.param("ip", "10.197.159.113");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .put(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddDns, Result: " + result );
   }
   //Test: Delete Dns of url abc3.com
   //Test: Check if result is success XML.
   private void testDeleteDns(){
      String callResult = client
         .target(REST_SERVICE_URL)
         .path("/{dnsurl}")
         .resolveTemplate("dnsurl", "abc3.com")
         .request(MediaType.APPLICATION_XML)
         .delete(String.class);

      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testDeleteDns, Result: " + result );
   }
}
