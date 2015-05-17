/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zybo_server;

import zybo_server.handlers.SensorHandler;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hippomormor
 */
public class SensorHandlerTest
{

    /**
     * Test of increase method, of class SensorHandler.
     * @throws java.io.IOException
     */
    @Test
    public void testIncrease() throws IOException
    {
        System.out.println("increase");
        int sensorNumber = 1;
        SensorHandler instance = new SensorHandler();
        String expResult = "Successful, Sensor 1 now has an update rate of 16 Seconds";
        String result = instance.increase(sensorNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of decrease method, of class SensorHandler.
     * @throws java.io.IOException
     */
    @Test
    public void testDecrease() throws IOException
    {
        System.out.println("decrease");
        int sensorNumber = 1;
        SensorHandler instance = new SensorHandler();
        String expResult = "Successful, Sensor 1 now has an update rate of 4 Seconds";
        String result = instance.decrease(sensorNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of stop method, of class SensorHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testStop() throws Exception
    {
        System.out.println("stop");
        int sensorNumber = 1;
        SensorHandler instance = new SensorHandler();   
        String expResult = "Successful, Sensor 1 has been stopped";
        instance.start(sensorNumber);
        String result = instance.stop(sensorNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of start method, of class SensorHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception
    {
        System.out.println("start");
        int sensorNumber = 1;
        SensorHandler instance = new SensorHandler();   
        String expResult = "Successful, Sensor 1 has started logging with an update rate of 8 Seconds";
        instance.stop(sensorNumber);
        String result = instance.start(sensorNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of list method, of class SensorHandler.
     * @throws java.io.IOException
     */
    @Test
    public void testList() throws IOException
    {
        System.out.println("list");
        SensorHandler instance = new SensorHandler();   
        String expResult = "[Sensor 1, Sensor 2, Sensor 3, Sensor 4, Sensor 5]";      
        String result = instance.list();
        assertEquals(expResult, result);
    }

    /**
     * Test of wipeLog method, of class SensorHandler.
     */
    @Test
    public void testWipeLog() throws Exception
    {
        System.out.println("wipeLog");
        SensorHandler instance = new SensorHandler();   
        String expResult = "Log has been wiped.";      
        String result = instance.wipeLog();
        assertEquals(expResult, result);
    }   
}
