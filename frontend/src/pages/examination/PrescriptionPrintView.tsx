import { useClinic } from "@/context/ClinicContext";

export function PrescriptionPrintView({
  patient,
  items,
  extraInfo,
}: {
  patient: any;
  items: any[];
  extraInfo: React.ReactNode;
}) {
  const { clinicInfo } = useClinic();
  return (
    <div>
      {/* CLINIC INFO */}
      <div className="text-center mb-4">
        <div className="font-bold text-lg">{clinicInfo?.name}</div>
        <div className="text-muted-foreground text-sm">
          {clinicInfo?.address?.street}, {clinicInfo?.address?.districtName},{" "}
          {clinicInfo?.address?.city}
        </div>
        <div>Điện thoại: {clinicInfo?.phone}</div>
      </div>

      {/* PATIENT INFO */}
      <div className="border p-4 rounded mb-6 text-sm">
        <div>
          <b>Bệnh nhân:</b> {patient?.fullName}
        </div>
        <div>
          <b>Mã BN:</b> {patient?.patientCode}
        </div>
        <div>
          <b>Địa chỉ:</b> {patient?.address}
        </div>
      </div>

      {/* EXTRA INFO (role-dependent) */}
      {extraInfo}

      {/* ITEM TABLE */}
      <div className="mb-3 font-semibold">Danh sách thuốc</div>

      <table className="w-full border-collapse text-sm">
        <thead>
          <tr className="border">
            <th className="border p-2 w-12 text-center">STT</th>
            <th className="border p-2">Tên thuốc</th>
            <th className="border p-2 w-32">Liều dùng</th>
            <th className="border p-2 w-40">Hướng dẫn</th>
            <th className="border p-2 w-16 text-center">SL</th>
          </tr>
        </thead>

        <tbody>
          {items?.map((it, idx) => (
            <tr key={it.id} className="border">
              <td className="border p-2 text-center">{idx + 1}</td>
              <td className="border p-2">{it.medicine?.name}</td>
              <td className="border p-2">{it.dosage}</td>
              <td className="border p-2">{it.instruction}</td>
              <td className="border p-2 text-center">{it.quantity}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="mt-4 text-xs italic text-muted-foreground">
        Vui lòng kiểm tra thuốc trước khi rời quầy.
      </div>
    </div>
  );
}
