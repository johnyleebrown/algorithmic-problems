// 359
public class Logger 
{
    private Map<String, Integer> printSchedule;
    
    public Logger() 
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

