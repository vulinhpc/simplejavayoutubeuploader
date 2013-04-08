/**
 * This class is generated by jOOQ
 */
package org.chaosfisch.youtubeuploader.db.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.0"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked" })
public class PlaylistRecord extends org.jooq.impl.UpdatableRecordImpl<org.chaosfisch.youtubeuploader.db.generated.tables.records.PlaylistRecord> implements org.jooq.Record11<java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.util.GregorianCalendar, java.lang.Boolean> {

	private static final long serialVersionUID = -840099874;

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.ID</code>. 
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.ID</code>. 
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.PKEY</code>. 
	 */
	public void setPkey(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.PKEY</code>. 
	 */
	public java.lang.String getPkey() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.PRIVATE</code>. 
	 */
	public void setPrivate(java.lang.Boolean value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.PRIVATE</code>. 
	 */
	public java.lang.Boolean getPrivate() {
		return (java.lang.Boolean) getValue(2);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.TITLE</code>. 
	 */
	public void setTitle(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.TITLE</code>. 
	 */
	public java.lang.String getTitle() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.URL</code>. 
	 */
	public void setUrl(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.URL</code>. 
	 */
	public java.lang.String getUrl() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.THUMBNAIL</code>. 
	 */
	public void setThumbnail(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.THUMBNAIL</code>. 
	 */
	public java.lang.String getThumbnail() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.NUMBER</code>. 
	 */
	public void setNumber(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.NUMBER</code>. 
	 */
	public java.lang.Integer getNumber() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.SUMMARY</code>. 
	 */
	public void setSummary(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.SUMMARY</code>. 
	 */
	public java.lang.String getSummary() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.ACCOUNT_ID</code>. 
	 */
	public void setAccountId(java.lang.Integer value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.ACCOUNT_ID</code>. 
	 */
	public java.lang.Integer getAccountId() {
		return (java.lang.Integer) getValue(8);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.DATE_OF_MODIFIED</code>. 
	 */
	public void setDateOfModified(java.util.GregorianCalendar value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.DATE_OF_MODIFIED</code>. 
	 */
	public java.util.GregorianCalendar getDateOfModified() {
		return (java.util.GregorianCalendar) getValue(9);
	}

	/**
	 * Setter for <code>PUBLIC.PLAYLIST.HIDDEN</code>. 
	 */
	public void setHidden(java.lang.Boolean value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>PUBLIC.PLAYLIST.HIDDEN</code>. 
	 */
	public java.lang.Boolean getHidden() {
		return (java.lang.Boolean) getValue(10);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.util.GregorianCalendar, java.lang.Boolean> fieldsRow() {
		return (org.jooq.Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.util.GregorianCalendar, java.lang.Boolean> valuesRow() {
		return (org.jooq.Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field3() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.PRIVATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.THUMBNAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.SUMMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field9() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.ACCOUNT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.GregorianCalendar> field10() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.DATE_OF_MODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field11() {
		return org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST.HIDDEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getPkey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value3() {
		return getPrivate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getThumbnail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value7() {
		return getNumber();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getSummary();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value9() {
		return getAccountId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.GregorianCalendar value10() {
		return getDateOfModified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value11() {
		return getHidden();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PlaylistRecord
	 */
	public PlaylistRecord() {
		super(org.chaosfisch.youtubeuploader.db.generated.tables.Playlist.PLAYLIST);
	}
}