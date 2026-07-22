# Implementation Plan - Stunning UI Overhaul & Admin Panel

This plan addresses the product card visibility issues, creates a "stunning" new design, and implements the requested Admin Panel for dynamic product management.

## User Review Required

> [!IMPORTANT]
> - **Product Design**: I will switch to a **"Modern Boutique"** style. The card will have a taller aspect ratio, cleaner typography, and the "Add to Cart" button will be integrated more elegantly.
> - **Admin Panel**: You will be able to add products with Name, Price, Description, and Image URL. These will be saved locally on your device and will appear alongside existing products.
> - **Visibility Fix**: I will remove rigid height constraints where possible or increase them significantly to ensure the price is never hidden.

## Proposed Changes

### 1. "Stunning" Product Card Redesign

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/HomeScreen.kt)
- **Visual Style**:
    - Use a **very light Primary background** for the image area to make it feel premium.
    - Remove the hard card border; use a **soft ambient shadow**.
    - Position the **Price** in a large, bold font at the bottom left.
    - Change the **Add Button** to a floating circle that sits on the edge of the image and text section.
- **Layout Fix**: Use `Modifier.heightIn(min = 340.dp)` to allow the card to grow if the title is long, ensuring the price is always visible.

---

### 2. Admin Panel & Local Persistence

#### [NEW] [AdminScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/AdminScreen.kt)
- A form with `OutlinedTextField`s for Title, Price, Category, Description, and Image URL.
- An "Upload Product" button with a success animation.

#### [NEW] [ProductEntity.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/data/local/ProductEntity.kt)
- A new Room entity to store user-added products.

#### [MODIFY] [AppDatabase.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/data/local/AppDatabase.kt) & [ProductRepositoryImpl.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/data/repository/ProductRepositoryImpl.kt)
- Update the database and repository to merge API products with local products.

---

### 3. Navigation & Profile Update

#### [MODIFY] [ProfileScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/ProfileScreen.kt)
- Add a dedicated **"Admin Dashboard"** item with a gear icon.

#### [MODIFY] [Screen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/navigation/Screen.kt) & [NavGraph.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/navigation/NavGraph.kt)
- Add the `Admin` route and connect it.

## Verification Plan

### Manual Verification
- **UI Check**: Confirm product images are large and clear, and the price is fully visible at the bottom.
- **Admin Check**: Add a new product via the Admin Panel and verify it appears on the Home Screen immediately.
- **Persistence**: Restart the app and ensure the added product is still there.
