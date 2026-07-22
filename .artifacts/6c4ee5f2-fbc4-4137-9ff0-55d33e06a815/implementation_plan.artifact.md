# Implementation Plan - Ultra-Premium UI & Guest Auth Flow

Refining the app entry flow to be guest-first and redesigning product components to have a "mind-blowing" premium look.

## User Review Required

> [!IMPORTANT]
> - **Auth Flow**: The app will now launch directly into the store after the Welcome screen. Login/SignUp will be moved to the **Profile** tab.
> - **Product UI**: I will implement a modern "Floating Glass" design for product cards with soft gradients and subtle shadows.

## Proposed Changes

### 1. Entry Flow & Auth Relocation

#### [MODIFY] [NavGraph.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/navigation/NavGraph.kt)
- Update `Welcome` screen to navigate directly to `"main_dashboard"`.
- Keep `Login` and `SignUp` routes available for navigation from the Profile tab.

#### [MODIFY] [ProfileScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/ProfileScreen.kt)
- Redesign the profile header to show "Guest User" by default.
- Add a vibrant "Login or Join Now" button at the top.
- Navigation link to the Login screen.

---

### 2. "Mind-Blowing" Product Card Design

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/HomeScreen.kt)
- **Visual Overhaul**:
    - Use a soft **Inner Shadow** effect (via gradients) for product images.
    - Add a "Floating Heart" (Wishlist) icon in the top-right corner of the image.
    - Implement a "Glassmorphism" footer for the price and title.
    - Add a **Sales Badge** (e.g., "New" or "-20%") for a realistic e-commerce feel.
- **Micro-interactions**: Subtle elevation increase on tap.

---

### 3. Verification Plan

#### Manual Verification
- **Entry Check**: Splash -> Welcome -> Home (No login prompt).
- **Profile Check**: Open Profile, see Guest info, click Login -> Navigate to Login Screen.
- **Visual Check**: Inspect the new product cards on Home and Catalog screens.
