"use client";

import React, { useCallback } from "react";
import { Badge } from "@/components/ui/badge";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";

import { UserApi, type UserResponse } from "@/api/user/userApi";
import { Eye, LockIcon, UnlockIcon } from "lucide-react";
import DynamicInfo from "@/components/common/DynamicInfo";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { useImageLink } from "@/hooks/useImageLink";
import { Button } from "@/components/ui/button";
import { useConfirm } from "@/hooks/useConfirm";

const fetchUsers = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<UserResponse> }> => {
  try {
    const res = await UserApi.getUser(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data: res };
  } catch (err) {
    toast.error("Failed to load users");
    throw err;
  }
};

export default function UserManagementPage() {
  const [selectRow, setSelectRow] = React.useState<UserResponse | null>(null);
  const [showViewModal, setShowViewModal] = React.useState(false);
  const tableRef = React.useRef<any>(null);

  const { confirm, ConfirmDialog } = useConfirm();

  const handleViewDetails = (row: UserResponse) => {
    setSelectRow(row);
    setShowViewModal(true);
  };

  const fetchData = useCallback(fetchUsers, []);

  const lockAccount = async (userId: string) => {
  try {
    const confirmed = await confirm("Are you sure you want to lock this account?");
    if (!confirmed) return;

    // Call API to lock account
    await UserApi.lockUser(userId);
    toast.success("User account locked successfully");
    tableRef.current?.reload();

  } catch (err) {
    toast.error("Failed to lock user account");
    throw err;
  }
};

const unlockAccount = async (userId: string) => {
  try {
    const confirmed = await confirm("Are you sure you want to unlock this account?");
    if (!confirmed) return;
    // Call API to unlock account
    await UserApi.unlockUser(userId);
    toast.success("User account unlocked successfully");
    tableRef.current?.reload();
  } catch (err) {
    toast.error("Failed to unlock user account");
    throw err;
  }
};

  const columns = [
    { title: "Username", dataIndex: "username", sortable: true },
    { title: "Email", dataIndex: "email", sortable: true },

    {
      title: "Roles",
      dataIndex: "roles",
      render: (roles: any) =>
        roles.map((role: any) => (
          <Badge key={role.name} className="mr-1">
            {role.name.replace("ROLE_", "")}
          </Badge>
        )),
    },

    {
      title: "Status",
      dataIndex: "active",
      sortable: true,
      render: (v: boolean) => (
        <Badge variant={v ? "default" : "destructive"}>
          {v ? "Active" : "Inactive"}
        </Badge>
      ),
    },

    {
      title: "Locked",
      dataIndex: "locked",
      sortable: true,
      render: (v: boolean) => (
        <Badge variant={v ? "destructive" : "default"}>
          {v ? "Locked" : "Unlocked"}
        </Badge>
      ),
    },

    {
      title: "MFA",
      dataIndex: "mfaEnabled",
      sortable: true,
      render: (v: boolean) => (
        <Badge variant={v ? "default" : "outline"}>
          {v ? "Enabled" : "Disabled"}
        </Badge>
      ),
    },

    {
      title: "Full Name",
      dataIndex: "userProfile.fullName",
      render: (_: any, row: UserResponse) => row.userProfile?.fullName ?? "-",
    },

    {
      title: "First Login",
      dataIndex: "firstLogin",
      sortable: true,
      render: (v: boolean) => (
        <Badge variant={v ? "secondary" : "default"}>{v ? "Yes" : "No"}</Badge>
      ),
    },
  ];

  return (
    <>
      <EntityTableWrapper<UserResponse>
        ref = {tableRef}
        title="User Management"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["username", "email"]}
        pageSize={10}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => handleViewDetails(row)}
            >
              <Eye className="w-4 h-4" />
            </Button>
            {row.locked ? (
              <Button
                variant="destructive"
                onClick={() => unlockAccount(row.id)}
              >
                <UnlockIcon className="h-4 w-4" />
              </Button>
            ) : (
                <Button
                    variant="default"
                    onClick={() => lockAccount(row.id)}
                >
                  <LockIcon className="h-4 w-4" />
                </Button>
            )}
          </>
        )}
      />

      <Dialog open={showViewModal} onOpenChange={setShowViewModal}>
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>User Details</DialogTitle>
          </DialogHeader>

          {selectRow && (
            <DynamicInfo
              data={selectRow}
              config={[
                {
                  label: "Avatar",
                  name: "userProfile.avatarUrl",
                  render(value, _) {
                    return value ? (
                      <img
                        src={useImageLink(value)}
                        alt="Avatar"
                        className="w-24 h-24 rounded-full"
                      />
                    ) : (
                      <Badge>No Avatar</Badge>
                    );
                  },
                },
                { label: "Username", name: "username" },
                { label: "Email", name: "email" },
                { label: "Full Name", name: "userProfile.fullName" },
                { label: "Active", name: "active", type: "bool" },
                { label: "Locked", name: "locked", type: "bool" },
                { label: "MFA Enabled", name: "mfaEnabled", type: "bool" },
                { label: "First Login", name: "firstLogin", type: "bool" },
                {
                  label: "Roles",
                  name: "roles",
                  render(value, _) {
                    return value.map((role: any) => (
                      <Badge key={role.name} className="mr-1">
                        {role.name.replace("ROLE_", "")}
                      </Badge>
                    ));
                  },
                },
              ]}
              columns={2}
            />
          )}
        </DialogContent>
      </Dialog>


      <ConfirmDialog />
    </>
  );
}
