package com.luxuryvault.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point for the LuxuryVault app.
 *
 * @HiltAndroidApp triggers Hilt's code generation and creates
 * the root dependency container (SingletonComponent).
 *
 * This enables:
 * - Constructor injection in ViewModels
 * - Centralized dependency graphs
 * - Lifecycle-aware dependency scoping
 *
 * Without this annotation, Hilt cannot inject dependencies
 * into Android framework classes such as ViewModels.
 *
 * This class does not contain logic; it only initializes
 * the dependency injection system for the entire app.
 */

@HiltAndroidApp
class LuxuryVaultApplication : Application() {

}
