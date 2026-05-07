package com.wilplayer.android.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
import com.wilplayer.android.data.local.entity.PlayHistoryEntity;
import com.wilplayer.android.data.local.entity.SkipHistoryEntity;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoryDao_Impl implements HistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlayHistoryEntity> __insertionAdapterOfPlayHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfPruneOldHistory;

  private final SharedSQLiteStatement __preparedStmtOfDecrementSkip;

  private final SharedSQLiteStatement __preparedStmtOfPruneEmptySkips;

  private final EntityUpsertionAdapter<SkipHistoryEntity> __upsertionAdapterOfSkipHistoryEntity;

  public HistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlayHistoryEntity = new EntityInsertionAdapter<PlayHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `play_history` (`id`,`videoId`,`playedAt`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlayHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getVideoId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVideoId());
        }
        statement.bindLong(3, entity.getPlayedAt());
      }
    };
    this.__preparedStmtOfPruneOldHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM play_history WHERE playedAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementSkip = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE skip_history SET skipCount = MAX(0, skipCount - 1) WHERE videoId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfPruneEmptySkips = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM skip_history WHERE skipCount <= 0";
        return _query;
      }
    };
    this.__upsertionAdapterOfSkipHistoryEntity = new EntityUpsertionAdapter<SkipHistoryEntity>(new EntityInsertionAdapter<SkipHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `skip_history` (`videoId`,`skipCount`,`lastSkippedAt`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SkipHistoryEntity entity) {
        if (entity.getVideoId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getVideoId());
        }
        statement.bindLong(2, entity.getSkipCount());
        statement.bindLong(3, entity.getLastSkippedAt());
      }
    }, new EntityDeletionOrUpdateAdapter<SkipHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `skip_history` SET `videoId` = ?,`skipCount` = ?,`lastSkippedAt` = ? WHERE `videoId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SkipHistoryEntity entity) {
        if (entity.getVideoId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getVideoId());
        }
        statement.bindLong(2, entity.getSkipCount());
        statement.bindLong(3, entity.getLastSkippedAt());
        if (entity.getVideoId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getVideoId());
        }
      }
    });
  }

  @Override
  public Object recordPlay(final PlayHistoryEntity history,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlayHistoryEntity.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object pruneOldHistory(final long before, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfPruneOldHistory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, before);
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
          __preparedStmtOfPruneOldHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object decrementSkip(final String videoId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementSkip.acquire();
        int _argIndex = 1;
        if (videoId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, videoId);
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
          __preparedStmtOfDecrementSkip.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object pruneEmptySkips(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfPruneEmptySkips.acquire();
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
          __preparedStmtOfPruneEmptySkips.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertSkip(final SkipHistoryEntity skip,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSkipHistoryEntity.upsert(skip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentVideoIds(final int limit,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT videoId FROM play_history ORDER BY playedAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Object getAllSkips(final Continuation<? super List<SkipHistoryEntity>> $completion) {
    final String _sql = "SELECT * FROM skip_history";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SkipHistoryEntity>>() {
      @Override
      @NonNull
      public List<SkipHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVideoId = CursorUtil.getColumnIndexOrThrow(_cursor, "videoId");
          final int _cursorIndexOfSkipCount = CursorUtil.getColumnIndexOrThrow(_cursor, "skipCount");
          final int _cursorIndexOfLastSkippedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSkippedAt");
          final List<SkipHistoryEntity> _result = new ArrayList<SkipHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SkipHistoryEntity _item;
            final String _tmpVideoId;
            if (_cursor.isNull(_cursorIndexOfVideoId)) {
              _tmpVideoId = null;
            } else {
              _tmpVideoId = _cursor.getString(_cursorIndexOfVideoId);
            }
            final int _tmpSkipCount;
            _tmpSkipCount = _cursor.getInt(_cursorIndexOfSkipCount);
            final long _tmpLastSkippedAt;
            _tmpLastSkippedAt = _cursor.getLong(_cursorIndexOfLastSkippedAt);
            _item = new SkipHistoryEntity(_tmpVideoId,_tmpSkipCount,_tmpLastSkippedAt);
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
