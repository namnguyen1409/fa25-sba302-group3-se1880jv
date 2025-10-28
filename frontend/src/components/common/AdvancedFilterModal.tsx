import { useState } from "react";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { cn } from "@/lib/utils";
import { X, PlusCircle } from "lucide-react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import type { FieldOption, Filter, FilterGroup } from "./EntityTableWrapper";

interface AdvancedFilterModalProps {
  open: boolean;
  onClose: () => void;
  fieldOptions: FieldOption[];
  onApply: (group: FilterGroup) => void;
}

export function AdvancedFilterModal({
  open,
  onClose,
  fieldOptions,
  onApply,
}: AdvancedFilterModalProps) {
  const [conditions, setConditions] = useState<Filter[]>([]);
  const [groupOperator, setGroupOperator] = useState<"AND" | "OR">("AND");

  const addCondition = () =>
    setConditions((prev) => [...prev, { field: "", operator: "eq", value: "" }]);

  const removeCondition = (i: number) =>
    setConditions((prev) => prev.filter((_, idx) => idx !== i));

  const updateCondition = (i: number, key: keyof Filter, value: any) =>
    setConditions((prev) => prev.map((c, idx) => (idx === i ? { ...c, [key]: value } : c)));

  const handleApply = () => {
    const valid = conditions.filter((c) => c.field && c.operator);
    if (valid.length === 0) {
      toast.warning("Please add at least one valid filter");
      return;
    }
    onApply({ operator: groupOperator, filters: valid });
  };

  const getOperatorsForType = (type?: string): { value: string; label: string }[] => {
    switch (type) {
      case "number":
      case "date":
        return [
          { value: "eq", label: "= Equals" },
          { value: "ne", label: "≠ Not Equals" },
          { value: "gt", label: "> Greater" },
          { value: "lt", label: "< Less" },
          { value: "ge", label: "≥ Greater or Equal" },
          { value: "le", label: "≤ Less or Equal" },
          { value: "isNull", label: "Is Null" },
          { value: "isNotNull", label: "Is Not Null" },
        ];
      case "boolean":
        return [
          { value: "eq", label: "= Equals" },
          { value: "ne", label: "≠ Not Equals" },
        ];
      case "select":
        return [
          { value: "eq", label: "= Equals" },
          { value: "ne", label: "≠ Not Equals" },
          { value: "in", label: "In List" },
        ];
      case "text":
      default:
        return [
          { value: "contains", label: "Contains" },
          { value: "startsWith", label: "Starts With" },
          { value: "endsWith", label: "Ends With" },
          { value: "eq", label: "= Equals" },
          { value: "ne", label: "≠ Not Equals" },
          { value: "isNull", label: "Is Null" },
          { value: "isNotNull", label: "Is Not Null" },
        ];
    }
  };

  const renderValueInput = (cond: Filter, fieldType?: string, options?: any[]) => {
    switch (fieldType) {
      case "boolean":
        return (
          <Select
            value={cond.value?.toString() ?? ""}
            onValueChange={(v) => updateCondition(conditions.indexOf(cond), "value", v === "true")}
          >
            <SelectTrigger className="w-32">
              <SelectValue placeholder="Select" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="true">True</SelectItem>
              <SelectItem value="false">False</SelectItem>
            </SelectContent>
          </Select>
        );

      case "select":
        return (
          <Select
            value={cond.value ?? ""}
            onValueChange={(v) => updateCondition(conditions.indexOf(cond), "value", v)}
          >
            <SelectTrigger className="min-w-[150px]">
              <SelectValue placeholder="Select" />
            </SelectTrigger>
            <SelectContent>
              {options?.map((opt) => (
                <SelectItem key={opt.value} value={opt.value.toString()}>
                  {opt.label}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        );

      case "date":
        return (
          <Input
            type="date"
            value={cond.value ?? ""}
            onChange={(e) => updateCondition(conditions.indexOf(cond), "value", e.target.value)}
            className="w-40"
          />
        );

      default:
        return (
          <Input
            placeholder="Value"
            value={cond.value ?? ""}
            onChange={(e) => updateCondition(conditions.indexOf(cond), "value", e.target.value)}
            className="flex-1"
          />
        );
    }
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="max-w-3xl w-lg">
        <DialogHeader>
          <DialogTitle>Advanced Filters</DialogTitle>
        </DialogHeader>

        <div className="space-y-4">
          {/* Group operator */}
          <div className="flex items-center gap-3">
            <span className="text-sm text-muted-foreground">Match</span>
            <Select
              value={groupOperator}
              onValueChange={(v) => setGroupOperator(v as "AND" | "OR")}
            >
              <SelectTrigger className="w-24">
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="AND">ALL</SelectItem>
                <SelectItem value="OR">ANY</SelectItem>
              </SelectContent>
            </Select>
            <span className="text-sm text-muted-foreground">of the conditions:</span>
          </div>

          {/* Conditions */}
          {conditions.map((cond, i) => {
            const field = fieldOptions.find((f) => f.name === cond.field);
            const ops = getOperatorsForType(field?.type);
            return (
              <div
                key={i}
                className={cn("flex items-center gap-2 border p-2 rounded-md bg-muted/40")}
              >
                {/* Field */}
                <Select
                  value={cond.field ?? ""}
                  onValueChange={(v) => updateCondition(i, "field", v)}
                >
                  <SelectTrigger className="w-40">
                    <SelectValue placeholder="Field" />
                  </SelectTrigger>
                  <SelectContent>
                    {fieldOptions.map((f) => (
                      <SelectItem key={f.name} value={f.name}>
                        {f.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>

                {/* Operator */}
                <Select
                  value={cond.operator ?? ""}
                  onValueChange={(v) => updateCondition(i, "operator", v)}
                >
                  <SelectTrigger className="w-44">
                    <SelectValue placeholder="Operator" />
                  </SelectTrigger>
                  <SelectContent>
                    {ops.map((op) => (
                      <SelectItem key={op.value} value={op.value}>
                        {op.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>

                {/* Value */}
                {renderValueInput(cond, field?.type, field?.options)}

                <Button
                  type="button"
                  variant="ghost"
                  size="icon"
                  onClick={() => removeCondition(i)}
                >
                  <X className="h-4 w-4" />
                </Button>
              </div>
            );
          })}

          <Button
            variant="outline"
            size="sm"
            onClick={addCondition}
            className="flex items-center"
          >
            <PlusCircle className="h-4 w-4 mr-1" /> Add Condition
          </Button>
        </div>

        <DialogFooter className="mt-4">
          <Button variant="outline" onClick={onClose}>
            Cancel
          </Button>
          <Button onClick={handleApply}>Apply Filter</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
