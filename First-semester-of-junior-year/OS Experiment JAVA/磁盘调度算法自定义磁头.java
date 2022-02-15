package 操作系统实验;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/*
 * 磁盘调度FCFS、SSTF、SCAN
 * @author CSDN 张时贰
 * @data 2021年12月7日
 */

public class 磁盘调度算法自定义磁头 {
	// 冒泡排序(从小到大)
	public static void SortA(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			for (int j = 0; j < list.length - 1 - i; j++) {
				if (list[j] > list[j + 1]) {
					int temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

	// 冒泡排序(从大到小)
	public static void SortB(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			for (int j = 0; j < list.length - 1 - i; j++) {
				if (list[j] < list[j + 1]) {
					int temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

	// 先来先服务FCSFS,即根据磁盘访问的先后次序进行调度,遍历list数组即可，所以不做赘述
	private static void FCFS(int[] list) {
		int i = 0;
		System.out.println("请输入磁头初始位置");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		int num = 0;// 变量num记录寻道距离
		num = Math.abs(i - list[0]);
		for (int j = 0; j < list.length - 1; j++) {
			num += Math.abs(list[j + 1] - list[j]);
		}
		System.out.println("磁盘扫描序列为：" + Arrays.toString(list));
		System.out.println("寻道次数" + num);
		double w = ((float) num / (float) list.length);
		System.out.println("平均寻道距离" + w);
	}

	// 最短路径算法SSTF,即访问下一个磁道前，先计算一次当前磁头到访问各个磁道的距离
	private static void SSTF(int[] list) {
		int i = 0;// 变量i记录当前磁头位置
		int num = 0;// num记录总寻道距离
		int[] templist = new int[list.length];// 将结果保存在templist[]中
		int[] k = new int[list.length];// k数组记录访问的每一个磁道到磁头距离
		System.out.println("请输入磁头初始位置");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();

		for (int a = 0; a < list.length; a++) {
			// j循环遍历出每一个访问的磁道到当前磁头的距离保存在k数组中
			for (int j = 0; j < list.length; j++) {
				k[j] = Math.abs(list[j] - i);
			}

			// 将k数组赋值给temp数组，对temp数组冒泡排序
			int[] temp = new int[k.length];
			for (int j = 0; j < k.length; j++) {
				temp[j] = k[j];
			}
			SortA(temp);

			int j = temp[0];// 找到磁头到下一个磁道距离最小的为temp[0]

			// 循环遍历找到temp[0]在list磁道序列数组中的位置
			for (int t = 0; t < k.length; t++) {
				// if语句找到list[t]到当前磁头最近
				if (k[t] == j) {
					i = list[t];// 变量i记录磁头位置
					templist[a] = i;// 将结果存入templist数组中
					list[t] = 999999;// 将第g个磁道号设为999999，可以理解为权重设为99999，这样他在下一次遍历，k数组中总是最大
					break;
				}
			}
			num += j;
		}
		System.out.println("磁盘扫描序列为:" + Arrays.toString(templist));
		System.out.println("寻道次数:" + num);
		double w = ((float) num / (float) list.length);
		System.out.println("平均寻道距离:" + w);
	}

	/*扫描算法SCAN   磁盘的0磁道在最外圈
	扫描算法又叫电梯算法，即按从小到大或从大到小的顺序去依次访问磁道。
	所以这里分两种情况，1.寻道从内向外(从大到小) 2.寻道从外向内(从小到大)
	
	注意:实验要求直观的看出三种算法的区别，是以磁道序列的首磁道为磁头，用户自定义磁头方向。
	这里分三种情况,按教材的案例，磁头方向从内向外
	1.若当前磁头位置大于请求序列中最大者，则直接由内向外依次给予请求服务
	2.若当前磁头位置小于请求序列中最小者，则直接由外向内依次给予各请求服务
	3.若当前磁道号大于请求序列中最小者且小于最大者,调用SCAN算法
	*/
	private static void SCAN(int[] list) {
		SortA(list);//先进行一次从小到大排序
		int num = 0;// num记录总寻道距离
		System.out.println("请输入磁头初始位置");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();//变量i记录磁头位置
		int k = 0;
		int[] templist=new int[list.length];//将访问顺序存入templist中
		
		//1.若当前磁头位置大于请求序列中最大者，则直接由内向外依次给予请求服务
		if(i>list[list.length-1]) {
			SortB(list);//从大到小(从内向外)依次访问
			System.arraycopy(list, 0, templist, 0, list.length);//将访问顺序存入templist中
			//计算距离
			num +=Math.abs(i- list[list.length-1]);//磁头移动到第一个磁道的距离
			for (int j = 0; j < list.length - 1; j++) {
				num += Math.abs(list[j + 1] - list[j]);
			}
		}
		
		//2.若当前磁头位置小于请求序列中最小者，则直接由外向内依次给予各请求服务
		else if(i<list[0]) {
			//已经进行过从小到大排序，所以这里不用再次调用SortA
			System.arraycopy(list, 0, templist, 0, list.length);
			//计算距离
			num +=Math.abs(i- list[list.length-1]);//磁头移动到第一个磁道的距离
			for (int j = 0; j < list.length - 1; j++) {
				num += Math.abs(list[j + 1] - list[j]);
			}
		}
		//3.若当前磁头大于请求序列中最小者且小于最大者
		else {
			//寻找离磁头最近的访问磁道
			for (int j = 0; j < list.length; j++) {
				if (i <= list[j]) {
					k = j;
					break;
				}
			}
			//记录磁道访问顺序到templist中
			System.arraycopy(list, k, templist, 0, list.length-k);
			SortB(list);
			System.arraycopy(list,list.length-k, templist, list.length-k, k);
			
			num = Math.abs(i - templist[0]);//磁头移动到第一个磁道的距离
			//每个访问磁道之间的距离
			for (int j = 0; j < templist.length - 1; j++) {
				num += Math.abs(templist[j + 1] - templist[j]);
			}
			/*以下注释比较难懂，用上面这段计算距离*/
//			//找到后由外向内访问(由小到大)，然后再由内向外访问(从大到小)
//			//两次for计算总距离
//			num += list[k] - i;//磁头移动到第一个磁道的距离
//			//由外向内，区间[k,list.length-1)
//			for (int j = k; j < list.length - 1; j++) {
//				num += list[j + 1] - list[j];
//			}
//			//到达最里面，再由内向外访问剩余磁道,区间[0,k-1)
//			for (int j = 0; j < k - 1; j++) {
//				num += Math.abs(list[j + 1] - list[j]);
//			}		
//			num += list[list.length - 1] - list[k - 1];
		}
		
		System.out.println("磁盘扫描序列为:" + Arrays.toString(templist));
		System.out.println("寻道次数" + num);
		double w = ((float) num / (float) list.length);
		System.out.println("平均寻道长度" + w);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int Tracknum;// 磁道数

		// 菜单
		while (true) {
			System.out.println("请输入磁道数量Tracknum");
			Tracknum = sc.nextInt();
			// 输入磁道序列
			int[] TrackSequence = new int[Tracknum];
			System.out.println("磁道序列");
	        for (int i = 0; i < Tracknum; i++) {
	            TrackSequence[i] = sc.nextInt();
	        }
			System.out.println("磁道序列为" + Arrays.toString(TrackSequence));
			
			System.out.println("请选择算法 1.FCFS 2.SSTF 3.SCAN  0.退出");
			int k = sc.nextInt();
			switch (k) {
			case 1:
				FCFS(TrackSequence);
				break;
			case 2:
				SSTF(TrackSequence);
				break;
			case 3:
				SCAN(TrackSequence);
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("输的不对");
			}
		}
	}
}