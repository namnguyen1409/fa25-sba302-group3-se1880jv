

import type { UserProfileResponse } from "@/types/account";
import { useEffect, useState, useRef } from "react";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Separator } from "@/components/ui/separator";
import { Skeleton } from "@/components/ui/skeleton";
import { toast } from "sonner";
import { AccountApi } from "@/api/user/accountApi";
import { FormModal } from "@/components/common/FormModal";
import { Calendar, MapPin, Pencil, Camera } from "lucide-react";
import * as yup from "yup";
import { useImageLink } from "@/hooks/useImageLink";
import { useAuth } from "@/context/AuthContext";
import CropModal from "@/components/common/CropModal";

export default function ProfilePage() {
  const [account, setAccount] = useState<UserProfileResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [openModal, setOpenModal] = useState(false);
  const fileInputRef = useRef<HTMLInputElement | null>(null);

  const [openCrop, setOpenCrop] = useState(false);
  const [previewUrl, setPreviewUrl] = useState<string | null>(null);
  const [selectedFile, setSelectedFile] = useState<File | null>(null);

  const handleAvatarSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files?.[0]) return;
    const file = e.target.files[0];
    setSelectedFile(file);
    setPreviewUrl(URL.createObjectURL(file));
    setOpenCrop(true);
  };

  const handleCropSave = async (croppedFile: File) => {
    toast.loading("Đang tải ảnh lên...");
    const res = await AccountApi.updateAvatar(croppedFile);
    setAccount(res);
    setUser((prev) =>
      prev
        ? {
            ...prev,
            userProfile: {
              fullName: res.fullName,
              avatarUrl: res.avatarUrl,
            },
          }
        : prev
    );
    toast.success("Cập nhật avatar thành công!");
    toast.dismiss();
  };

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const data = await AccountApi.getUserProfile();
        setAccount(data);
      } catch (e: any) {
        toast.error(e.message || "Có lỗi xảy ra khi tải hồ sơ");
      } finally {
        setLoading(false);
      }
    })();
  }, [toast]);

  const { setUser } = useAuth();

  if (loading) {
    return (
      <div className="p-4 grid gap-4 md:grid-cols-[340px,1fr]">
        <Card>
          <CardHeader>
            <Skeleton className="h-6 w-40" />
          </CardHeader>
          <CardContent className="flex items-center gap-4">
            <Skeleton className="h-20 w-20 rounded-full" />
            <div className="space-y-2 w-full">
              <Skeleton className="h-4 w-2/3" />
              <Skeleton className="h-4 w-1/2" />
            </div>
          </CardContent>
          <CardFooter>
            <Skeleton className="h-9 w-28" />
          </CardFooter>
        </Card>
      </div>
    );
  }

  if (!account) {
    return (
      <div className="p-4">
        <Card>
          <CardContent>
            <Skeleton className="h-6 w-40" />
          </CardContent>
        </Card>
      </div>
    );
  }

  const formFields = [
    { name: "fullName", label: "Họ và tên", type: "text" as const },
    { name: "phone", label: "Số điện thoại", type: "text" as const },
    { name: "dateOfBirth", label: "Ngày sinh", type: "date" as const },
    { name: "street", label: "Địa chỉ", type: "text" as const },
    { name: "city", label: "Tỉnh/Thành phố", type: "text" as const },
    { name: "wardName", label: "Phường/Xã", type: "text" as const },
  ];

  const schema = yup.object().shape({
    fullName: yup.string().required("Họ và tên là bắt buộc"),
    userPhone: yup
      .string()
      .matches(/^[0-9]{10,15}$/, "Số điện thoại không hợp lệ")
      .nullable(),
    dateOfBirth: yup
      .date()
      .min(new Date("1900-01-01"), "Ngày sinh không hợp lệ")
      .max(new Date(), "Ngày sinh không hợp lệ")
      .nullable(),
    street: yup.string(),
    city: yup.string(),
    wardName: yup.string(),
  });

  const handleSubmit = async (data: any) => {
    try {
      const response = await AccountApi.updateUserProfile({
        ...data,
        address: {
          street: data.street,
          wardName: data.wardName,
          city: data.city,
        },
      });
      toast.success("Cập nhật hồ sơ thành công");
      setAccount(response);
      setUser((prev) =>
        prev
          ? {
              ...prev,
              userProfile: {
                fullName: response.fullName,
                avatarUrl: response.avatarUrl,
              },
            }
          : prev
      );
      setOpenModal(false);
    } catch (e: any) {
      toast.error(e.message || "Có lỗi xảy ra khi cập nhật hồ sơ");
    }
  };

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <Card>
        <CardHeader className="flex justify-between items-center">
          <CardTitle>Hồ sơ người dùng</CardTitle>
          <Button onClick={() => setOpenModal(true)} size="sm">
            <Pencil className="w-4 h-4 mr-2" /> Chỉnh sửa
          </Button>
        </CardHeader>

        <CardContent className="p-6 bg-muted/30 rounded-lg">
          <div className="grid md:grid-cols-[auto,1fr] gap-6 items-center">
            <div className="relative group cursor-pointer w-fit">
              <Avatar
                className="h-24 w-24 border-2 border-primary/20 group-hover:opacity-80 transition"
                onClick={() => fileInputRef.current?.click()}
              >
                <AvatarImage src={useImageLink(account.avatarUrl)} />
                <AvatarFallback className="text-xl font-semibold">
                  {account.fullName
                    ? account.fullName
                        .split(" ")
                        .map((n) => n[0])
                        .join("")
                        .toUpperCase()
                    : "?"}
                </AvatarFallback>
              </Avatar>
                <div className="absolute bottom-0.5 right-10 rounded-full p-1 shadow group-hover:scale-110 transition">
                  <Camera className="h-4 w-4" />
                </div>
              <input
                type="file"
                accept="image/*"
                ref={fileInputRef}
                className="hidden"
                onChange={handleAvatarSelect}
              />
            </div>

            <div>
              <h2 className="text-xl font-bold">
                {account.fullName || "Chưa cập nhật"}
              </h2>
              <p className="text-sm text-muted-foreground">
                {account.userEmail}
              </p>
              <p className="text-sm text-muted-foreground">
                {account.phone || "—"}
              </p>
            </div>
          </div>

          <Separator className="my-4" />

          <div className="grid md:grid-cols-2 gap-4">
            <div className="flex flex-col">
              <span className="text-xs text-muted-foreground mb-1 flex items-center gap-1">
                <Calendar className="h-4 w-4" /> Ngày sinh
              </span>
              <span className="text-sm">
                {account.dateOfBirth || "Chưa cập nhật"}
              </span>
            </div>

            <div className="flex flex-col">
              <span className="text-xs text-muted-foreground mb-1 flex items-center gap-1">
                <MapPin className="h-4 w-4" /> Địa chỉ
              </span>
              <span className="text-sm">
                {[
                  account.address?.street,
                  account.address?.wardName,
                  account.address?.city,
                ]
                  .filter(Boolean)
                  .join(", ") || "Chưa cập nhật"}
              </span>
            </div>
          </div>
        </CardContent>
      </Card>

      <FormModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        onSubmit={handleSubmit}
        title="Cập nhật hồ sơ"
        big
        defaultValues={{
          fullName: account.fullName,
          phone: account.phone,
          dateOfBirth: account.dateOfBirth,
          street: account.address?.street,
          city: account.address?.city,
          wardName: account.address?.wardName,
        }}
        formFields={formFields}
        schema={schema}
      />
      {previewUrl && selectedFile && (
        <CropModal
          imageUrl={previewUrl}
          open={openCrop}
          onClose={() => setOpenCrop(false)}
          onSave={handleCropSave}
          originalFile={selectedFile}
        />
      )}
    </div>
  );
}
