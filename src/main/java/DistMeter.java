import components.distconfmanager.DistConfManager;
import conf.ConfManager;

public class DistMeter {

    public static void main(String [] args){
        ConfManager confManager = new ConfManager(); // Configuration object

        //TODO: manage configuration flag
        DistConfManager distmeterConfManager = new DistConfManager(confManager);
        distmeterConfManager.run(args);


    }
}