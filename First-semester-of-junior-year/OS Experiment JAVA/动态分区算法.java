package 操作系统实验;

import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

/*
 * 动态分区算法 FF NF BF WF
 * @author CSDN 张时贰
 * @data 2021年12月9日
 */
public class 动态分区算法 {
	public static void main(String[] args) {
		Memory my = new Memory();// 新建一个内存对象
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("请输入要分配内存还是要释放内存");
			System.out.println("1.分配内存 2.释放内存 3.展示分区状况");
			int n = sc.nextInt();
			switch (n) {
			case 1: {
				System.out.println("请输入要分配的内存大小");
				int size = sc.nextInt();
				my.allocation(size);// 调用对象Memory中内存分配函数allocation
				my.showZones();// 展示分区状况
				break;
			}
			case 2: {
				System.out.println("输入想要释放内存的分区号");
				int id = sc.nextInt();
				my.collection(id);// 调用对象Memory中内存释放函数collection
				break;
			}
			case 3: {
				my.showZones();// 展示分区状况
				break;
			}
			default:
				System.out.println("输的不对");
			}
		}
	}
}

//新建一个内存类
class Memory {
	private int size;// 内存大小
	private static final int MIN_SIZE = 2;// 最小剩余分区大小
	private LinkedList<Zone> zones;// 内存分区
	private int pointer;// 上次分配的空闲区位置

	// 新建一个内部类，分区节点类
	class Zone {
		private int size;// 分区大小
		private int head;// 分区始址
		private boolean isFree;// 空闲状态

		public Zone(int head, int size) {
			this.head = head;
			this.size = size;
			this.isFree = true;
		}
	}

	// 默认内存大小512K
	public Memory() {
		this.size = 512;
		this.pointer = 0;// 默认首次运行程序，上次分配的空闲区位置为0
		this.zones = new LinkedList<>();
		zones.add(new Zone(0, size));
	}

	public Memory(int size) {
		this.size = size;
		this.pointer = 0;
		this.zones = new LinkedList<>();
		zones.add(new Zone(0, size));
	}

	// 内存分配菜单
	public void allocation(int size) {
		System.out.println("1.首次适应算法 2.循环首次适应算法 3.最佳适应算法 4.最坏适应算法");
		System.out.print("请选择分配算法:");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch (choice) {
		case 1:
			FF(size);
			break;
		case 2:
			NF(size);
			break;
		case 3:
			BF(size);
			break;
		case 4:
			WF(size);
			break;
		default:
			System.out.println("请重新选择!");
		}
	}

	// 首次适应算法
	private void FF(int size) {
		// 遍历分区链表
		for (pointer = 0; pointer < zones.size(); pointer++) {
			Zone tmp = zones.get(pointer);
			// 找到可用分区（空闲且大小足够）
			if (tmp.isFree && (tmp.size > size)) {
				doAllocation(size, pointer, tmp);
				return;
			}
		}
		// 遍历结束后未找到可用分区, 则内存分配失败
		System.out.println("无可用内存空间!");
	}

	// 循环首次适应算法
	private void NF(int size) {
		Zone tmp = zones.get(pointer);
		if (tmp.isFree && (tmp.size > size)) {
			doAllocation(size, pointer, tmp);
			return;
		}
		int len = zones.size();
		int i = (pointer + 1) % len;
		for (; i != pointer; i = (i + 1) % len) {
			tmp = zones.get(i);
			if (tmp.isFree && (tmp.size > size)) {
				doAllocation(size, i, tmp);
				return;
			}
		}
		// 全遍历后如果未分配则失败
		System.out.println("无可用内存空间!");
	}

	// 最佳适应算法
	private void BF(int size) {
		int flag = -1;
		int min = this.size;
		for (pointer = 0; pointer < zones.size(); pointer++) {
			Zone tmp = zones.get(pointer);
			if (tmp.isFree && (tmp.size > size)) {
				if (min > tmp.size - size) {
					min = tmp.size - size;
					flag = pointer;
				}
			}
		}
		if (flag == -1) {
			System.out.println("无可用内存空间!");
		} else {
			doAllocation(size, flag, zones.get(flag));
		}
	}

	// 最坏适应算法
	private void WF(int size) {
		int flag = -1;
		int max = 0;
		for (pointer = 0; pointer < zones.size(); pointer++) {
			Zone tmp = zones.get(pointer);
			if (tmp.isFree && (tmp.size > size)) {
				if (max < tmp.size - size) {
					max = tmp.size - size;
					flag = pointer;
				}
			}
		}
		if (flag == -1) {
			System.out.println("无可用内存空间!");
		} else {
			doAllocation(size, flag, zones.get(flag));
		}
	}

	// 开始分配
	private void doAllocation(int size, int location, Zone tmp) {
		// 要是剩的比最小分区MIN_SIZE小，则把剩下那点给前一个进程
		if (tmp.size - size <= MIN_SIZE) {
			tmp.isFree = false;
		} else {
			Zone split = new Zone(tmp.head + size, tmp.size - size);
			zones.add(location + 1, split);
			tmp.size = size;
			tmp.isFree = false;
		}
		System.out.println("成功分配 " + size + "KB 内存!");
	}

	// 内存回收
	public void collection(int id) {
		if (id >= zones.size()) {
			System.out.println("无此分区编号!");
			return;
		}
		Zone tmp = zones.get(id);
		int size = tmp.size;
		if (tmp.isFree) {
			System.out.println("指定分区未被分配, 无需回收");
			return;
		}
		// 如果回收的分区后一个是空闲就和后一个合并
		if (id < zones.size() - 1 && zones.get(id + 1).isFree) {
			Zone next = zones.get(id + 1);
			tmp.size += next.size;
			zones.remove(next);
		}
		// 回收的分区要是前一个是空闲就和前分区合并
		if (id > 0 && zones.get(id - 1).isFree) {
			Zone previous = zones.get(id - 1);
			previous.size += tmp.size;
			zones.remove(id);
			id--;
		}
		zones.get(id).isFree = true;
		System.out.println("内存回收成功!, 本次回收了 " + size + "KB 空间!");
	}

	// 展示分区状况
	public void showZones() {
		System.out.println("分区编号\t    分区始址\t     分区大小\t 空闲状态\t");
		for (int i = 0; i < zones.size(); i++) {
			Zone tmp = zones.get(i);
			System.out.println(i + "\t\t" + tmp.head + "\t\t" + tmp.size + "  \t" + tmp.isFree);
		}
	}
}
