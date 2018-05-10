import org.rapidoid.u.U;
import org.rapidoid.setup.On;

public class ServerExample {
    public static void main(String [] args){

        On.get("/").html("<html> <body> Example of html page</body> </html");
        On.get("/hello").json(() -> U.map("msg", "Hello world"));
    }
}
