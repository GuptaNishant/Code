package dns_pkg;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "dns")
public class Dns implements Serializable {

   private static final long serialVersionUID = 1L;
   private String url;
   private String ip;
   
   public Dns(){}

   public Dns(String url, String ip){
      this.url = url;
      this.ip = ip;
      }

   public String getUrl() {
      return url;
   }
   @XmlElement
   public void setUrl(String url) {
      this.url = url;
   }
   public String getIp() {
      return ip;
   }
   @XmlElement
      public void setIp(String ip) {
      this.ip = ip;
   }

   @Override
   public boolean equals(Object object){
      if(object == null){
         return false;
      }else if(!(object instanceof Dns)){
         return false;
      }else {
         Dns dns = (Dns)object;
         if(url == dns.getUrl()
            && ip.equals(dns.getIp())
         ){
            return true;
         }			
      }
      return false;
   }	
}