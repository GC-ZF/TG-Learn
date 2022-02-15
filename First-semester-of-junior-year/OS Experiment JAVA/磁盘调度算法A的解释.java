package 操作系统实验;

import java.util.Arrays;
import java.util.Scanner;

public class 磁盘调度算法A的解释 {
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] list = { 5, 2, 3, 8, 10, 12, 9 };// 7
		int[] templist = new int[list.length];
		int k = list[0];
		int tempk1 = 0;
		int tempk2 = 0;

		System.out.println("1.磁头从外向内  2.磁头从内向外(从大到小)");
		int choice = sc.nextInt();

		if (choice == 1) {
			System.out.println("排序前的list" + Arrays.toString(list));
			SortA(list);
			System.out.println("从小到大的list" + Arrays.toString(list));
			//找出重排序后，k在数组中的位置，templist记录区间[0,list.length-tempk1]
			for (int i = 0; i < list.length; i++) {
				if (k == list[i])
					tempk1 = i;
			}

			System.arraycopy(list, tempk1, templist, 0, list.length - tempk1);
			System.out.println("第一次的templist" + Arrays.toString(templist));

			SortB(list);
			System.out.println("从大到小的list" + Arrays.toString(list));
			//找出重排序后，k在数组中的位置,templist记录区间[tempk2+1,list.length]
			for (int i = 0; i < list.length; i++) {
				if (k == list[i])
					tempk2 = i;
			}
			System.arraycopy(list, tempk2 + 1, templist, tempk2 + 1, list.length - tempk2 - 1);
			System.out.println("第二次的templist" + Arrays.toString(templist));
		}
		else if(choice==2){
			System.out.println("排序前的list" + Arrays.toString(list));
			SortB(list);
			System.out.println("从大到小的list" + Arrays.toString(list));
			//找出重排序后，k在数组中的位置，templist记录区间[0,list.length-tempk1]
			for (int i = 0; i < list.length; i++) {
				if (k == list[i])
					tempk1 = i;
			}

			System.arraycopy(list, tempk1, templist, 0, list.length - tempk1);
			System.out.println("第一次的templist" + Arrays.toString(templist));

			SortA(list);//从小到大
			System.out.println("从小到大的list" + Arrays.toString(list));

			for (int i = 0; i < list.length; i++) {
				if (k == list[i])
					tempk2 = i;
			}
			System.arraycopy(list, tempk2 + 1, templist, tempk2 + 1, list.length - tempk2 - 1);
			System.out.println("第二次的templist" + Arrays.toString(templist));
		}
	}
}
