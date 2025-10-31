import React from "react";
import get from "lodash/get";
import dayjs from "dayjs";
import "dayjs/locale/vi";

dayjs.locale("vi");

interface InfoField {
  label?: string;
  name?: string;
  type?:
    | "text"
    | "date"
    | "currency"
    | "enum"
    | "phone"
    | "bool";
  map?: Record<string, any>;
  render?: (value: any, data: any) => React.ReactNode;
  component?: React.ReactNode;
}

interface InfoGroup {
  groupTitle?: string;
  fields: InfoField[];
}

interface DynamicInfoProps {
  data: any;
  config: (InfoField | InfoGroup)[];
  className?: string;
  columns?: 1 | 2 | 3;
}

const formatValue = (value: any, field: InfoField) => {
  if (field.render) return field.render(value, null);

  switch (field.type) {
    case "date":
      return value ? dayjs(value).format("DD/MM/YYYY") : "-";

    case "currency":
      return value != null
        ? value.toLocaleString("vi-VN", { style: "currency", currency: "VND" })
        : "-";

    case "enum":
      return field.map?.[value] ?? value ?? "-";

    case "bool":
      return value ? "Có" : "Không";

    case "phone":
      return value
        ? (
            <a href={`tel:${value}`} className="text-primary underline">
              {value}
            </a>
          )
        : "-";

    default:
      return value ?? "-";
  }
};

export default function DynamicInfo({
  data,
  config,
  className = "",
  columns = 2,
}: DynamicInfoProps) {
  const colClass = {
    1: "grid-cols-1",
    2: "grid-cols-2",
    3: "grid-cols-3",
  }[columns];

  const renderField = (field: InfoField, idx: number) => {
    const value = field.name ? get(data, field.name) : null;

    const content = field.component
      ? field.component
      : formatValue(value, field);

    return (
      <div key={idx} className="flex flex-col gap-0.5">
        {field.label && (
          <span className="text-sm font-medium text-muted-foreground print:text-black">
            {field.label}
          </span>
        )}
        <span className="text-base font-semibold text-foreground print:text-black">
          {content}
        </span>
      </div>
    );
  };

  return (
    <div className={`space-y-6 ${className}`}>
      {config.map((item, idx) => {
        if ("fields" in item) {
          // Group section
          return (
            <div key={idx} className="space-y-3">
              {item.groupTitle && (
                <div className="font-bold text-lg border-b pb-1 text-foreground print:text-black">
                  {item.groupTitle}
                </div>
              )}
              <div className={`grid ${colClass} gap-x-6 gap-y-3`}>
                {item.fields.map(renderField)}
              </div>
            </div>
          );
        }

        return (
          <div key={idx} className={`grid ${colClass}`}>
            {renderField(item, idx)}
          </div>
        );
      })}
    </div>
  );
}
