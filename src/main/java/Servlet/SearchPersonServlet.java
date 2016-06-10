package Servlet;

import com.filestore.DataStore.PersonStore;
import com.filestore.Model.PersonModel;
import org.codehaus.jackson.map.ObjectMapper;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by stephen on 6/8/2016.
 */
public class SearchPersonServlet extends HttpServlet {


    private PersonStore personStore;

    public  SearchPersonServlet(PersonStore personStore){

        this.personStore = personStore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("id");
        PersonModel tempPerson = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
           tempPerson = getPerson(Integer.valueOf(param));

        } catch (NoSuchPaddingException  |NoSuchAlgorithmException |IllegalBlockSizeException | BadPaddingException | InvalidKeyException |ClassNotFoundException ex) {
            throw new ServletException();
        }

        resp.getWriter().println(objectMapper.writeValueAsString(tempPerson));
    }

    public PersonModel getPerson(int param) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        return personStore.search(param);
    }
}
