package zybo_client.gui.handlers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MenuHandler implements Runnable
{

    private boolean tcpUp;
    private boolean ftpUp;
    private final String ip;
    private int totalTime;
    private JPanelCreator jpc;

    public MenuHandler(String ip)
    {
        this.ip = ip;
        //tcpUp = true;
    }

    public String testUpState()
    {
        if (ftpUp && !tcpUp)
            return "IP: " + ip + "\nUp: True\nLatency: " + totalTime + " ms.\nTCP is down";
        else if (tcpUp && !ftpUp)
            return "IP: " + ip + "\nUp: True\nLatency: " + totalTime + " ms.\nFTP is down";
        else if (tcpUp && ftpUp)
            return "IP: " + ip + "\nUp: True\nLatency: " + totalTime + " ms.";
        else
            return "IP: " + ip + "\nUp: False";
    }

    // Paint FTP window:
    public void runFtp(String ip) throws InterruptedException, IOException
    {
        if (ftpUp)
        {
            jpc = new JPanelCreator(ip);
            jpc.paintFtp();
        }
    }

    // Paint TCP window:
    public void runTcp(String ip) throws InterruptedException, IOException
    {
        if (tcpUp)
        {
            jpc = new JPanelCreator(ip);
            jpc.paintTcp();
        }
    }
    
    public void exit()
    {
        System.exit(0);
    }
    
    public boolean getTcpUp()
    {
        return tcpUp;
    }
    
    public boolean getFtpUp()
    {
        return ftpUp;
    }
      
    private boolean isFtpUp(InetAddress host) throws IOException
    {
        boolean status = false;
        try
        {
            Socket sock = new Socket();
            double startTime = System.currentTimeMillis();
            // Set time-out to 3 seconds:
            sock.connect(new InetSocketAddress(host, 21), 3000);
            if (sock.isConnected())
            {
                double endTime = System.currentTimeMillis();
                // Calculate delay:
                if (!tcpUp)
                    totalTime = (int) ((endTime - startTime));

                sock.close();
                status = true;
            }
        }
        catch (IOException ex)
        {
            return false;
        }
       return status;
    }

    private boolean isTcpUp(InetAddress host) throws IOException
    {
        boolean status = false;
        try
        {
            Socket sock = new Socket();
            double startTime = System.currentTimeMillis();
            // Set time-out to 3 seconds:
            sock.connect(new InetSocketAddress(host, 8000), 3000);
            if (sock.isConnected())
            {
                double endTime = System.currentTimeMillis();
                // Calculate delay:
                totalTime = (int) ((endTime - startTime));
                sock.close();
                status = true;
            }
        }
        catch (IOException ex)
        {
            return false;
        }
       return status;
    }

    // Loop checking server reachability every 5 seconds:
    @Override
    public void run()
    {
        while (true)
        {
            try
            {               
                tcpUp = isTcpUp(InetAddress.getByName(ip));
                ftpUp = isFtpUp(InetAddress.getByName(ip));            
                Thread.sleep(10000);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
