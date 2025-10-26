"use client";

import * as React from "react";
import {
  HomeIcon,
  StethoscopeIcon,
  CalendarDaysIcon,
  SunIcon,
  MoonIcon,
  MenuIcon,
  LogInIcon,
  LogOutIcon,
  ShieldCheckIcon,
  SettingsIcon,
  MonitorCheckIcon,
  ActivitySquareIcon,
  UserCircle,
} from "lucide-react";
import { Link, useNavigate, useLocation } from "react-router-dom";

import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
} from "@/components/ui/navigation-menu";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";
import { useTheme } from "@/components/theme-provider";
import { useAuth } from "@/context/AuthContext";

export const Header: React.FC = () => {
  const { theme, setTheme } = useTheme();
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [isMobile, setIsMobile] = React.useState(window.innerWidth < 768);

  React.useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 768);
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const navItems = [
    { path: "/", label: "Trang chủ", icon: HomeIcon },
    { path: "/doctors", label: "Bác sĩ", icon: StethoscopeIcon },
    { path: "/booking", label: "Đặt lịch", icon: CalendarDaysIcon },
  ];

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

  return (
    <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur px-4 md:px-6">
      <div className="mx-auto flex h-16 max-w-screen-2xl items-center justify-between">
        <div className="flex items-center gap-2">
          {isMobile && (
            <Popover>
              <PopoverTrigger asChild>
                <Button variant="ghost" size="icon" className="h-8 w-8">
                  <MenuIcon className="h-5 w-5" />
                </Button>
              </PopoverTrigger>
              <PopoverContent align="start" className="w-56 p-2">
                <nav className="flex flex-col gap-1">
                  {navItems.map((item) => (
                    <Button
                      key={item.path}
                      variant="ghost"
                      className={cn(
                        "justify-start gap-2 w-full",
                        location.pathname === item.path &&
                          "bg-accent text-accent-foreground"
                      )}
                      onClick={() => navigate(item.path)}
                    >
                      <item.icon className="h-4 w-4" />
                      {item.label}
                    </Button>
                  ))}
                  {!user && (
                    <Button
                      variant="outline"
                      className="justify-start gap-2 w-full mt-2"
                      onClick={() => navigate("/login")}
                    >
                      <LogInIcon className="h-4 w-4" />
                      Đăng nhập
                    </Button>
                  )}
                </nav>
              </PopoverContent>
            </Popover>
          )}

          <Link
            to="/"
            className="text-primary font-bold text-xl flex items-center gap-2"
          >
            🏥 <span className="hidden sm:inline">ClinicCare</span>
          </Link>
          {!isMobile && (
            <NavigationMenu>
              <NavigationMenuList className="gap-1">
                <TooltipProvider>
                  {navItems.map((item) => (
                    <NavigationMenuItem key={item.path}>
                      <Tooltip>
                        <TooltipTrigger asChild>
                          <NavigationMenuLink
                            asChild
                            className={cn(
                              "flex h-10 w-25 items-center justify-center rounded-md hover:bg-accent hover:text-accent-foreground",
                              location.pathname === item.path &&
                                "bg-accent text-accent-foreground"
                            )}
                          >
                            <Link to={item.path}>
                              <item.icon className="h-5 w-5" />
                            </Link>
                          </NavigationMenuLink>
                        </TooltipTrigger>
                        <TooltipContent side="bottom">
                          {item.label}
                        </TooltipContent>
                      </Tooltip>
                    </NavigationMenuItem>
                  ))}
                </TooltipProvider>
              </NavigationMenuList>
            </NavigationMenu>
          )}
        </div>
        <div className="flex items-center gap-2">
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
          {user ? (
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="ghost" className="h-12 w-12 p-0">
                  <Avatar className="h-10 w-10">
                    <AvatarImage
                      src={
                        user.userProfile?.avatarUrl
                          ? user.userProfile.avatarUrl
                          : "/default-avatar.png"
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
                {/* <DropdownMenuItem onClick={() => navigate("/account")}>
                  Hồ sơ
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => navigate("/account/settings")}>
                  Cài đặt
                </DropdownMenuItem> */}
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
          ) : (
            !isMobile && (
              <Button
                variant="outline"
                size="sm"
                onClick={() => navigate("/login")}
              >
                <LogInIcon className="h-10 w-25 mr-1" />
                Đăng nhập
              </Button>
            )
          )}
        </div>
      </div>
    </header>
  );
};
