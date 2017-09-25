package Facade;


import Domain.Group;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author mathiasjepsen
 */
public class GroupFacade {
    
    public String getGroups() throws InterruptedException, ExecutionException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        final String[] hostList = {"http://pcatana.eu/ca1/", "http://146.185.167.16/CA1_ORM_REST_JS/", "http://165.227.151.92:8080/CA1/documentation.html",
      "http://46.101.216.31/CA1-Group13/",
      "http://46.101.138.20:8080/CA1_Group4-1.0-SNAPSHOT/",
      "http://207.154.220.147/company/"};
        
        ExecutorService es = Executors.newCachedThreadPool();

        List<Future<Group>> futures = new ArrayList();
        
        List<Group> groups = new ArrayList();

        for (int i = 0; i < hostList.length; i++) {
            final int innerI = i;
            final Future<Group> future = es.submit(() -> {
                Document doc = Jsoup.connect(hostList[innerI]).get();
                Elements authors = doc.select("#authors");
                Elements c = doc.select("#class");
                Elements group = doc.select("#group");
                Group g = new Group(authors.text(), c.text(), group.text());
                return g;
            });
            
            futures.add(future);
        }

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        
        for(Future<Group> g : futures) {
            Group group = g.get();
            groups.add(group);
        }
        
        return gson.toJson(groups);
    }
    
}
