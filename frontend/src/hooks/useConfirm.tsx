import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { Button } from "@/components/ui/button";

export const useConfirm = () => {
  const [promise, setPromise] = useState<any>(null);

  const confirm = (message: string) =>
    new Promise((resolve) => {
      setPromise({ message, resolve });
    });

  const ConfirmDialog = () =>
    promise && (
      <Dialog open>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>{promise.message}</DialogTitle>
          </DialogHeader>
          <div className="flex justify-end gap-2">
            <Button
              variant="outline"
              onClick={() => {
                promise.resolve(false);
                setPromise(null);
              }}
            >
              Cancel
            </Button>
            <Button
              onClick={() => {
                promise.resolve(true);
                setPromise(null);
              }}
            >
              OK
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    );

  return { confirm, ConfirmDialog };
};
