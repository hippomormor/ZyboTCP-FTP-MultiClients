package zybo_client.tcp;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TcpHandlerTest
{
    private final TcpHandler tcp;
    private final String ip;
    
    public TcpHandlerTest() throws IOException
    {        
        ip = "192.168.0.25";
        tcp = new TcpHandler(ip, 8001);
    }

    /**
     * Test of send method, of class TcpHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testSend() throws Exception
    {
        System.out.println("send");      
        String output = "LIST";
        String expResult = "[Sensor 1, Sensor 2, Sensor 3, Sensor 4, Sensor 5]";
        String result = tcp.send(output);
        System.out.println(result);
        tcp.disconnect();
        assertEquals(expResult, result);
    }   
}
