package com.luxuryvault.android.core.di


import com.luxuryvault.android.data.repository.LuxuryItemRepositoryImpl
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This Hilt module defines how the application resolves
 * the LuxuryItemRepository dependency at runtime.
 *
 * The ViewModels depend only on the repository interface
 * (LuxuryItemRepository), not on a concrete implementation.
 * This follows the Dependency Inversion Principle.
 *
 * By binding FakeLuxuryItemRepositoryTest to the interface here,
 * we achieve the following:
 *
 * 1. ViewModels remain completely unaware of where data comes from.
 * 2. The concrete data source can be swapped later (Room, Retrofit)
 *    without changing any ViewModel code.
 * 3. Navigation and composables do not create repositories manually.
 * 4. Dependency wiring is centralized and declarative.
 *
 * During testing, this module is bypassed and repositories
 * are provided manually, keeping unit tests deterministic.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class LuxuryRepositoryModule
{
    @Binds
    abstract fun bindLuxuryRepository(repositoryImpl: LuxuryItemRepositoryImpl): LuxuryItemRepository

}