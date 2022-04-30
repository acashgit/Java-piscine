package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.client.Client;

import java.io.IOException;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = "--server-port")
    private Integer port;

    private static String host = "localhost";
    public static void main(String[] args) {
        Main main = create(args);

        Client client = new Client();
        try {
            client.start(host, main.port);
        } catch (IOException e) {
            System.out.println("Non block read error!");;
        }
    }

    private static Main create(String[] args) {
        Main main = new Main();
        try {
            JCommander.newBuilder().addObject(main).build().parse(args);
        } catch (ParameterException ex) {
            System.err.println("Error arguments!");
            System.exit(1);
        }
        if (main.port == null || main.port < 1 || main.port > 65535) {
            System.err.println("The port number must be in the range from 1 to 65535!");
            System.exit(1);
        }
        return main;
    }
}
