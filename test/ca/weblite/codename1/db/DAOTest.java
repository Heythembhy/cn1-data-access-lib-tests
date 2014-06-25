/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.codename1.db;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import com.codename1.testing.AbstractTest;
import com.codename1.ui.Display;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author shannah
 */
public class DAOTest extends AbstractTest{
    private static String configPath = "/setup.sql";
    private static String dbname = "testdb1";
    public DAOTest() {
    }

    
    public void testNewCollection() throws IOException{
        Database db = null;
        try {
            db = Database.openOrCreate(dbname);
            DAOProvider provider = new DAOProvider(db, configPath, 2);
            DAO collectionDAO = (DAO)provider.get("collections");
            
            Map coll = (Map)collectionDAO.newObject();
            coll.put("name", "Test Collection");
            
            collectionDAO.save(coll);
            assertBool(coll.containsKey("id"), "After saving, the collection should contain an ID");
            assertBool((Long)coll.get("id") == 1l, "ID should be 1" );
            
            Map coll2 = (Map)collectionDAO.getById(1l);
            assertBool(coll2 != null, "The collection should be not null, but it loaded as null from getById(1l)");
            assertBool(coll2 == coll, "Should have loaded from cache, but we have a different collection object.");
            
            //collectionDAO.clearCache();
            
            //coll2 = (Map)collectionDAO.getById(1l);
            //assertBool(coll2 != coll, "Should have loaded new object because we cleared the cache, but received the same old one.");
            
            assertBool((Long)coll2.get("id") == 1l, "Collection should have id of 1");
            assertBool("Test Collection".equals(coll2.get("name")), "Collection should have name 'Test Collection'");
            
            List<Map> colls = (List<Map>)collectionDAO.fetchAll();
            assertBool(1==colls.size(), "There should be exactly one record on collections.");
            
            assertBool(colls.get(0)== coll2, "The collection should  be the same object, but they are not the same");
            
            
            
            
        } finally {
            try {
                Display.getInstance().delete(dbname);
            } catch ( Throwable t){}
        }
    }
    
    public void testLoadSchema() throws IOException{
        Database db = null;
        try {
            db = Database.openOrCreate(dbname);
            DAOProvider provider = new DAOProvider(db, configPath, 2);
            DAO collectionDAO = (DAO)provider.get("collections");
            
            Map dbSchema = provider.getDatabaseSchema(db, configPath);
            assertBool(dbSchema.containsKey("tables"), "Schema should contains a tables key");
            
            Map tables = (Map)dbSchema.get("tables");
            assertBool(tables.containsKey("collections"), "collections schema was not loaded, but should have been");
            
            Map collectionsMap = (Map)tables.get("collections");
            assertBool(collectionsMap.containsKey("fields"), "collections schema should contain fields.");
            
            Map fields = (Map)collectionsMap.get("fields");
            assertBool(fields.size() == 2, "Collections should contain 2 fields");
            
            
            
        } finally {
            try {
                Display.getInstance().delete(dbname);
            } catch ( Throwable t){}
        }
        
        
    }

    
    public void testLoadDatabaseSQL() throws IOException{
        Database db = null;
        try {
            db = Database.openOrCreate(dbname);
            Map<Integer, List<String>> results = DAOProvider.loadDatabaseSQL(configPath);
            this.assertBool(results.size() == 2, "Should be exactly one version in this file");
            List<String> commands = results.get(1);
            this.assertBool(commands.size() == 5, "There should be 5 create table statements in this file.");
            for ( String cmd : commands ){
                db.execute(cmd);
            }
            Cursor c = db.executeQuery("select * from collections");
            this.assertBool(c.getColumnCount() == 2, "Expected 5 cols but received "+c.getColumnCount());
            c.close();
            c = db.executeQuery("PRAGMA table_info(collections)");
            Map myFields = new HashMap();
            while ( c.next() ){
                Row row = c.getRow();
                String name = row.getString(1);
                String type = row.getString(2);
                //dao.colTypes.put(name, DAO.ColType.valueOf(type));
                
                Map thisField = new HashMap();
                thisField.put("name", name);
                thisField.put("type", type);
                myFields.put(name, thisField);
                
            }
            this.assertBool(myFields.containsKey("name"), "Collections table should contain name field.");
            this.assertBool(myFields.containsKey("id"), "Collections table should contain id field");
            Map idField = (Map)myFields.get("id");
            Map nameField = (Map)myFields.get("name");
            this.assertBool("INTEGER".equals(idField.get("type")), "id field should be an INTEGER but is "+idField.get("id"));
            this.assertBool("VARCHAR".equals(nameField.get("type")), "name field should be a varchar but is "+nameField.get("type"));
            c.close();
            
            //DAOProvider provider = new DAOProvider(db, "/setup.sql", 1);
            //provider.
        } finally {
            try {
                Display.getInstance().delete(dbname);
            } catch ( Throwable t){}
        }
    }
    
    public void testLoadDatabaseSchema() throws IOException{
        Database db = null;
        try {
            db = Database.openOrCreate(dbname);
            
            this.assertBool(DAOProvider.getDatabaseVersion(db)==0, "Database version should be 0 at this point");
            DAOProvider daoProvider = new DAOProvider(db, configPath, 2);
            
            Map schema = daoProvider.getDatabaseSchema(db, configPath);
            this.assertBool(DAOProvider.getDatabaseVersion(db)==2, "Database version should be 2 at this point");
            
            //DAOProvider provider = new DAOProvider(db, "/setup.sql", 1);
            //provider.
        } finally {
            try {
                Display.getInstance().delete(dbname);
            } catch ( Throwable t){}
        }
    }
    
    public void testImportJSON() throws IOException{
        Database db = null;
        try {
            db = Database.openOrCreate(dbname);
            DAOProvider provider = new DAOProvider(db, configPath, 2);
            DAO issueDAO = (DAO)provider.get("issues");
            
            InputStream is = Display.getInstance().getResourceAsStream(null, "/test1.json");
            issueDAO.importJSON(is, "tables/issues");
            is.close();
            
            
            List<Map> issues = issueDAO.fetchAll();
            this.assertBool(issues.size() == 2, "Should be 2 issues imported");
            this.assertBool("Test issue 1".equals(issues.get(0).get("title")), "Wrong title for issue 1");
            
            
            is = Display.getInstance().getResourceAsStream(null, "/test1.json");
            Map<String,String> colMap = new HashMap<String,String>(1);
            colMap.put("id", "remote_id");
            
            String[] keyCols = new String[]{"remote_id"};
            issueDAO.importJSON(is, "tables/issues", colMap, keyCols);
            is.close();
            
            issues = issueDAO.fetchAll();
            this.assertBool(issues.size() == 4 , "Should be 4 issues after 2nd import");
            this.assertBool((Integer)issues.get(2).get("remote_id") == 1, "3rd issue should have remote ID 1");
            this.assertBool((Integer)issues.get(3).get("remote_id") == 2, "4th issue should have remote ID 2");
            
            is = Display.getInstance().getResourceAsStream(null, "/test1.json");
           
            issueDAO.importJSON(is, "tables/issues", colMap, keyCols);
            is.close();
            
            
            
            
        } finally {
            try {
                Display.getInstance().delete(dbname);
            } catch ( Throwable t){}
        }
    }
    
    public boolean runTest() throws Exception {
        testLoadDatabaseSQL();
        testLoadDatabaseSchema();
        testLoadSchema();
        testNewCollection();
        testImportJSON();
        return true;
    }
}
