import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "@/pages/LoginPage";
import SsoCallbackPage from "@/pages/SsoCallbackPage";
import ChatPage from "./pages/ChatPage";
import RoomPage from "./pages/RoomPage";
import { useAuth } from "./context/AuthContext";
function App() {
  const { isAuthenticated, loading } = useAuth();

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50 text-gray-700">
        <div className="animate-pulse">Loading...</div>
      </div>
    );
  }

  return (
    <Router>
      <Routes>
        {/* Trang callback từ Clinic */}
        <Route path="/sso/callback" element={<SsoCallbackPage />} />

        {/* Trang login */}
        <Route path="/login" element={<LoginPage />} />

        {/* Trang chat chính */}
        <Route
          path="/"
          element={isAuthenticated ? <ChatPage /> : <Navigate to="/login" />}
        />
        {/* Trang room cụ thể */}
        <Route
          path="/room/:id"
          element={isAuthenticated ? <RoomPage /> : <Navigate to="/login" />}
        />
      </Routes>
    </Router>
  );
}

export default App;
