package org.chris.tools.randomania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPlus {

    private static Random random = new Random();

    public String randomWithWeight(List<String> items) {
        List<String> itemsWithWeight = new ArrayList<>();
        int size = items.size();
        System.out.println("Item size: " + size);
        for (int i = 0; i < size; i++) {
            for (int j = size; j > i; j--) {
                itemsWithWeight.add(items.get(i));
            }
        }
        printItems(itemsWithWeight);
        return itemsWithWeight.get(random.nextInt(itemsWithWeight.size()));
    }

    public String random(List<String> items) {
        int size = items.size();
        for (int i = 0; i < size; i++) {
            Collections.shuffle(items);
        }
        return items.get(random.nextInt(size));
    }

    public String randomWithWeight(int count) {
        List<String> items = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            items.add(i + "");
        }
        return randomWithWeight(items);
    }
    
    private void printItems(List<String> items) {
    	for (String item : items) {
    		System.out.println(item);
    	}
    }

    public List<String> actions() {
        List<String> items = new ArrayList<>();
        items.add("ERROR日志 review");
        items.add("主数据异常处理流程");
        items.add("DCR异常处理逻辑");
        items.add("落库异常整理");
        items.add("/api/Portal/doctor/verifyQRCode （Portal获取医生专属二维码）");
        items.add("Multi Pods for CKafka consumer");
        items.add("黑名单功能");
        items.add("SIT压测");
        items.add("安全管理员用户权限管理");
        items.add("Automation testing run/result check");
        items.add("kubectl setup");
        items.add("Quality Gate Rules");
        items.add("HCO同步");
        items.add("DoctorView优化计划");
        items.add("SQL慢查询整理");
        items.add("兴趣领域接口问题");
        items.add("SQL慢查询整理");
        items.add("HCP落库处理逻辑");
        items.add("方法调用的同步异步");
        items.add("AS Doc Renew");
        items.add("Job时间表");
        items.add("AS on Confluence");
        items.add("Confluence维护");
        items.add("DCR有效性判断");
        items.add("主数据Position");
        items.add("HOP代码文件总大小check");
        items.add("DCR结果处理");
        items.add("主数据-Employee-Position");
        items.add("主数据-Employee");
        items.add("模板消息");
        items.add("广播消息");
        items.add("主数据-省市区");
        items.add("TAPD上原来的文档");
        items.add("/api/wechatUsers/roletype/userinfo");
        items.add("UT via ChatGPT");
        items.add("签名非空约束");
        items.add("姓名非空约束");
        items.add("统计主数据同步量");
        items.add("ELK");
        items.add("绑定的定义(active)");
        items.add("告警指标");
        items.add("核心功能代码接口标准化");
        items.add("绑定记录历史化");
        items.add("GitHub上命名调整");
        items.add("API Consolidation");
        items.add("COS browser环境+账号");
        items.add("PR Re-review");
        items.add("异常整理");
        items.add("API文档鉴权参数单独提出来");
        return items;
    }

    public List<String> crusades() {
        List<String> items = new ArrayList<>();
        items.add("API Consolidation/Simplification/Standardization");
        items.add("核心功能代码接口标准化");
        items.add("异常处理标准化");
        items.add("代码质量提升");
        items.add("数据库表清理");
        items.add("数据安全");
        items.add("闲置服务下线");
        items.add("代码安全性扫描");
        items.add("预用户方案");
        items.add("Unit Testing");
        items.add("SonarQube Bug修复");
        items.add("OP与外部系统数据同步整理与清理");
        items.add("OP与三方系统集成关系的调整与整理");
        items.add("测试效率提升");
        items.add("OP环境搭建流程标准化");
        items.add("数据同步2.0方案");
        items.add("OP日常运维工作");
        items.add("OP内部主数据维护");
        items.add("HOP开发定期维护工作");
        items.add("存储过程代码化");
        items.add("数据表冗余字段清理");
        items.add(".NET Core大版本升级到5.x");
        items.add("过期数据清理");
        items.add("数据库超量数据处理");
        items.add("多重身份数据层优化");
        items.add("弃用代码清理");
        items.add("白屏问题");
        items.add("服务降级机制");
        items.add("Job功能优化");
        items.add("TKE容器服务优化");
        items.add("数据库表约束优化");
        items.add("op-wxcentral decommission");
        items.add("Issue Driven");
        items.add("Code Review Actions");
        items.add("Swagger API加入API说明");
        items.add("API自动化测试");
        items.add("HOP事件机制");
        items.add("HOP数据字典");
        items.add("数据质量提升");
        items.add("Tech Debts");
        return items;
    }

    public List<String> tasks() {
        List<String> items = new ArrayList<>();
        items.add("Actions");
        items.add("Checks");
        items.add("Management");
        items.add("Examine");
        items.add("Technical");
        items.add("Other");
        return items;
    }

    public List<String> essentials() {
        List<String> items = new ArrayList<>();
        items.add("Codes");
        items.add("Confluence");
        items.add("Issues");
        items.add("JIRA Files");
        items.add("JIRA Issues");
        items.add("Links");
        items.add("TAPD Files");
        items.add("Topics");
        items.add("Project Note");
        items.add("Projects (Legacy Folder)");
        return items;
    }

    public static void main(String[] args) {
        RandomPlus rp = new RandomPlus();
        // String randomItem = rp.randomWithWeight(193);
        // String randomItem = rp.randomWithWeight(rp.actions());
        // String randomItem = rp.randomWithWeight(rp.crusades());
        String randomItem = rp.random(rp.essentials());
        System.out.println("The chosen one: " + randomItem);
    }
}
