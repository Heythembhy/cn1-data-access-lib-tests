package ca.weblite.codename1.db;


/**
 *  An importer for importing sets of data into a table.  This can be used to import
 *  JSON data, or lists of Maps.
 *  @author shannah
 *  @param <T> The type of the entity object.
 */
public class Importer {

	/**
	 *  Creates an importer for the given DAO.
	 *  @param dao The DAO into which the importer will import records.
	 */
	public Importer(DAO dao) {
	}

	/**
	 *  Creates an importer for the given DAO with the specified key columns and column map.
	 *  @param dao The DAO into which the importer will import records.
	 *  @param keyColumns List of columns in the import set that are key columns.
	 *  @param columnMap A map of columns in the import set to the corresponding column in the DAO. 
	 *  Odd entries are column names in the import set, and the next even entry is the corresponding 
	 *  column name in the DAO.
	 */
	public Importer(DAO dao, String[] keyColumns, String[] columnMap) {
	}

	/**
	 *  Creates an importer for the given DAO with specified key columns and column map.
	 *  @param dao The DAO into which the importer will import records.
	 *  @param keyColumns List of columns in the import set that are key columns.
	 *  @param columnMap A map of columns in the import set to the corresponding column in the DAO.
	 */
	public Importer(DAO dao, String[] keyColumns, java.util.Map columnMap) {
	}

	/**
	 *  Gets a reference to the column map.
	 *  @return The column map for this importer.  This maps column names of the import set to
	 *  column names in the DAO.
	 */
	public java.util.Map getColumnMap() {
	}

	/**
	 *  Gets the key columns for this set.  These are the columns that will be used
	 *  to look up existing records in the DAO.  These should be column names of the DAO,
	 *  and not the import set.
	 *  @return 
	 */
	public java.util.List getKeyCols() {
	}

	/**
	 *  Imports a set of rows into the table.
	 *  @param set A Map with a nested data structure.  Typically this will have been parsed from JSON.
	 *  @param selector A selector path to indicate where, within the dataset, the rows to be imported
	 *   are located.  E.g. "tables/data" indicates that the Map set has a key "tables" with another Map, 
	 *  which contains a key "data", which is a List of Maps - each of which represents a row to be imported.
	 *  @param columnMap Maps columns in the imported set, into the corresponding column names in the table.
	 *  @throws IOException 
	 */
	public void importSet(java.util.Map set, String selector) {
	}

	/**
	 *  Imports a set of rows into the DAO.
	 *  @param rows List of Maps to be imported.
	 *  @throws IOException 
	 */
	public void importSet(java.util.List rows) {
	}

	/**
	 *  Trigger called just before a row is imported.  This gives subclasses
	 *  an opportunity to modify data in the row before it is applied to the 
	 *  Entity object.
	 *  @param row The row that is being imported.  It has already had the
	 *  importer's column map applied to it.
	 */
	protected void prepareRowForImport(java.util.Map row) {
	}

	/**
	 *  Trigger called just before an entity object is saved.
	 *  @param object The entity object that is being imported.  It may be an 
	 *  existing object or a new object.
	 *  @param row A map of values that were applied to the entity object.
	 */
	protected void beforeImport(Object object, java.util.Map row) {
	}

	/**
	 *  Trigger called just after the entity object is saved.
	 *  @param object The entity object that was saved.
	 *  @param row The row of Map data that was applied to the entity object to be saved.
	 */
	protected void afterImport(Object object, java.util.Map row) {
	}

	/**
	 *  Imports JSON data from an input stream into the database.
	 *  @param is InputStream containing JSON data.
	 *  @param selector The selector to specify the path to the list of data to be imported.
	 *  @throws IOException 
	 */
	public final void importJSON(java.io.InputStream is, String selector) {
	}
}
