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
  Bell,
  LogOutIcon,
  MonitorCheckIcon,
  SettingsIcon,
  ShieldCheckIcon,
  UserCircle
} from "lucide-react";
import { useNavigate } from "react-router-dom";

export function StaffHeader() {
  const { user, logout } = useAuth();
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
      <div className="font-semibold text-lg">Clinic Panel</div>
      <div className="flex items-center gap-4">
        <Bell className="w-5 h-5" />
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
                    {user.username[0].toUpperCase()}
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
