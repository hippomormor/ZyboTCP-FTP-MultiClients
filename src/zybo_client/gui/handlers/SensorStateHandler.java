package zybo_client.gui.handlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                Thread.sleep(3000);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(SensorStateHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public String getStates()
    {
        return sensors;
    }
}
