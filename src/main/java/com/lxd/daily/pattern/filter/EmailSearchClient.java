package com.lxd.daily.pattern.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件查找客户端
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class EmailSearchClient {

    public static void main(String[] args) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("sender", "xxx");
        condition.put("subject", "xxx");
        condition.put("sendTime", "xxx");
        Filter senderFilter = new SenderFilter("sender");
        Filter subjectFilter = new SubjectFilter("subject");
        Filter sendTime = new SendTimeFilter("sendTime");
        // 组装成链
        senderFilter.add(subjectFilter);
        subjectFilter.add(sendTime);
        senderFilter.doFilter(null,condition);
    }
}
