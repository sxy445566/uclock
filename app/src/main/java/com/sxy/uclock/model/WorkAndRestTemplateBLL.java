package com.sxy.uclock.model;

import java.util.ArrayList;

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
            list.remove(entity);
            return true;
        }
    }

    public static boolean addWorkAndRestTemplateEntity(WorkAndRestTemplateEntity entity) {
        if (entity == null) {
            return false;
        } else {
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
        String[] s = days.split(",");
        int[] i = new int[s.length];
        for (int j = 0; j < s.length; j++) {
            i[j] = Integer.parseInt(s[j]);
        }
        return i;
    }

    public static boolean isHaveDay(String days, int day) {
        if (days != null) {
            for (int d : StringDaysToIntArray(days)) {
                if (d == day) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean addDay(String days, int day) {
        if (days != null) {
            days = days + "," + day;
            return true;
        }
        return false;
    }
    public static boolean delDay(String days, int day) {
        if (days != null) {
            days.substring(days.charAt(day)-1,days.charAt(day));
            return true;
        }
        return false;
    }

}
