package com.sxy.uclock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class WorkAndRestDetailsBLL {
    public static ArrayList<WorkAndRestDetailsEntity> warList = new ArrayList<>();

    public static ArrayList<WorkAndRestDetailsEntity> getWarListByTemplateID(int templateID) {
        WorkAndRestDetailsEntity entity0 = new WorkAndRestDetailsEntity();
        entity0.detailsID.set(0);
        entity0.templateID.set(templateID);
        entity0.detailsTime.set("07:30");
        entity0.detailsDescribe.set("起床洗漱");
        entity0.detailsIsUsing.set(true);
        warList.add(entity0);

        WorkAndRestDetailsEntity entity1 = new WorkAndRestDetailsEntity();
        entity1.detailsID.set(1);
        entity1.templateID.set(templateID);
        entity1.detailsTime.set("08:15");
        entity1.detailsDescribe.set("出门吃早饭");
        entity1.detailsIsUsing.set(false);
        warList.add(entity1);

        WorkAndRestDetailsEntity entity2 = new WorkAndRestDetailsEntity();
        entity2.detailsID.set(2);
        entity2.templateID.set(templateID);
        entity2.detailsTime.set("09:00");
        entity2.detailsDescribe.set("开始工作");
        entity2.detailsIsUsing.set(false);
        warList.add(entity2);

        WorkAndRestDetailsEntity entity3 = new WorkAndRestDetailsEntity();
        entity3.detailsID.set(3);
        entity3.templateID.set(templateID);
        entity3.detailsTime.set("10:30");
        entity3.detailsDescribe.set("该喝水啦");
        entity3.detailsIsUsing.set(true);
        warList.add(entity3);

        WorkAndRestDetailsEntity entity4 = new WorkAndRestDetailsEntity();
        entity4.detailsID.set(4);
        entity4.templateID.set(templateID);
        entity4.detailsTime.set("12:00");
        entity4.detailsDescribe.set("吃午饭");
        entity4.detailsIsUsing.set(false);
        warList.add(entity4);

        WorkAndRestDetailsEntity entity5 = new WorkAndRestDetailsEntity();
        entity5.detailsID.set(5);
        entity5.templateID.set(templateID);
        entity5.detailsTime.set("12:40");
        entity5.detailsDescribe.set("午休");
        entity5.detailsIsUsing.set(false);
        warList.add(entity5);

        WorkAndRestDetailsEntity entity6 = new WorkAndRestDetailsEntity();
        entity6.detailsID.set(6);
        entity6.templateID.set(templateID);
        entity6.detailsTime.set("13:00");
        entity6.detailsDescribe.set("开始工作");
        entity6.detailsIsUsing.set(true);
        warList.add(entity6);

        WorkAndRestDetailsEntity entity7 = new WorkAndRestDetailsEntity();
        entity7.detailsID.set(7);
        entity7.templateID.set(templateID);
        entity7.detailsTime.set("15:00");
        entity7.detailsDescribe.set("该喝水啦");
        entity7.detailsIsUsing.set(true);
        warList.add(entity7);

        WorkAndRestDetailsEntity entity8 = new WorkAndRestDetailsEntity();
        entity8.detailsID.set(8);
        entity8.templateID.set(templateID);
        entity8.detailsTime.set("18:00");
        entity8.detailsDescribe.set("下班回家");
        entity8.detailsIsUsing.set(false);
        warList.add(entity8);

        WorkAndRestDetailsEntity entity9 = new WorkAndRestDetailsEntity();
        entity9.detailsID.set(9);
        entity9.templateID.set(templateID);
        entity9.detailsTime.set("18:40");
        entity9.detailsDescribe.set("晚餐少吃");
        entity9.detailsIsUsing.set(false);
        warList.add(entity9);

        WorkAndRestDetailsEntity entity10 = new WorkAndRestDetailsEntity();
        entity10.detailsID.set(10);
        entity10.templateID.set(templateID);
        entity10.detailsTime.set("19:00");
        entity10.detailsDescribe.set("锻炼身体");
        entity10.detailsIsUsing.set(true);
        warList.add(entity10);

        WorkAndRestDetailsEntity entity11 = new WorkAndRestDetailsEntity();
        entity11.detailsID.set(11);
        entity11.templateID.set(templateID);
        entity11.detailsTime.set("20:00");
        entity11.detailsDescribe.set("学习");
        entity11.detailsIsUsing.set(true);
        warList.add(entity11);

        WorkAndRestDetailsEntity entity12 = new WorkAndRestDetailsEntity();
        entity12.detailsID.set(12);
        entity12.templateID.set(templateID);
        entity12.detailsTime.set("22:00");
        entity12.detailsDescribe.set("娱乐休息");
        entity12.detailsIsUsing.set(true);
        warList.add(entity12);

        WorkAndRestDetailsEntity entity13 = new WorkAndRestDetailsEntity();
        entity13.detailsID.set(13);
        entity13.templateID.set(templateID);
        entity13.detailsTime.set("23:00");
        entity13.detailsDescribe.set("洗澡");
        entity13.detailsIsUsing.set(true);
        warList.add(entity7);

        WorkAndRestDetailsEntity entity14 = new WorkAndRestDetailsEntity();
        entity14.detailsID.set(14);
        entity14.templateID.set(templateID);
        entity14.detailsTime.set("23:30");
        entity14.detailsDescribe.set("睡觉");
        entity14.detailsIsUsing.set(false);
        warList.add(entity14);

        return warList;
    }

    public static boolean delDetail(WorkAndRestDetailsEntity entity){
        if (entity == null) {
            return false;
        } else {
            if (warList.remove(entity))
                return true;
            else
                return false;
        }
    }

    public static boolean delDetails(List<WorkAndRestDetailsEntity> entitys){
        if (entitys == null) {
            return false;
        } else {
            if (warList.removeAll(entitys))
                return true;
            else
                return false;
        }
    }
}
