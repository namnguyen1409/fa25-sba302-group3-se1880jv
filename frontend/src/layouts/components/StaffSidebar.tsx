import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuItem,
  SidebarMenuButton,
} from "@/components/ui/sidebar";
import { NavLink } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import { staffNavItems } from "@/config/nav-items";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";
import { Sheet, SheetContent } from "@/components/ui/sheet";

export function StaffSidebar({
  openMobile,
  setOpenMobile,
  collapsed,
}: {
  openMobile: boolean;
  setOpenMobile: (open: boolean) => void;
  collapsed: boolean;
}) {
  const { user } = useAuth();
  const items = staffNavItems(user?.roles.map((r) => r.name) || []);

  const width = collapsed ? "w-16" : "w-60";

  const MenuItem = ({ item }: { item: (typeof items)[number] }) => (
    <NavLink to={item.to}>
      {({ isActive }) => {
        const IconBtn = (
          <SidebarMenuButton
            isActive={isActive}
            className={`flex items-center ${
              collapsed ? "justify-center px-0 w-full" : "justify-start gap-3"
            }`}
          >
            <item.icon className="h-5 w-5" />
            {!collapsed && (
              <span className="whitespace-nowrap">{item.label}</span>
            )}
          </SidebarMenuButton>
        );

        return collapsed ? (
          <Tooltip>
            <TooltipTrigger className="w-full">{IconBtn}</TooltipTrigger>
            <TooltipContent side="right">{item.label}</TooltipContent>
          </Tooltip>
        ) : (
          IconBtn
        );
      }}
    </NavLink>
  );

  return (
    <>
      {/* Desktop */}
      <div
        className={`hidden lg:flex flex-col transition-all duration-300 border-r bg-background ${width}`}
      >
        <Sidebar className={`flex-1 ${width}`}>
          <SidebarContent>
            <SidebarGroup>
              {!collapsed && (
                <SidebarGroupLabel>Clinic System</SidebarGroupLabel>
              )}

              <SidebarMenu>
                <TooltipProvider>
                  {items.map((item) => (
                    <SidebarMenuItem key={item.to}>
                      <MenuItem item={item} />
                    </SidebarMenuItem>
                  ))}
                </TooltipProvider>
              </SidebarMenu>
            </SidebarGroup>
          </SidebarContent>
        </Sidebar>
      </div>

      <Sheet open={openMobile} onOpenChange={setOpenMobile}>
        <SheetContent side="left" className="p-0 w-64">
          <SidebarContent className="px-2 pt-4">
            {items.map((item) => (
              <SidebarMenuItem key={item.to} className="mb-1 list-none mt-2 ">
                <MenuItem item={item} />
              </SidebarMenuItem>
            ))}
          </SidebarContent>
        </SheetContent>
      </Sheet>
    </>
  );
}
