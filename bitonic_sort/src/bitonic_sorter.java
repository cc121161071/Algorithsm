import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 主函数
 * @author apple 陈晨
 *
 */
public class bitonic_sorter {

	public static void main(String[] args) {

		new Sort(generateXlist(8), false);//产生8个随机数列并排序，个数是二的幂次方的，方向递减
		new Sort(generateXlist(8), true);//产生8个随机数列并排序，个数是二的幂次方的，方向递增
		new Sort(generateXlist(7), false);//产生7个随机数列并排序，个数不是二的幂次方的，方向递减
		new Sort(generateXlist(7), true);//产生7个随机数列并排序，个数不是二的幂次方的，方向递增

	}

	/*****
	 * 
	 * @param size
	 * @return 大小为size的无序数列
	 */
	public static List<Integer> generateXlist(int size) {
		List<Integer> Xlist = new ArrayList<Integer>();
		Random randnumber = new Random();
		System.out
				.println("<--------------------------before bitonic_sort---------------------------->");
		for (int i = 0; i < size; i++) {
			int r = Math.abs(randnumber.nextInt() % 128);
			Xlist.add(r);
			System.out.println(r);
		}

		return Xlist;

	}
}

/*******
 *  Sort 类
 * 
 * @author apple 陈晨
 * 
 */
class Sort {

	private List<Integer> Xlist = new ArrayList<Integer>();
	/*****
	 * 
	 * @param Xlist 待排序数列
	 * @param up 排序方向：true：增序，false：减序
	 */

	public Sort(List<Integer> Xlist, boolean up) {

		this.Xlist = Xlist;

		bitonicSort(0, Xlist.size(), up);// 排序

		System.out
				.println("<--------------------------after bitonic_sort---------------------------->");
		for (int newr : Xlist) {
			System.out.println(newr);

		}

	}
	/****
	 * 二分排序，前一半反序，后一半增序，构造双调序列
	 * @param low 启始下标
	 * @param n 长度
	 * @param up 排序方向
	 */

	private void bitonicSort(int low, int n, boolean up) {
		if(n<=1) return;
		else{
			int m = n / 2;
			bitonicSort(low, m, !up);
			bitonicSort(low + m, n - m, up);
			bitonicMerge(low, n, up);
		}
	}

	/***
	 * 归并
	 * @param low
	 * @param n
	 * @param up
	 */
	private void bitonicMerge(int low, int n, boolean up) {
		if(n<=1) return;
		else{
	    
		int m = getFlag(n);//找到不比n大的最大的2的幂次方的数
			
		for (int i = low; i < low + n - m; i++)
				compare(i, i + m, up);//比较大小（按照排序方向换位置。）
			bitonicMerge(low, m, up);//需要进行比较的数列合并
			bitonicMerge(low + m, n - m, up);//剩下的数列合并
		}
	}

	/****
	 * 比较i,j,如果不符合排序方向则交换
	 * @param i
	 * @param j
	 * @param up
	 */
	private void compare(int i, int j, boolean up) {
		if (up == (Xlist.get(i) > Xlist.get(j)))
			exchange(i, j);
	}
/****
 * 交换i，j的位置
 * @param i
 * @param j
 */
	private void exchange(int i, int j) {
		int t = Xlist.get(i);
		Xlist.set(i, Xlist.get(j));
		Xlist.set(j, t);
	}

	/***
	 * 找到不比n大的最大的是二的幂次方的数
	 * @param n
	 * @return
	 */
	private int getFlag(int n) {
		int k = 1;
		while (k < n)
			k = k << 1;
		return k >> 1;
	}

}
