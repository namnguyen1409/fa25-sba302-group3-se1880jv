

import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { cn } from "@/lib/utils";
import {
  Plus,
  Pencil,
  Trash2,
  ArrowUp,
  ArrowDown,
  ArrowUpDown,
} from "lucide-react";

interface Column<T> {
  title: string;
  dataIndex: keyof T | string;
  render?: (value: any, record: T, index: number) => React.ReactNode;
  show?: boolean;
  sortable?: boolean;
  className?: string;
}

interface SortRequest {
  field: string;
  direction: "ASC" | "DESC";
}

interface ReusableTableProps<T> {
  title?: string;
  columns: Column<T>[];
  data?: T[];
  loading?: boolean;
  onAdd?: () => void;
  onEdit?: (row: T) => void;
  onDelete?: (id: any) => void;
  renderRowActions?: (row: T) => React.ReactNode;
  headerExtra?: React.ReactNode;
  getRowId?: (row: T) => any;
  pagination?: {
    page: number;
    size: number;
    total: number;
    onPageChange: (page: number) => void;
  };
  onSortChange?: (sorts: SortRequest[]) => void;
  activeSorts?: SortRequest[];
  className?: string;
  footerExtra?: React.ReactNode;
}

export function ReusableTable<T>({
  title,
  columns,
  data = [],
  loading,
  onAdd,
  onEdit,
  onDelete,
  renderRowActions,
  headerExtra,
  getRowId,
  pagination,
  onSortChange,
  activeSorts = [],
  className,
  footerExtra,
}: ReusableTableProps<T>) {
  const idKey = getRowId
    ? getRowId
    : (row: any) => row?.id || row?.uuid || row?.key || Math.random();

  const toggleSort = (field: string) => {
    if (!onSortChange) return;

    const existing = activeSorts.find((s) => s.field === field);
    let newSorts: SortRequest[];

    if (!existing) {
      // add ASC
      newSorts = [...activeSorts, { field, direction: "ASC" }];
    } else if (existing.direction === "ASC") {
      // switch to DESC
      newSorts = activeSorts.map((s) =>
        s.field === field ? { ...s, direction: "DESC" } : s
      );
    } else {
      // remove from list (toggle off)
      newSorts = activeSorts.filter((s) => s.field !== field);
    }

    onSortChange(newSorts);
  };

  const getSortIcon = (field: string) => {
    const sort = activeSorts.find((s) => s.field === field);
    if (!sort) return <ArrowUpDown className="h-4 w-4 opacity-40" />;
    return sort.direction === "ASC" ? (
      <ArrowUp className="h-4 w-4 text-primary" />
    ) : (
      <ArrowDown className="h-4 w-4 text-primary" />
    );
  };

  return (
    <Card className={cn("shadow-sm border", className)}>
      {title && (
        <CardHeader className="flex flex-row items-center justify-between">
          <CardTitle>{title}</CardTitle>
          <div className="flex gap-2 items-center">
            {headerExtra}
            {onAdd && (
              <Button size="sm" onClick={onAdd}>
                <Plus className="h-4 w-4 mr-1" /> Add
              </Button>
            )}
          </div>
        </CardHeader>
      )}

      <CardContent>
        {loading ? (
          <div className="space-y-2">
            {[...Array(5)].map((_, i) => (
              <Skeleton key={i} className="h-8 w-full rounded-md" />
            ))}
          </div>
        ) : (
          <div className="w-full overflow-x-auto overflow-y-hidden scrollbar-thin scrollbar-thumb-muted-foreground/30 scrollbar-track-transparent hover:scrollbar-thumb-muted-foreground/50 rounded-md">
            <div className="min-w-max">
              <Table>
                <TableHeader>
                  <TableRow>
                    {columns.map(
                      (col, i) =>
                        col.show !== false && (
                          <TableHead
                            key={i}
                            className={cn(
                              "font-semibold select-none cursor-pointer",
                              col.sortable &&
                                "hover:bg-muted/40 transition-colors",
                              col.className
                            )}
                            onClick={() =>
                              col.sortable
                                ? toggleSort(col.dataIndex.toString())
                                : undefined
                            }
                          >
                            <div className="flex items-center gap-1">
                              {col.title}
                              {col.sortable &&
                                getSortIcon(col.dataIndex.toString())}
                            </div>
                          </TableHead>
                        )
                    )}
                    {(onEdit || onDelete || renderRowActions) && (
                      <TableHead className="text-center">Actions</TableHead>
                    )}
                  </TableRow>
                </TableHeader>

                <TableBody>
                  {data.length > 0 ? (
                    data.map((row, i) => (
                      <TableRow key={idKey(row) ?? i}>
                        {columns.map(
                          (col, j) =>
                            col.show !== false && (
                              <TableCell key={j}>
                                {col.render
                                  ? col.render(
                                      (row as any)[col.dataIndex],
                                      row,
                                      i
                                    )
                                  : (row as any)[col.dataIndex]}
                              </TableCell>
                            )
                        )}
                        {(onEdit || onDelete || renderRowActions) && (
                          <TableCell className="text-center">
                            {renderRowActions ? (
                              renderRowActions(row)
                            ) : (
                              <div className="flex justify-center gap-2">
                                {onEdit && (
                                  <Button
                                    size="icon"
                                    variant="outline"
                                    onClick={() => onEdit(row)}
                                  >
                                    <Pencil className="h-4 w-4" />
                                  </Button>
                                )}
                                {onDelete && (
                                  <Button
                                    size="icon"
                                    variant="destructive"
                                    onClick={() => onDelete(idKey(row))}
                                  >
                                    <Trash2 className="h-4 w-4" />
                                  </Button>
                                )}
                              </div>
                            )}
                          </TableCell>
                        )}
                      </TableRow>
                    ))
                  ) : (
                    <TableRow>
                      <TableCell
                        colSpan={columns.length + 1}
                        className="text-center py-6 text-muted-foreground"
                      >
                        No data available
                      </TableCell>
                    </TableRow>
                  )}
                </TableBody>

                {!data.length && (
                  <TableCaption>No data found for this entity</TableCaption>
                )}
              </Table>
            </div>
          </div>
        )}
        <div className="flex justify-end items-center mt-4 gap-2 text-sm">
          {footerExtra && (
            <div className="mr-auto">
              {footerExtra}
            </div>
          )}

          {/* Pagination */}
          {pagination && (
            <>
              <span>
                Page {pagination.page + 1} of{" "}
                {Math.ceil(pagination.total / pagination.size)}
              </span>
              <div className="flex gap-1">
                <Button
                  size="sm"
                  variant="outline"
                  disabled={pagination.page === 0}
                  onClick={() => pagination.onPageChange(pagination.page - 1)}
                >
                  Prev
                </Button>
                <Button
                  size="sm"
                  variant="outline"
                  disabled={
                    (pagination.page + 1) * pagination.size >= pagination.total
                  }
                  onClick={() => pagination.onPageChange(pagination.page + 1)}
                >
                  Next
                </Button>
              </div>
            </>
          )}
        </div>
      </CardContent>
    </Card>
  );
}
