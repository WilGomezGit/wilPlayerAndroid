package com.wilplayer.android.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wilplayer.android.data.local.entity.SongEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SongDao_Impl implements SongDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfSetLiked;

  private final SharedSQLiteStatement __preparedStmtOfIncrementPlayCount;

  private final EntityUpsertionAdapter<SongEntity> __upsertionAdapterOfSongEntity;

  public SongDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfSetLiked = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE songs SET isLiked = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfIncrementPlayCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE songs SET playCount = playCount + 1, lastPlayedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfSongEntity = new EntityUpsertionAdapter<SongEntity>(new EntityInsertionAdapter<SongEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `songs` (`id`,`videoId`,`title`,`artist`,`thumbnailUrl`,`duration`,`durationFormatted`,`viewCount`,`isLiked`,`playCount`,`lastPlayedAt`,`addedAt`,`tags`,`categoryId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getVideoId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVideoId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getArtist() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getArtist());
        }
        if (entity.getThumbnailUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThumbnailUrl());
        }
        statement.bindLong(6, entity.getDuration());
        if (entity.getDurationFormatted() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDurationFormatted());
        }
        statement.bindLong(8, entity.getViewCount());
        final int _tmp = entity.isLiked() ? 1 : 0;
        statement.bindLong(9, _tmp);
        statement.bindLong(10, entity.getPlayCount());
        statement.bindLong(11, entity.getLastPlayedAt());
        statement.bindLong(12, entity.getAddedAt());
        if (entity.getTags() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTags());
        }
        if (entity.getCategoryId() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCategoryId());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<SongEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `songs` SET `id` = ?,`videoId` = ?,`title` = ?,`artist` = ?,`thumbnailUrl` = ?,`duration` = ?,`durationFormatted` = ?,`viewCount` = ?,`isLiked` = ?,`playCount` = ?,`lastPlayedAt` = ?,`addedAt` = ?,`tags` = ?,`categoryId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SongEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getVideoId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVideoId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getArtist() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getArtist());
        }
        if (entity.getThumbnailUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThumbnailUrl());
        }
        statement.bindLong(6, entity.getDuration());
        if (entity.getDurationFormatted() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDurationFormatted());
        }
        statement.bindLong(8, entity.getViewCount());
        final int _tmp = entity.isLiked() ? 1 : 0;
        statement.bindLong(9, _tmp);
        statement.bindLong(10, entity.getPlayCount());
        statement.bindLong(11, entity.getLastPlayedAt());
        statement.bindLong(12, entity.getAddedAt());
        if (entity.getTags() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTags());
        }
        if (entity.getCategoryId() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCategoryId());
        }
        if (entity.getId() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getId());
        }
      }
    });
  }

  @Override
  public Object setLiked(final String id, final boolean liked,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetLiked.acquire();
        int _argIndex = 1;
        final int _tmp = liked ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfSetLiked.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementPlayCount(final String id, final long time,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementPlayCount.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, time);
        _argIndex = 2;
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
          __preparedStmtOfIncrementPlayCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertSong(final SongEntity song, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSongEntity.upsert(song);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertSongs(final List<SongEntity> songs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSongEntity.upsert(songs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SongEntity>> getAllSongs() {
    final String _sql = "SELECT * FROM songs ORDER BY lastPlayedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"songs"}, new Callable<List<SongEntity>>() {
      @Override
      @NonNull
      public List<SongEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDurationFormatted = CursorUtil.getColumnIndexOrThrow(_cursor, "durationFormatted");
          final int _cursorIndexOfViewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "viewCount");
          final int _cursorIndexOfIsLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "isLiked");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "playCount");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedAt");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final List<SongEntity> _result = new ArrayList<SongEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongEntity _item;
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
            _item = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
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
  public Flow<List<SongEntity>> getLikedSongs() {
    final String _sql = "SELECT * FROM songs WHERE isLiked = 1 ORDER BY addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"songs"}, new Callable<List<SongEntity>>() {
      @Override
      @NonNull
      public List<SongEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDurationFormatted = CursorUtil.getColumnIndexOrThrow(_cursor, "durationFormatted");
          final int _cursorIndexOfViewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "viewCount");
          final int _cursorIndexOfIsLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "isLiked");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "playCount");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedAt");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final List<SongEntity> _result = new ArrayList<SongEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongEntity _item;
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
            _item = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
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
  public Object getSongById(final String id, final Continuation<? super SongEntity> $completion) {
    final String _sql = "SELECT * FROM songs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SongEntity>() {
      @Override
      @Nullable
      public SongEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDurationFormatted = CursorUtil.getColumnIndexOrThrow(_cursor, "durationFormatted");
          final int _cursorIndexOfViewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "viewCount");
          final int _cursorIndexOfIsLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "isLiked");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "playCount");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedAt");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final SongEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
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

  @Override
  public Object searchLocal(final String q,
      final Continuation<? super List<SongEntity>> $completion) {
    final String _sql = "SELECT * FROM songs WHERE title LIKE '%' || ? || '%' OR artist LIKE '%' || ? || '%' ORDER BY playCount DESC LIMIT 50";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    _argIndex = 2;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SongEntity>>() {
      @Override
      @NonNull
      public List<SongEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDurationFormatted = CursorUtil.getColumnIndexOrThrow(_cursor, "durationFormatted");
          final int _cursorIndexOfViewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "viewCount");
          final int _cursorIndexOfIsLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "isLiked");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "playCount");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedAt");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final List<SongEntity> _result = new ArrayList<SongEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongEntity _item;
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
            _item = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSongsByArtist(final String artist,
      final Continuation<? super List<SongEntity>> $completion) {
    final String _sql = "SELECT * FROM songs WHERE artist = ? ORDER BY playCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (artist == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, artist);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SongEntity>>() {
      @Override
      @NonNull
      public List<SongEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDurationFormatted = CursorUtil.getColumnIndexOrThrow(_cursor, "durationFormatted");
          final int _cursorIndexOfViewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "viewCount");
          final int _cursorIndexOfIsLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "isLiked");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "playCount");
          final int _cursorIndexOfLastPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPlayedAt");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final List<SongEntity> _result = new ArrayList<SongEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SongEntity _item;
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
            _item = new SongEntity(_tmpId,_tmpVideoId,_tmpTitle,_tmpArtist,_tmpThumbnailUrl,_tmpDuration,_tmpDurationFormatted,_tmpViewCount,_tmpIsLiked,_tmpPlayCount,_tmpLastPlayedAt,_tmpAddedAt,_tmpTags,_tmpCategoryId);
            _result.add(_item);
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
}
