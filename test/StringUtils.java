package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static test.Out.sout;
import java.util.regex.Pattern;

public class StringUtils
{
	public static String replaceWith(String source, final String[][] strs)
	{
		for (String[] str: strs) source = source.replaceAll(str[0], str[1]);
		return source;
	}

	public static void main(String[] args) throws IOException
	{
		String source = new String(Files.readAllBytes(Paths.get("test.txt")));
		sout(replaceWith(source, 
					new String[][]{{Pattern.quote("["),"{"},
						{Pattern.quote("]"),"}"}}));
	}

}

