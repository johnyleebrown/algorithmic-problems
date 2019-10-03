package hard.bfs;

import java.util.*;
public class WordLadderII
{
	static class Solution 
	{
		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) 
		{
			Set<String> seenBase = new HashSet<>();
			for (String s: wordList) seenBase.add(s);
			Queue<Set<String>> seenQ = new LinkedList<>();
			seenBase.remove(beginWord);
			seenQ.add(seenBase);
			
			Queue<List<String>> paths = new LinkedList<>();
			List<String> pathBase = new ArrayList<>();
			pathBase.add(beginWord);
			paths.add(pathBase);
			
			Queue<String> q = new LinkedList<>();
			q.add(beginWord);
			
			List<List<String>> result = new ArrayList<>();
				
			while(!q.isEmpty())
			{
				int queueSize = q.size();
				for (int i = 0; i < queueSize; i++)
				{
					String target = q.poll();
				  //  System.out.println(target);
					
					List<String> path = paths.poll();
				  //  System.out.println(path);
					
					Set<String> seen = seenQ.poll(); 
				  //  System.out.println(seen);
					
				  //  System.out.println();
					
					if (endWord.equals(target)) result.add(path);
					else
					{
						for (String variant: getVariants(seen, target))
						{
							q.add(variant);
							
							List<String> newPath = new LinkedList(path);
							newPath.add(variant);
							paths.add(newPath);
							
							Set<String> newSeen = new HashSet(seen);
							newSeen.remove(variant);
							seenQ.add(newSeen);
						}    
					}
				}
			}
			
			return result;
		}
		
		private List<String> getVariants(Set<String> seen, String str)
		{
			List<String> strList = new ArrayList<>();
			StringBuilder sb = new StringBuilder(str);
			
			for (int i = 0; i < str.length(); i++)
			{
				for (int j = 0; j < 26; j++)
				{
					sb.setCharAt(i, (char)('a' + j));
					String newStr = sb.toString();
					if (!seen.remove(newStr)) continue;
					strList.add(newStr);
				}
				sb.setCharAt(i, str.charAt(i));
			}
			
			return strList;
		}
	}

	public static void main(String[] args)
	{
		Solution s = new Solution();
		String beginWord = "qa"; 
		String endWord = "sq";
		List<String> wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
		System.out.println(s.findLadders(beginWord, endWord, wordList));
	}
}

