package com.sxy.uclock.model;

import java.util.ArrayList;

/**
 * Created by 123 on 2015/9/7.
 */
public class WorkAndRestTemplateBLL {
    public static ArrayList<WorkAndRestTemplateEntity> getTemplateList(){
        ArrayList<WorkAndRestTemplateEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            WorkAndRestTemplateEntity entityWorkAndRestTemplate = new WorkAndRestTemplateEntity();
            entityWorkAndRestTemplate.templateName.set("作息模版" + i);
            entityWorkAndRestTemplate.templateDays.set("周一、周二、周三、周四、周五、周六、周日");
            if (i % 2 == 0)
                entityWorkAndRestTemplate.templateIsUsing.set(true);
            else
                entityWorkAndRestTemplate.templateIsUsing.set(false);
            list.add(entityWorkAndRestTemplate);
        }
        return list;
    }

    public static boolean delTemplate(WorkAndRestTemplateEntity entity){
        return true;
    }
}
