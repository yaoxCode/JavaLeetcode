import java.util.*;

public class Leecode {
    //two sum
    public int[] twoSum(int[] nums, int target) {
        return null;
    }

    //NO.16
    //求数组中三个数之和最接近target的那个和
    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        int gap = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                int tmp = nums[i] + nums[j] + nums[k];
                if (tmp < target) {
                    j++;
                } else if (tmp > target) {
                    k--;
                } else {
                    return target;
                }
                gap = Math.abs(gap) < Math.abs(tmp - target) ? gap : tmp - target;
            }

        }
        return target + gap;
    }

    //NO.15
    //从数组中找到加起来等于target0的三个数所在的索引，返回索引位置对，可能存在多个满足的条件
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> res = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1, k = nums.length - 1;
            //if ( i > 0 && nums[i]==nums[i-1]) continue;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return new ArrayList<List<Integer>>(res);
    }


    //NO.121
    //买卖股票的最佳时间，一次买，一次卖，先买先卖，返回最大收益
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        if (prices.length <= 1) {
            return 0;
        }
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                profit = prices[i] - min > profit ? prices[i] - min : profit;
            }
            return profit;
        }
        return profit;
    }


    //NO.42
    //求蓄水池能装多少水
    public int trap(int[] height) {
        int res = 0;

        return res;
    }

    //NO.78
    //求集合的子集,迭代的写法
    //最核心的是，初始化一个只有空集的res,
    //然后依次遍历数组元素，取出已经存在的子集，把新元素加到原来已经纳入res的子集里
    public List<List<Integer>> subsets(int[] nums) {
        // 存放结果的列表
        List<List<Integer>> res = new ArrayList<>();
        // 合法性校验
        if (nums == null || nums.length < 1) {
            return res;
        }
        // 任何结果必定存在[]
        List<Integer> nu = new ArrayList<>();
        res.add(new ArrayList<>(nu));        // 循环遍历数组，每次取出来的新元素，都要加入到res的每个元素里
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) { //
                //这里有必要注意下传值和传地址以及深浅拷贝
                List<Integer> tmp = new ArrayList<>(res.get(j)); //这里必须new一下，不然也会更新原始的对象
                tmp.add(nums[i]);
                res.add(tmp); //更新以后再存进去
            }
        }
        return res;
    }

    //NO,78求集合子集的递归写法
    //

    //No.62 unique paths
    //dp求解，维护一个二维状态转移矩阵
    //或者实用数学知识，排列组合，向右走有n-1个方案，向下走有m-1个方案
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    //No.75 sort colors
    //将array中的0，1，2进行排序
    //method1：看出是排序问题，用快排解决
    public void sortColors(int[] nums) {

    }

    //No.46 Permutations
    //全排列，最经典的回溯法
    //递归结束条件是当前路径长度==nums.length
    // 加入路径的条件是路径中不存在这个元素，用链表或者stack记录当前路径
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> track = new LinkedList<>();
        //这里使用dfs搜索nums数组
        permuteDfs(nums,track,res);
        return res;

    }
    private void permuteDfs(int[]nums, List<Integer> track, List<List<Integer>> res){
        for(int i =0;i<nums.length;i++) {
            if (track.size() == nums.length) {
                res.add(new LinkedList(track));
                return;
            }

            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            permuteDfs(nums,track,res);
            track.remove(track.lastIndexOf(nums[i]));
        }
    }
    private void quickSort(int[] nums,int start,int end) {
        int x = nums[0];
        while ( start < end ){
            while (nums[end] >= x && start < end) end--;
            while (nums[start] < x && start < end) start++;
            nums[0] = nums[end];
            nums[end] = nums[start];
        }
    }

    //No.17 回溯法
    //各九宫格输入法，输入阿拉伯数字，输出可能的字符串组合
    //全排列问题的变种
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        Map<Character,Character[]> phoneKeyBoardMap = new HashMap<>();
        phoneKeyBoardMap.put('2',new Character[] {'a','b','c'});
        phoneKeyBoardMap.put('3',new Character[] {'d','e','f'});
        phoneKeyBoardMap.put('4',new Character[] {'g','h','i'});
        phoneKeyBoardMap.put('5',new Character[] {'j','k','l'});
        phoneKeyBoardMap.put('6',new Character[] {'m','n','o'});
        phoneKeyBoardMap.put('7',new Character[] {'p','q','r','s'});
        phoneKeyBoardMap.put('8',new Character[] {'t','u','v'});
        phoneKeyBoardMap.put('9',new Character[] {'w','x','y','z'});

        phoneKeyBoardBackTrack(res,phoneKeyBoardMap,new StringBuffer(),0,digits);
        //递归搜索

        return res;

    }
    public void phoneKeyBoardBackTrack(List<String>res, Map<Character,Character[]> map,StringBuffer currentS,int index,String digits){
        if ( currentS.length() == digits.length()) {
            res.add(currentS.toString());
            return ;
        }
        System.out.println(map.get(digits.charAt(0)).length);
        for (int i = 0; i < map.get(digits.charAt(index)).length; i++) {
            currentS.append(map.get(digits.charAt(index))[i]);
            phoneKeyBoardBackTrack(res,map,currentS,index+1,digits);
            currentS.deleteCharAt(currentS.length()-1);
        }
    }

    public static void main(String[] args) {
        Leecode tmp = new Leecode();
        tmp.letterCombinations("");
    }
}
