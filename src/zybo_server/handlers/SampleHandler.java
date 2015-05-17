package zybo_server.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleHandler implements Runnable
{

    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final String sensorName;
    private final int sampleRate;
    private final int sampleValue;
    private boolean exit;

    public SampleHandler(String name, int rate, int value)
    {
        sensorName = name;
        sampleRate = rate;
        sampleValue = value;
        exit = false;
    }

    public synchronized void saveToFile() throws IOException
    {
        if (new File("SensorData.log").exists())
        {
            //FileWriter file = new FileWriter("/home/xilinx/SensorData.log", true);
            FileWriter file = new FileWriter("SensorData.log", true);
            PrintWriter out = new PrintWriter(file);
            out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
            out.close();
        }
        else
        {
            //FileWriter file = new FileWriter("/home/xilinx/SensorData.log");
            FileWriter file = new FileWriter("SensorData.log");
            PrintWriter out = new PrintWriter(file);
            out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
            out.close();
        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                if (!exit)
                {
                    saveToFile();
                    Thread.sleep(sampleRate * 1000);
                }
                else
                {
                    return;
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("\n" + date.format(new Date()) + " - Logging of " + sensorName + " stopped.");
                return;
            }
            catch (IOException e)
            {
                System.out.println("\n" + date.format(new Date()) + " - Cannot write sensor-data to log.");
                exit = true;
            }
        }
    }
}
