import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/Login";
import { Toaster } from "./components/ui/sonner";
import OAuthCallback from "./pages/auth/OAuthCallback";
import { AppProvider } from "./context/AppProvider";
import { PublicLayout } from "./layouts/PublicLayout";
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
          </Route>
        </Routes>
      </BrowserRouter>
      <Toaster position="top-right" />
    </AppProvider>
  )
}

export default App
