package components.distconfmanager;

import org.rapidoid.annotation.Controller;
import org.rapidoid.annotation.POST;
import org.rapidoid.annotation.Page;
import org.rapidoid.data.JSON;
import org.rapidoid.http.Req;
import org.rapidoid.http.Resp;
import org.rapidoid.log.Log;


@Controller
public class DistConfManagerHTTP {

    @Page("/server/conf/tests")
    public Object main_page(){
        //TODO: Replace by instructions to read items in html page
        String aux = "<p><b>RAW page</b> HTML!<p>" +
                "<form action=\"process_conf_form\" method=\"post\"> " +
                "<input type=\"input\" name=\"test_name\"> <br>" +
                "<input type=\"submit\" name=\"submit\">" +
                "</form>";
        Log.debug("main page "   );
        return aux;
    }

    @POST
    public String process_conf_form(Req req, Resp resp) {
        Log.info("process_conf_form " + req.posted() + " test_name=" + req.posted("test_name")  );
        Log.info("" + JSON.stringify(req.posted()));
        return JSON.stringify(req.posted());
    }


}
