package 操作系统实验;

import java.util.Scanner;

public class 页面置换算法 {
	private static int MaxPage_num = 100;
	private static int[] PageView = new int[MaxPage_num];// 访问页面顺序
	private static int[][] Blocks = new int[MaxPage_num][MaxPage_num];// 物理块
	private static int[] PageCount = new int[MaxPage_num];// 记录权重

	private static int PageNum;// 访问页面个数
	private static int BlockNum;// 物理块个数

//	private static int temp = 0;
//	private static int flag = 0;
	private static int MissingPageNum;// 缺页个数
	private static double MissingPageRate;// 缺页率

	private static boolean found;// 判断是否找到

	private static int j;
	private static int i;
	private static int k;

	private static int NULL = -1;// 缺页

	// 动态输入物理块、页面、访问页面顺序
	public static void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入页面个数");
		PageNum = sc.nextInt();
		System.out.println("请输入物理块个数");
		BlockNum = sc.nextInt();
		System.out.println("请输入页面访问顺序");
		for (int i = 0; i < PageNum; i++) {
			PageView[i] = sc.nextInt();
		}
	}

	// 输出结果
	public static void output() {
		// 输出访问页面
		System.out.print("访问页面:");
		for (i = 0; i < PageNum; i++) {
			System.out.print(PageView[i] + " ");
		}
		System.out.println();
		// 输出物理块
		System.out.println("物理块");
		for (int j = 0; j < BlockNum; j++) {
			for (int i = 0; i < PageNum; i++) {
			if (Blocks[i][j] == NULL)
					System.out.print("  ");
				else
					System.out.print(Blocks[i][j] + " ");
			}
			System.out.println(" ");
		}
		MissingPageRate = (double) MissingPageNum / PageNum;
		System.out.println();
		System.out.println("总页面数" + PageNum + "缺页次数" + MissingPageNum + "缺页率" + MissingPageRate + "%");
	}

	// 初始化物理块
	public static void original() {
		for (i = 0; i < PageNum; i++) {
			for (j = 0; j < BlockNum; j++) {
				Blocks[i][j] = NULL;
			}
		}
		MissingPageNum = 1;
	}

	// 先进先出
	public static void FIFO() {
		original();

		Blocks[0][0] = PageView[0];
		int temp = 0, flag = 0;// temp标记本列，flag标记上一列
		for (i = 0; i < PageNum; i++) {
			// 如果页已进入物理块跳出循环
			for (j = 0; j < BlockNum; j++) {
				if (PageView[i] == Blocks[flag][j]) {
					break;           
				}
			}

			//如果不存在
			if (j == BlockNum) {
				for (k = 0; k < BlockNum; k++) {
					if (Blocks[flag][k] == NULL) {
						break;
					} else {
						Blocks[i][k] = Blocks[flag][k];
					}
				}
				temp++;
				temp = temp % BlockNum;
				Blocks[i][temp] = PageView[i];
				MissingPageNum++;
				flag = i;
			} else {
				continue;
			}
		}

		output();
	}

	// 最佳置换
	public static void OPT() {
		original();

		Blocks[0][0] = PageView[0];
		int temp, flag = 0;

		for (i = 0; i < PageNum; i++) {
			// 不缺页
			for (j = 0; j < BlockNum; j++) {
				if (PageView[i] == Blocks[flag][j]) {
					break;
				}
			}
			if (j != BlockNum) { // 代表页面已经在内存块中不缺页
				continue;
			}
			// 缺页
			for (k = 0; k < BlockNum; k++) {
				if (Blocks[flag][k] == NULL) {
					break;
				} else {
					Blocks[i][k] = Blocks[flag][k];
				}
			}
			// 页面选择，满和不满
			// 缺页时物理块未满
			for (j = 0; j < BlockNum; j++) {
				if (Blocks[i][j] == NULL) {
					Blocks[i][j] = PageView[i];
					MissingPageNum++;
					flag = i;
					break;
				}
			}
			if (j != BlockNum) {
				continue;
			}

			// 缺页时内存块已满
			temp = 0;
			for (j = 0; j < BlockNum; j++) {
				// 向后遍历，寻找最久不访问
				for (k = i + 1; k < PageNum; k++) {
					// 寻找最长时间内不被访问的页面 k是内存页面序列下标 存进PageCount数组内
					if (Blocks[i][j] == PageView[k]) {
						PageCount[j] = k;//找到,记录权重
						break;
					}
				}
				// 剩下的页面没有和当前物理块一样的，将其权重设为最大值
				if (k == PageNum) {
					PageCount[j] = PageNum;
				}
			}

//			找出权重最大的块
			for(int a=1;a<BlockNum;a++) {
				if(PageCount[a]>PageCount[temp])
					temp=a;
			}

			Blocks[i][temp] = PageView[i];
			MissingPageNum++;
			flag = i;
		}
		output();
	}

	// 最近最久未使用
	public static void LRU() {
		original();

		Blocks[0][0] = PageView[0];
		PageCount[0] = 0;
		int temp = 0, flag = 0;// temp标记本列，flag标记上一列
		for (i = 0; i < PageNum; i++) {
			// 不缺页
			for (j = 0; j < BlockNum; j++) {
				if (PageView[i] == Blocks[flag][j]) {
					PageCount[j] = i;
					break;
				}
			}
			if (j != BlockNum)
				continue;

			//缺页
			for (k = 0; k < BlockNum; k++) {
				if (Blocks[flag][k] == NULL) {
					break;
				} else {
					Blocks[i][k] = Blocks[flag][k];
				}
			}
			//缺页时物理块有空余
			for (j = 0; j < BlockNum; j++) {
				if (Blocks[i][j] == NULL) {
					Blocks[i][j] = PageView[i];
					PageCount[j] = i;
					MissingPageNum++;
					flag = i;
					break;
				}
			}
			if (j != BlockNum) {
				continue;
			}
			//缺页时物理块没空余
			temp = 0;
			for (j = 0; j < BlockNum; j++) {
				if (PageCount[temp] > PageCount[j]) {
					temp = j;
				}
			}
			Blocks[i][temp] = PageView[i];
			PageCount[temp] = i;
			MissingPageNum++;
			flag = i;

			
		}
		output();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			input();
			System.out.println("1.先进先出 2.最佳置换算法 3.最近最久未使用 4.退出");
			int c = sc.nextInt();
			switch (c) {
			case 1:
				FIFO();
				break;
			case 2:
				OPT();
				break;
			case 3:
				LRU();
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("输入错误 重新输入");
				break;
			}
		}
	}

}

//12 3 
//4 3 2 1 4 3 5 4 3 2 1 5
