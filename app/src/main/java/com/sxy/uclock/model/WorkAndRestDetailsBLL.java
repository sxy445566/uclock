package com.sxy.uclock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class WorkAndRestDetailsBLL {
    public static ArrayList<WorkAndRestDetailsEntity> warList = new ArrayList<>();
    public static ArrayList<WorkAndRestDetailsEntity> weekPlanList = new ArrayList<>();
    public static ArrayList<WorkAndRestDetailsEntity> monthPlanList = new ArrayList<>();
    public static ArrayList<WorkAndRestDetailsEntity> importantPlanList = new ArrayList<>();

    public static ArrayList<WorkAndRestDetailsEntity> getWarListByTemplateID(int templateID) {
        if (warList == null || warList.size() <= 0) {
            WorkAndRestDetailsEntity entity0 = new WorkAndRestDetailsEntity();
            entity0.detailsID.set(0);
            entity0.templateID.set(templateID);
            entity0.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity0.detailsDate.set("");
            entity0.detailsTime.set("07:30");
            entity0.detailsDescribe.set("起床洗漱");
            entity0.detailsIsUsing.set(true);
            warList.add(entity0);

            WorkAndRestDetailsEntity entity1 = new WorkAndRestDetailsEntity();
            entity1.detailsID.set(1);
            entity1.templateID.set(templateID);
            entity1.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity1.detailsDate.set("");
            entity1.detailsTime.set("08:15");
            entity1.detailsDescribe.set("出门吃早饭");
            entity1.detailsIsUsing.set(false);
            warList.add(entity1);

            WorkAndRestDetailsEntity entity2 = new WorkAndRestDetailsEntity();
            entity2.detailsID.set(2);
            entity2.templateID.set(templateID);
            entity2.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity2.detailsDate.set("");
            entity2.detailsTime.set("09:00");
            entity2.detailsDescribe.set("开始工作");
            entity2.detailsIsUsing.set(false);
            warList.add(entity2);

            WorkAndRestDetailsEntity entity3 = new WorkAndRestDetailsEntity();
            entity3.detailsID.set(3);
            entity3.templateID.set(templateID);
            entity3.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity3.detailsDate.set("");
            entity3.detailsTime.set("10:30");
            entity3.detailsDescribe.set("该喝水啦");
            entity3.detailsIsUsing.set(true);
            warList.add(entity3);

            WorkAndRestDetailsEntity entity4 = new WorkAndRestDetailsEntity();
            entity4.detailsID.set(4);
            entity4.templateID.set(templateID);
            entity4.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity4.detailsDate.set("");
            entity4.detailsTime.set("12:00");
            entity4.detailsDescribe.set("吃午饭");
            entity4.detailsIsUsing.set(false);
            warList.add(entity4);

            WorkAndRestDetailsEntity entity5 = new WorkAndRestDetailsEntity();
            entity5.detailsID.set(5);
            entity5.templateID.set(templateID);
            entity5.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity5.detailsDate.set("");
            entity5.detailsTime.set("12:40");
            entity5.detailsDescribe.set("午休");
            entity5.detailsIsUsing.set(false);
            warList.add(entity5);

            WorkAndRestDetailsEntity entity6 = new WorkAndRestDetailsEntity();
            entity6.detailsID.set(6);
            entity6.templateID.set(templateID);
            entity6.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity6.detailsDate.set("");
            entity6.detailsTime.set("13:00");
            entity6.detailsDescribe.set("开始工作");
            entity6.detailsIsUsing.set(true);
            warList.add(entity6);

            WorkAndRestDetailsEntity entity7 = new WorkAndRestDetailsEntity();
            entity7.detailsID.set(7);
            entity7.templateID.set(templateID);
            entity7.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity7.detailsDate.set("");
            entity7.detailsTime.set("15:00");
            entity7.detailsDescribe.set("该喝水啦");
            entity7.detailsIsUsing.set(true);
            warList.add(entity7);

            WorkAndRestDetailsEntity entity8 = new WorkAndRestDetailsEntity();
            entity8.detailsID.set(8);
            entity8.templateID.set(templateID);
            entity8.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity8.detailsDate.set("");
            entity8.detailsTime.set("18:00");
            entity8.detailsDescribe.set("下班回家");
            entity8.detailsIsUsing.set(false);
            warList.add(entity8);

            WorkAndRestDetailsEntity entity9 = new WorkAndRestDetailsEntity();
            entity9.detailsID.set(9);
            entity9.templateID.set(templateID);
            entity9.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity9.detailsDate.set("");
            entity9.detailsTime.set("18:40");
            entity9.detailsDescribe.set("晚餐少吃");
            entity9.detailsIsUsing.set(false);
            warList.add(entity9);

            WorkAndRestDetailsEntity entity10 = new WorkAndRestDetailsEntity();
            entity10.detailsID.set(10);
            entity10.templateID.set(templateID);
            entity10.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity10.detailsDate.set("");
            entity10.detailsTime.set("19:00");
            entity10.detailsDescribe.set("锻炼身体");
            entity10.detailsIsUsing.set(true);
            warList.add(entity10);

            WorkAndRestDetailsEntity entity11 = new WorkAndRestDetailsEntity();
            entity11.detailsID.set(11);
            entity11.templateID.set(templateID);
            entity11.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity11.detailsDate.set("");
            entity11.detailsTime.set("20:00");
            entity11.detailsDescribe.set("学习");
            entity11.detailsIsUsing.set(true);
            warList.add(entity11);

            WorkAndRestDetailsEntity entity12 = new WorkAndRestDetailsEntity();
            entity12.detailsID.set(12);
            entity12.templateID.set(templateID);
            entity12.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity12.detailsDate.set("");
            entity12.detailsTime.set("22:00");
            entity12.detailsDescribe.set("娱乐休息");
            entity12.detailsIsUsing.set(true);
            warList.add(entity12);

            WorkAndRestDetailsEntity entity13 = new WorkAndRestDetailsEntity();
            entity13.detailsID.set(13);
            entity13.templateID.set(templateID);
            entity13.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity13.detailsDate.set("");
            entity13.detailsTime.set("23:00");
            entity13.detailsDescribe.set("洗澡");
            entity13.detailsIsUsing.set(true);
            warList.add(entity7);

            WorkAndRestDetailsEntity entity14 = new WorkAndRestDetailsEntity();
            entity14.detailsID.set(14);
            entity14.templateID.set(templateID);
            entity14.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
            entity14.detailsDate.set("");
            entity14.detailsTime.set("23:30");
            entity14.detailsDescribe.set("睡觉");
            entity14.detailsIsUsing.set(false);
            warList.add(entity14);
        }
        sortList(WorkAndRestDetailsEntity.DETAILS_TYPE_WAR);
        return warList;
    }

    public static ArrayList<WorkAndRestDetailsEntity> getWeekPlanList() {
        if (weekPlanList == null || weekPlanList.size() <= 0) {
            WorkAndRestDetailsEntity entity0 = new WorkAndRestDetailsEntity();
            entity0.detailsID.set(0);
            entity0.templateID.set(-1);
            entity0.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity0.detailsDate.set("周三");
            entity0.detailsTime.set("07:30");
            entity0.detailsDescribe.set("起床洗漱");
            entity0.detailsIsUsing.set(true);
            weekPlanList.add(entity0);

            WorkAndRestDetailsEntity entity1 = new WorkAndRestDetailsEntity();
            entity1.detailsID.set(1);
            entity1.templateID.set(-1);
            entity1.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity1.detailsDate.set("周二");
            entity1.detailsTime.set("09:30");
            entity1.detailsDescribe.set("fads发");
            entity1.detailsIsUsing.set(true);
            weekPlanList.add(entity1);

            WorkAndRestDetailsEntity entity2 = new WorkAndRestDetailsEntity();
            entity2.detailsID.set(2);
            entity2.templateID.set(-1);
            entity2.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity2.detailsDate.set("周三");
            entity2.detailsTime.set("05:30");
            entity2.detailsDescribe.set("起床洗漱啊啊啊啊");
            entity2.detailsIsUsing.set(false);
            weekPlanList.add(entity2);

            WorkAndRestDetailsEntity entity3 = new WorkAndRestDetailsEntity();
            entity3.detailsID.set(3);
            entity3.templateID.set(-1);
            entity3.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity3.detailsDate.set("周三");
            entity3.detailsTime.set("12:30");
            entity3.detailsDescribe.set("午饭");
            entity3.detailsIsUsing.set(false);
            weekPlanList.add(entity3);

            WorkAndRestDetailsEntity entity4 = new WorkAndRestDetailsEntity();
            entity4.detailsID.set(4);
            entity4.templateID.set(-1);
            entity4.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity4.detailsDate.set("周五");
            entity4.detailsTime.set("16:30");
            entity4.detailsDescribe.set("玩");
            entity4.detailsIsUsing.set(true);
            weekPlanList.add(entity4);

            WorkAndRestDetailsEntity entity5 = new WorkAndRestDetailsEntity();
            entity5.detailsID.set(5);
            entity5.templateID.set(-1);
            entity5.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity5.detailsDate.set("周二");
            entity5.detailsTime.set("11:30");
            entity5.detailsDescribe.set("哈哈哈哈");
            entity5.detailsIsUsing.set(true);
            weekPlanList.add(entity5);

            WorkAndRestDetailsEntity entity6 = new WorkAndRestDetailsEntity();
            entity6.detailsID.set(6);
            entity6.templateID.set(-1);
            entity6.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity6.detailsDate.set("周二");
            entity6.detailsTime.set("8:25");
            entity6.detailsDescribe.set("起1849848");
            entity6.detailsIsUsing.set(false);
            weekPlanList.add(entity0);

            WorkAndRestDetailsEntity entity7 = new WorkAndRestDetailsEntity();
            entity7.detailsID.set(7);
            entity7.templateID.set(-1);
            entity7.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity7.detailsDate.set("周三");
            entity7.detailsTime.set("15:30");
            entity7.detailsDescribe.set("钱钱钱钱钱");
            entity7.detailsIsUsing.set(true);
            weekPlanList.add(entity0);

            WorkAndRestDetailsEntity entity8 = new WorkAndRestDetailsEntity();
            entity8.detailsID.set(8);
            entity8.templateID.set(-1);
            entity8.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity8.detailsDate.set("周五");
            entity8.detailsTime.set("09:55");
            entity8.detailsDescribe.set("起床洗漱");
            entity8.detailsIsUsing.set(false);
            weekPlanList.add(entity0);

            WorkAndRestDetailsEntity entity9 = new WorkAndRestDetailsEntity();
            entity9.detailsID.set(9);
            entity9.templateID.set(-1);
            entity9.detailsType.set(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
            entity9.detailsDate.set("周四");
            entity9.detailsTime.set("04:30");
            entity9.detailsDescribe.set("么么么么么");
            entity9.detailsIsUsing.set(true);
            weekPlanList.add(entity9);
        }
        sortList(WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK);
        return weekPlanList;
    }

    public static boolean editDetail(WorkAndRestDetailsEntity entity) {
        if (entity == null) {
            return false;
        } else {
            List<WorkAndRestDetailsEntity> list =null;
            switch (entity.detailsType.get()) {
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WAR:
                    list=warList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK:
                    list=weekPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_MONTH:
                    list=monthPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_IMPORTANT:
                    list=importantPlanList;
                    break;
            }
            for (WorkAndRestDetailsEntity detail : list) {
                if (detail.detailsID.get() == entity.detailsID.get()) {
                    detail.detailsIsUsing.set(entity.detailsIsUsing.get());
                    detail.detailsIsDelChecked.set(entity.detailsIsDelChecked.get());
                    detail.detailsIsDelModel.set(entity.detailsIsDelModel.get());
                    detail.detailsDescribe.set(entity.detailsDescribe.get());
                    detail.detailsType.set(entity.detailsType.get());
                    detail.detailsDate.set(entity.detailsDate.get());
                    detail.detailsTime.set(entity.detailsTime.get());
                    detail.templateID.set(entity.templateID.get());
                    sortList(entity.detailsType.get());
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean addDetail(WorkAndRestDetailsEntity entity) {
        if (entity == null) {
            return false;
        } else {
            List<WorkAndRestDetailsEntity> list =null;
            switch (entity.detailsType.get()) {
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WAR:
                    list=warList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK:
                    list=weekPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_MONTH:
                    list=monthPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_IMPORTANT:
                    list=importantPlanList;
                    break;
            }
            int id = 0;
            for (WorkAndRestDetailsEntity detail : list) {
                if (detail.detailsID.get() > id) {
                    id = detail.detailsID.get();
                }
            }
            entity.detailsID.set(id + 1);
            list.add(entity);
            sortList(entity.detailsType.get());
            return true;
        }
    }

    public static boolean delDetail(WorkAndRestDetailsEntity entity) {
        if (entity == null) {
            return false;
        } else {
            List<WorkAndRestDetailsEntity> list =null;
            switch (entity.detailsType.get()) {
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WAR:
                    list=warList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK:
                    list=weekPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_MONTH:
                    list=monthPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_IMPORTANT:
                    list=importantPlanList;
                    break;
            }
            if (list.remove(entity))
                return true;
            else
                return false;
        }
    }

    public static boolean delDetails(List<WorkAndRestDetailsEntity> entitys) {
        if (entitys == null) {
            return false;
        } else {
            List<WorkAndRestDetailsEntity> list =null;
            switch (entitys.get(0).detailsType.get()) {
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WAR:
                    list=warList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK:
                    list=weekPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_MONTH:
                    list=monthPlanList;
                    break;
                case WorkAndRestDetailsEntity.DETAILS_TYPE_IMPORTANT:
                    list=importantPlanList;
                    break;
            }
            if (list.removeAll(entitys))
                return true;
            else
                return false;
        }
    }

    private static void sortList(int type) {
        switch (type) {
            case WorkAndRestDetailsEntity.DETAILS_TYPE_WAR:
                Collections.sort(warList, new Comparator<WorkAndRestDetailsEntity>() {
                    @Override
                    public int compare(WorkAndRestDetailsEntity lhs, WorkAndRestDetailsEntity rhs) {
                        return lhs.detailsTime.get().compareTo(rhs.detailsTime.get());
                    }
                });
                break;
            case WorkAndRestDetailsEntity.DETAILS_TYPE_WEEK:
                break;
            case WorkAndRestDetailsEntity.DETAILS_TYPE_MONTH:
                break;
            case WorkAndRestDetailsEntity.DETAILS_TYPE_IMPORTANT:
                break;
        }
    }
}
