import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public static void main(String[] args) throws Exception {
        topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2);
        topKFrequent(new int[] { 1, 1, 1, 2, 2,2,2,2, 3 }, 1);

    }

    public static int[] topKFrequent(int[] nums, int k) {
     Map<Integer, Integer> map = new HashMap<Integer, Integer>();
     for ( int i : nums){
        map.put( i , map.getOrDefault(i,0)+1);
     }
     PriorityQueue< Entry<Integer, Integer>> pq=new PriorityQueue<>((x,y)->y.getValue()-x.getValue());
     for(Entry<Integer,Integer> entry :map.entrySet()){
        pq.add(entry);
    }
        int [] result = new int [k];
        for (int i = 0; i<k; i++){
           result[i] =  pq.poll().getKey();
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
