import {
  Empty,
  EmptyContent,
  EmptyDescription,
  EmptyHeader,
  EmptyTitle,
} from "@/components/ui/empty";

const Unauthorized = () => {
  return (
    <div className="flex items-center justify-center min-h-screen">
      <Empty>
        <EmptyHeader>
          <EmptyTitle>
            401 - Unauthorized
          </EmptyTitle>
          <EmptyDescription>
            You do not have permission to view this page.
          </EmptyDescription>
        </EmptyHeader>
        <EmptyContent>
          <EmptyDescription>
            Need help? <a href="#">Contact support</a>
          </EmptyDescription>
        </EmptyContent>
      </Empty>
    </div>
  );
};

export default Unauthorized;
