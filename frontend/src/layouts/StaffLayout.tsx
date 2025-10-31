"use client";

import { SidebarProvider } from "@/components/ui/sidebar";
import { Outlet } from "react-router-dom";
import { QueueDock } from "./components/QueueDock";
import { StaffSidebar } from "./components/StaffSidebar";
import { StaffHeader } from "./components/StaffHeader";

export default function StaffLayout() {
  return (
    <SidebarProvider>
      <div className="flex h-screen w-full">
        <StaffSidebar />
        <div className="flex flex-col flex-1 overflow-hidden">
          <StaffHeader />

          <main className="flex-1 overflow-auto p-4 bg-muted/40">
            <Outlet />
          </main>

          <QueueDock />
        </div>
      </div>
    </SidebarProvider>
  );
}
