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
import java.util.List;


/**
 * Created by stephen on 6/8/2016.
 */
public class ListAllServlet extends HttpServlet {

    private PersonStore personStore;

    public ListAllServlet(PersonStore personStore){
        this.personStore = personStore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<PersonModel> personModelList = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            personModelList = personStore.getAllRecords();

        } catch (NoSuchPaddingException  |NoSuchAlgorithmException |IllegalBlockSizeException | BadPaddingException | InvalidKeyException |ClassNotFoundException ex) {

            throw new ServletException();
        }

        resp.setStatus(HttpServletResponse.SC_OK);


            resp.getWriter().println(objectMapper.writeValueAsString(personModelList));



    }
}
