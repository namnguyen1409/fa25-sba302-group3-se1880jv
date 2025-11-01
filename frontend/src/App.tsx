import { BrowserRouter, Outlet, Route, Routes } from "react-router-dom";
import "./App.css"
import Login from "./pages/auth/Login";
import { Toaster } from "./components/ui/sonner";
import OAuthCallback from "./pages/auth/OAuthCallback";
import { AppProvider } from "./context/AppProvider";
import { PublicLayout } from "./layouts/PublicLayout";
import NotFound from "./pages/error/NotFound";
import ProtectedRoute from "./routes/ProtectedRoute";
import ProfilePage from "./pages/account/Profile";
import AccountSettingsPage from "./pages/account/Setting";
import VerifyEmailPage from "./pages/account/verify-email";
import AccountSecurityPage from "./pages/account/Security";
import { GoogleOAuthProvider } from "@react-oauth/google";
import AccountDevicesPage from "./pages/account/Device";
import ForgotPasswordPage from "./pages/auth/ForgotPasswordPage";
import ResetPasswordPage from "./pages/auth/ResetPasswordPage";
import LoginActivityPage from "./pages/account/loginAction";
import StaffLayout from "./layouts/StaffLayout";
import UserManagementPage from "./pages/user/UserManagerPage";
function App() {
  return (
    <AppProvider>
      <GoogleOAuthProvider clientId={import.meta.env.VITE_GOOGLE_CLIENT_ID}>
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route
              path="/verify-email"
              element={
                <ProtectedRoute>
                  <VerifyEmailPage />
                </ProtectedRoute>
              }
            />
            <Route path="/forgot-password" element={<ForgotPasswordPage />} />
            <Route path="/reset-password" element={<ResetPasswordPage />} />

            <Route
              path="/oauth/callback/:provider"
              element={<OAuthCallback />}
            />
            <Route path="/" element={<PublicLayout />}>
              <Route index element={<div>Home Page</div>} />

              <Route
                path="account"
                element={
                  <ProtectedRoute>
                    <Outlet />
                  </ProtectedRoute>
                }
              >
                <Route index element={<div>Account Overview</div>} />
                <Route path="profile" element={<ProfilePage />} />
                <Route path="settings" element={<AccountSettingsPage />} />
                <Route path="security" element={<AccountSecurityPage />} />
                <Route path="devices" element={<AccountDevicesPage />} />
                <Route path="login-activity" element={<LoginActivityPage />} />
              </Route>
            </Route>

            <Route path="/staff" element={<StaffLayout />}>
              <Route index element={<div>Staff Dashboard</div>} />
              <Route path="users" element={<UserManagementPage />} />
            </Route>

            <Route path="*" element={<NotFound />} />
          </Routes>
        </BrowserRouter>
        <Toaster
          position="top-right"
          toastOptions={{
            duration: 3000,
          }}
          closeButton
        />
      </GoogleOAuthProvider>
    </AppProvider>
  );
}

export default App;
