import { Link, Outlet, useLocation } from "react-router-dom";
import { Button } from "@/components/ui/button";

import { cn } from "@/lib/utils";
import { useTheme } from "@/components/theme-provider";
import { useAuth } from "@/context/AuthContext";

export const PublicLayout = () => {
  const { theme, setTheme } = useTheme();
  const { user } = useAuth();
  const location = useLocation();

  const navItems = [
    { path: "/", label: "Trang chủ" },
    { path: "/doctors", label: "Bác sĩ" },
    { path: "/booking", label: "Đặt lịch" },
    { path: "/login", label: "Đăng nhập" },
  ];

  return (
    <div className={cn("min-h-screen flex flex-col", theme === "dark" && "bg-gray-900 text-white")}>
      {/* Header */}
      <header className="flex items-center justify-between px-6 py-4 border-b border-gray-200 dark:border-gray-800">
        <Link to="/" className="font-semibold text-xl text-primary">
          🏥 ClinicCare
        </Link>

        <nav className="hidden md:flex gap-6">
          {navItems.map((item) => (
            <Link
              key={item.path}
              to={item.path}
              className={cn(
                "text-sm font-medium hover:text-primary transition",
                location.pathname === item.path && "text-primary"
              )}
            >
              {item.label}
            </Link>
          ))}
        </nav>

        <Button variant="outline" size="sm" onClick={() => setTheme(theme === "dark" ? "light" : "dark")}>
          {theme === "dark" ? "☀️" : "🌙"}
        </Button>
      </header>

      {/* Content */}
      <main className="flex-1 px-4 md:px-12 py-6">
        <Outlet />
      </main>

      {/* Footer */}
      <footer className="border-t border-gray-200 dark:border-gray-800 py-4 text-center text-sm text-gray-500">
        © 2025 ClinicCare. All rights reserved.
      </footer>
    </div>
  );
};
