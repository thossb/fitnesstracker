![image](https://github.com/user-attachments/assets/dee85d8b-7577-4170-aaf4-ef2974ac0acf)

https://drive.google.com/drive/folders/19-yvvE0cbAmGaMMBOfdwteuaokJ3mPin?usp=sharing

## 🎯 Deskripsi Singkat Proyek
### Tema Aplikasi
Fitness & Health Tracking - Aplikasi mobile untuk melacak aktivitas kebugaran dan kesehatan pribadi dengan interface yang modern dan user-friendly.
Tujuan Proyek

Membantu pengguna melacak berbagai jenis workout dan aktivitas fisik
Menyediakan analisis progress kebugaran secara visual dan terstruktur
Memotivasi konsistensi olahraga melalui tracking harian dan historical data
Memberikan insight tentang pola olahraga dan kalori yang terbakar
Implementasi best practices Android development dengan MVVM Architecture

### Target Pengguna

Fitness enthusiasts yang ingin tracking workout secara detail
Pemula olahraga yang butuh motivasi dan monitoring progress
Atlet amateur yang ingin analisis performa training
Health-conscious individuals yang peduli dengan aktivitas harian
Usia target: 18-45 tahun dengan lifestyle aktif


## ⭐ Daftar Fitur Utama
1. 📊 Dashboard Screen

Today's Summary Cards - Kalori terbakar, durasi workout, jumlah aktivitas hari ini
Workout Distribution Chart - Visualisasi persentase jenis workout
Recent Activities List - 5 aktivitas terakhir dengan detail timestamp
Quick Stats Overview - Ringkasan performa harian yang mudah dibaca

2. ➕ Add Workout Screen

Workout Type Selection - Cardio, Strength, Flexibility, Sports
Dynamic Subtype Options - Sub-kategori yang berubah sesuai jenis workout
Duration & Intensity Input - Input waktu dan level intensitas (Low/Medium/High)
Calories Calculator - Input manual kalori yang terbakar
Notes Feature - Catatan personal untuk setiap workout
Form Validation - Validasi input untuk memastikan data akurat

3. 📋 History Screen

Complete Workout History - Daftar semua workout yang pernah dilakukan
Advanced Filtering - Filter berdasarkan jenis workout (Cardio, Strength, dll)
Detailed Workout Cards - Informasi lengkap setiap workout dengan metadata
Delete Functionality - Hapus workout yang salah input
Date Sorting - Urutan chronological dari yang terbaru

4. 📈 Progress Screen

Weekly Statistics - Analisis performa 7 hari terakhir
Monthly Overview - Summary performa bulanan dengan rata-rata
Workout Type Analysis - Breakdown progress per kategori olahraga
Trend Visualization - Grafik progress untuk motivasi berkelanjutan

5. 🧭 Navigation System

Bottom Navigation Bar - Navigasi intuitif dengan 4 tab utama
Smooth Transitions - Perpindahan antar screen yang fluid
State Management - Maintain state saat berpindah screen
User-Friendly Icons - Material Design icons yang familiar

6. 💾 Data Persistence

Room Database - Local storage untuk offline functionality
Real-time Updates - Data sync otomatis antar screens
Data Safety - Backup dan recovery data workout
Performance Optimization - Efficient database queries


## 🏗️ Struktur MVVM (Model-View-ViewModel)
📁 Architecture Overview
```
app/src/main/java/com/example/fitnesstracker/
├── data/                     # DATA LAYER
│   ├── model/               # Data Models & Entities
│   ├── database/            # Room Database Components  
│   └── repository/          # Data Repository Pattern
├── ui/                      # PRESENTATION LAYER
│   ├── screens/             # Composable UI Screens
│   ├── viewmodels/          # Business Logic ViewModels
│   ├── components/          # Reusable UI Components
│   └── theme/               # UI Theming & Styling
└── navigation/              # Navigation Logic
```
## 🔄 MVVM Pattern Implementation
### MODEL (Data Layer)

Workout.kt - Entity class dengan Room annotations
WorkoutType.kt - Enums untuk kategorisasi workout
WorkoutDao.kt - Database Access Object dengan suspend functions
WorkoutDatabase.kt - Room database configuration dengan TypeConverters
WorkoutRepository.kt - Single source of truth untuk data access

### VIEW (UI Layer)

DashboardScreen.kt - Stateless Composable untuk dashboard
AddWorkoutScreen.kt - Form UI dengan reactive state
HistoryScreen.kt - List UI dengan filtering capabilities
ProgressScreen.kt - Data visualization components
FitnessTrackerApp.kt - Main navigation container

### VIEWMODEL (Business Logic)

DashboardViewModel.kt - Dashboard business logic & state management
AddWorkoutViewModel.kt - Form validation & workout creation logic
HistoryViewModel.kt - History filtering & workout deletion logic
State Management - MutableStateFlow untuk reactive UI updates

### 🎯 MVVM Benefits dalam Proyek Ini

Separation of Concerns - Setiap layer punya responsibility yang jelas
Testability - ViewModels mudah di-unit test tanpa UI dependencies
Maintainability - Code terorganisir dan mudah untuk scale
Reusability - Components dan ViewModels bisa digunakan ulang
Configuration Changes - ViewModel survive device rotation
