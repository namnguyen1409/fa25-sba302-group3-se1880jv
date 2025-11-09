import { RegisterForm } from "@/components/RegisterForm";
import { GalleryVerticalEnd } from "lucide-react";

const RegisterPage = () => {
  return (
    <div className="grid min-h-svh lg:grid-cols-2">
      {/* LEFT: Form */}
      <div className="flex flex-col gap-4 p-6 md:p-10">
        {/* Logo */}
        <div className="flex justify-center gap-2 md:justify-start">
          <a href="#" className="flex items-center gap-2 font-medium">
            <div className="bg-primary text-primary-foreground flex size-6 items-center justify-center rounded-md">
              <GalleryVerticalEnd className="size-4" />
            </div>
            Clinic App
          </a>
        </div>

        {/* Form */}
        <div className="flex flex-1 items-center justify-center">
          <div className="w-full max-w-xs">
            <RegisterForm />
          </div>
        </div>
      </div>

      {/* RIGHT: Background Image */}
      <div className="bg-muted relative hidden lg:block">
        <img
          src="/image.png"
          alt="Image"
          className="absolute inset-0 h-full w-full object-cover dark:brightness-[0.2] dark:grayscale"
        />
      </div>
    </div>
  );
};

export default RegisterPage;
