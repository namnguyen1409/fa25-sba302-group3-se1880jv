import { Search } from "lucide-react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar";
import { useAuth } from "@/context/AuthContext";

export const Topbar = () => {
  const { user, logout } = useAuth();

  return (
    <header className="h-14 border-b bg-white dark:bg-gray-900 flex items-center px-4 justify-between">
      <div className="flex items-center gap-2 w-1/3">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input placeholder="Search patients, orders..." className="h-8" />
      </div>

      <div className="flex items-center gap-4">
        <Button variant="ghost" onClick={logout}>Logout</Button>

        <Avatar className="cursor-pointer">
          <AvatarImage src={user?.userProfile?.avatarUrl ? user.userProfile.avatarUrl : ""} />
          <AvatarFallback>{user?.username?.charAt(0)}</AvatarFallback>
        </Avatar>
      </div>
    </header>
  );
};
