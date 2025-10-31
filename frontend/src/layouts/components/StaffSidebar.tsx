import { Sidebar, SidebarContent, SidebarGroup, SidebarGroupLabel, SidebarMenu, SidebarMenuItem, SidebarMenuButton } from "@/components/ui/sidebar"
import { NavLink } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import { staffNavItems } from "@/config/nav-items";

export function StaffSidebar() {
  const { user } = useAuth();
  const items = staffNavItems(user?.roles.map(role => role.name) || []);

  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel>Clinic System</SidebarGroupLabel>
          <SidebarMenu>
            {items.map((item) => (
              <SidebarMenuItem key={item.to}>
                <SidebarMenuButton asChild>
                  <NavLink to={item.to}>
                    <item.icon className="mr-2 h-4 w-4" />
                    {item.label}
                  </NavLink>
                </SidebarMenuButton>
              </SidebarMenuItem>
            ))}
          </SidebarMenu>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
}
