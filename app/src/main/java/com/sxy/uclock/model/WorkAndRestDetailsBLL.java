package com.sxy.uclock.model;

import com.sxy.uclock.app.MyApplication;
import com.sxy.uclock.db.WorkAndRestDetails;
import com.sxy.uclock.db.WorkAndRestDetailsDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/1/19.
 */
public class WorkAndRestDetailsBLL {
    private static WorkAndRestDetailsDao detailsDao = MyApplication.daoSession.getWorkAndRestDetailsDao();
    private static ArrayList<WorkAndRestDetails> warList = new ArrayList<>();
    private static ArrayList<WorkAndRestDetails> weekPlanList = new ArrayList<>();
    private static HashMap<String, List<WorkAndRestDetails>> weekPlanMap;
    private static ArrayList<WorkAndRestDetails> monthPlanList = new ArrayList<>();
    private static ArrayList<WorkAndRestDetails> importantPlanList = new ArrayList<>();

    public static ArrayList<WorkAndRestDetails> getWarListByTemplateIDAndDetailsType(long templateID,int detailsType) {
        Query query = detailsDao.queryBuilder().where(WorkAndRestDetailsDao.Properties.TemplateID.eq(templateID), WorkAndRestDetailsDao.Properties.DetailsType.eq(detailsType)).build();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        warList.clear();
        if (query.list().size()>0) {
            warList.addAll((ArrayList<WorkAndRestDetails>) query.list());
        }
        sortList(WorkAndRestDetails.DETAILS_TYPE_WAR);
        return warList;
    }

    public static HashMap<String, List<WorkAndRestDetails>> getWeekPlanList() {
        sortList(WorkAndRestDetails.DETAILS_TYPE_WEEK);
        return weekPlanMap;
    }

    private static HashMap<String, List<WorkAndRestDetails>> formatList(List<WorkAndRestDetails> pList) {
        HashMap<String, List<WorkAndRestDetails>> map = new HashMap<>();
//        for (WorkAndRestDetails entity : pList) {
//            if (map.containsKey(entity.detailsDate.get())) {
//                map.get(entity.detailsDate.get()).add(entity);
//            } else {
//                List<WorkAndRestDetails> list = new ArrayList<>();
//                list.add(entity);
//                map.put(entity.detailsDate.get(), list);
//            }
//        }
        return map;
    }

    public static boolean editDetail(WorkAndRestDetails entity) {
        if (entity == null) {
            return false;
        } else {
            List<WorkAndRestDetails> list = null;
            switch (entity.getDetailsType()) {
                case WorkAndRestDetails.DETAILS_TYPE_WAR:
                    list = warList;
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_WEEK:
                    list = weekPlanList;
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_MONTH:
                    list = monthPlanList;
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_IMPORTANT:
                    list = importantPlanList;
                    break;
            }
            detailsDao.update(entity);
            for (WorkAndRestDetails detail : list) {
                if (detail.getDetailsID() == entity.getDetailsID()) {
                    detail.setDetailsIsUsing(entity.getDetailsIsUsing());
                    detail.setDetailsIsDelChecked(entity.getDetailsIsDelChecked());
                    detail.setDetailsIsDelModel(entity.getDetailsIsDelModel());
                    detail.setDetailsDescribe(entity.getDetailsDescribe());
                    detail.setDetailsType(entity.getDetailsType());
                    detail.setDetailsDate(entity.getDetailsDate());
                    detail.setDetailsTime(entity.getDetailsTime());
                    detail.setTemplateID(entity.getTemplateID());
                    sortList(entity.getDetailsType());
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean addDetail(WorkAndRestDetails entity) {
        if (entity == null) {
            return false;
        } else {
            long id = detailsDao.insert(entity);
            entity.setDetailsID(id);
            switch (entity.getDetailsType()) {
                case WorkAndRestDetails.DETAILS_TYPE_WAR:
                    warList.add(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_WEEK:
                    weekPlanList.add(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_MONTH:
                    monthPlanList.add(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_IMPORTANT:
                    importantPlanList.add(entity);
                    break;
            }
            sortList(entity.getDetailsType());
            return true;
        }
    }

    public static boolean delDetail(WorkAndRestDetails entity) {
        if (entity == null) {
            return false;
        } else {
            detailsDao.delete(entity);
            boolean del=false;
            switch (entity.getDetailsType()) {
                case WorkAndRestDetails.DETAILS_TYPE_WAR:
                    del= warList.remove(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_WEEK:
                    del=weekPlanList.remove(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_MONTH:
                    del=monthPlanList.remove(entity);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_IMPORTANT:
                    del=importantPlanList.remove(entity);
                    break;
            }
            if (del)
                return true;
            else
                return false;
        }
    }

    public static boolean delDetails(List<WorkAndRestDetails> entities) {
        if (entities == null) {
            return false;
        } else {
            detailsDao.deleteInTx(entities);
            boolean del=false;
            switch (entities.get(0).getDetailsType()) {
                case WorkAndRestDetails.DETAILS_TYPE_WAR:
                    del= warList.removeAll(entities);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_WEEK:
                    del=weekPlanList.removeAll(entities);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_MONTH:
                    del=monthPlanList.removeAll(entities);
                    break;
                case WorkAndRestDetails.DETAILS_TYPE_IMPORTANT:
                    del=importantPlanList.removeAll(entities);
                    break;
            }
            if (del)
                return true;
            else
                return false;
        }
    }

    private static void sortList(int type) {
        switch (type) {
            case WorkAndRestDetails.DETAILS_TYPE_WAR:
                Collections.sort(warList, new Comparator<WorkAndRestDetails>() {
                    @Override
                    public int compare(WorkAndRestDetails lhs, WorkAndRestDetails rhs) {
                        return lhs.getDetailsTime().compareTo(rhs.getDetailsTime());
                    }
                });
                break;
            case WorkAndRestDetails.DETAILS_TYPE_WEEK:

                break;
            case WorkAndRestDetails.DETAILS_TYPE_MONTH:
                break;
            case WorkAndRestDetails.DETAILS_TYPE_IMPORTANT:
                break;
        }
    }
}
