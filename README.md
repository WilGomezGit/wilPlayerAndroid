# 🎵 WilPlayer Android

> Reproductor de música Android con integración a YouTube Data API v3 y **Smart Shuffle™** — un modo aleatorio 1000x mejor que YouTube Music.

---

## ✨ Características

| Feature | Descripción |
|---|---|
| 🎬 YouTube Integration | Busca y reproduce cualquier video musical de YouTube |
| 🔀 Smart Shuffle™ | Aleatorio inteligente con pesos, historial y diversidad de artistas |
| 🎵 Mini Player | Controles siempre visibles desde cualquier pantalla |
| 📚 Biblioteca | Crea y gestiona playlists personalizadas |
| ❤️ Favoritos | Guarda tus canciones favoritas con un toque |
| 🎤 Letras | Vista de letras sincronizadas |
| 🌍 Tendencias | Top music trending en Colombia y tu región |
| 🎭 Estados de ánimo | Filtros de música por mood: Chill, Energía, Foco, etc. |
| 🔔 Notificación persistente | Control de reproducción desde la barra de notificaciones |
| 💾 Caché local | Room DB para reproducción sin conexión parcial |

---

## 🚀 Setup rápido

### 1. Obtener API Key de YouTube

1. Ve a [Google Cloud Console](https://console.cloud.google.com/apis/credentials)
2. Crea un proyecto nuevo → **Habilitar APIs** → busca **YouTube Data API v3**
3. Crea credenciales → **API Key**
4. (Recomendado) Agrega restricción: Android app → `com.wilplayer.android`

### 2. Configurar el proyecto

```bash
# Clonar el repositorio
git clone https://github.com/TU_USUARIO/wilPlayerAndroid.git
cd wilPlayerAndroid

# Crear local.properties (NO subir a git)
cp local.properties.template local.properties

# Editar local.properties y poner tu API key:
# sdk.dir=/ruta/a/tu/android/sdk
# YOUTUBE_API_KEY=AIzaSy...tu_clave_aquí
```

### 3. Abrir en Android Studio

```
File → Open → selecciona la carpeta wilPlayerAndroid
```

Espera a que Gradle sincronice (puede tardar 2-3 min la primera vez).

### 4. Ejecutar

- Conecta un dispositivo Android (API 26+) o inicia un emulador
- Presiona ▶️ Run

---

## 🔀 Smart Shuffle™ — Cómo funciona

El modo aleatorio de WilPlayer es fundamentalmente diferente al de YouTube Music:

```
YouTube Music shuffle: random() puro → repite las mismas canciones
WilPlayer Smart Shuffle™: sistema de pesos probabilístico
```

### Factores del algoritmo:

1. **Penalización por recencia** — Las últimas 8 canciones no se repetirán (peso × 0.05)
2. **Penalización por skip** — Las canciones saltadas tienen menor probabilidad (decaimiento exponencial)
3. **Balance de play count** — Canciones nunca escuchadas tienen bonus (× 1.5), las muy repetidas se reducen con curva logarítmica
4. **Spread de artista** — Máximo 2 canciones consecutivas del mismo artista
5. **Diversidad de tags** — Evita clustering de mismo género
6. **Fisher-Yates ponderado** — Selección O(n log n) con pesos dinámicos

El resultado: un shuffle que se siente "bien curado" en lugar de aleatorio puro.

---

## 📁 Arquitectura

```
app/src/main/java/com/wilplayer/android/
├── data/
│   ├── api/          # Retrofit — YouTube Data API v3
│   ├── local/        # Room DB — entities, DAOs, database
│   ├── model/        # DTOs de la API
│   └── repository/   # MusicRepository (single source of truth)
├── domain/
│   └── model/        # Song, Playlist, PlayerState, ShuffleMode
├── di/               # Hilt modules (Network + Database)
├── service/          # MusicPlayerService (Media3/ExoPlayer)
├── ui/
│   ├── screens/      # HomeScreen, SearchScreen, PlayerScreen, etc.
│   ├── components/   # Componentes reutilizables (CoverArt, MiniPlayer, etc.)
│   ├── theme/        # Design tokens (colores, gradientes, tipografía)
│   └── *ViewModel.kt # ViewModels por feature
└── util/
    ├── SmartShuffleEngine.kt  # 🔀 Algoritmo de shuffle
    └── DurationParser.kt      # ISO 8601 → ms → "3:45"
```

### Stack tecnológico

| Capa | Tecnología |
|---|---|
| UI | Jetpack Compose + Material 3 |
| State | ViewModel + StateFlow |
| DI | Hilt |
| Red | Retrofit + OkHttp |
| Audio | Media3 (ExoPlayer) + MediaSession |
| DB | Room |
| Imágenes | Coil |
| Navegación | Navigation Compose |

---

## 🎨 Design System

Tokens principales (extraídos del diseño):

```kotlin
Bg0 = Color(0xFF08080F)        // Fondo principal
AccentPurple = Color(0xFF8B5CF6)
AccentBlue   = Color(0xFF3B82F6)
GradientBrand = LinearGradient(Purple → Blue)
```

---

## 📝 Notas importantes

- **YouTube reproduce audio/video completo** — La API de YouTube Data v3 solo provee metadatos. Para la reproducción real, se usa la URL del video con ExoPlayer (YouTube-IFrame alternative).
- **Cuota de API**: El plan gratuito de YouTube Data API v3 permite ~10,000 unidades/día. Una búsqueda cuesta ~100 unidades.
- **Offline**: Las canciones cacheadas en Room estarán disponibles como metadatos, pero la reproducción requiere conexión.

---

## 🤝 Contribuciones

Creado por WilGomez. PRs bienvenidos.

---

*WilPlayer — Porque la música merece un shuffle decente.*
