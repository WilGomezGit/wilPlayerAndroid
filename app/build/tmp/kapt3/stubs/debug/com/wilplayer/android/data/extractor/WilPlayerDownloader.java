package com.wilplayer.android.data.extractor;

import okhttp3.OkHttpClient;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Request;
import org.schabi.newpipe.extractor.downloader.Response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/wilplayer/android/data/extractor/WilPlayerDownloader;", "Lorg/schabi/newpipe/extractor/downloader/Downloader;", "()V", "client", "Lokhttp3/OkHttpClient;", "execute", "Lorg/schabi/newpipe/extractor/downloader/Response;", "request", "Lorg/schabi/newpipe/extractor/downloader/Request;", "Companion", "app_debug"})
public final class WilPlayerDownloader extends org.schabi.newpipe.extractor.downloader.Downloader {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.wilplayer.android.data.extractor.WilPlayerDownloader instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.wilplayer.android.data.extractor.WilPlayerDownloader.Companion Companion = null;
    
    private WilPlayerDownloader() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.schabi.newpipe.extractor.downloader.Response execute(@org.jetbrains.annotations.NotNull()
    org.schabi.newpipe.extractor.downloader.Request request) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/wilplayer/android/data/extractor/WilPlayerDownloader$Companion;", "", "()V", "instance", "Lcom/wilplayer/android/data/extractor/WilPlayerDownloader;", "getInstance", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wilplayer.android.data.extractor.WilPlayerDownloader getInstance() {
            return null;
        }
    }
}