package components.distconfmanager;

import org.rapidoid.annotation.Controller;
import org.rapidoid.annotation.Page;

import java.io.FileReader;

@Controller
public class DistConfManagerHTTP {

    @Page("/")
    public Object main_page(){
        //TODO: Replace by instructions to read items in html page
        return "<p><b>RAW page</b> HTML!<p>";
    }

}
