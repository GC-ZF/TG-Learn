package 操作系统实验;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/*
 * 磁盘调度FCFS、SSTF、SCAN
 * @author 张时贰
 * @data 2021年12月7日
 */
public class 磁盘调度算法 {
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
		System.out.println("磁道开始位置为" + list[0]);

		int num = 0;// 变量num记录寻道距离
		for (int j = 0; j < list.length - 1; j++) {
			num += Math.abs(list[j + 1] - list[j]);
		}
		System.out.println("磁盘扫描序列为:" + Arrays.toString(list));
		System.out.println("寻道次数:" + num);
		double w = ((float) num / (float) list.length);
		System.out.println("平均寻道距离:" + w);
	}

	// 最短路径算法SSTF,即访问下一个磁道前，先计算一次当前磁头到访问各个磁道的距离
	private static void SSTF(int[] list) {
		int[] list1=new int[list.length];//因为下面修改了list，再次运行算法3时，list数组已经被修改，所以这里把值传给list1
		System.arraycopy(list,0, list1, 0, list.length);
		int i = list1[0];// 变量i记录当前磁头位置
		System.out.println("磁道开始位置为" + list1[0]);
		int num = 0;// num记录总寻道距离
		int[] k = new int[list1.length];// k数组记录访问的每一个磁道到磁头距离
		int[] templist = new int[list1.length];// 将结果保存在templist[]中

		for (int a = 0; a < list1.length; a++) {
			// j循环遍历出每一个访问的磁道到当前磁头的距离保存在k数组中
			for (int j = 0; j < list1.length; j++) {
				k[j] = Math.abs(list1[j] - i);
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
					i = list1[t];// 变量i记录磁头位置
					templist[a] = i;// 将结果存入templist数组中
					list1[t] = 999999;// 将第g个磁道号设为999999，可以理解为权重设为99999，这样他在下一次遍历，在k数组中总是最大
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

	/*
	 * 扫描算法SCAN 磁盘的0磁道在最外圈 扫描算法又叫电梯算法，即按从小到大或从大到小的顺序去依次访问磁道。
	 * 所以这里分两种情况，1.寻道从内向外(从大到小) 2.寻道从外向内(从小到大)
	 * 
	 * 
	 * 1.若当前磁头位置大于请求序列中最大者 2.若当前磁头位置小于请求序列中最小者 3.若当前磁道号大于请求序列中最小者且小于最大者
	 * 这里用同一个函数去解决三种情况
	 */
	private static void SCAN(int[] list) {
		int[] list1=new int[list.length];//因为下面修改了list，再次运行算法3时，list数组已经被修改，所以这里把值传给list1
		System.arraycopy(list,0, list1, 0, list.length);
		Scanner sc = new Scanner(System.in);
		int k = list1[0];// 变量i记录当前磁头位置
		int tempk1 = 0;
		int tempk2 = 0;
		int flag=1;
		int num = 0;// num记录总寻道距离
		int[] templist = new int[list1.length];// 将访问顺序存入templist中
		System.out.println("磁道开始位置为:" + list1[0]);
		//while中先计算出磁道访问的顺序
		while (true) {
			System.out.println("1.磁头从外向内  2.磁头从内向外");
			int choice = sc.nextInt();
			
			if (choice == 1) {
				flag=5;

				SortA(list1);// 从小到大排序
				// 找出重排序后，k在数组中的位置，templist记录区间[0,list.length-tempk1]
				for (int i = 0; i < list1.length; i++) {
					if (k == list1[i])
						tempk1 = i;
				}
				System.arraycopy(list1, tempk1, templist, 0, list1.length - tempk1);

				SortB(list1);// 从大到小
				// 找出重排序后，k在数组中的位置,templist记录区间[tempk2+1,list.length]
				for (int i = 0; i < list1.length; i++) {
					if (k == list1[i])
						tempk2 = i;
				}
				System.arraycopy(list1, tempk2 + 1, templist, tempk2 + 1, list1.length - tempk2 - 1);
				break;
			}

			else if (choice == 2) {
				flag=5;

				SortB(list1);
				// 找出重排序后，k在数组中的位置，templist记录区间[0,list.length-tempk1]
				for (int i = 0; i < list1.length; i++) {
					if (k == list1[i])
						tempk1 = i;
				}
				System.arraycopy(list1, tempk1, templist, 0, list1.length - tempk1);

				SortA(list1);// 从小到大
				// 找出重排序后，k在数组中的位置,templist记录区间[tempk2+1,list.length]
				for (int i = 0; i < list1.length; i++) {
					if (k == list1[i])
						tempk2 = i;
				}
				System.arraycopy(list1, tempk2 + 1, templist, tempk2 + 1, list1.length - tempk2 - 1);
				break;
			}

			else if (flag==5)
				break;
			
			else
				System.out.println("输入错误请重新输入");
		}
		//计算寻道距离
		for (int j = 0; j < templist.length - 1; j++) {
			num += Math.abs(templist[j + 1] - templist[j]);
		}

		System.out.println("磁盘扫描序列为:" + Arrays.toString(templist));
		System.out.println("寻道次数" + num);
		double w = ((float) num / (float) list.length);
		System.out.println("平均寻道距离" + w);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Tracknum;// 磁道数
		System.out.println("请输入磁道数量Tracknum");
		Scanner sc = new Scanner(System.in);
		Tracknum = sc.nextInt();

		// 随机生成磁道序列
		Random r = new Random();
		int[] TrackSequence = new int[Tracknum];
		for (int i = 0; i < Tracknum; i++) {
			TrackSequence[i] = r.nextInt(100)+1;
		}
		System.out.println("磁道序列为" + Arrays.toString(TrackSequence));

		// 菜单
		while (true) {
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
