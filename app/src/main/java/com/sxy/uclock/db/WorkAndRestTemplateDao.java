package com.sxy.uclock.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.sxy.uclock.db.WorkAndRestTemplate;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORK_AND_REST_TEMPLATE".
*/
public class WorkAndRestTemplateDao extends AbstractDao<WorkAndRestTemplate, Long> {

    public static final String TABLENAME = "WORK_AND_REST_TEMPLATE";

    /**
     * Properties of entity WorkAndRestTemplate.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property TemplateID = new Property(0, Long.class, "templateID", true, "TEMPLATE_ID");
        public final static Property UserID = new Property(1, Long.class, "userID", false, "USER_ID");
        public final static Property TemplateName = new Property(2, String.class, "templateName", false, "TEMPLATE_NAME");
        public final static Property TemplateDays = new Property(3, String.class, "templateDays", false, "TEMPLATE_DAYS");
        public final static Property TemplateIsUsing = new Property(4, boolean.class, "templateIsUsing", false, "TEMPLATE_IS_USING");
    };

    private DaoSession daoSession;

    private Query<WorkAndRestTemplate> user_WarTemplateQuery;

    public WorkAndRestTemplateDao(DaoConfig config) {
        super(config);
    }
    
    public WorkAndRestTemplateDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORK_AND_REST_TEMPLATE\" (" + //
                "\"TEMPLATE_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: templateID
                "\"USER_ID\" INTEGER," + // 1: userID
                "\"TEMPLATE_NAME\" TEXT NOT NULL ," + // 2: templateName
                "\"TEMPLATE_DAYS\" TEXT NOT NULL ," + // 3: templateDays
                "\"TEMPLATE_IS_USING\" INTEGER NOT NULL );"); // 4: templateIsUsing
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORK_AND_REST_TEMPLATE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, WorkAndRestTemplate entity) {
        stmt.clearBindings();
 
        Long templateID = entity.getTemplateID();
        if (templateID != null) {
            stmt.bindLong(1, templateID);
        }
 
        Long userID = entity.getUserID();
        if (userID != null) {
            stmt.bindLong(2, userID);
        }
        stmt.bindString(3, entity.getTemplateName());
        stmt.bindString(4, entity.getTemplateDays());
        stmt.bindLong(5, entity.getTemplateIsUsing() ? 1L: 0L);
    }

    @Override
    protected void attachEntity(WorkAndRestTemplate entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public WorkAndRestTemplate readEntity(Cursor cursor, int offset) {
        WorkAndRestTemplate entity = new WorkAndRestTemplate( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // templateID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userID
            cursor.getString(offset + 2), // templateName
            cursor.getString(offset + 3), // templateDays
            cursor.getShort(offset + 4) != 0 // templateIsUsing
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, WorkAndRestTemplate entity, int offset) {
        entity.setTemplateID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTemplateName(cursor.getString(offset + 2));
        entity.setTemplateDays(cursor.getString(offset + 3));
        entity.setTemplateIsUsing(cursor.getShort(offset + 4) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(WorkAndRestTemplate entity, long rowId) {
        entity.setTemplateID(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(WorkAndRestTemplate entity) {
        if(entity != null) {
            return entity.getTemplateID();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "warTemplate" to-many relationship of User. */
    public List<WorkAndRestTemplate> _queryUser_WarTemplate(Long userID) {
        synchronized (this) {
            if (user_WarTemplateQuery == null) {
                QueryBuilder<WorkAndRestTemplate> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserID.eq(null));
                user_WarTemplateQuery = queryBuilder.build();
            }
        }
        Query<WorkAndRestTemplate> query = user_WarTemplateQuery.forCurrentThread();
        query.setParameter(0, userID);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM WORK_AND_REST_TEMPLATE T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID\"=T0.\"USER_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected WorkAndRestTemplate loadCurrentDeep(Cursor cursor, boolean lock) {
        WorkAndRestTemplate entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);

        return entity;    
    }

    public WorkAndRestTemplate loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<WorkAndRestTemplate> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<WorkAndRestTemplate> list = new ArrayList<WorkAndRestTemplate>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<WorkAndRestTemplate> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<WorkAndRestTemplate> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
