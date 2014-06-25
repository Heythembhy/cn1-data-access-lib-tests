package ca.weblite.codename1.db;


/**
 *  A provider for DAO objects in a database.  You would generally instantiate one
 *  DAOProvider object per database, and you would register one DAO per table in the 
 *  database that you need to use.  A default, generic DAO implementation is provided
 *  for tables that you don't explicitly register with custom DAO classes.
 *  @author shannah
 */
public class DAOProvider {

	/**
	 *  Constructor to create a provider for the given database and with the given schema version.  This version 
	 *  of the constructor uses a default config file path of /config.sql.  So you should place your config
	 *  file in a file named config.sql in the src root.
	 *  @param db The database to operate on.
	 *  @param schemaVersion The schema version.
	 */
	public DAOProvider(com.codename1.db.Database db, int schemaVersion) {
	}

	/**
	 *  Constructor to create a provider for the given database, using a specified config file, and at a 
	 *  specified version.
	 *  @param db
	 *  @param configFile
	 *  @param schemaVersion 
	 */
	public DAOProvider(com.codename1.db.Database db, String configFile, int schemaVersion) {
	}

	/**
	 *  Sets the schema version of this DAOProvider.
	 *  @param version 
	 */
	public void setSchemaVersion(int version) {
	}

	/**
	 *  Gets the schema version of this DAOProvider.
	 *  @return 
	 */
	public int getSchemaVersion() {
	}

	/**
	 *  Gets the DAO object for the specified table name.  If none is registered,
	 *  it will instantiate a new GenericDAO object for the given table and register
	 *  it.
	 *  @param tableName The table name.
	 *  @return The DAO corresponding to the table name.
	 *  @throws IOException 
	 */
	public DAO get(String tableName) {
	}

	/**
	 *  Registers a DAO for the given table name.
	 *  @param tableName The table name.
	 *  @param dao The DAO object to use for interacting with the table.
	 */
	public void set(String tableName, DAO dao) {
	}
}
