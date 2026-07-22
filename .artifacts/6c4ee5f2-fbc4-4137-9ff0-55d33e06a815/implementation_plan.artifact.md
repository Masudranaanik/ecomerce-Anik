# Implementation Plan - Authentication & Realistic Checkout Flow

Adding a complete user authentication system (Login/SignUp) and a realistic order confirmation flow to make the app feel like a "real site."

## User Review Required

> [!IMPORTANT]
> - **Authentication**: I will implement a local authentication mock. Users can "Sign Up" and then "Log In" to access the store.
> - **App Flow**: The flow will now be: `Splash -> Welcome -> Login/SignUp -> Home Dashboard`.
> - **Checkout**: After placing an order in the Checkout screen, users will be taken to a beautiful **Order Success** screen.

## Proposed Changes

### 1. Navigation & Screens

#### [NEW] [LoginScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/LoginScreen.kt)
- Email and Password fields.
- "Login" button.
- "Don't have an account? Sign Up" link.

#### [NEW] [SignUpScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/SignUpScreen.kt)
- Name, Email, and Password fields.
- "Sign Up" button.
- Account creation success snackbar.

#### [NEW] [OrderSuccessScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/OrderSuccessScreen.kt)
- Animated success checkmark.
- "Order Placed Successfully!" message.
- "Continue Shopping" button to go back to Home.

---

### 2. Core Logic Updates

#### [MODIFY] [Screen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/navigation/Screen.kt)
- Add `Login`, `SignUp`, and `OrderSuccess` routes.

#### [MODIFY] [NavGraph.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/navigation/NavGraph.kt)
- Include the new auth screens and the success screen.

#### [MODIFY] [MainViewModel.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/viewmodel/MainViewModel.kt)
- Add logic to `clearCart()` when an order is placed.

#### [MODIFY] [CheckoutScreen.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/screens/CheckoutScreen.kt)
- Trigger order placement logic and navigate to `OrderSuccess`.

---

### 3. UI Polish
- Ensure Login/SignUp screens follow the **Joyful Theme** (Orange/Teal).
- Use `OutlinedTextField` with rounded corners for consistency.

## Verification Plan

### Manual Verification
- **Auth Flow**: Open app -> Welcome -> Sign Up -> Login -> Dashboard.
- **Order Flow**: Add to Cart -> Checkout -> Fill Info -> Place Order -> Success Screen.
- **Cart Reset**: Verify cart is empty after order placement.
