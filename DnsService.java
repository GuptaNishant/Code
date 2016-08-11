package dns_pkg;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/DnsService")
public class DnsService {
	
   DnsDao dnsDao = new DnsDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";


   @GET
   @Path("/dnss")
   @Produces(MediaType.APPLICATION_XML)
   public List<Dns> getDnss(){
      return dnsDao.getAllDnss();
   }

   @GET
   @Path("/dnss/{dnsurl}")
   @Produces(MediaType.APPLICATION_XML)
   public Dns getDns(@PathParam("dsnurl") String dnsurl){
      return dnsDao.getDns(dnsurl);
   }

   @PUT
   @Path("/dnss")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createDns(@FormParam("url") String url,
      @FormParam("ip") String ip,
      @Context HttpServletResponse servletResponse) throws IOException{
      Dns dns = new Dns(url, ip);
      int result = dnsDao.addDns(dns);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @POST
   @Path("/dnss")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateDns(@FormParam("url") String url,
      @FormParam("ip") String ip,
      @Context HttpServletResponse servletResponse) throws IOException{
      Dns dns = new Dns(url, ip);
      int result = dnsDao.updateDns(dns);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/dnss/{dnsurl}")
   @Produces(MediaType.APPLICATION_XML)
   public String deleteDns(@PathParam("dnsurl") String dnsurl){
      int result = dnsDao.deleteDns(dnsurl);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/dnss")
   @Produces(MediaType.APPLICATION_XML)
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }
}