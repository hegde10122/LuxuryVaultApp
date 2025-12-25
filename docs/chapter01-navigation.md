# Phase 01 — Dumb UI Screens

What we are doing here :

- Layout structure
- State → UI mapping
- Click callbacks
- Empty / loading / error visuals

How we test
- Manual interaction
- Compose Preview
- Fake state injection

What we are not testing (correctly)

- Navigation correctness
- Business rules
- Data persistence
 
- This phase eliminates flaky UI tests later because UI contracts are frozen early.
- Covers: UI Contract Testing

## Flow of Navigation and Data (Diagrams)

![Navigation Graph](images/navigation.png)

---

![Part 2 - Sequence Diagram - LuxuryItemsListScreen](images/dataflow_screen1-LuxuryItemsListScreen___Data_Flow.png)


---

![Part 3 - Sequence Diagram - LuxuryItemDetailsScreen](images/dataflow2_screen2-LuxuryItemDetailsScreen___Data_Flow.png)

---

![Part 4 - Sequence Diagram - AddEditLuxuryItemScreen](images/dataflow3_screen3-AddEditLuxuryItemScreen___Data_Flow.png)

---

![Part 5 - Sequence Diagram - ImageSearchScreen](images/dataflow4_screen4-ImageSearchScreen___Data_Flow.png)

---

## Importance of Keeping Screens "Dumb"

A dumb screen means

- Receives UiState
- Emits UiEvent

Has NO IDEA about:

- Room
- Retrofit
- Hilt
- Repository
- UseCases

This guarantees:

- Easy UI testing
- Minimal or zero refactor later
- Compose previews work
