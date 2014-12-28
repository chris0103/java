package org.chirs.tools.randomania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderRandoms {

	public void generateTaskOrder() {
		Set<String> taskSet = new HashSet<String>();
		taskSet.add("Major");
		taskSet.add("Minor");
		taskSet.add("Videos");
		taskSet.add("Board");
		taskSet.add("Arrangement");
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
		// videoSet.add("从地球出发.NASA任务50年");
		// videoSet.add("二次大戰全彩實錄");
		// videoSet.add("奋斗");
		// videoSet.add("航空母舰日志");
		// videoSet.add("裸婚时代");
		// videoSet.add("生活大爆炸");
		// videoSet.add("新幕府大将军德川家康");
		// videoSet.add("兄弟连");
		// videoSet.add("易中天评三国");
		// videoSet.add("自然界大事件");
		
		// in process video set
		videoSet.add("Gossip Girl");
		videoSet.add("东周列国");
		videoSet.add("风林火山");
		videoSet.add("名侦探柯南");
		videoSet.add("耶鲁大学开放课程-哲学：死亡");
		/*
		 */
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
