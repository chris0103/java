package org.chris.tools.randomania.bookscanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookScanner {

	private List<Book> books = new ArrayList<>();
	
	public BookScanner() {
		
		// shelf #1-1
		books.add(new Book("这个历史挺靠谱-1", 331));													// ###
		books.add(new Book("这个历史挺靠谱-2", 246));													// #
		books.add(new Book("这个历史挺靠谱-3", 293));													// #
		books.add(new Book("彩色欧洲史-1", 0, 192));													// #
		books.add(new Book("彩色欧洲史-2", 193, 384));													// ##||
		books.add(new Book("彩色欧洲史-3", 385, 575));													// #
		books.add(new Book("鱼羊野史-1", 332));
		books.add(new Book("鱼羊野史-2", 305));
		books.add(new Book("失去的胜利", 537));															// ##
		books.add(new Book("二十四史人物与故事丛书-春秋战国卷", 337));											// ###||
		books.add(new Book("二十四史人物与故事丛书-西汉东汉卷", 456));											// #
		books.add(new Book("二十四史人物与故事丛书-魏晋南北朝卷", 366));
		books.add(new Book("二十四史人物与故事丛书-隋唐五代卷", 296));
		books.add(new Book("二十四史人物与故事丛书-宋辽金卷", 215));											// ##
		books.add(new Book("二十四史人物与故事丛书-元明卷", 334));											// ##
		books.add(new Book("希区柯克悬念故事全集-上", 428));												// #
		books.add(new Book("希区柯克悬念故事全集-下", 491));
		books.add(new Book("生死河", 321));															// #
		books.add(new Book("世界尽头", 218));															// ##||
		books.add(new Book("老子的智慧", 315));															// #
		books.add(new Book("京华烟云", 576));															// ##
		books.add(new Book("梦的解析", 387));															// #
		books.add(new Book("青铜时代", 669));															// #
		books.add(new Book("白银时代", 222));															// ##
		books.add(new Book("黄金时代", 413));															// ###
		books.add(new Book("身边的江湖", 225));															// ##
		books.add(new Book("1980年代的爱情", 173));														// #
		books.add(new Book("没有时间的世界", 223));														// ##
		books.add(new Book("国学概论", 121));															// #
		books.add(new Book("论自由", 115));
		books.add(new Book("枪与玫瑰的使用方法", 230));													// ##||
		books.add(new Book("越弱越暗越美丽", 234));														// ##||
		books.add(new Book("世界神话传说选", 232));
		books.add(new Book("物理学", 305));
		books.add(new Book("Sherlock Holmes: The Complete Novels and Stories, Volumn I", 1059));	//
		books.add(new Book("Sherlock Holmes: The Complete Novels and Stories, Volumn II", 737));	// #
		
		// shelf #1-2
		books.add(new Book("基础德语", 411, false));													// #
		books.add(new Book("英文文摘-2005-下", 64 * 6, false));											// #
		books.add(new Book("英文文摘-2006", 64 * 12, false));
		books.add(new Book("英文文摘-2007-上", 64 * 6, false));											// ##
		books.add(new Book("图说二战: 永远的记忆", 464));													// ###
		books.add(new Book("世界战争与西方的衰落-上", 340));												// ##
		books.add(new Book("世界战争与西方的衰落-下", 341, 682));
		books.add(new Book("科学与宗教", 318));
		books.add(new Book("李自成-第一卷: 潼关南原大战", 484));
		books.add(new Book("李自成-第二卷: 商洛壮歌", 473));
		books.add(new Book("李自成-第三卷: 紫禁城内外", 380));												// #
		books.add(new Book("李自成-第四卷: 李信与红娘子", 415));
		books.add(new Book("李自成-第五卷: 三雄聚会", 435));												// #
		books.add(new Book("李自成-第六卷: 燕辽纪事", 384));
		books.add(new Book("李自成-第七卷: 洪水滔滔", 460));
		books.add(new Book("李自成-第八卷: 崇祯皇帝之死", 463));
		books.add(new Book("李自成-第九卷: 兵败山海关", 414));												// #
		books.add(new Book("李自成-第十卷: 巨星陨落", 402));												// #
		books.add(new Book("王阳明全集-1", 267));														// #
		books.add(new Book("王阳明全集-2", 294));
		books.add(new Book("王阳明全集-3", 405));
		books.add(new Book("王阳明全集-4", 364));
		books.add(new Book("汉字的现在", 144));
		books.add(new Book("事关良心", 221));
		books.add(new Book("不敢问希区柯克的，问S先生吧", 188));
		books.add(new Book("野蛮大陆: 第二次世界大战后的欧洲", 427));											// ##
		books.add(new Book("Star Wars: Trilogy", 260 + 216 + 229));									// #
		books.add(new Book("星际穿越", 358));
		books.add(new Book("鱼羊野史-3", 300));														// #
		books.add(new Book("鱼羊野史-4", 366));														// #
		books.add(new Book("被禁止的考古学", 183));
		books.add(new Book("被禁止的知识", 345));
		books.add(new Book("隆美尔战时文件", 515));														// #
		books.add(new Book("有一类战犯叫参谋", 304));														// #
		books.add(new Book("时寒冰说: 未来二十年经济大趋势: 现实篇", 408));
		
		// shelf #1-3
		books.add(new Book("中国少儿百科全书-1", 178));
		books.add(new Book("中国少儿百科全书-2", 179, 360));												// #
		books.add(new Book("中国少儿百科全书-3", 361, 542));												// #
		books.add(new Book("中国少儿百科全书-4", 543, 724));
		books.add(new Book("中国上下五千年·世界上下五千年-1", 184));											// #
		books.add(new Book("中国上下五千年·世界上下五千年-2", 185, 372));										// #
		books.add(new Book("中国上下五千年·世界上下五千年-3", 373, 560));
		books.add(new Book("中国上下五千年·世界上下五千年-4", 561, 748));
		books.add(new Book("0~3岁婴幼儿养育专家指导", 337, false));
		books.add(new Book("0~3岁婴幼儿心理与优教", 192, false));
		books.add(new Book("超级宝贝", 404, false));
		books.add(new Book("法伯睡眠宝典", 200, false));
		books.add(new Book("小学家庭教育的100个难题", 308, false));
		books.add(new Book("实用程序育儿法", 394, false));
		books.add(new Book("十万个为什么", 131, false));
		books.add(new Book("十万个为什么全知道", 191, false));
		books.add(new Book("每个孩子都能管好自己", 210, false));
		books.add(new Book("每个孩子都能学好规矩", 204, false));
		books.add(new Book("每个孩子都能好好睡觉", 199, false));
		books.add(new Book("发明发现小百科", 167, false));												// #
		books.add(new Book("地球小百科", 167, false));
		books.add(new Book("儿童知识宝库-1", 121, false));												// #||
		books.add(new Book("儿童知识宝库-2", 121, false));												// #||
		books.add(new Book("儿童知识宝库-3", 121, false));
		books.add(new Book("儿童知识宝库-4", 121, false));
		books.add(new Book("儿童知识宝库-5", 121, false));
		
		// shelf #2-1
		books.add(new Book("史记-1", 1, 340));
		books.add(new Book("史记-2", 341, 758));
		books.add(new Book("史记-3", 759, 1156));
		books.add(new Book("史记-4", 1157, 1444));
		books.add(new Book("史记-5", 1445, 1778));
		books.add(new Book("史记-6", 1779, 2120));
		books.add(new Book("史记-7", 2121, 2438));
		books.add(new Book("史记-8", 2439, 2750));
		books.add(new Book("史记-9", 2751, 3074));
		books.add(new Book("史记-10", 3075, 3322 + 33 + 23));
		
		// shelf #2-2
		books.add(new Book("中国历史图谱", 38, false));
		books.add(new Book("简明中国历史地图集", 125, false));
		books.add(new Book("左传正宗", 719));
		books.add(new Book("战国策正宗", 596));
		books.add(new Book("孔子的智慧", 217));
		books.add(new Book("训诂学", 198));
		books.add(new Book("春秋左传注-1", 1, 506));
		books.add(new Book("春秋左传注-2", 507, 914));
		books.add(new Book("春秋左传注-3", 915, 1196));
		books.add(new Book("春秋左传注-4", 1197, 1736));
	}
	
	public void printBooksInfo() {
		System.out.println("Books:");
		int totalPage = 0;
		Collections.sort(books);
		int unreadCount = 0;
		for (Book book : books) {
			System.out.println(book.getTitle() + ": " + (book.getPages() - book.getStart()));
			totalPage += book.getUnreadPageCount();
			if (book.isUnread()) {
				unreadCount++;
			}
		}
		System.out.println("Book count: " + books.size() + "; total pages: " + totalPage + "; average page: " + totalPage / unreadCount + ".");
	}
	
	public void generateRandomReading() {
		printBooksInfo();
		System.out.println();
		Collections.shuffle(books);
		int index = (int) (Math.random() * books.size());
		Book book = books.get(index);
		int page = book.getStart() + (int) (Math.random() * (book.getPages() - book.getStart()));
		System.out.println("Reading: " + book.getTitle() + ", page: " + page + ".");
	}
	
	public static void main(String[] args) {
		BookScanner scanner = new BookScanner();
		scanner.generateRandomReading();
	}
}
