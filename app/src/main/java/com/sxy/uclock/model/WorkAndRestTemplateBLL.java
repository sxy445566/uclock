package com.sxy.uclock.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2015/9/7.
 */
public class WorkAndRestTemplateBLL {
    private static ArrayList<WorkAndRestTemplateEntity> list = new ArrayList<>();

    public static ArrayList<WorkAndRestTemplateEntity> getTemplateList() {
        for (int i = 0; i < 20; i++) {
            WorkAndRestTemplateEntity entityWorkAndRestTemplate = new WorkAndRestTemplateEntity();
            entityWorkAndRestTemplate.templateID.set(i);
            entityWorkAndRestTemplate.templateName.set("作息模版" + i);
            if (i % 2 == 0) {
                entityWorkAndRestTemplate.templateIsUsing.set(true);
                entityWorkAndRestTemplate.templateDays.set("1,2,4,7");
            } else {
                entityWorkAndRestTemplate.templateIsUsing.set(false);
                entityWorkAndRestTemplate.templateDays.set("3,4,6,7");
            }
            list.add(entityWorkAndRestTemplate);
        }
        return list;
    }

    public static boolean delTemplate(WorkAndRestTemplateEntity entity) {
        if (entity == null) {
            return false;
        } else {
            if (list.remove(entity))
                return true;
            else
                return false;
        }
    }

    public static boolean delTemplates(List<WorkAndRestTemplateEntity> entitys) {
        if (entitys == null) {
            return false;
        } else {
            if (list.removeAll(entitys))
                return true;
            else
                return false;
        }
    }

    public static boolean addWorkAndRestTemplateEntity(WorkAndRestTemplateEntity entity) {
        if (entity == null) {
            return false;
        } else {
            int id = entity.templateID.get();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).templateID.get() > id) {
                    id = list.get(i).templateID.get() + 1;
                }
            }
            entity.templateID.set(id);
            list.add(entity);
            return true;
        }
    }

    public static boolean modifyWorkAndRestTemplateEntity(WorkAndRestTemplateEntity entity) {
        if (entity == null) {
            return false;
        } else {
            for (int i = 0; i < list.size(); i++) {
                WorkAndRestTemplateEntity template = list.get(i);
                if (template.templateID == entity.templateID) {
                    template.templateName.set(entity.templateName.get());
                    template.templateDays.set(entity.templateDays.get());
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

    public static boolean addDay(WorkAndRestTemplateEntity entity, int day) {
        if (entity.templateDays.get() != null) {
            if (!TextUtils.isEmpty(entity.templateDays.get())) {
                entity.templateDays.set(entity.templateDays.get() + "," + day);
            } else {
                entity.templateDays.set("" + day);
            }
            return true;
        }
        return false;
    }

    public static boolean delDay(WorkAndRestTemplateEntity entity, int day) {
        String days = entity.templateDays.get();
        if (days != null && !TextUtils.isEmpty(days)) {
            if (days.length() > 1) {
                if (days.indexOf(day + "") == 0) {
                    entity.templateDays.set(days.substring(2));
                } else {
                    entity.templateDays.set(days.substring(0, days.indexOf(day + "") - 1) + days.substring(days.indexOf(day + "") + 1));
                }
            } else {
                entity.templateDays.set("");
            }
            return true;
        }
        return false;
    }

}
