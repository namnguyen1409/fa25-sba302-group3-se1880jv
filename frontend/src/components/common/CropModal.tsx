import Cropper from "react-easy-crop";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import getCroppedImg from "@/utils/getCroppedImg";

interface CropModalProps {
  imageUrl: string;
  open: boolean;
  onClose: () => void;
  onSave: (file: File) => void;
  originalFile: File;
}

export default function CropModal({
  imageUrl,
  open,
  onClose,
  onSave,
  originalFile,
}: CropModalProps) {
  const [crop, setCrop] = useState({ x: 0, y: 0 });
  const [zoom, setZoom] = useState(1);
  const [croppedAreaPixels, setCroppedAreaPixels] = useState<any>(null);

  if (!open) return null;

  const handleSave = async () => {
    const croppedBlob = await getCroppedImg(imageUrl, croppedAreaPixels);
    const croppedFile = new File([croppedBlob], originalFile.name, {
      type: originalFile.type,
    });
    onSave(croppedFile);
    onClose();
  };

  return (
    <div className="fixed inset-0 bg-black/60 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-4 w-[90vw] max-w-md flex flex-col items-center gap-3">
        <div className="relative w-full aspect-square bg-black/80 rounded-md overflow-hidden">
          <Cropper
            image={imageUrl}
            crop={crop}
            zoom={zoom}
            aspect={1}
            cropShape="round"
            showGrid={false}
            onCropChange={setCrop}
            onZoomChange={setZoom}
            onCropComplete={(_, area) => setCroppedAreaPixels(area)}
          />
        </div>

        <input
          type="range"
          min={1}
          max={3}
          step={0.1}
          value={zoom}
          onChange={(e) => setZoom(Number(e.target.value))}
          className="w-full"
        />

        <div className="flex gap-2 mt-2">
          <Button variant="outline" onClick={onClose}>
            Hủy
          </Button>
          <Button onClick={handleSave}>Lưu ảnh</Button>
        </div>
      </div>
    </div>
  );
}
