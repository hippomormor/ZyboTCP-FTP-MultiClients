/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zybo_server;

import zybo_server.handlers.SampleHandler;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hippomormor
 */
public class SampleHandlerTest
{

    /**
     * Test of saveToFile method, of class SampleHandler.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testSaveToFile() throws IOException
    {
        System.out.println("saveToFile");
        File file = new File("SensorData.log");
        file.delete();
        String name = "1";
        int rate = 16;
        int value = 2;
        SampleHandler instance = new SampleHandler(name, rate, value);
        try
        {
            instance.saveToFile();
        }
        catch (IOException e)
        {
            fail("Unable to write to file");
        }
        boolean expResult = true;
        boolean result = new File("SensorData.log").exists();
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class SampleHandler.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        String name = "2";
        int rate = 16;
        int value = 2;
        String result = null;
        String expResult = name;
        SampleHandler instance = new SampleHandler(name, rate, value);
        Thread testThread = new Thread(instance, name);
        testThread.start();
        for (Thread t : Thread.getAllStackTraces().keySet())
        {
            if (t.getName().equals(name))
            {
                t.interrupt();
                System.out.println("Running thread with name '" + t.getName() + "' found");
                result = t.getName();
            }
        }
        assertEquals(expResult, result);     
    }

}
