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
	
	public void generateTasksOrder() {
		Set<String> tasks = new HashSet<String>();

        tasks.add("Computer Books scanner");
        tasks.add("Move tools Classes from tools project to all-in-all project");
        tasks.add("English Translation");
        tasks.add("Deutsch");
        tasks.add("English Listening");
        tasks.add("Practice");
        tasks.add("Spider nets");
        tasks.add("Canon Research");
        tasks.add("To Be Enhanced Movie");
        tasks.add("手机容量清理");
        tasks.add("新药涉及药物整理");
        tasks.add("B站待学习视频下载");
        tasks.add("游戏整理");
        tasks.add("Book scanner (12.5K)");
		// tasks.add(""); 
		generateOrder(tasks);
	}
	
	
	public static void main(String[] args) {
		OrderRandoms randoms = new OrderRandoms();
		randoms.generateTasksOrder();
	}
}
