

import { SidebarProvider } from "@/components/ui/sidebar";
import { Outlet } from "react-router-dom";
import { QueueDock } from "./components/QueueDock";
import { StaffSidebar } from "./components/StaffSidebar";
import { StaffHeader } from "./components/StaffHeader";
import { useState } from "react";

export default function StaffLayout() {
  const [openMobile, setOpenMobile] = useState(false);
  const [collapsed, setCollapsed] = useState(false);

  return (
    <SidebarProvider>
      <div className="flex h-screen w-full">

        <StaffSidebar
          openMobile={openMobile}
          setOpenMobile={setOpenMobile}
          collapsed={collapsed}
        />

        <div className="flex flex-col flex-1 overflow-hidden">
          <StaffHeader
            toggleMobile={() => setOpenMobile(!openMobile)}
            toggleCollapse={() => setCollapsed(!collapsed)}
            collapsed={collapsed}
          />

          <main className="flex-1 overflow-auto p-4 bg-muted/40">
            <Outlet />
          </main>

          <QueueDock />
        </div>
      </div>
    </SidebarProvider>
  );
}
