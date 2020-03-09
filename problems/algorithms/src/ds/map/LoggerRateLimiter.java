package ds.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 359
 */
public class LoggerRateLimiter
{
    private Map<String, Integer> printSchedule;
    
    public LoggerRateLimiter()
	{
        printSchedule = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) 
    {
        if (timestamp < printSchedule.getOrDefault(message, 0))
        {
            return false;
        }
            
        printSchedule.put(message, timestamp + 10);
        return true;
    }
}