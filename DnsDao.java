package dns_pkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DnsDao {
   @SuppressWarnings("unchecked")
public List<Dns> getAllDnss(){
      List<Dns> dnsList = null;
      try {
         File file = new File("Dns.dat");
         if (!file.exists()) {
            Dns dns1 = new Dns("abc1.com", "10.197.159.101");
            Dns dns2 = new Dns("abc2.com", "10.197.159.102");
            Dns dns3 = new Dns("abc3.com", "10.197.159.103");
            dnsList = new ArrayList<Dns>();
            dnsList.add(dns1);
            dnsList.add(dns2);
            dnsList.add(dns3);
            saveDnsList(dnsList);		
         }
         else{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            dnsList = (List<Dns>) ois.readObject();
            ois.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }		
      return dnsList;
   }

   public Dns getDns(String url){
      List<Dns> dnss = getAllDnss();

      for(Dns dns: dnss){
         if(dns.getUrl().equalsIgnoreCase(url)){
            return dns;
         }
      }
      return null;
   }

   public int addDns(Dns pDns){
      List<Dns> dnsList = getAllDnss();
      boolean dnsExists = false;
      for(Dns dns: dnsList){
         if(dns.getUrl().equalsIgnoreCase(pDns.getUrl())){
            dnsExists = true;
            break;
         }
      }		
      if(!dnsExists){
         dnsList.add(pDns);
         saveDnsList(dnsList);
         return 1;
      }
      return 0;
   }

   public int updateDns(Dns pDns){
      List<Dns> dnsList = getAllDnss();

      for(Dns dns: dnsList){
         if(dns.getUrl().equalsIgnoreCase(pDns.getUrl())){
            int index = dnsList.indexOf(dns);			
            dnsList.set(index, pDns);
            saveDnsList(dnsList);
            return 1;
         }
      }		
      return 0;
   }

   public int deleteDns(String url){
      List<Dns> dnsList = getAllDnss();

      for(Dns dns: dnsList){
         if(dns.getUrl().equalsIgnoreCase(url)){
            int index = dnsList.indexOf(dns);			
            dnsList.remove(index);
            saveDnsList(dnsList);
            return 1;   
         }
      }		
      return 0;
   }

   private void saveDnsList(List<Dns> dnsList){
      try {
         File file = new File("Dns.dat");
         FileOutputStream fos;

         fos = new FileOutputStream(file);

         ObjectOutputStream oos = new ObjectOutputStream(fos);		
         oos.writeObject(dnsList);
         oos.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}