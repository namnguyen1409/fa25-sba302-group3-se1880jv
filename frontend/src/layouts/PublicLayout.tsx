import { Outlet } from "react-router-dom";
import { cn } from "@/lib/utils";
import { useTheme } from "@/components/theme-provider";
import { Header } from "./components/Header";
import { Footer } from "./components/Footer";

export const PublicLayout = () => {
  const { theme } = useTheme();

  return (
    <div className={cn("min-h-screen flex flex-col", theme === "dark" && "bg-gray-900 text-white")}>
      <Header />
      <main className="flex-1 px-4 md:px-12 py-6">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};
