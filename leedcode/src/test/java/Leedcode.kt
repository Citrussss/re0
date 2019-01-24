import org.junit.Test
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Leedcode {
    @Test
    fun test() = {
        var nums:IntArray= intArrayOf(3,2,4)
        var target=6
        System.out.println(twoSum(nums,target))
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        var map = HashMap<Int,Int>()
        for (i in 0 until nums.size) {
            if (map.contains(target-nums[i])){
                return intArrayOf(i, map[target-nums[i]]!!)
            }
            map.put(nums[i],i)
        }
       throw Exception()
    }
}