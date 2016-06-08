package Main;

import Servlet.AddPersonServlet;
import Servlet.ListAllServlet;
import Servlet.SearchPersonServlet;
import com.filestore.DataStore.PersonDataStoreFile;
import com.filestore.DataStore.PersonStore;
import com.filestore.DataStore.RSAKey;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by stephen on 6/8/2016.
 */
public class App {

    public static void main (String[] args) throws Exception {
        Server server = new Server(7070);

        PersonDataStoreFile psdf = new PersonDataStoreFile(new RSAKey());
        PersonStore ps = new PersonStore(psdf);

        ServletContextHandler  sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
        sch.setContextPath("/");
        server.setHandler(sch);

        sch.addServlet(new ServletHolder(new ListAllServlet(ps)),"/listrecord/*");
        sch.addServlet(new ServletHolder(new SearchPersonServlet(ps)),"/search/*");
        sch.addServlet(new ServletHolder(new AddPersonServlet(ps)), "/addrecord/*");

        server.start();


    }
}
