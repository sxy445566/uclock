package com.sxy.uclock.model;

import android.text.TextUtils;

import com.sxy.uclock.app.MyApplication;
import com.sxy.uclock.db.WorkAndRestTemplate;
import com.sxy.uclock.db.WorkAndRestTemplateDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by 123 on 2015/9/7.
 */
public class WorkAndRestTemplateBLL {
    private static WorkAndRestTemplateDao templateDao = MyApplication.daoSession.getWorkAndRestTemplateDao();
    private static ArrayList<WorkAndRestTemplate> list = new ArrayList<>();

    public static ArrayList<WorkAndRestTemplate> getTemplateList() {
        Query query = templateDao.queryBuilder().where(WorkAndRestTemplateDao.Properties.UserID.eq(1l)).build();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        if (query.list().size() < 1) {
            for (int i = 0; i < 20; i++) {
                WorkAndRestTemplate entityWorkAndRestTemplate = new WorkAndRestTemplate((long) i, 1l, "作息模版" + i, i % 2 == 0 ? "1,2,4,7" : "3,4,6,7", i % 2 == 0);
                templateDao.insert(entityWorkAndRestTemplate);
            }
        }
        return list = (ArrayList<WorkAndRestTemplate>) query.list();
    }

    public static boolean delTemplate(WorkAndRestTemplate entity) {
        if (entity == null) {
            return false;
        } else {
            if (list.remove(entity)) {
                templateDao.delete(entity);
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean delTemplates(List<WorkAndRestTemplate> entitys) {
        if (entitys == null) {
            return false;
        } else {
            if (list.removeAll(entitys)) {
                templateDao.deleteInTx(entitys);
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean addWorkAndRestTemplateEntity(WorkAndRestTemplate entity) {
        if (entity == null) {
            return false;
        } else {
            long id = templateDao.insert(entity);
            entity.setTemplateID(id);
            list.add(entity);
            return true;
        }
    }

    public static boolean modifyWorkAndRestTemplateEntity(WorkAndRestTemplate entity) {
        if (entity == null) {
            return false;
        } else {
            templateDao.update(entity);
            for (int i = 0; i < list.size(); i++) {
                WorkAndRestTemplate template = list.get(i);
                if (template.getTemplateID() == entity.getTemplateID()) {
                    template.setTemplateName(entity.getTemplateName());
                    template.setTemplateDays(entity.getTemplateDays());
                    return true;
                }
            }
            return false;
        }
    }

    public static int[] StringDaysToIntArray(String days) {
        int[] i;
        if (days.length() == 1) {
            i = new int[1];
            i[0] = Integer.parseInt(days);
            return i;
        } else {
            String[] s = days.split(",");
            i = new int[s.length];
            for (int j = 0; j < s.length; j++) {
                i[j] = Integer.parseInt(s[j]);
            }
            return i;
        }
    }

    public static boolean isHaveDay(String days, int day) {
        if (days != null && !TextUtils.isEmpty(days)) {
            for (int d : StringDaysToIntArray(days)) {
                if (d == day) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean addDay(WorkAndRestTemplate entity, int day) {
        if (!TextUtils.isEmpty(entity.getTemplateDays())) {
            entity.setTemplateDays(entity.getTemplateDays() + "," + day);
        } else {
            entity.setTemplateDays("" + day);
        }
        return true;
    }

    public static boolean delDay(WorkAndRestTemplate entity, int day) {
        String days = entity.getTemplateDays();
        if (days != null && !TextUtils.isEmpty(days)) {
            if (days.length() > 1) {
                if (days.indexOf(day + "") == 0) {
                    entity.setTemplateDays(days.substring(2));
                } else {
                    entity.setTemplateDays(days.substring(0, days.indexOf(day + "") - 1) + days.substring(days.indexOf(day + "") + 1));
                }
            } else {
                entity.setTemplateDays("");
            }
            return true;
        }
        return false;
    }

}
