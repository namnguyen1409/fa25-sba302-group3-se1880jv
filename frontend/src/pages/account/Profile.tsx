"use client";

import type { UserProfileResponse } from "@/types/account";
import { useEffect, useState } from "react";
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
import { Pencil } from "lucide-react";

export default function ProfilePage() {
  const [account, setAccount] = useState<UserProfileResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [openModal, setOpenModal] = useState(false);

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

  // UI
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
        <Card>
          <CardHeader>
            <Skeleton className="h-6 w-48" />
          </CardHeader>
          <CardContent className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <Skeleton className="h-9 w-full" />
            <Skeleton className="h-9 w-full" />
            <Skeleton className="h-9 w-full" />
            <Skeleton className="h-9 w-full" />
            <Skeleton className="h-9 w-full" />
            <Skeleton className="h-9 w-full" />
          </CardContent>
          <CardFooter className="flex gap-2">
            <Skeleton className="h-9 w-24" />
            <Skeleton className="h-9 w-24" />
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

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <Card>
        <CardHeader className="flex justify-between items-center">
          <CardTitle>Hồ sơ người dùng</CardTitle>
          <Button onClick={() => setOpenModal(true)} size="sm">
            <Pencil className="w-4 h-4 mr-2" /> Chỉnh sửa
          </Button>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="flex items-center gap-4">
            <Avatar className="h-20 w-20">
              <AvatarImage src={account.avatarUrl} />
              <AvatarFallback>
                {account.fullName
                  ? account.fullName
                      .split(" ")
                      .map((n) => n[0])
                      .join("")
                      .toUpperCase()
                  : "?"}
              </AvatarFallback>
            </Avatar>
            <div>
              <h2 className="font-semibold text-lg">
                {account.fullName || "Chưa cập nhật"}
              </h2>
              <p className="text-sm text-muted-foreground">
                {account.userEmail}
              </p>
              <p className="text-sm text-muted-foreground">
                {account.userPhone || "—"}
              </p>
            </div>
          </div>

          <Separator />

          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="text-sm text-muted-foreground">Ngày sinh</p>
              <p>{account.dateOfBirth || "Chưa cập nhật"}</p>
            </div>
            <div>
              <p className="text-sm text-muted-foreground">Địa chỉ</p>
              <p>
                {[
                  account.address?.street,
                  account.address?.wardName,
                  account.address?.city,
                ]
                  .filter(Boolean)
                  .join(", ") || "Chưa cập nhật"}
              </p>
            </div>
          </div>
        </CardContent>
      </Card>

      <FormModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        onSubmit={() => {}}
        title="Cập nhật hồ sơ"
        // big
        defaultValues={{
          fullName: account.fullName,
          userPhone: account.userPhone,
          dateOfBirth: account.dateOfBirth,
          street: account.address?.street,
          wardName: account.address?.wardName,
          city: account.address?.city,
        }}
        formFields={[
          { name: "fullName", label: "Họ và tên", required: true },
          { name: "userPhone", label: "Số điện thoại" },
          { name: "dateOfBirth", label: "Ngày sinh", type: "date" },
          { name: "street", label: "Địa chỉ",},
          { name: "city", label: "Tỉnh/Thành phố" },
          { name: "wardName", label: "Phường/Xã" },
        ]}
      />
    </div>
  );
}
