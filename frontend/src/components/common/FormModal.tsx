

import React, { useEffect } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import {
  Form,
  FormField,
  FormItem,
  FormLabel,
  FormControl,
  FormMessage,
} from "@/components/ui/form";
import { Checkbox } from "@/components/ui/checkbox";
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem,
} from "@/components/ui/select";
import { useForm, Controller } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

type Option = { value: string; label: string };

export interface FormFieldConfig {
  name: string;
  label: string;
  type?: string;
  required?: boolean;
  placeholder?: string;
  options?: Option[];
  defaultValue?: any;
  renderFormItem?: (props: {
    value: any;
    onChange: (v: any) => void;
    watch: (name: string) => any;
  }) => React.ReactNode;
}

export interface FormModalProps {
  open: boolean;
  onClose: () => void;
  onSubmit: (data: any) => void;
  title: string;
  formFields: FormFieldConfig[];
  defaultValues?: Record<string, any>;
  big?: boolean;
  schema?: yup.ObjectSchema<any>;
}

export const FormModal: React.FC<FormModalProps> = ({
  open,
  onClose,
  onSubmit,
  title,
  formFields,
  defaultValues = {},
  big = false,
  schema: customSchema,
}) => {
  const generatedSchema = yup.object(
    Object.fromEntries(
      formFields.map((f) => [
        f.name,
        f.required
          ? f.type === "checkbox"
            ? yup.boolean().oneOf([true], `${f.label} is required`)
            : f.type === "number"
            ? yup
                .number()
                .typeError(`${f.label} must be a number`)
                .required(`${f.label} is required`)
            : f.type === "url" || f.name.toLowerCase().includes("url")
            ? yup
                .string()
                .url(`${f.label} must be a valid URL`)
                .required(`${f.label} is required`)
            : yup.string().required(`${f.label} is required`)
          : yup.mixed(),
      ])
    )
  );

  const schema = customSchema || generatedSchema;

  const form = useForm({
    defaultValues,
    resolver: yupResolver(schema),
  });

  const { control, handleSubmit, reset, watch } = form;

  useEffect(() => {
    if (open) {
      reset(defaultValues || {});
    }
  }, [open, defaultValues, reset]);

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent
        className={`${
          big ? "min-w-4xl" : "max-w-lg"
        } max-h-[80vh] overflow-y-auto`}
      >
        <DialogHeader>
          <DialogTitle>{title}</DialogTitle>
        </DialogHeader>

        <Form {...form}>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4 py-2">
            {formFields.map((f) => (
              <FormField
                key={f.name}
                control={control}
                name={f.name}
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>{f.label}</FormLabel>
                    <FormControl>
                      {f.renderFormItem ? (
                        <Controller
                          name={f.name}
                          control={control}
                          render={({ field: { value, onChange } }) => (
                            <>
                              {f.renderFormItem!({
                                value,
                                onChange,
                                watch,
                              })}
                            </>
                          )}
                        />
                      ) : f.type === "select" ? (
                        <Select
                          value={field.value || f.defaultValue || ""}
                          onValueChange={field.onChange}
                        >
                          <SelectTrigger>
                            <SelectValue placeholder="Select..." />
                          </SelectTrigger>
                          <SelectContent>
                            {f.options?.map((opt) => (
                              <SelectItem key={opt.value} value={opt.value}>
                                {opt.label}
                              </SelectItem>
                            ))}
                          </SelectContent>
                        </Select>
                      ) : f.type === "checkbox" ? (
                        <div className="flex items-center space-x-2">
                          <Checkbox
                            checked={!!field.value}
                            onCheckedChange={field.onChange}
                          />
                          <label>{f.label}</label>
                        </div>
                      ) : f.type === "textarea" ? (
                        <Textarea
                          placeholder={f.placeholder || ""}
                          {...field}
                        />
                      ) : (
                        <Input
                          type={f.type || "text"}
                          placeholder={f.placeholder || ""}
                          {...field}
                        />
                      )}
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            ))}

            <DialogFooter className="pt-4">
              <Button variant="outline" type="button" onClick={onClose}>
                Cancel
              </Button>
              <Button type="submit">Save</Button>
            </DialogFooter>
          </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
};
