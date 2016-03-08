package com.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class UClockDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.sxy.uclock.db");
        addTable(schema);
        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void addTable(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addLongProperty("userID").primaryKey().autoincrement();
        user.addStringProperty("userName");
        user.addStringProperty("userPhoneNum").notNull().unique();
        user.addStringProperty("userPassword").notNull();
        user.addStringProperty("userIcon");
        user.addStringProperty("userWeChat");
        user.addStringProperty("userQQ");
        user.addStringProperty("userSina");

        Entity workAndRestTemplate = schema.addEntity("WorkAndRestTemplate");
        workAndRestTemplate.addLongProperty("templateID").primaryKey().autoincrement();
        Property userID=workAndRestTemplate.addLongProperty("userID").getProperty();
        workAndRestTemplate.addStringProperty("templateName").notNull();
        workAndRestTemplate.addStringProperty("templateDays").notNull();
        workAndRestTemplate.addBooleanProperty("templateIsUsing").notNull();
        user.addToMany(workAndRestTemplate,userID).setName("warTemplate");
        workAndRestTemplate.addToOne(user,userID);

        Entity workAndRestDetails = schema.addEntity("WorkAndRestDetails");
        workAndRestDetails.addLongProperty("detailsID").primaryKey().autoincrement();
        Property templateID=workAndRestDetails.addLongProperty("templateID").getProperty();
        workAndRestDetails.addIntProperty("detailsType").notNull();
        workAndRestDetails.addStringProperty("detailsDate");
        workAndRestDetails.addStringProperty("detailsTime");
        workAndRestDetails.addStringProperty("detailsDescribe");
        workAndRestDetails.addBooleanProperty("detailsIsUsing");
        workAndRestTemplate.addToMany(workAndRestDetails,templateID).setName("warDetails");
        workAndRestDetails.addToOne(workAndRestTemplate,templateID);
    }
}
