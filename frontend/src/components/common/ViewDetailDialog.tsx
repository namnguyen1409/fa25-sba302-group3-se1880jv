import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import DynamicInfo from "./DynamicInfo";

interface ViewDetailDialogProps {
  open: boolean;
  onClose: (v: boolean) => void;
  title: string;
  data: any;
  config: any[];
  columns?: 1 | 2 | 3;
  width?: string; // optional width
}

export function ViewDetailDialog({
  open,
  onClose,
  title,
  data,
  config,
  columns = 2,
  width = "max-w-2xl",
}: ViewDetailDialogProps) {
  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className={`${width}`}>
        <DialogHeader>
          <DialogTitle>{title}</DialogTitle>
        </DialogHeader>

        {data && (
          <DynamicInfo
            data={data}
            config={config}
            columns={columns}
          />
        )}
      </DialogContent>
    </Dialog>
  );
}
