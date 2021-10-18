import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class DynamicProgramming {
    //NO.53 Maximum Subarray
    //求一个数组中，连续部分的最大和，返回这个和
    // 用一维数组记录res记录转移状态
    //res[i]代表输入数组0到i这之间的最大和
    //res[i] = res[i-1] + nums[i] > res[i-1] ? res[i-1] + nums[i] : nums[i]
    public int maxSubArray(int[] nums) {
        int rs = Integer.MIN_VALUE;
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i =1; i < nums.length;i++) {
            int tmp = nums[i]+res[i-1] >nums[i] ? nums[i]+res[i-1] :nums[i];
            res[i] = tmp;
        }
        for (int re : res) {
            rs = rs < re ? re : rs;
        }
        return rs;
    }


    //121. Best Time to Buy and Sell Stock
    /**
     * 一次买，一次卖，先买后卖，求最大收益
     * 这里有一点需要彻底想明白，遍历一次输入数组prices
     * 用变量buy记录每次遍历时更新的最小值，这里不用担心如果后面的值
     * 减去这个最小值不是最优解咋办，因为如果这个prices[i]不是比已经记录的buy更小的话，
     * 就尝试去更新最大收益，看看此时收益有没有比已记录的更大
     * profit = prices[i] - min > profit
     * ? prices[i] - min : profit;
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        if (prices.length <= 1) {
            return 0;
        }
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                profit = price - min > profit ? price - min : profit;
            }
        }
        return profit;

    }

    //NO62. Unique Paths
    /**
     * 求不同的走法，只能向右走和向下走
     * 用二维数组记录动态转移
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
    //NO.300 求最长递增子序列

    /**
     * 先用最笨的方法，双重循环，注意看子循环是如何遍历的
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j]+1,dp[i]);

                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        DynamicProgramming tmp = new DynamicProgramming();
        tmp.lengthOfLIS(new int[]{4,10,4,3,8,9});
    }
}
