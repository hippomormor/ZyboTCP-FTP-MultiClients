package zybo_client.gui.handlers;

import java.io.IOException;
import zybo_client.tcp.TcpHandler;

public class SensorStateHandler implements Runnable
{

    private static String sensors;
    private TcpHandler tcp;

    public SensorStateHandler(String ip) throws IOException
    {
        sensors = "";
        tcp = new TcpHandler(ip, 8001);
    }

    @Override
    public void run()
    {

        while (true)
        {
            try
            {
                sensors = tcp.send("GSTAT");
                Thread.sleep(300);
            }
            catch (IOException ex)
            {
                System.out.println("Server down. Please reconnect.");
                break;
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
        }

    }
    
    public String getStates()
    {
        return sensors;
    }
}
