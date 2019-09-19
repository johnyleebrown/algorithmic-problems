// 359
public class Logger 
{
    private Map<String, Integer> ok = new HashMap<>();
    
    public Logger() {
        
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) 
    {
        if (timestamp < ok.getOrDefault(message, 0))
        {
            return false;
        }
            
        ok.put(message, timestamp + 10);
        return true;
    }
}

