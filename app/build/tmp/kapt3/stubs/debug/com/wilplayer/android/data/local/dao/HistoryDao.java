package com.wilplayer.android.data.local.dao;

import androidx.room.*;
import com.wilplayer.android.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/wilplayer/android/data/local/dao/HistoryDao;", "", "decrementSkip", "", "videoId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSkips", "", "Lcom/wilplayer/android/data/local/entity/SkipHistoryEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentVideoIds", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pruneEmptySkips", "pruneOldHistory", "before", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordPlay", "history", "Lcom/wilplayer/android/data/local/entity/PlayHistoryEntity;", "(Lcom/wilplayer/android/data/local/entity/PlayHistoryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertSkip", "skip", "(Lcom/wilplayer/android/data/local/entity/SkipHistoryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface HistoryDao {
    
    @androidx.room.Query(value = "SELECT videoId FROM play_history ORDER BY playedAt DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentVideoIds(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recordPlay(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.entity.PlayHistoryEntity history, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM play_history WHERE playedAt < :before")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object pruneOldHistory(long before, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertSkip(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.entity.SkipHistoryEntity skip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM skip_history")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSkips(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.data.local.entity.SkipHistoryEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE skip_history SET skipCount = MAX(0, skipCount - 1) WHERE videoId = :videoId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object decrementSkip(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM skip_history WHERE skipCount <= 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object pruneEmptySkips(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}