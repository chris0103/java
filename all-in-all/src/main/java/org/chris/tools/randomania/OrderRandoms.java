package org.chris.tools.randomania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderRandoms {

	public void generateTaskOrder() {
		Set<String> taskSet = new HashSet<String>();

		taskSet.add("The Stream Zoo");
		taskSet.add("Spring Webmvc");
		taskSet.add("");
		taskSet.add("风色幻想");
		taskSet.add("Movies");
		taskSet.add("TV Series");
		// taskSet.add("");
		
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
	
	public void generateVideoOrder() {
		Set<String> videoSet = new HashSet<String>();
		
		// in process video set
		videoSet.add("Gossip Girl");
		videoSet.add("东周列国");
		videoSet.add("风林火山");
		videoSet.add("名侦探柯南");
		videoSet.add("耶鲁大学开放课程-哲学：死亡");
		
		 
		/*
		videoSet.add("百战经典");
		videoSet.add("二胎时代");
		 */
		// videoSet.add("");
		 
		List<String> videoList = new ArrayList<String>(videoSet);
		Collections.shuffle(videoList);
		
		Set<Integer> taskVideoSet = new HashSet<Integer>();
		for (int i = 0; i < videoSet.size(); i++) {
			taskVideoSet.add(i);
		}
		List<Integer> videoOrderList = new ArrayList<Integer>(taskVideoSet);
		Collections.shuffle(videoOrderList);
		
		// original video order
		for (String video : videoList) {
			System.out.println(video);
		}
		System.out.println();
		// shuffled video order
		for (int video : videoOrderList) {
			System.out.println((video + 1) + " - " + videoList.get(video));
		}
	}
	
	public static void main(String[] args) {
		OrderRandoms randoms = new OrderRandoms();
		// randoms.generateTaskOrder();
		randoms.generateVideoOrder();
	}
}
