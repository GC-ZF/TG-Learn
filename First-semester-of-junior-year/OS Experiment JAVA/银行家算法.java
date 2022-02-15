package 操作系统实验;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 银行家算法
 * @author 张时贰
 * @data 2021年12月14日
 */
public class 银行家算法 {
	// 首先定义全局变量
	static int resourceNum = 3; // 初始资源种类数
	static int processNum = 5; // 初始进程数
	static int[] available = new int[] { 3, 3, 2 }; // 可用（剩余）资源j的数量
	static int[][] maxRequest = new int[][] { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } }; // 进程i对资源j的最大需求量
	static int[][] allocation = new int[][] { { 0, 1, 0 }, { 2, 0, 0 }, { 3, 0, 2 }, { 2, 1, 1 }, { 0, 0, 2 } }; // 进程i已分配资源j的数量
	static int[][] need = new int[][] { { 7, 4, 3 }, { 1, 2, 2 }, { 6, 0, 0 }, { 0, 1, 1 }, { 4, 3, 1 } }; // 进程i还需资源j的数量
	static boolean[] finish = new boolean[] { false, false, false, false, false }; // 是否安全
	static int[] safeSeries = new int[] { 0, 0, 0, 0, 0 }; // 安全序列号
	static int[] request = new int[] { 0, 0, 0 }; // 进程请求资源j的数量
	static int num; // 资源数量计数
	Scanner sc = new Scanner(System.in);

	// 打印：初始资源、进程信息
	public static void showInfo() {
		System.out.println("**************************************************************");
		System.out.println("当前系统各类资源剩余:");
		System.out.println(Arrays.toString(available));

		System.out.printf("\n当前系统资源情况：\n");
		System.out.printf(" PID\t\t maxRequest\tAllocation\t Need\n");

		for (int i = 0; i < processNum; i++) {
			System.out.printf(" P%d\t\t", i);
			for (int j = 0; j < resourceNum; j++) {
				System.out.printf("%2d", maxRequest[i][j]);
			}
			System.out.printf("\t\t");
			for (int j = 0; j < resourceNum; j++) {
				System.out.printf("%2d", allocation[i][j]);
			}
			System.out.printf("\t\t");
			for (int j = 0; j < resourceNum; j++) {
				System.out.printf("%2d", need[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 打印安全检查信息
	 * 
	 * @param work 工作向量，表示系统可提供给进程继续运行所需的各类资源数目
	 * @param i    第i个进程
	 */
	public static void safeInfo(int[] work, int i) {
		int j;
		System.out.printf(" P%d\t\t", i);
		for (j = 0; j < resourceNum; j++) {
			System.out.printf("%2d", work[j]);
		}
		System.out.printf("\t\t");
		for (j = 0; j < resourceNum; j++) {
			System.out.printf("%2d", allocation[i][j]);
		}
		System.out.printf("\t\t");
		for (j = 0; j < resourceNum; j++) {
			System.out.printf("%2d", need[i][j]);
		}
		System.out.printf("\t\t");
		for (j = 0; j < resourceNum; j++) {
			System.out.printf("%2d", allocation[i][j] + work[j]);
		}
		System.out.println();
	}

	/**
	 * 判断一个进程的资源数是否全为0，全为0，则说明已执行完毕，可释放其持有的资源
	 * 
	 * @param i 第i个进程
	 * @return
	 */
	static boolean isAllZero(int i) {
		num = 0;
		for (int j = 0; j < resourceNum; j++) {
			if (need[i][j] == 0) { // 进程i已完成
				num++;
			}
		}
		if (num == resourceNum) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 安全性检查
	 * 
	 * @return
	 */
	static boolean Safe() {
		int safeIndex = 0;
		int allFinishNum = 0;
		// 工作向量，表示系统可提供给进程继续运行所需的各类资源数目
		int[] work = new int[resourceNum];
		int r = 0; // 第r个进程
		int temp = 0;
		int pNum = 0;

		for (int j = 0; j < resourceNum; j++) {
			work[j] = available[j];
		}

		// 标识进程完成（true）或未完成（false）
		for (int i = 0; i < processNum; i++) {
			if (isAllZero(i)) {
				finish[i] = true;
				allFinishNum++;
			} else {
				finish[i] = false;
			}
		}

		// 预分配开始
		while (allFinishNum != processNum) { // 进程并未全部完成
			num = 0; // 进程r，所需资源种类数
			for (int j = 0; j < resourceNum; j++) {
				// 当某进程未完成，且其资源需求量小于当前系统中该资源的剩余量时
				if (need[r][j] <= work[j] && finish[r] == false) {
					num++;
				}
			}
			if (num == resourceNum) { // 当num等于resourceNum时，说明 进程r 的三种资源情况都满足上述if条件，即都可以分配成功
				allFinishNum++;
				safeInfo(work, r);
				safeSeries[safeIndex] = r;
				safeIndex++;
				finish[r] = true;
				for (int j = 0; j < resourceNum; j++) { // 进程执行完毕，释放 进程r 所持有的资源
					work[j] = work[j] + allocation[r][j];
				}
			}
			r++;
			if (r >= processNum) {
				r = r % processNum;
				if (temp == allFinishNum) {
					break;
				}
				temp = allFinishNum;
			}
			pNum = allFinishNum;
		}

		// 判断系统是否安全
		for (int i = 0; i < processNum; i++) {
			if (finish[i] == false) {
				System.out.printf("\n当前系统不安全！\n\n");
				return false;
			}
		}

		// 打印安全序列
		System.out.printf("\n当前系统安全~\n\n安全序列为：");
		for (int i = 0; i < processNum; i++) {
			if (isAllZero(i)) {
				pNum--;
			}
		}
		for (int i = 0; i < pNum; i++) {
			System.out.printf("%d ", safeSeries[i]);
		}
		return true;
	}

	public static void main(String[] args) {
		int curProcess = 0;
		showInfo();
		System.out.printf("\n\n系统安全情况分析：\n");
		System.out.printf(" PID\t\t Work\t\t Allocation\t Need\t\t Work+Allocation\n");
		boolean safe = Safe();
		Scanner scanner = new Scanner(System.in);
		while (safe) {
			// 用户输入或预设系统资源分配合理才继续进行进程分配工作
			do {
				if (curProcess >= processNum || curProcess < 0) { // 注：进程标识序列从0开始
					System.out.printf("\n请不要输入超出进程数量的值或其他字符：\n");
				}
				System.out.printf("\n**************************************************************\n");
				System.out.printf("\n输入要分配的进程：");
				curProcess = scanner.nextInt(); // 接收用户输入的进程号
				System.out.println();
			} while (curProcess >= processNum || curProcess < 0);

			// 用户输入或预设系统资源分配合理才继续进行进程分配工作
			for (int j = 0; j < resourceNum; j++) {
				int requestJ = 0;
				do {
					if (requestJ < 0 || requestJ > available[j]) {
						System.out.printf("\n请不要输入超出进程所需资源数量或输入为负数或其他字符：\n");
					}
					System.out.printf("请输入要分配给进程 P%d 的第 %d 类资源：", curProcess, j + 1);
					requestJ = scanner.nextInt();
					request[j] = requestJ;
				} while (requestJ < 0 || requestJ > available[j]);
			}

			// 判断用户输入的分配是否合理，合理则进行预分配
			num = 0;
			for (int j = 0; j < resourceNum; j++) {
				if (request[j] <= need[curProcess][j] && request[j] <= available[j]) {
					num++;
				} else {
					System.out.printf("\n发生错误！可能原因如下：\n(1)您请求分配的资源可能大于该进程的某些资源的最大需要！\n(2)系统所剩的资源已经不足了！\n");
					break;
				}
			}
			if (num == resourceNum) {
				num = 0;
				for (int j = 0; j < resourceNum; j++) {
					// 更新资源信息
					available[j] = available[j] - request[j];
					allocation[curProcess][j] = allocation[curProcess][j] + request[j];
					need[curProcess][j] = need[curProcess][j] - request[j];
					if (need[curProcess][j] == 0) { // 此进程的资源j已满足所需
						num++;
					}
				}

				// 如果分配以后，该进程对所有所需资源需求为0，那么视为完成，释放此进程
				if (num == resourceNum) {
					for (int j = 0; j < resourceNum; j++) {
						available[j] = available[j] + allocation[curProcess][j];
					}
					System.out.printf("\n\n本次分配进程 P%d 完成,该进程占用资源全部释放完毕！\n", curProcess);
				} else {
					System.out.printf("\n\n本次分配进程 P%d 未完成！\n", curProcess);
				}

				showInfo();
				System.out.printf("\n系统安全情况分析\n");
				System.out.printf(" PID\t\t Work\t\tAllocation\t Need\t\tWork+Allocation\n");

				// 预分配完成以后，判断该系统是否安全，若安全，则可继续进行分配，若不安全，将已经分配的资源换回来
				if (!Safe()) {
					for (int j = 0; j < resourceNum; j++) {
						available[j] = available[j] + request[j];
						allocation[curProcess][j] = allocation[curProcess][j] - request[j];
						need[curProcess][j] = need[curProcess][j] + request[j];
					}
					System.out.printf("资源不足，等待中...\n\n分配失败！\n");
				}
			}
		}
	}
}
