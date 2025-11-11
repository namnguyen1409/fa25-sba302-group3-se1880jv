import { Dialog, DialogContent } from "@/components/ui/dialog";
import DocViewer, { DocViewerRenderers } from "@cyntler/react-doc-viewer";

export function FilePreviewModal({ open, onClose, fileUrl }: any) {
  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="max-w-4xl h-[85vh]">
        {fileUrl && (
          <DocViewer
            documents={[{ uri: fileUrl }]}
            pluginRenderers={DocViewerRenderers}
            style={{ height: "100%" }}
          />
        )}
      </DialogContent>
    </Dialog>
  );
}
