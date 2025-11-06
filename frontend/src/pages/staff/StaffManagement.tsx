import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import { Eye, Pencil, Plus } from "lucide-react";
import * as yup from "yup";

import type { Column } from "@/components/common/ReuseAbleTable";
import type { FormFieldConfig } from "@/components/common/FormModal";

import { type StaffRequest, type StaffResponse } from "@/api";
import { StaffApi } from "@/api/staff/StaffApi";
import { SearchSelect } from "@/components/common/SearchSelect";
import { DepartmentApi } from "@/api/department/DepartmentApi";
import { SpecialtyApi } from "@/api/specialty/SpecialtyApi";
import { PositionApi } from "@/api/staff/PositionApi";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";
import { useNavigate } from "react-router-dom";

export default function StaffManagementPage() {
  const [selectForForm, setSelectForForm] =
    React.useState<StaffResponse | null>(null);

  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");

  const navigate = useNavigate();

  const tableRef = React.useRef<any>(null);

  const { translate, toOptions } = useEnumTranslation();

  /* --------------------------- FETCH STAFF --------------------------- */
  const fetchStaff = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<StaffResponse> }> => {
    try {
      const res = await StaffApi.filter(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );
      return { data: res };
    } catch {
      toast.error("Không tải được danh sách nhân viên");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchStaff, []);

  /* --------------------------- TABLE COLUMNS --------------------------- */
  const columns: Column<StaffResponse>[] = [
    { title: "Họ tên", dataIndex: "fullName", sortable: true },
    { title: "Email", dataIndex: "email", sortable: true },
    { title: "SĐT", dataIndex: "phone", sortable: true },
    {
      title: "Khoa",
      dataIndex: "department",
      render: (_, row) => row.department?.name || "",
    },
    {
      title: "Chuyên khoa",
      dataIndex: "specialty",
      render: (_, row) => row.specialty?.name || "",
    },
    {
      title: "Chức vụ",
      dataIndex: "position",
      render: (_, row) => row.position?.title || "",
    },
    {
      title: "Loại nhân viên",
      dataIndex: "staffType",
      sortable: true,
      render: (v: string) => translate("staffType", v),
    },
    {
      title: "Ngày vào làm",
      dataIndex: "joinedDate",
      sortable: true,
      render: (_, row) =>
        row.joinedDate
          ? new Date(row.joinedDate).toLocaleDateString("vi-VN")
          : "",
    },
  ];

  /* --------------------------- NORMALIZE FORM VALUES --------------------------- */
  const normalizeDefaults = (data: any) => ({
    fullName: data?.fullName ?? "",
    phone: data?.phone ?? "",
    email: data?.email ?? "",
    departmentId: data?.department?.id ?? "",
    specialtyId: data?.specialty?.id ?? "",
    positionId: data?.position?.id ?? "",
    staffType: data?.staffType ?? "",
    licenseNumber: data?.licenseNumber ?? "",
    experienceYears: data?.experienceYears ?? 0,
    education: data?.education ?? "",
    bio: data?.bio ?? "",
    joinedDate: data?.joinedDate
      ? new Date(data.joinedDate).toISOString().substring(0, 10)
      : "",
  });

  /* --------------------------- FORM FIELDS --------------------------- */
  const formFieldConfigs: FormFieldConfig[] = [
    { name: "fullName", label: "Họ tên", type: "text", required: true },
    { name: "email", label: "Email", type: "text", required: true },
    { name: "phone", label: "Số điện thoại", type: "text", required: true },

    {
      name: "departmentId",
      label: "Khoa",
      type: "select",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn khoa"
          fetchOptions={async (keyword) => {
            const res = await DepartmentApi.search(keyword);
            return res.content.map((d) => ({
              value: String(d.id),
              label: d.name!,
            }));
          }}
          initialOption={
            selectForForm?.department
              ? {
                  label: selectForForm.department.name!,
                  value: selectForForm.department.id!,
                }
              : null
          }
        />
      ),
    },

    {
      name: "specialtyId",
      label: "Chuyên khoa",
      type: "select",
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn chuyên khoa"
          fetchOptions={async (keyword) => {
            const res = await SpecialtyApi.search(keyword);
            return res.content.map((s) => ({
              value: String(s.id),
              label: s.name!,
            }));
          }}
          initialOption={
            selectForForm?.specialty
              ? {
                  label: selectForForm.specialty.name!,
                  value: selectForForm.specialty.id!,
                }
              : null
          }
        />
      ),
    },

    {
      name: "positionId",
      label: "Chức vụ",
      type: "select",
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn chức vụ"
          fetchOptions={async (keyword) => {
            const res = await PositionApi.search(keyword);
            return res.content.map((p) => ({
              value: String(p.id),
              label: p.title!,
            }));
          }}
          initialOption={
            selectForForm?.position
              ? {
                  label: selectForForm.position.title!,
                  value: selectForForm.position.id!,
                }
              : null
          }
        />
      ),
    },

    {
      name: "staffType",
      label: "Loại nhân viên",
      type: "select",
      required: true,
      options: toOptions("staffType"),
    },

    { name: "licenseNumber", label: "Mã hành nghề", type: "text" },
    { name: "experienceYears", label: "Số năm kinh nghiệm", type: "number" },
    { name: "education", label: "Học vấn", type: "textarea" },
    { name: "bio", label: "Tiểu sử", type: "textarea" },

    { name: "joinedDate", label: "Ngày vào làm", type: "date" },
  ];

  /* --------------------------- VALIDATION --------------------------- */
  const schema = yup.object({
    fullName: yup.string().required("Tên bắt buộc"),
    phone: yup.string().required("Số điện thoại bắt buộc"),
    email: yup.string().email("Email không hợp lệ").required("Email bắt buộc"),
    departmentId: yup.string().required("Khoa bắt buộc"),
    staffType: yup.string().required("Loại nhân viên bắt buộc"),
    experienceYears: yup.number().nullable().min(0),
  });

  /* --------------------------- FORM MODAL --------------------------- */
  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm nhân viên" : "Sửa nhân viên",
    formFields: formFieldConfigs,
    defaultValues: normalizeDefaults(selectForForm),
    schema,
    onSubmit: async (data: StaffRequest) => {
      try {
        if (formType === "create") {
          await StaffApi.createStaff(data);
          toast.success("Tạo nhân viên thành công");
        } else if (formType === "update" && selectForForm) {
          await StaffApi.updateStaff(selectForForm.id!, data);
          toast.success("Cập nhật nhân viên thành công");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        toast.error("Không thể lưu nhân viên");
      }
    },
    big: true,
  };

  return (
    <EntityTableWrapper<StaffResponse>
      ref={tableRef}
      title="Quản lý nhân viên"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["fullName", "email", "phone"]}
      pageSize={10}
      renderRowActions={(row) => (
        <>
          <Button
            variant="outline"
            className="mr-2"
            onClick={() => navigate(`/staff/staffs/${row.id}`)}
          >
            <Eye className="w-4 h-4" />
          </Button>
          <Button
            variant="outline"
            onClick={() => {
              setSelectForForm(row);
              setFormType("update");
            }}
          >
            <Pencil className="w-4 h-4" />
          </Button>
        </>
      )}
      formModalProps={formModalProps}
      headerExtra={
        <Button
          onClick={() => {
            setSelectForForm(null);
            setFormType("create");
          }}
        >
          <Plus className="w-4 h-4 mr-2" /> Thêm nhân viên
        </Button>
      }
      footerExtra={(ctx) => (
        <div>
          <strong>Tổng nhân viên:</strong> {ctx.totalElements}
        </div>
      )}
    />
  );
}
