SET SCHEMA PUBLIC;
CREATE TABLE IF NOT EXISTS PUBLIC.ACCOUNTS (
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  NAME VARCHAR(255),
  PASSWORD VARCHAR(255),
  SECRET VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS PUBLIC.DIRECTORIES(
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  DIRECTORY VARCHAR(255),
  ACTIVE BOOLEAN,
  LOCKED BOOLEAN,
  PRESET_ID INTEGER
);
CREATE TABLE IF NOT EXISTS PUBLIC.MESSAGES(
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  MESSAGE VARCHAR(255),
  UPLOADID INTEGER,
  FACEBOOK BOOLEAN,
  TWITTER BOOLEAN,
  GOOGLEPLUS BOOLEAN,
  YOUTUBE BOOLEAN
);
CREATE TABLE IF NOT EXISTS PUBLIC.PRESETS(
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  AUTOTITLE BOOLEAN,
  AUTOTITLEFORMAT VARCHAR(255),
  CATEGORY VARCHAR(255),
  COMMENT SMALLINT,
  COMMENTVOTE BOOLEAN,
  DEFAULTDIR VARCHAR(255),
  DESCRIPTION VARCHAR(16777216),
  EMBED BOOLEAN,
  KEYWORDS VARCHAR(16777216),
  MOBILE BOOLEAN,
  NAME VARCHAR(255),
  NUMBERMODIFIER SMALLINT,
  RATE BOOLEAN,
  VIDEORESPONSE SMALLINT,
  VISIBILITY SMALLINT,
  PLAYLISTS_ID INTEGER,
  ACCOUNT_ID INTEGER,
  PLAYLIST_ID INTEGER
);
CREATE TABLE IF NOT EXISTS PUBLIC.QUEUE(
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  ARCHIVED BOOLEAN,
  CATEGORY VARCHAR(255),
  COMMENT SMALLINT,
  COMMENTVOTE BOOLEAN,
  DESCRIPTION VARCHAR(16777216),
  EMBED BOOLEAN,
  FAILED BOOLEAN,
  FILE VARCHAR(255),
  KEYWORDS VARCHAR(16777216),
  MIMETYPE VARCHAR(255),
  MOBILE BOOLEAN,
  PRIVATEFILE BOOLEAN,
  RATE BOOLEAN,
  TITLE VARCHAR(255),
  UNLISTED BOOLEAN,
  UPLOADURL VARCHAR(255),
  VIDEORESPONSE SMALLINT,
  SEQUENCE INTEGER,
  STARTED TIMESTAMP,
  INPROGRESS BOOLEAN,
  LOCKED BOOLEAN DEFAULT FALSE,
  VIDEOID VARCHAR(255),
  ACCOUNT_ID INTEGER,
  PLAYLISTS_ID INTEGER,
  PLAYLIST_ID INTEGER
);
CREATE TABLE IF NOT EXISTS PUBLIC.PLAYLISTS(
  IDENTITY INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
  PLAYLISTKEY VARCHAR(255),
  TITLE VARCHAR(255),
  URL VARCHAR(255),
  NUMBER INTEGER,
  SUMMARY VARCHAR(16777216),
  ACCOUNT_ID INTEGER
);