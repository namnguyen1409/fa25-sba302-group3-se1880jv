import { Spinner } from "@/components/ui/spinner";
import { useAuth } from "@/context/AuthContext";

const ProtectedRoute = ({
  children,
  allowedRoles,
}: {
  children: React.ReactNode;
  allowedRoles?: string[];
}) => {
  const { user, loading } = useAuth();

  if (loading) {
    return (
      <div className="flex h-full w-full">
        <Spinner className="m-auto" />
      </div>
    );
  }

  if (!user) {
    return <div>
    You must be logged in to view this page.
        Code: 401 Unauthorized
    </div>;
  }

  if (
    allowedRoles &&
    !allowedRoles.some((role) => user.roles.map((r) => r.name).includes(role))
  ) {
    return <div>You do not have permission to view this page.
        Code: 403 Forbidden
    </div>;
  }

  return <div>{children}</div>;
};

export default ProtectedRoute;
