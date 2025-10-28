"use client";

import { useCallback } from "react";
import { Badge } from "@/components/ui/badge";
import { EntityTableWrapper, type FilterGroup, type PageResponse, type SortRequest, type FieldOption } from "@/components/common/EntityTableWrapper";
import { AccountApi } from "@/api/user/accountApi";
import { toast } from "sonner";

/* ------------------ Types ------------------ */
export interface LoginAttemptResponse {
    id: string;
    createdDate: string;
    ipAddress: string;
    userAgent: string;
    status: "SUCCESS" | "FAILURE";
    loginMethod: string;
}

/* ------------------ API ------------------ */
const loginActivityApi = {
  async search(page: number, size: number, filterGroup?: FilterGroup, sorts?: SortRequest[]): Promise<{ data: PageResponse<LoginAttemptResponse> }> {
    try {
      const res = await AccountApi.getLoginHistory(page, size, filterGroup, sorts);
      return { data: res };
    } catch (err: any) {
      console.error("Failed to fetch login activity:", err);
      toast.error("Failed to load login history");
      throw err;
    }
  },
};

/* ------------------ Page ------------------ */
export default function LoginActivityPage() {
const fetchData = useCallback(
  (page: number, size: number, filterGroup?: FilterGroup | null, sorts?: SortRequest[]) =>
    loginActivityApi.search(page, size, filterGroup ?? undefined, sorts ?? undefined),
  []
);


  const columns = [
    { title: "IP Address", dataIndex: "ipAddress", sortable: true },
    { title: "User Agent", dataIndex: "userAgent", sortable: false },
    {
      title: "Status",
      dataIndex: "status",
      sortable: true,
      render: (value: string) =>
        value === "SUCCESS" ? (
          <Badge variant="secondary">Success</Badge>
        ) : (
          <Badge variant="destructive">Failure</Badge>
        ),
    },
    { title: "Login Method", dataIndex: "loginMethod", sortable: true },
    {
      title: "Login Time",
      dataIndex: "createdDate",
      sortable: true,
      render: (value: string) => new Date(value).toLocaleString(),
    },  
    
  ];

  const fieldOptions: FieldOption[] = [
    { name: "ipAddress", label: "IP Address", type: "text" },
    { name: "deviceName", label: "Device", type: "text" },
    { name: "userAgent", label: "User Agent", type: "text" },
    {
      name: "status",
      label: "Status",
      type: "select",
      options: [
        { value: "SUCCESS", label: "Success" },
        { value: "FAILURE", label: "Failure" },
      ],
    },
    { name: "createdAt", label: "Login Time", type: "date" },
  ];

  return (
    <EntityTableWrapper<LoginAttemptResponse>
      title="Login Activity History"
      fetchData={fetchData}
      columns={columns}
      fieldOptions={fieldOptions}
      smartSearchFields={["ipAddress", "userAgent"]}
      pageSize={10}
    />
  );
}
