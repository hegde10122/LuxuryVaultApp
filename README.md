# Luxury Vault App
A modular, testable, and scalable Android application that 
enables users to curate a luxury inventory used by billionaires — while strictly following Clean Architecture, 
MVVM, Room, Retrofit, Jetpack Compose, and Hilt best practices.


## Core User Capabilities

A user should be able to:

View a list of luxury items
- Items are stored locally and available offline.
- Each item contains structured metadata.

Add a new luxury item
- Name (e.g., “Gulfstream G700”)
- Category (Jet, Yacht, Watch, Car, Real Estate, Art, etc.)
- Brand / Manufacturer
- Estimated value
- Description
- Image

Search luxury item images online

- Fetch images from a remote image search API.
- Select an image and attach it to a luxury item.

Use the app fully offline
- Previously saved items remain accessible without internet.
- Images selected earlier are cached locally.

## Functional Requirements
- Maintain a local catalog of luxury items using a Room database.
- Allow users to create, read, and search luxury items.
- Integrate a remote image search service using Retrofit.

Ensure offline-first behavior:
- Local DB is the source of truth.
- Network is used only for enrichment (image search).

## Non-Functional Requirements
- Offline resilience: App must not break without network.
- Scalability: Easy to add new luxury categories or data sources later.
- Testability: Business logic must be independently testable.
- Maintainability: Clear separation of responsibilities.
- UI Responsiveness: Smooth UI using modern Compose patterns.

## Architectural Constraints
The app must be built using:
- Clean Architecture
- Clear separation of Presentation, Domain, and Data layers.
- Domain layer must not depend on Android or framework code.

MVVM
- ViewModels manage UI state.
- UI observes state via StateFlow / Flow.

Jetpack Compose
- Declarative UI.
- No XML or Fragments.

Room
- Local persistence for luxury items.

Retrofit
- Remote image search API integration.

Hilt
- Dependency injection across all layers.

## Architectural Goals
- UI is fully decoupled from data sources.
- Business rules (e.g., validation, sorting, filtering) live in the domain layer.
- Repositories abstract away data origin (local vs remote).

The system is easy to extend:
- Add price history
- Add multiple image providers
- Sync with cloud later

