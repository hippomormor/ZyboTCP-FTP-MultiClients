package zybo_client.ftp;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FtpHandlerTest
{

    private final FtpHandler ftp;
    private final String ip;
    private final String user;
    private final String pass;

    public FtpHandlerTest() throws IOException
    {
        ip = "192.168.0.25";
        user = "xilinx";
        pass = "ftp";
        ftp = new FtpHandler(ip);
    }

    /**
     * Test of connect method, of class FtpHandler.
     */
    @Test
    public void testConnect() throws Exception
    {
        System.out.println("connect");
        String ipTest = "192.168.0.25";
        String userTest = "xilinx";
        String passTest = "ftp";
        boolean expResult = true;
        boolean result = ftp.connect(ipTest, userTest, passTest);
        ftp.disconnect();
        assertEquals(expResult, result);
    }

    /**
     * Test of readLine method, of class FtpHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadLine() throws Exception
    {
        System.out.println("readLine");
        String expResult = "220 (vsFTPd";
        String result = ftp.readLine();
        boolean expBool = true;
        boolean bool = false;
        if (result.contains(expResult))
            bool = true;
        ftp.disconnect();
        assertEquals(expBool, bool);
    }

    /**
     * Test of send method, of class FtpHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testSend() throws Exception
    {
        System.out.println("send");
        ftp.connect(ip, user, pass);
        String in = "HELP";
        String expResult = "ABOR ACCT ALLO APPE CDUP CWD  DELE EPRT EPSV FEAT HELP LIST MDTM MKD\n"
                + " MODE NLST NOOP OPTS PASS PASV PORT PWD  QUIT REIN REST RETR RMD  RNFR\n"
                + " RNTO SITE SIZE SMNT STAT STOR STOU STRU SYST TYPE USER XCUP XCWD XMKD\n"
                + " XPWD XRMD";
        String result = ftp.send(in);
        System.out.println(result);
        boolean expBool = true;
        boolean bool = false;
        if (result.contains(expResult))
            bool = true;
        ftp.disconnect();
        assertEquals(expBool, bool);
    }

    /**
     * Test of getData method, of class FtpHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetData() throws Exception
    {
        System.out.println("getData");
        ftp.connect(ip, user, pass);
        String msg = "LIST";
        String expResult = "SensorData.log";
        String result = ftp.getData(msg);
        System.out.println(result);
        boolean expBool = true;
        boolean bool = false;
        if (result.contains(expResult))
            bool = true;
        ftp.disconnect();
        assertEquals(expBool, bool);
    }
}
