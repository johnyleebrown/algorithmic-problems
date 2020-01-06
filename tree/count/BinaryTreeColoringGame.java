// 1145
class Solution 
{
    private int leftCount = 0, rightCount = 0;

	// look at 2 cases, if the x is root, or it is somewhere else
	// take max of that and compare to n/2 
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) 
	{
        if (root == null) return false;
        count(root, x);
        return Math.max(Math.max(leftCount, rightCount), 
				n- (leftCount + rightCount + 1)) > n/2;
    }

	// when we reach x node we record left and right
    private int count(TreeNode root, int x)
    {
        if (root == null) return 0;
        
		int left = count(root.left, x);
        int right = count(root.right, x);

        if (root.val == x)
        {
            leftCount = left;
            rightCount = right;
        }

        return left + right + 1;
    }
}

