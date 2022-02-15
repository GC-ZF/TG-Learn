package 操作系统实验;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 模拟调度时间片
 * @author 张时贰
 * @data 2021年12月16日
 */
public class 模拟调度时间片 {
	public static void main(String[] args) {
		// 定义五个进程，attribure表示到达时间，服务时间
		attribute[] ab = { 
				new attribute(1, 5),
				new attribute(8, 5),
				new attribute(10, 5),
				new attribute(16, 15),
				new attribute(14, 25) };
		utils.runattribute(ab);// 选择排序方式
	}
}

//为进程定义一个对象
class attribute {
	static int runname = -1;// 给当前进度起名字
	int Run_time;// 需要服务时间
	int input_time;// 输入时间
	int name = -1;// 进程序号

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public attribute() {
	}

	public attribute(int input_time, int run_time) {
		this.Run_time = run_time;
		this.input_time = input_time;
		runname++;
		this.name = runname + 0;

	}

	public int getRun_time() {
		return Run_time;
	}

	public void setRun_time(int run_time) {
		Run_time = run_time;
	}

	public int getInput_time() {
		return input_time;
	}

	public void setInput_time(int input_time) {
		this.input_time = input_time;
	}
}

class utils {
	// 输出结果OutputAttribute
	public static void OutputAttribute(attribute[] ab) {
		int time = ab[0].input_time;
		int TimeSum[] = new int[ab.length];// 作业总周转时间
		double DTimeSum[] = new double[ab.length];// 带权周转总时间
		System.out.print("进程ID ");
		System.out.print("到达时间 ");
		System.out.print("服务时间 ");
		System.out.print("完成时间 ");
		System.out.print("周转时间  ");
		System.out.print("带权周转时间 \n");
		for (int i = 0; i < ab.length; i++) {
			if (ab[i].input_time > time) {
				time = ab[i].input_time;
			}

			System.out.print(ab[i].name + "\t  ");// 进程ID
			System.out.print(ab[i].input_time + "\t    ");// 到达时间
			time += ab[i].Run_time;
			System.out.print(ab[i].Run_time + "\t    ");// 服务时间
			System.out.print(time + "\t   ");// 完成时间
			TimeSum[i] = (time - ab[i].input_time);
			System.out.print(TimeSum[i] + "\t   ");// 周转时间
			DTimeSum[i] = (double)(TimeSum[i] / ab[i].Run_time);
			
			System.out.print(DTimeSum[i]);// 带权周转时间
			System.out.println();
		}
		System.out.println("周转总时间:" + Arrays.stream(TimeSum).sum());
		System.out.println("平均周转时间:" + (double)Arrays.stream(TimeSum).sum()/ab.length);
		System.out.println("带权周转总时间:" + Arrays.stream(DTimeSum).sum());
		System.out.println("平均带权周转时间:" + Arrays.stream(DTimeSum).sum()/ab.length);
	}

	public static void runattribute(attribute[] ab) {// 运行调用
		System.out.println("请选择排序方式");
		Scanner in = new Scanner(System.in);
		int n = -1;
		while (n != 0) {
			System.out.println("1.先到先服务  2.最短进程优先 3.时间片转轮 4.抢占式高优先权 0退出");
			n = in.nextInt();
			switch (n) {
			case 1:
				ab = InputTimesort(ab);
				OutputAttribute(ab);
				break;
			case 2:
				ab = Short_jobs_take_precedence(ab, 0, 0);
				OutputAttribute(ab);
				break;
			case 3:
				System.out.println("请输入时间片时间");
				n = in.nextInt();
				Time_slice_rotation(ab, n);
				break;
			case 4:
				ab = priorityTimesort(ab, 0, 0);
				OutputAttribute(ab);
				break;
			}
		}
		return;
	}

	// 先到先服务，对输入时间排序(冒泡排序)
	private static attribute[] InputTimesort(attribute[] ab) {
		for (int i = 0; i < ab.length; i++) {
			int min = i;
			for (int J = i; J < ab.length; J++) {
				if (ab[J].getInput_time() < ab[i].getInput_time()) {
					min = J;
				}
			}
			swat(ab, i, min);
		}
		return ab;
	}
	
	//最短进程优先
	private static attribute[] Short_jobs_take_precedence(attribute[] ab, int i, int Begin) {
		int min_Run_time = -1;
		int min_input_time = i;
		int index = -1;
		// 获取运行时间之前的运行时间最短的
		for (int j = Begin; j < ab.length; j++) {
			if (ab[j].input_time <= i) {
				if (min_Run_time == -1) {
					index = j;
					min_Run_time = ab[j].Run_time;
				} else {
					if (min_Run_time > ab[j].Run_time) {
						index = j;
						min_Run_time = ab[j].Run_time;
					}
				}
			}
		}
		if (index == -1) {
			min_Run_time = ab[Begin].Run_time;
			min_input_time = ab[Begin].input_time;
			index = Begin;
			for (int j = Begin; j < ab.length; j++) {
				if (ab[j].input_time < min_input_time) {
					min_input_time = ab[j].input_time;
					index = j;
					min_Run_time = ab[j].Run_time;
				} else {
					if (ab[j].input_time == min_input_time) {
						if (min_Run_time < ab[j].Run_time) {
							min_input_time = ab[j].input_time;
							index = j;
							min_Run_time = ab[j].Run_time;
						}
					}
				}
			}
		}
		swat(ab, Begin, index);
		if (Begin + 1 == ab.length) {
			return ab;
		}
		Short_jobs_take_precedence(ab, (min_input_time > i ? min_input_time : i) + min_Run_time, Begin + 1);
		return ab;
	}
	// 交换位置
	public static void swat(attribute[] ab, int i, int index) {
		attribute a = ab[i];
		ab[i] = ab[index];
		ab[index] = a;
	}

	//时间片转轮
	private static attribute[] Time_slice_rotation(attribute[] ab, int n) {
		int[] Alltime = new int[ab.length];// 完成需要时间
		for (int j = 0; j < ab.length; j++) {
			Alltime[j] = ab[j].Run_time;
		}
		int[] complete_Alltime = new int[ab.length];// 已完成时间
		int min_input_time = -1;
		int index;
		int complete = 0;// 完成个数
		int N = 1;
		while (complete < ab.length) {
			index = -1;
			for (int i = 0; i < ab.length; i++) {
				if (ab[i].input_time >= N * n) {
					continue;
				}
				if (Alltime[i] <= complete_Alltime[i]) {
					continue;
				}
				if (index == -1) {
					index = i;
					min_input_time = ab[i].input_time;
				}
				if (ab[i].input_time < min_input_time) {
					index = i;
				}
			}
			if (index != -1) {
				if (ab[index].input_time > N * n - n) {
					complete_Alltime[index] = N * n - ab[index].input_time;
				} else {
					complete_Alltime[index] += n;
				}

				if (Alltime[index] <= complete_Alltime[index]) {
					complete++;
					complete_Alltime[index] = Alltime[index];
				}
				System.out.println("第" + N + "个时间片 运行进程" + ab[index].name + "进度为 " + complete_Alltime[index] + "/"
						+ Alltime[index]);
			} else {
				System.out.println("第" + N + "个时间片 暂无进程进入");

			}
			N++;
		}
		return null;
	}
		
	// 抢占式高优先权
	private static attribute[] priorityTimesort(attribute[] ab, int i, int Begin) {
		int min_Run_time = -1;
		int min_input_time = i;
		int min_priority = 0;
		int index = -1;
		// 获取运行时间之前的优先级最高的
		for (int j = Begin; j < ab.length; j++) {
			if (ab[j].input_time <= min_input_time) {
				if (min_Run_time == -1) {
					index = j;
					min_Run_time = ab[j].Run_time;
					min_priority = (ab[j].input_time - i) / ab[j].Run_time;
				} else {
					if (min_priority > (ab[j].input_time - i) / ab[j].Run_time) {// 优先级比我大
						index = j;
						min_Run_time = ab[j].Run_time;
						min_priority = (ab[j].input_time - i) / ab[j].Run_time;
					}
				}
			}
		}
		if (index == -1) {
			min_Run_time = ab[Begin].Run_time;
			min_input_time = ab[Begin].input_time;
			index = Begin;
			for (int j = Begin; j < ab.length; j++) {
				if (ab[j].input_time < min_input_time) {// 运行时间在我之前直接换
					min_input_time = ab[j].input_time;
					index = j;
					min_Run_time = ab[j].Run_time;
				} else {
					if (ab[j].input_time == min_input_time) {
						if (min_input_time < ab[j].Run_time) {// 运行时间小直接换
							min_input_time = ab[j].input_time;
							index = j;
							min_Run_time = ab[j].Run_time;
						}
					}
				}
			}
		}
		swat(ab, Begin, index);
		if (Begin + 1 == ab.length) {
			return ab;
		}
		priorityTimesort(ab, (min_input_time > i ? min_input_time : i) + min_Run_time, Begin + 1);
		return ab;
	}
}
