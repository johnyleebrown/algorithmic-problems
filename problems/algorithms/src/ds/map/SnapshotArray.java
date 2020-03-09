package ds.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1146
 */
public class SnapshotArray
{
	// opt: use array
	private Map<Integer, TreeMap<Integer, Integer>> history;
	private int snap_id = 0;

	public SnapshotArray(int length)
	{
		history = new HashMap<>();
		for (int i = 0; i < length; i++)
		{
			TreeMap<Integer, Integer> indHistory = new TreeMap<>();
			indHistory.put(0, 0);
			history.put(i, indHistory);
		}
	}

	public void set(int index, int val)
	{
		TreeMap<Integer, Integer> v = history.get(index);
		v.put(snap_id, val);
		history.put(index, v);
	}

	public int snap()
	{
		int temp = snap_id;
		snap_id++;
		return temp;
	}

	public int get(int index, int snap_id)
	{
		TreeMap<Integer, Integer> v = history.get(index);
		if (v.containsKey(snap_id))
		{
			return v.get(snap_id);
		}
		else
		{
			int f = v.floorKey(snap_id);
			return v.get(f);
		}
	}
}