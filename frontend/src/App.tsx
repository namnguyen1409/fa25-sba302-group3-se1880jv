import { BrowserRouter, Outlet, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/Login";
import { Toaster } from "./components/ui/sonner";
import OAuthCallback from "./pages/auth/OAuthCallback";
import { AppProvider } from "./context/AppProvider";
import { PublicLayout } from "./layouts/PublicLayout";
import NotFound from "./pages/error/NotFound";
import ProtectedRoute from "./routes/ProtectedRoute";
import ProfilePage from "./pages/account/Profile";
import AccountSettingsPage from "./pages/account/Setting";
function App() {
 
  return (
    <AppProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login/>} />
          <Route path="/oauth/callback/:provider" element={<OAuthCallback />} />
          <Route path="/dashboard" element={<div>Dashboard Page</div>} />
          <Route path="/" element={<PublicLayout />}>
            <Route index element={<div>Home Page</div>} />

            <Route path = "account" element={<ProtectedRoute>
                <Outlet />
            </ProtectedRoute>}
            >
              <Route index element={<div>Account Overview</div>} />
              <Route path="profile" element={<ProfilePage />} />
              <Route path="settings" element={<AccountSettingsPage/>} />
            </Route>
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
    </AppProvider>
  )
}

export default App
