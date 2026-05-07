package com.wilplayer.android.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.wilplayer.android.data.local.dao.HistoryDao;
import com.wilplayer.android.data.local.dao.HistoryDao_Impl;
import com.wilplayer.android.data.local.dao.PlaylistDao;
import com.wilplayer.android.data.local.dao.PlaylistDao_Impl;
import com.wilplayer.android.data.local.dao.SongDao;
import com.wilplayer.android.data.local.dao.SongDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WilPlayerDatabase_Impl extends WilPlayerDatabase {
  private volatile SongDao _songDao;

  private volatile PlaylistDao _playlistDao;

  private volatile HistoryDao _historyDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `songs` (`id` TEXT NOT NULL, `videoId` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `thumbnailUrl` TEXT NOT NULL, `duration` INTEGER NOT NULL, `durationFormatted` TEXT NOT NULL, `viewCount` INTEGER NOT NULL, `isLiked` INTEGER NOT NULL, `playCount` INTEGER NOT NULL, `lastPlayedAt` INTEGER NOT NULL, `addedAt` INTEGER NOT NULL, `tags` TEXT NOT NULL, `categoryId` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playlists` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `thumbnailUrl` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `isYouTubePlaylist` INTEGER NOT NULL, `youtubePlaylistId` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playlist_songs` (`playlistId` TEXT NOT NULL, `songId` TEXT NOT NULL, `position` INTEGER NOT NULL, PRIMARY KEY(`playlistId`, `songId`), FOREIGN KEY(`playlistId`) REFERENCES `playlists`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`songId`) REFERENCES `songs`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_playlist_songs_songId` ON `playlist_songs` (`songId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `play_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `videoId` TEXT NOT NULL, `playedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `skip_history` (`videoId` TEXT NOT NULL, `skipCount` INTEGER NOT NULL, `lastSkippedAt` INTEGER NOT NULL, PRIMARY KEY(`videoId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f71518aababad479645fb6ab0879e5f7')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `songs`");
        db.execSQL("DROP TABLE IF EXISTS `playlists`");
        db.execSQL("DROP TABLE IF EXISTS `playlist_songs`");
        db.execSQL("DROP TABLE IF EXISTS `play_history`");
        db.execSQL("DROP TABLE IF EXISTS `skip_history`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSongs = new HashMap<String, TableInfo.Column>(14);
        _columnsSongs.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("videoId", new TableInfo.Column("videoId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("thumbnailUrl", new TableInfo.Column("thumbnailUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("durationFormatted", new TableInfo.Column("durationFormatted", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("viewCount", new TableInfo.Column("viewCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("isLiked", new TableInfo.Column("isLiked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("playCount", new TableInfo.Column("playCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("lastPlayedAt", new TableInfo.Column("lastPlayedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongs.put("categoryId", new TableInfo.Column("categoryId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongs = new TableInfo("songs", _columnsSongs, _foreignKeysSongs, _indicesSongs);
        final TableInfo _existingSongs = TableInfo.read(db, "songs");
        if (!_infoSongs.equals(_existingSongs)) {
          return new RoomOpenHelper.ValidationResult(false, "songs(com.wilplayer.android.data.local.entity.SongEntity).\n"
                  + " Expected:\n" + _infoSongs + "\n"
                  + " Found:\n" + _existingSongs);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylists = new HashMap<String, TableInfo.Column>(7);
        _columnsPlaylists.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("thumbnailUrl", new TableInfo.Column("thumbnailUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("isYouTubePlaylist", new TableInfo.Column("isYouTubePlaylist", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("youtubePlaylistId", new TableInfo.Column("youtubePlaylistId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylists = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylists = new TableInfo("playlists", _columnsPlaylists, _foreignKeysPlaylists, _indicesPlaylists);
        final TableInfo _existingPlaylists = TableInfo.read(db, "playlists");
        if (!_infoPlaylists.equals(_existingPlaylists)) {
          return new RoomOpenHelper.ValidationResult(false, "playlists(com.wilplayer.android.data.local.entity.PlaylistEntity).\n"
                  + " Expected:\n" + _infoPlaylists + "\n"
                  + " Found:\n" + _existingPlaylists);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylistSongs = new HashMap<String, TableInfo.Column>(3);
        _columnsPlaylistSongs.put("playlistId", new TableInfo.Column("playlistId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSongs.put("songId", new TableInfo.Column("songId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSongs.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylistSongs = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPlaylistSongs.add(new TableInfo.ForeignKey("playlists", "CASCADE", "NO ACTION", Arrays.asList("playlistId"), Arrays.asList("id")));
        _foreignKeysPlaylistSongs.add(new TableInfo.ForeignKey("songs", "CASCADE", "NO ACTION", Arrays.asList("songId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPlaylistSongs = new HashSet<TableInfo.Index>(1);
        _indicesPlaylistSongs.add(new TableInfo.Index("index_playlist_songs_songId", false, Arrays.asList("songId"), Arrays.asList("ASC")));
        final TableInfo _infoPlaylistSongs = new TableInfo("playlist_songs", _columnsPlaylistSongs, _foreignKeysPlaylistSongs, _indicesPlaylistSongs);
        final TableInfo _existingPlaylistSongs = TableInfo.read(db, "playlist_songs");
        if (!_infoPlaylistSongs.equals(_existingPlaylistSongs)) {
          return new RoomOpenHelper.ValidationResult(false, "playlist_songs(com.wilplayer.android.data.local.entity.PlaylistSongCrossRef).\n"
                  + " Expected:\n" + _infoPlaylistSongs + "\n"
                  + " Found:\n" + _existingPlaylistSongs);
        }
        final HashMap<String, TableInfo.Column> _columnsPlayHistory = new HashMap<String, TableInfo.Column>(3);
        _columnsPlayHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("videoId", new TableInfo.Column("videoId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("playedAt", new TableInfo.Column("playedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayHistory = new TableInfo("play_history", _columnsPlayHistory, _foreignKeysPlayHistory, _indicesPlayHistory);
        final TableInfo _existingPlayHistory = TableInfo.read(db, "play_history");
        if (!_infoPlayHistory.equals(_existingPlayHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "play_history(com.wilplayer.android.data.local.entity.PlayHistoryEntity).\n"
                  + " Expected:\n" + _infoPlayHistory + "\n"
                  + " Found:\n" + _existingPlayHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsSkipHistory = new HashMap<String, TableInfo.Column>(3);
        _columnsSkipHistory.put("videoId", new TableInfo.Column("videoId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkipHistory.put("skipCount", new TableInfo.Column("skipCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkipHistory.put("lastSkippedAt", new TableInfo.Column("lastSkippedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSkipHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSkipHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSkipHistory = new TableInfo("skip_history", _columnsSkipHistory, _foreignKeysSkipHistory, _indicesSkipHistory);
        final TableInfo _existingSkipHistory = TableInfo.read(db, "skip_history");
        if (!_infoSkipHistory.equals(_existingSkipHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "skip_history(com.wilplayer.android.data.local.entity.SkipHistoryEntity).\n"
                  + " Expected:\n" + _infoSkipHistory + "\n"
                  + " Found:\n" + _existingSkipHistory);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f71518aababad479645fb6ab0879e5f7", "2a9541580d10312716673e6c2adde397");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "songs","playlists","playlist_songs","play_history","skip_history");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `songs`");
      _db.execSQL("DELETE FROM `playlists`");
      _db.execSQL("DELETE FROM `playlist_songs`");
      _db.execSQL("DELETE FROM `play_history`");
      _db.execSQL("DELETE FROM `skip_history`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SongDao.class, SongDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlaylistDao.class, PlaylistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HistoryDao.class, HistoryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SongDao songDao() {
    if (_songDao != null) {
      return _songDao;
    } else {
      synchronized(this) {
        if(_songDao == null) {
          _songDao = new SongDao_Impl(this);
        }
        return _songDao;
      }
    }
  }

  @Override
  public PlaylistDao playlistDao() {
    if (_playlistDao != null) {
      return _playlistDao;
    } else {
      synchronized(this) {
        if(_playlistDao == null) {
          _playlistDao = new PlaylistDao_Impl(this);
        }
        return _playlistDao;
      }
    }
  }

  @Override
  public HistoryDao historyDao() {
    if (_historyDao != null) {
      return _historyDao;
    } else {
      synchronized(this) {
        if(_historyDao == null) {
          _historyDao = new HistoryDao_Impl(this);
        }
        return _historyDao;
      }
    }
  }
}
