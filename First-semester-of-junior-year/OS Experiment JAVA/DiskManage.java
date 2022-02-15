package 操作系统实验;

import java.util.Scanner;
import java.util.Arrays;

/*
 * 磁盘调度FCFS、SSTF、SCAN
 * @author 张时贰
 * @data 2021年12月7日
 */

public class DiskManage {
	public static void main(String[] args) {
		DiskManage A = new DiskManage();
		int choice = -1;
		int[] cidao = { 55, 72, 100, 88, 93, 66 }; // 用来存磁道号
		// int[] cidao = new int[6];
		int i = -1; // 全局变量i
		int[] str = new int[6];
		System.out.println("随机产生一个磁道序列（包含6个磁道）\n");


		System.out.println("随机得到的磁道序列为：");

		for (i = 0; i < 6; i++) {
			System.out.print(cidao[i] + "  ");
		}
		System.out.println();
		while (true) {
			System.out.println();
			System.out.println("----------------------------------------------");
			System.out.println("|                 增福系统菜单                                 |");
			System.out.println("|                                            |");
			System.out.println("|               1. 先来先服务                                   |");
			System.out.println("|               2. 最短寻道时间优先                        |");
			System.out.println("|               3. 扫描（电梯）调度                        |");
			System.out.println("|               4. 循环扫描                                      |");
			System.out.println("|               5. 退出增福菜单                               |");
			System.out.println("----------------------------------------------");
			System.out.print("请选择算法：");
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			if (choice == 5) {
				System.err.println("已成功退出增福程序！");
				break;
			}
			switch (choice) {
			case 1: // 使用FCFS算法
				A.FCFS(cidao, 6);
				break;
			case 2: // 使用SSTF算法
				A.SSTF(cidao, 6);
				break;
			case 3: // 使用SCAN算法
				A.SCAN(cidao, 6);
				break;
			case 4: // 使用CSCAN算法
				A.CSCAN(cidao, 6);
				break;
			default:
				System.err.println("请输入1-5之间的数字");
			}
		}
	}

	/**
	 * 先来先服务算法
	 * 
	 * @param cidao 要访问的磁道数组
	 * @param m
	 */
	void FCFS(int cidao[], int m) // 磁道号数组，个数为m
	{
		int now = -1; // 当前磁道号
		int sum = 0; // 总寻道长度
		int i;
		float ave = 0.0f; // 平均寻道长度
		System.out.println("磁盘请求序列为：");
		for (i = 0; i < m; i++) // 按先来先服务的策略输出磁盘请求序列
		{
			System.out.print(cidao[i] + "  ");
		}
		System.out.println();
		System.out.print("请输入当前的磁道号：");
		Scanner input = new Scanner(System.in);
		now = input.nextInt();
		sum += Math.abs(cidao[0] - now);// 求当前输入磁道和第一要访问磁道的距离
		System.out.println("磁盘扫描序列为：");
		System.out.print(now);
		for (i = 0; i < m; i++) // 输出磁盘扫描序列
		{
			System.out.print(" --> " + cidao[i]);
		}
		for (i = 0; i < m - 1; i++) // 求平均寻道长度
		{
			sum += Math.abs(cidao[i + 1] - cidao[i]);
			ave = (float) sum / m;
		}
		System.out.println();
		System.out.println("平均寻道长度：" + ave);
	}

	/**
	 * 有三种情况：前提是先按升序排序好 1、若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
	 * 2、若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务，与第一种同理 3、若当前磁道号大于请求序列中最小者且小于最大者
	 * 最短寻到时间优先算法
	 * 
	 * @param cidao 要访问的磁道数组
	 * @param m
	 */
	void SSTF(int cidao[], int m) {
		int k = 1; // 用于第三种情况找到当前磁道号在排序好序列的位置
		int now = -1, left = -1, right = -1;
		int i, j, sum = 0;

		float ave = 0.0f; // 用来存平均寻道长度
		Arrays.sort(cidao); // 调用快速排序算法升序处理
		System.out.println("排序后：");
		for (int n = 0; n < cidao.length; n++) {
			System.out.print(cidao[n] + "  ");
		}
		System.out.println();
		System.out.print("请输入当前的磁道号：");
		Scanner input = new Scanner(System.in);
		now = input.nextInt();
		// 注：柱面的编号是从外到内从0开始编号的
		if (cidao[m - 1] <= now) // 第一种种情况：若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
		{ // 比如：排序后 55 66 72 88 93 100 若当前磁道大于100就直接从100开始予以各请求服务
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = m - 1; i >= 0; i--)
				System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0];
		}

		if (cidao[0] >= now) // 第二种情况：若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务，与第一种同理
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = 0; i < m; i++)
				System.out.print(" --> " + cidao[i]);
			sum = cidao[m - 1] - now;
		}

		if (now > cidao[0] && now < cidao[m - 1]) // 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者
		{
			System.out.println("磁盘扫描序列为：");

			while (cidao[k] < now) // 确定当前磁道在已排的序列中的位置
			{
				k++; // k从1开始找
				// 例如：55 66 72 88 93 100 若当前磁道号为90 直到找到93才会跳出循环，此时k=4
			}
			left = k - 1; // 表示此时位置的左边逻辑下标
			right = k; // 表示此时位置的右边逻辑下表

			System.out.print(now);
			while ((left >= 0) && (right < m)) // 当前磁道在请求序列范围内

			{
				if ((now - cidao[left]) <= (cidao[right] - now)) // 选择与当前磁道最近的请求给予服务，
				{ // 例如90和88比较在和93比较看谁距离短就先给哪个服务
					System.out.print(" --> " + cidao[left]); // 这是左边距离近的情况
					sum += now - cidao[left];
					now = cidao[left];
					left = left - 1;
				} else { // System.out.print(now);
					System.out.print(" --> " + cidao[right]); // 这是右边距离近的情况
					sum += cidao[right] - now;
					now = cidao[right];
					right = right + 1;
				}
			}

			if (left == -1) // 磁头移动到序列的最小号，返回内侧扫描仍未扫描的磁道
			{
				for (j = right; j < m; j++) {
					System.out.print("--> " + cidao[j]);
				}
				sum += cidao[m - 1] - cidao[0];
			} else // 磁头移动到序列的最大号，返回外侧扫描仍未扫描的磁道
			{
				for (j = left; j >= 0; j--) {
					System.out.print("--> " + cidao[j]);// 例如55 66 72 88 93 100假设当前磁道90
				} // 则按前面的算法会有90-->88-->93-->100,此时到100时right+1=6跳出循环
				sum += cidao[m - 1] - cidao[0]; // 来到了else这个分支，把磁头转到之前left的位置循环输出
			} // 那个sum直接等于最后一个减去第一个，因为他总是会扫描到最外侧的磁道号
		} // 和（100-72）+（72-66）+（66-55）= 100-55 = 45 是一样的

		ave = sum / (float) (m);
		System.out.println();
		System.out.println("平均寻道长度： " + ave);
	}

	/**
	 * 这个算法和最短寻道差不多一样的，先排序，也有三种情况，但是多了一个选择移动臂的方向 1、若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
	 * 2、若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务 3、第三种情况：若当前磁道号大于请求序列中最小者且小于最大者 扫描算法(电梯算法)
	 * 
	 * @param cidao 用来存磁道号
	 * @param m
	 */
	void SCAN(int cidao[], int m) // 先要给出当前磁道号和移动臂的移动方向
	{
		int k = 1;
		int now, left = -1, right = -1, choice = -1;
		int i, j, sum = 0;
		float ave = 0.0f;
		Arrays.sort(cidao);
		System.out.println("排序后：");

		for (int n = 0; n < cidao.length; n++) {
			System.out.print(cidao[n] + "  ");
		}
		System.out.println();
		System.out.print("请输入当前的磁道号：");
		Scanner input = new Scanner(System.in);
		now = input.nextInt();
		if (cidao[m - 1] <= now) // 第一种情况：若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = m - 1; i >= 0; i--)
				System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0]; // 反正都要扫描到最外侧，直接减最小的那个得的距离和一个一个减在相加是等价的
		}
		if (cidao[0] >= now) // 第二种情况：若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = 0; i < m; i++)
				System.out.print(" --> " + cidao[i]);
			sum = cidao[m - 1] - now; // 同理
		}
		if (now > cidao[0] && now < cidao[m - 1]) // 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者
		{
			while (cidao[k] < now) // 和SSTF算法一样先找到当前寻道号的位置
			{
				k++;
			}
			left = k - 1;
			right = k;
			System.out.println("请输入当前移动臂的移动的方向 (1 表示向内 ，0表示向外) : ");
			Scanner input2 = new Scanner(System.in);
			choice = input2.nextInt();
			if (choice == 0) // 选择移动臂方向向外，则先向外扫描
			{
				System.out.println("磁盘扫描序列为：");
				System.out.print(now);
				for (j = left; j >= 0; j--) // 往磁道号小的方向扫描，即向外扫描
				{
					System.out.print(" --> " + cidao[j]);
				}
				for (j = right; j < m; j++) // 磁头移动到最小号，则改变方向向内扫描未扫描的磁道
				{
					System.out.print(" --> " + cidao[j]);
				}
				sum = now - 2 * cidao[0] + cidao[m - 1]; // 还是拿55 66 72 88 93 100举例子，当前磁道号是90，那么90先会往内扫描，扫描到最小号
			} // 然后在转回到了right=93处，又再一次经过了now,然后向右扫描到最大号
			// 所以可以这么算sum =(now-cidao[0])*2 + (cidao[m-1]-now)

			else // 选择移动臂方向向外，则先向外扫描
			{
				System.out.println("磁盘扫描序列为：");
				System.out.print(now);
				for (j = right; j < m; j++) {
					System.out.print(" --> " + cidao[j]);
				}
				for (j = left; j >= 0; j--) // 磁头移动到最大号，则改变方向向外扫描未扫描的磁道
				{
					System.out.print(" --> " + cidao[j]);
				}
				sum = -now - cidao[0] + 2 * cidao[m - 1]; // 同理sum =(cidao[m-1]-now)*2 + (now - cidao[0])
			}
		}
		ave = sum / (float) (m);
		System.out.println();
		System.out.print("平均寻道长度： " + ave);
	}

	/**
	 * 这个也要先排序，这里是升序，循环扫描也有三种情况 1、若当前磁道号大于请求序列中最大者，则直接将移动臂移动到最小号磁道依次向内给予各请求服务
	 * 2、若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务,就和SSTF一样了 3、若当前磁道号大于请求序列中最小者且小于最大者
	 * 循环扫描算法(即单向扫描，没有访问时磁头不动，若访问到最小号则直接跳到最大号往最小号单向扫描，反之一样。)
	 * 
	 * @param cidao //存的是磁道号
	 * @param m
	 */
	void CSCAN(int cidao[], int m) // 我默认是扫描方向是先向磁道号变小的方向进行，即从内到外
	{
		int k = 1;
		int now = -1, left = -1, right = -1;
		int i, j, sum = 0;
		float ave;
		Arrays.sort(cidao);
		System.out.println("排序后：");
		for (int n = 0; n < cidao.length; n++) {
			System.out.print(cidao[n] + "  ");
		}
		System.out.println();
		System.out.print("请输入当前的磁道号：");
		Scanner input = new Scanner(System.in);
		now = input.nextInt();
		if (cidao[m - 1] <= now) // 第一种情况：若当前磁道号大于请求序列中最大者，则直接将移动臂移动到最小号磁道依次向内给予各请求服务
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = m - 1; i >= 0; i--)
				System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0]; // 和SCAN算法一样 55 66 72 88 93 100 当前磁道号为101
		}

		if (cidao[0] >= now) // 第二种情况：若当前磁道号小于请求序列中最小者，则移动磁道到最大位置由外向内依次给予各请求服务,
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = m - 1; i >= 0; i--)
				System.out.print(" --> " + cidao[i]);
			sum = 2 * cidao[m - 1] - now - cidao[0]; // 55 66 72 88 93 100 当前磁道号54
		} // sum = (cidao[m-1] - now) + (cidao[m-1]-cidao[0])

		if (now > cidao[0] && now < cidao[m - 1]) // 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者
		{
			System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			while (cidao[k] < now) // 同上，先找到当前磁道号的位置
			{
				k++;
			}
			left = k - 1;
			right = k;
			for (j = left; j >= 0; j--) // 因为是默认先扫描最小磁道号在移到最大磁道号扫描
			{ // 例：55 66 72 88 93 100 当前磁道号90
				System.out.print(" --> " + cidao[j]);
			}
			for (j = m - 1; j > left; j--) // 当扫描完最小号磁道，磁头直接移动到最大号磁道，再向外扫描未扫描的磁道
			{
				System.out.print(" --> " + cidao[j]);
			}
			sum = 2 * cidao[m - 1] - cidao[right] + now - 2 * cidao[0];// sum=(now-cidao[0])+(cidao[m-1]-cidao[0])+(cidao[m-1]-cidao[right])
		}
		ave = sum / (float) (m);
		System.out.println();
		System.out.println("平均寻道长度： " + ave);
	}

}
