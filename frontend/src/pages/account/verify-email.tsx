

import { useEffect, useState } from "react";

import { AccountApi } from "@/api/user/accountApi";
import { Loader2, CheckCircle2, XCircle } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useNavigate, useSearchParams } from "react-router";

export default function VerifyEmailPage() {
  const [searchParams, _] = useSearchParams();
  const token = searchParams.get("token");
  const navigate = useNavigate();

  const [status, setStatus] = useState<
    "loading" | "success" | "error" | "invalid"
  >("loading");

  useEffect(() => {
    if (!token) {
      setStatus("invalid");
      return;
    }

    (async () => {
      try {
        await AccountApi.confirmEmailChange(token);
        setStatus("success");
      } catch (err: any) {
        console.error(err);
        setStatus("error");
      }
    })();
  }, [token]);

  return (
    <div className="flex items-center justify-center min-h-screen bg-muted/30">
      <Card className="max-w-md w-full text-center">
        <CardHeader>
          <CardTitle>Xác minh thay đổi email</CardTitle>
        </CardHeader>
        <CardContent className="py-6 space-y-4">
          {status === "loading" && (
            <>
              <Loader2 className="animate-spin mx-auto h-10 w-10 text-primary" />
              <p className="text-sm text-muted-foreground">
                Đang xác minh, vui lòng đợi...
              </p>
            </>
          )}

          {status === "success" && (
            <>
              <CheckCircle2 className="mx-auto h-10 w-10 text-green-500" />
              <p className="text-green-700 font-medium">
                Xác minh thành công! Email của bạn đã được cập nhật.
              </p>
              <Button
                className="mt-3"
                onClick={() => navigate("/account/settings")}
              >
                Về trang cài đặt
              </Button>
            </>
          )}

          {status === "error" && (
            <>
              <XCircle className="mx-auto h-10 w-10 text-red-500" />
              <p className="text-red-700 font-medium">
                Xác minh thất bại hoặc token đã hết hạn.
              </p>
              <Button
                className="mt-3"
                variant="outline"
                onClick={() => navigate("/")}
              >
                Quay lại trang chủ
              </Button>
            </>
          )}

          {status === "invalid" && (
            <>
              <XCircle className="mx-auto h-10 w-10 text-red-500" />
              <p className="text-red-700 font-medium">
                Token không hợp lệ. Vui lòng kiểm tra lại email.
              </p>
            </>
          )}
        </CardContent>
      </Card>
    </div>
  );
}
