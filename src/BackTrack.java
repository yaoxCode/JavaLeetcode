import java.util.*;

public class BackTrack {
    /**
     * 如何判断一题目是否用回溯可以看两点
     * 首先回溯法时间复杂度很高，接近穷举，因此可以使用回溯的题目，输入nums.length肯定比较小
     * 其次，要能写出回溯树，才可以进行回溯，因此回溯题一定要把回溯树给写出来
     *
     * 回溯法需要找到以下内容才能顺利解题
     * 1. 可选路径，可选路径是指接下来要在哪些内容里进行选择，对于可选路径，有的题目每次都是从头遍历
     * 有的题目每次遍历都是上一个位置之后的位置来避免重复，因此递归参数里要考虑startIndex是否需要每次变化
     * 2. 当前路径，依次遍历可选路径，需要将符合条件的值插入当前路径，对于数字问题，当前路径用Stack来保存
     * 对于字符串问题，则用StringBuffer来保存
     * 3. 对于一个元素是否能加入当前路径，负责的题目会出现，对于当前路径不同的选择，之后进行不同的递归处理
     * 4. 知道何时进行下一轮递归，注意递归出口都是写在递归体最前面的部分，不满足递归出口才能遍历可选路径，进入
     * 下一次递归，对于递归出口的判断，可以很好的减少处理时间
     * 5.遍历可选路径时，有时候也可以进行递归return剪枝
     * 6.有一次往当前路径的add操作，就对应依次remove操作，这里的add，remove不一定都在递归前后
     * 也有可能是先add然后加入最终路径，加入后再remove，然后再return递归
     * 7。 对于stack注意，因此要保存到res中，所以res.add(new ArrayList<>(Stack)),但是对于StringBuffer
     * 直接add(sb.toString)
     * 8. 最主要的难点是可选路径的选择以及遍历，最后是实时的add和remove，
     * 9. 如何写递归，就直接模拟最后依次选择了，前面都选择完成了，那么针对最后依次选择这个递归函数如何写
     */

    /**
     * NO.46 Permutations
     * 给一定不重复元素的数组nums，给出所有全排列的可能性
     * 1. 可选路径为nums,无论哪次递归都是nums从头遍历
     * 2. 进入当前队列的条件为 !track.contains(nums[i])
     * 3. 递归结束条件为 当前路径有nums.length()个元素了
     * 4. 这里不需要纠结当前路径超出了咋办，这里是由递归出口控制最终某个结果里元素个数的，
     * 后面很多题目也是这样，不需要纠结最终当前队列的元素个数超过了咋办，超过了递归就return了
     * 5。 这里就是标准的递归前add,递归后remove
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> track = new LinkedList<>();
        //这里使用dfs搜索nums数组
        permuteDfs(nums,track,res);
        return res;

    }
    private void permuteDfs(int[]nums,List<Integer> track,List<List<Integer>> res){
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


    /**
     * NO.17
     * 手机九宫格按数字，返回所以数字给定的所有字母的情形
     * 1. 这里当前路径是字符串，所有由stringbuffer存储，遍历出一个就可以add
     * 2. 可选路径，这里假设键盘输入了一个2 ，那么可选路径就是2对应的abc里遍历，不用考虑后面按的3
     * 这个是由递归参数决定的，下次递归就是遍历3对应的'def'
     * 3. 这里怎么确保返回的字符串长度等于输入的数字长度呢，由递归出口决定，sb.length()==digit.len就终止递归了
     * 4.这里也是标准的递归前add，递归后remove
     * 5, 这里注意的可选路径是字符串，但是字符串不容易遍历，所有都是遍历s.toArray();
     */
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
        if (digits.length()==0) return res;

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
            //这里可以剪枝，index+1 < digits.length才进入递归，否则直接delete
            phoneKeyBoardBackTrack(res,map,currentS,index+1,digits);
            currentS.deleteCharAt(currentS.length()-1);
        }
    }
}
