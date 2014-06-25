package ca.weblite.codename1.db;


/**
 *  A Data access object for a single table.  You can implement a separate DAO for
 *  each table of the database.  Currently tables must have an auto increment column named "id"
 *  in order to be compatible with this class.  
 *  @author shannah
 *  @param <T> The Class type of the POJO model object.
 */
public abstract class DAO {

	/**
	 *  Map of column types of fields in this table.  This maps field names to ColTypes.
	 */
	protected final java.util.Map colTypes;

	/**
	 *  Creates a new DAO object.
	 *  @param tableName The tablename that this DAO works on.
	 *  @param provider The DAO provider for this tablename.
	 *  @throws IOException 
	 */
	public DAO(String tableName, DAOProvider provider) {
	}

	/**
	 *  Returns the DAOProvider that this DAO is a member of.
	 *  @return 
	 */
	public DAOProvider getProvider() {
	}

	/**
	 *  Gets an entity object by its ID.  This version only checks the cache.
	 *  If you want to check the database, you should use {@link #getById(long,boolean)}.
	 *  @param id The ID of the object.
	 *  @return The entity object with the given ID or null if it isn't already loaded and cached.
	 *  @throws IOException 
	 */
	public final Object getById(long id) {
	}

	/**
	 *  Gets all of the entities in this DAO that are currently cached.  Please note, that the
	 *  cache only stores weak references to the entities, so this is not a reliable way to store
	 *  a set of objects for caching and retrieval.  
	 *  <p>Use {@link #fetchAll} to fetch all of the items from the data source.</p>
	 *  @return A List of all of the entities that are currently cached.
	 *  @throws IOException 
	 *  @see #fetchAll 
	 */
	public java.util.List getAll() {
	}

	/**
	 *  Fetches all of the entities in the underlying table as entity objects.
	 *  @return List of entities.
	 *  @throws IOException 
	 */
	public final java.util.List fetchAll() {
	}

	/**
	 *  Fetches a set of entities from the underlying table that match a given
	 *  query.
	 *  @param query A field=>value mapping the constitutes a query.
	 *  @return A set of entities from the underlying table.
	 *  @throws IOException 
	 */
	public final java.util.List fetch(java.util.Map query) {
	}

	/**
	 *  Fetches a set of entities from the underlying table that match a given query.
	 *  This is a wrapper for {@link #fetch(java.util.Map)} that juse uses a String[] array
	 *  to pass the query instead of a Map.
	 *  @param query Query parameters.  Odd indices contain column names, Even indices contain column values.
	 *  @return List of entities in the underlying table that match the query.
	 *  @throws IOException 
	 */
	public final java.util.List fetch(String[] query) {
	}

	/**
	 *  Fetches a single entity from the underlying table that matches a given query.
	 *  @param query
	 *  @return An entity object, or null if none was found.
	 *  @throws IOException 
	 */
	public final Object fetchOne(java.util.Map query) {
	}

	/**
	 *  Fetchs a single entity from the underlying table that matches a given query.
	 *  @param query
	 *  @return An entity object or null if none was found.
	 *  @throws IOException 
	 */
	public final Object fetchOne(String[] query) {
	}

	/**
	 *  Fills a map with the data of the current row of database Cursor
	 *  @param c The database cursor from a query.
	 *  @param m The map to fill.
	 *  @throws IOException 
	 */
	protected void fillMap(com.codename1.db.Cursor c, java.util.Map m) {
	}

	/**
	 *  Gets an entity by ID. If the refresh parameter is false, then this will
	 *  only check the cache.  If it is true, then this will reload the entity
	 *  from the database, and update it's data.
	 *  @param id The ID of the entity to load.
	 *  @param refresh True to refresh from the database.  False to just load from cache.
	 *  @return
	 *  @throws IOException 
	 */
	public Object getById(long id, boolean refresh) {
	}

	/**
	 *  Fetches records from the database using a specified SQL query.  This is protected
	 *  as an encouragement for implementors to create a finite set of fetchXXX methods in 
	 *  the subclass for performing specific searches, rather than exposing SQL to the
	 *  caller.
	 *  @param sqlQuery The SQL query
	 *  @param params The SQL query params
	 *  @return List of entity objects.
	 *  @throws IOException 
	 */
	protected java.util.List fetchAll(String sqlQuery, String[] params) {
	}

	/**
	 *  Inserts an entity into the database.  This will fail if the entity is already
	 *  inserted.
	 *  @param object The entity object to insert.
	 *  @throws IOException 
	 *  @see #save
	 *  @see #update
	 */
	public void insert(Object object) {
	}

	/**
	 *  Updates an existing entity in the database.
	 *  @param object The entity to update.
	 *  @throws IOException 
	 */
	public void update(Object object) {
	}

	/**
	 *  Saves an entity to the database.  This will check to see if the entity exists already,
	 *  and will call update() if not.  It will call insert() if it doesn't exist yet.
	 *  @param object The entity object to save.
	 *  @throws IOException 
	 */
	public void save(Object object) {
	}

	/**
	 *  Checks to see if the entity object is dirty and should be saved.
	 *  @param object
	 *  @return 
	 */
	public boolean isDirty(Object object) {
	}

	/**
	 *  Creates a new empty entity object for this DAO.  This should be implemented
	 *  by a subclass.
	 *  @return 
	 */
	public abstract Object newObject() {
	}

	/**
	 *  Copies values from the provided map into the provided entity object.
	 *  @param obj The entity object to which the values should be copied.
	 *  @param values The map from which the values are to be copied
	 */
	public abstract void unmap(Object obj, java.util.Map values) {
	}

	/**
	 *  Copies values from the provided entity object to the provided map.
	 *  @param obj The entity object from which the values should be copied.
	 *  @param values The map to which the values should be copied.
	 */
	public abstract void map(Object obj, java.util.Map values) {
	}

	/**
	 *  Initializes a wrapper object with the given id, entity object, and values.
	 *  @param id The ID of the object.
	 *  @param object The entity object.
	 *  @param m Values to add to the object.
	 *  @return The resulting wrapper.
	 */
	protected DAO.Wrapper initObject(long id, Object object, java.util.Map m) {
	}

	/**
	 *  Updates the dirty flag of the given entity object.
	 *  @param object The entity object to apply the flag to.
	 *  @param dirty True to make the object dirty.  False to make it clean.
	 */
	public void setDirty(Object object, boolean dirty) {
	}

	/**
	 *  Gets the ID of a provided entity object.  Should be implemented by a subclass.
	 *  @param object The entity object whose ID we wish to obtain.
	 *  @return The ID of the provided entity object.
	 */
	public abstract long getId(Object object) {
	}

	/**
	 *  Reference to the Database object for this DAO.
	 *  @return 
	 */
	protected com.codename1.db.Database db() {
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
	public void importSet(java.util.Map set, String selector, java.util.Map columnMap, String[] keyCols) {
	}

	public final void importSet(java.util.Map set, String selector, java.util.Map columnMap) {
	}

	public final void importSet(java.util.Map set, String selector) {
	}

	public void importSet(java.util.List rows, java.util.Map columnMap, String[] keyCols) {
	}

	public final void importSet(java.util.List rows, java.util.Map columnMap) {
	}

	public final void importSet(java.util.List rows) {
	}

	public final void importJSON(java.io.InputStream is, String selector, java.util.Map columnMap, String[] keyCols) {
	}

	public final void importJSON(java.io.InputStream is, String selector, java.util.Map columnMap) {
	}

	public final void importJSON(java.io.InputStream is, String selector) {
	}

	/**
	 *  Enum type for a column type in the DAO.
	 */
	public static final class ColType {


		public static final DAO.ColType INTEGER;

		public static final DAO.ColType FLOAT;

		public static final DAO.ColType DOUBLE;

		public static final DAO.ColType BLOB;

		public static final DAO.ColType LONG;

		public static final DAO.ColType SHORT;

		public static final DAO.ColType STRING;

		public static final DAO.ColType VARCHAR;

		public static DAO.ColType[] values() {
		}

		public static DAO.ColType valueOf(String name) {
		}
	}

	/**
	 *  A wrapper class for the POJO model object.  This stores a weak reference
	 *  to the POJO object so that the GC can handle unloading the cache.
	 */
	protected class Wrapper {


		protected DAO.Wrapper() {
		}

		/**
		 *  Listens for updates to the POJO and marks dirty flags.
		 *  @param o
		 *  @param arg 
		 */
		public void update(java.util.Observable o, Object arg) {
		}
	}
}
