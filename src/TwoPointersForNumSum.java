import java.util.*;

public class TwoPointersForNumSum {
    /**
     * 数字求和问题，
     * 包括三值求和问题，三值求最接近target的问题，以及四值求和问题
     * 双指针解决sum问题最重要的一点是，要求return的是值而不是index
     * 如果要返回index,则大概率没法用双指针。
     * 双指针问题的基本思路是，先对输入进行排序，然后一左一右两个指针
     * 两个指针依次移动，先移动一个指针，然后进行if判断，判断完决定移动
     * 左指针还是右指针。
     * 移动结束的标志是左指针的index > 右指针的index
     *
     * sum问题的思路是，如果是three sum，则先固定一个数，剩下的数里用
     * 双指针依次进行遍历。
     *
     * four sum问题的思路是，先固定两个数，A[0] 和A[1],然后对A[1:]后面、
     * 的数进行双指针依次遍历。
     */


    //NO.2
    //这题返回的是满足条件两个数的索引
    //输入的数组中可能会出现重复元素，但是因为输出的唯一型，
    //所以如果重复的元素A1，A2都满足输出，那么输出的必将是<A1.index,A2.index>
    //不可能是<A1.index,B.index>,那样不满足输出唯一性条件
    //基于这一点，我们可以用index作为key,具体的值作为Value
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++){

            int sub = target - nums[i];
            if(map.containsKey(sub)){
                result[1] = i;
                result[0] = map.get(sub);
                break;

            }
            map.put(nums[i],i);
        }
        return result;
    }

    //NO.15
    //3sum 返回包含三个数的list,这三个数加起来为0，
    // 双指针，先排序，再依次遍历输入i++
    // 每次固定一个数，然后剩余的数里用双指针进行一一比对
    //这里注意一点，左指针start与start-1所代表的值相同时，则直接break
    // nums[start]==start[i-1]可以剪枝，但是nums[i]=nums[i-1]时候不能剪枝,end那边的指针也剪枝一次
    //这里还有一个技巧，那就是因为要返回不重复的数值对，依次遍历时，i与i+1时可能有相同的满足情况
    //但是i与i+1不容易剪枝，因此临时用hashset存储，最后return的时候new ArrayList<>(HashSet)一下
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> res = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int j = i+1, k = nums.length -1;
            //if ( i > 0 && nums[i]==nums[i-1]) continue;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if ( sum == 0) {
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                } else if ( sum < 0) {
                    j ++;
                } else {
                    k--;
                }
            }
            while ( j < k && nums[j]==nums[j-1]) {j++;}
            //while (j<k && nums[k]==nums[k+1]) {k++;}

        }
        return new ArrayList<List<Integer>>(res);
    }

    //NO.16
    //3sum closet
    //和15题的差别就是，这里返回一个最接近给定target的值
    //因此这里增加变量来记录这个最接近target的gap值
    public int threeSumClosest(int[] nums, int target) {
        int res=0;
        int gap=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0;i<=nums.length-3;i++) {
            if ( i >0 && nums[i]==nums[i-1]) continue;
            for (int j = i+1,k = nums.length-1;j<k;){
                int tmp = nums[i] + nums[j] +nums[k];
                if ( tmp < target) {
                    j++;
                } else if ( tmp > target) {
                    k--;
                } else {
                    return target;
                }
                gap = Math.abs(gap) < Math.abs( tmp - target) ? gap : tmp-target;
            }

        }
        return target+gap;
    }

    //No.18
    //4sum 和 3sum思路一样，但是这里是固定两个数i=0,j=1,剩下的数用双指针依次去遍历
    //剪枝策略也同与3sum问题
    public List<List<Integer>> fourSum(int[] nums, int val){
        Set<List<Integer>> currentSet = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for ( int i = 0;i < nums.length; i++){
            if (i>0 && nums[i] == nums[i-1]) continue;
            for ( int j = i+1; j < nums.length; j++) {
                //if ( j > 1 && nums[j]==nums[j-1]) continue;
                int start = j+1,end = nums.length-1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] +nums[end];
                    if (sum == val) {
                        currentSet.add(Arrays.asList(nums[i],nums[j],nums[start],nums[end]));
                        start ++;
                        end --;
                    } else if (sum > val) {
                        end --;
                    } else start++;
                }
                while(start < end && nums[start-1]==nums[start]) start++;
            }
        }
        return new ArrayList<List<Integer>>(currentSet);
    }
}
