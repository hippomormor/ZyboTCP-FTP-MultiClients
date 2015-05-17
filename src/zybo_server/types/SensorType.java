/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zybo_server.types;

/**
 *
 * @author Hippomormor
 */
public class SensorType
{
    public String sensorName;
    public int sampleRate;
    public int sampleValue;

    public SensorType(String sensorName, int sampleRate, int sampleValue)
    {
        this.sensorName = sensorName;
        this.sampleRate = sampleRate;
        this.sampleValue = sampleValue;
    }   
}
