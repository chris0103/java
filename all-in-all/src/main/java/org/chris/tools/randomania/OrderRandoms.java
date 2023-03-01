package org.chris.tools.randomania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderRandoms {

	private void generateOrder(Set<String> taskSet) {
	
		List<String> taskList = new ArrayList<String>(taskSet);
		Collections.shuffle(taskList);
		
		Set<Integer> taskOrderSet = new HashSet<Integer>();
		for (int i = 0; i < taskSet.size(); i++) {
			taskOrderSet.add(i);
		}
		List<Integer> taskOrderList = new ArrayList<Integer>(taskOrderSet);
		Collections.shuffle(taskOrderList);
		
		// original task order
		for (String task : taskList) {
			System.out.println(task);
		}
		System.out.println();
		// shuffled task order
		for (int order : taskOrderList) {
			System.out.println((order + 1) + " - " + taskList.get(order));
		}
	}
	
	public void generateDebtsOrder() {
		Set<String> taskSet = new HashSet<String>();

		taskSet.add("数据备份");
		taskSet.add("手机容量清理");
		taskSet.add("新药涉及药物整理");
		taskSet.add("B站待学习视频下载");
		taskSet.add("残余Rocket Items");
		taskSet.add("修小黑屏幕");
		taskSet.add("网易云耳机研究");
		taskSet.add("Just My Socks研究 + MacOS使用");
		taskSet.add("购物附加保障服务整理");
		taskSet.add("ThinkPad升级/修理");
		taskSet.add("游戏整理");
		taskSet.add("程序整理，切换到IDEA");
		taskSet.add("Download stash IT Videos整理");
		taskSet.add("Download stash整理");
		taskSet.add("电脑书籍整理");
		taskSet.add("Rainlendar Tasks");
		taskSet.add("Book scanner (12.5K)");
		taskSet.add("电脑IT书籍整理");
		taskSet.add("Move tools Classes from tools project to all-in-all project");
		taskSet.add("English Translation");
		taskSet.add("Deutsch");
		taskSet.add("English Listening");
		taskSet.add("Practice");
		taskSet.add("Spider nets");
		taskSet.add("Canon Research");
		taskSet.add("整理各种读书资源");
		// taskSet.add(""); 
		generateOrder(taskSet);
	}
	
	
	public static void main(String[] args) {
		OrderRandoms randoms = new OrderRandoms();
		randoms.generateDebtsOrder();
	}
}
