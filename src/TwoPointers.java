import java.util.HashMap;

public class TwoPointers {
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

}
