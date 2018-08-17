package entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class TestsConf {
    @Id
    @GeneratedValue
    public Long ID;

    @NotNull
    public String Name;

    @NotNull
    public int n_threads;

    @NotNull
    public int ramp_up;

    @NotNull
    public int runs;

    @NotNull
    public int n_clients;

    @NotNull
    public int duration;

    @NotNull
    public String protocol;

    @NotNull
    public String server_name;

    @NotNull
    public int server_port;


    public String test_file;


    public Date date_conf;


    public TestsConf(){
        this.Name = this.test_file = this.server_name = this.protocol = "";
        this.n_threads = this.runs = this.n_clients = this.server_port = this.duration = 0;
        this.date_conf = new java.sql.Date(System.currentTimeMillis());
    }

    public TestsConf(String name,  int n_threads,  int ramp_up,
                     int runs, int n_clients,  int duration,
                     String protocol,  String server_name,  int server_port,
                     String test_file, String date_conf){
        this.Name = name;
        this.n_threads = n_threads;
        this.ramp_up = ramp_up;
        this.runs = runs;
        this.n_clients = n_clients;
        this.duration = duration;
        this.protocol = protocol;
        this.server_name = server_name;
        this.server_port = server_port;
        this.test_file = test_file;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date aux_parse = fmt.parse(date_conf);
            this.date_conf = new java.sql.Date(aux_parse.getTime());
        }catch (java.text.ParseException e){
            this.date_conf = new Date(System.currentTimeMillis()); // Set to current time
        }
    }



}
