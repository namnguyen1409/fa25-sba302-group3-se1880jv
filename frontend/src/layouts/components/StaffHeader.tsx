import { Button } from "@/components/ui/button";
import { useAuth } from "@/context/AuthContext";
import { useImageLink } from "@/hooks/useImageLink";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  ActivitySquareIcon,
  LogOutIcon,
  Menu,
  MonitorCheckIcon,
  MoonIcon,
  PanelLeftClose,
  PanelLeftOpen,
  SettingsIcon,
  ShieldCheckIcon,
  SunIcon,
  UserCircle,
} from "lucide-react";
import { useNavigate } from "react-router-dom";
import { useTheme } from "@/components/theme-provider";

export function StaffHeader({
  toggleMobile,
  toggleCollapse,
  collapsed,
}: {
  toggleMobile: () => void;
  toggleCollapse: () => void;
  collapsed: boolean;
}) {
  const { user, logout } = useAuth();
  const { theme, setTheme } = useTheme();
  const navigate = useNavigate();
  const settingItems = [
    {
      path: "/account/profile",
      label: "Hồ sơ",
      icon: <UserCircle />,
    },
    {
      path: "/account/settings",
      label: "Cài đặt",
      icon: <SettingsIcon />,
    },
    {
      path: "/account/security",
      label: "Bảo mật",
      icon: <ShieldCheckIcon />,
    },
    {
      path: "/account/devices",
      label: "Thiết bị",
      icon: <MonitorCheckIcon />,
    },
    {
      path: "/account/login-activity",
      label: "Hoạt động đăng nhập",
      icon: <ActivitySquareIcon />,
    },
  ];

  if (!user) {
    return null;
  }

  return (
    <header className="w-full h-14 border-b flex items-center justify-between px-4 bg-background">
      {/* mobile hamburger */}
      <button
        className="lg:hidden p-2 rounded-md hover:bg-muted"
        onClick={toggleMobile}
      >
        <Menu className="h-6 w-6" />
      </button>

      {/* desktop collapse icon */}
      <button
        className="hidden lg:block p-2 rounded-md hover:bg-muted"
        onClick={toggleCollapse}
      >
        {collapsed ? (
          <PanelLeftOpen className="h-5 w-5" />
        ) : (
          <PanelLeftClose className="h-5 w-5" />
        )}
      </button>
      <div className="font-semibold text-lg">Clinic Panel</div>
      <div className="flex items-center gap-4">
        <Button
          variant="ghost"
          size="icon"
          onClick={() => setTheme(theme === "dark" ? "light" : "dark")}
          className="h-10 w-10"
        >
          {theme === "dark" ? (
            <SunIcon className="h-4 w-4" />
          ) : (
            <MoonIcon className="h-4 w-4" />
          )}
        </Button>

        <div className="flex items-center gap-2">
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost" className="h-12 w-12 p-0">
                <Avatar className="h-10 w-10">
                  <AvatarImage
                    src={
                      user.userProfile?.avatarUrl
                        ? useImageLink(user.userProfile.avatarUrl)
                        : undefined
                    }
                  />
                  <AvatarFallback>
                    {user.username ? user.username[0].toUpperCase() : ""}
                  </AvatarFallback>
                </Avatar>
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuLabel>
                <div className="text-sm font-medium">{user.username}</div>
                <div className="text-xs text-muted-foreground">
                  {user.email}
                </div>
              </DropdownMenuLabel>
              <DropdownMenuSeparator />
              {settingItems.map((item) => (
                <DropdownMenuItem
                  key={item.path}
                  onClick={() => navigate(item.path)}
                >
                  <div className="mr-2 flex h-5 w-5 items-center justify-center">
                    {item.icon}
                  </div>
                  {item.label}
                </DropdownMenuItem>
              ))}
              <DropdownMenuSeparator />
              <DropdownMenuItem onClick={logout}>
                <LogOutIcon className="h-10 w-25mr-2" /> Đăng xuất
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </div>
    </header>
  );
}
