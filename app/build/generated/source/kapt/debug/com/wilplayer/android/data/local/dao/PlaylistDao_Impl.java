package com.wilplayer.android.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wilplayer.android.data.local.entity.PlaylistEntity;
import com.wilplayer.android.data.local.entity.PlaylistSongCrossRef;
import com.wilplayer.android.data.local.entity.PlaylistWithSongs;
import com.wilplayer.android.data.local.entity.SongEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlaylistDao_Impl implements PlaylistDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlaylistSongCrossRef> __insertionAdapterOfPlaylistSongCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlaylist;

  private final SharedSQLiteStatement __preparedStmtOfRemoveSongFromPlaylist;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSongPosition;

  private final EntityUpsertionAdapter<PlaylistEntity> __upsertionAdapterOfPlaylistEntity;

  public PlaylistDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaylistSongCrossRef = new EntityInsertionAdapter<PlaylistSongCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `playlist_songs` (`playlistId`,`songId`,`position`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlaylistSongCrossRef entity) {
        if (entity.getPlaylistId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getPlaylistId());
        }
        if (entity.getSongId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSongId());
        }
        statement.bindLong(3, entity.getPosition());
      }
    };
    this.__preparedStmtOfDeletePlaylist = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM playlists WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveSongFromPlaylist = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM playlist_songs WHERE playlistId = ? AND songId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSongPosition = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE playlist_songs SET position = ? WHERE playlistId = ? AND songId = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfPlaylistEntity = new EntityUpsertionAdapter<PlaylistEntity>(new EntityInsertionAdapter<PlaylistEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `playlists` (`id`,`name`,`description`,`thumbnailUrl`,`createdAt`,`isYouTubePlaylist`,`youtubePlaylistId`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlaylistEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getThumbnailUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getThumbnailUrl());
        }
        statement.bindLong(5, entity.getCreatedAt());
        final int _tmp = entity.isYouTubePlaylist() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getYoutubePlaylistId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getYoutubePlaylistId());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<PlaylistEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `playlists` SET `id` = ?,`name` = ?,`description` = ?,`thumbnailUrl` = ?,`createdAt` = ?,`isYouTubePlaylist` = ?,`youtubePlaylistId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlaylistEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getThumbnailUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getThumbnailUrl());
        }
        statement.bindLong(5, entity.getCreatedAt());
        final int _tmp = entity.isYouTubePlaylist() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getYoutubePlaylistId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getYoutubePlaylistId());
        }
        if (entity.getId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getId());
        }
      }
    });
  }

  @Override
  public Object addSongToPlaylist(final PlaylistSongCrossRef crossRef,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlaylistSongCrossRef.insert(crossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlaylist(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlaylist.acquire();
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePlaylist.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object removeSongFromPlaylist(final String playlistId, final String songId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveSongFromPlaylist.acquire();
        int _argIndex = 1;
        if (playlistId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, playlistId);
        }
        _argIndex = 2;
        if (songId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, songId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveSongFromPlaylist.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSongPosition(final String playlistId, final String songId, final int position,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSongPosition.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, position);
        _argIndex = 2;
        if (playlistId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, playlistId);
        }
        _argIndex = 3;
        if (songId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, songId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateSongPosition.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertPlaylist(final PlaylistEntity playlist,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfPlaylistEntity.upsert(playlist);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PlaylistEntity>> getAllPlaylists() {
    final String _sql = "SELECT * FROM playlists ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"playlists"}, new Callable<List<PlaylistEntity>>() {
      @Override
      @NonNull
      public List<PlaylistEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIsYouTubePlaylist = CursorUtil.getColumnIndexOrThrow(_cursor, "isYouTubePlaylist");
          final int _cursorIndexOfYoutubePlaylistId = CursorUtil.getColumnIndexOrThrow(_cursor, "youtubePlaylistId");
          final List<PlaylistEntity> _result = new ArrayList<PlaylistEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlaylistEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpThumbnailUrl;
            if (_cursor.isNull(_cursorIndexOfThumbnailUrl)) {
              _tmpThumbnailUrl = null;
            } else {
              _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final boolean _tmpIsYouTubePlaylist;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsYouTubePlaylist);
            _tmpIsYouTubePlaylist = _tmp != 0;
            final String _tmpYoutubePlaylistId;
            if (_cursor.isNull(_cursorIndexOfYoutubePlaylistId)) {
              _tmpYoutubePlaylistId = null;
            } else {
              _tmpYoutubePlaylistId = _cursor.getString(_cursorIndexOfYoutubePlaylistId);
            }
            _item = new PlaylistEntity(_tmpId,_tmpName,_tmpDescription,_tmpThumbnailUrl,_tmpCreatedAt,_tmpIsYouTubePlaylist,_tmpYoutubePlaylistId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<PlaylistWithSongs> getPlaylistWithSongs(final String id) {
    final String _sql = "SELECT * FROM playlists WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.createFlow(__db, true, new String[] {"playlist_songs", "songs",
        "playlists"}, new Callable<PlaylistWithSongs>() {
      @Override
      @Nullable
      public PlaylistWithSongs call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfIsYouTubePlaylist = CursorUtil.getColumnIndexOrThrow(_cursor, "isYouTubePlaylist");
            final int _cursorIndexOfYoutubePlaylistId = CursorUtil.getColumnIndexOrThrow(_cursor, "youtubePlaylistId");
            final ArrayMap<String, ArrayList<SongEntity>> _collectionSongs = new ArrayMap<String, ArrayList<SongEntity>>();
            while (_cursor.moveToNext()) {
              final String _tmpKey;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpKey = null;
              } else {
                _tmpKey = _cursor.getString(_cursorIndexOfId);
              }
              if (_tmpKey != null) {
                if (!_collectionSongs.containsKey(_tmpKey)) {
                  _collectionSongs.put(_tmpKey, new ArrayList<SongEntity>());
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipsongsAscomWilplayerAndroidDataLocalEntitySongEntity(_collectionSongs);
            final PlaylistWithSongs _result;
            if (_cursor.moveToFirst()) {
              final PlaylistEntity _tmpPlaylist;
              final String _tmpId;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = _cursor.getString(_cursorIndexOfId);
              }
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpThumbnailUrl;
              if (_cursor.isNull(_cursorIndexOfThumbnailUrl)) {
                _tmpThumbnailUrl = null;
              } else {
                _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
              }
              final long _tmpCreatedAt;
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
              final boolean _tmpIsYouTubePlaylist;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfIsYouTubePlaylist);
              _tmpIsYouTubePlaylist = _tmp != 0;
              final String _tmpYoutubePlaylistId;
              if (_cursor.isNull(_cursorIndexOfYoutubePlaylistId)) {
                _tmpYoutubePlaylistId = null;
              } else {
                _tmpYoutubePlaylistId = _cursor.getString(_cursorIndexOfYoutubePlaylistId);
              }
              _tmpPlaylist = new PlaylistEntity(_tmpId,_tmpName,_tmpDescription,_tmpThumbnailUrl,_tmpCreatedAt,_tmpIsYouTubePlaylist,_tmpYoutubePlaylistId);
              final ArrayList<SongEntity> _tmpSongsCollection;
              final String _tmpKey_1;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpKey_1 = null;
              } else {
                _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
              }
              if (_tmpKey_1 != null) {
                _tmpSongsCollection = _collectionSongs.get(_tmpKey_1);
              } else {
                _tmpSongsCollection = new ArrayList<SongEntity>();
              }
              _result = new PlaylistWithSongs(_tmpPlaylist,_tmpSongsCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getPlaylistSongCount(final String playlistId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM playlist_songs WHERE playlistId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (playlistId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, playlistId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipsongsAscomWilplayerAndroidDataLocalEntitySongEntity(
      @NonNull final ArrayMap<String, ArrayList<SongEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchArrayMap(_map, true, (map) -> {
        __fetchRelationshipsongsAscomWilplayerAndroidDataLocalEntitySongEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `songs`.`id` AS `id`,`songs`.`videoId` AS `videoId`,`songs`.`title` AS `title`,`songs`.`artist` AS `artist`,`songs`.`thumbnailUrl` AS `thumbnailUrl`,`songs`.`duration` AS `duration`,`songs`.`durationFormatted` AS `durationFormatted`,`songs`.`viewCount` AS `viewCount`,`songs`.`isLiked` AS `isLiked`,`songs`.`playCount` AS `playCount`,`songs`.`lastPlayedAt` AS `lastPlayedAt`,`songs`.`addedAt` AS `addedAt`,`songs`.`tags` AS `tags`,`songs`.`categoryId` AS `categoryId`,_junction.`playlistId` FROM `playlist_songs` AS _junction INNER JOIN `songs` ON (_junction.`songId` = `songs`.`id`) WHERE _junction.`playlistId` IN (");
    final int _inputSize = __mapKeySet == null ? 1 : __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (__mapKeySet == null) {
      _stmt.bindNull(_argIndex);
    } else {
      for (String _item : __mapKeySet) {
        if (_item == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _item);
        }
        _argIndex++;
      }
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.playlistId;
      final int _itemKeyIndex = 14;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfVideoId = 1;
      final int _cursorIndexOfTitle = 2;
      final int _cursorIndexOfArtist = 3;
      final int _cursorIndexOfThumbnailUrl = 4;
      final int _cursorIndexOfDuration = 5;
      final int _cursorIndexOfDurationFormatted = 6;
      final int _cursorIndexOfViewCount = 7;
      final int _cursorIndexOfIsLiked = 8;
      final int _cursorIndexOfPlayCount = 9;
      final int _cursorIndexOfLastPlayedAt = 10;
      final int _cursorIndexOfAddedAt = 11;
      final int _cursorIndexOfTags = 12;
      final int _cursorIndexOfCategoryId = 13;
      while (_cursor.moveToNext()) {
        final String _tmpKey;
        if (_cursor.isNull(_itemKeyIndex)) {
          _tmpKey = null;
        } else {
          _tmpKey = _cursor.getString(_itemKeyIndex);
        }
        if (_tmpKey != null) {
          final ArrayList<SongEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final SongEntity _item_1;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpVideoId;
            if (_cursor.isNull(_cursorIndexOfVideoId)) {
              _tmpVideoId = null;
            } else {
              _tmpVideoId = _cursor.getString(_cursorIndexOfVideoId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpThumbnailUrl;
            if (_cursor.isNull(_cursorIndexOfThumbnailUrl)) {
              _tmpThumbnailUrl = null;
            } else {
              _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            }
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpDurationFormatted;
            if (_cursor.isNull(_cursorIndexOfDurationFormatted)) {
              _tmpDurationFormatted = null;
            } else {
              _tmpDurationFormatted = _cursor.getString(_cursorIndexOfDurationFormatted);
            }
            final long _tmpViewCount;
            _tmpViewCount = _cursor.getLong(_cursorIndexOfViewCount);
            final boolean _tmpIsLiked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLiked);
            _tmpIsLiked = _tmp != 0;
            final int _tmpPlayCount;
            _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
            final long _tmpLastPlayedAt;
            _tmpLastPlayedAt = _cursor.getLong(_cursorIndexOfLastPlayedAt);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            final String _tmpTags;
            if (_cursor.isNull(_cursorIndexOfTags)) {
              _tmpTags = null;
            } else {
              _tmpTags = _cursor.getString(_cursorIndexOfTags);
            }
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            _item_1 = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
