import { Card, CardContent } from "@/components/ui/card"
import { Link } from "react-router-dom"
import type { StaffResponse } from "@/api/models"

export function StaffCard({ item }: { item: StaffResponse }) {
  return (
    <Link to={`/staffs/${item.id}`}>
      <Card className="group cursor-pointer overflow-hidden rounded-xl border shadow-sm transition hover:shadow-md">
        <CardContent className="p-5 flex flex-col items-center text-center">

          {/* Avatar */}
          <div className="w-24 h-24 mb-4">
            <img
              src={`https://api.dicebear.com/8.x/initials/svg?seed=${item.fullName}`}
              className="rounded-full w-full h-full object-cover bg-gray-100"
            />
          </div>

          {/* Name */}
          <h2 className="font-semibold text-lg text-gray-900 group-hover:text-blue-600 transition">
            {item.fullName}
          </h2>

          {/* Department */}
          {item.department && (
            <p className="text-sm text-gray-500 mt-1">
              {item.department.name}
            </p>
          )}

          {/* Specialty */}
          {item.specialty && (
            <p className="text-sm text-gray-600 mt-1">
              {item.specialty.name}
            </p>
          )}

          {/* Position / Staff Type */}
          <div className="text-xs text-gray-500 mt-2">
            {item.position?.title ?? item.staffType}
          </div>

          {/* Experience */}
          {item.experienceYears !== null && (
            <p className="mt-2 text-xs text-gray-500">
              Kinh nghiệm: <b>{item.experienceYears} năm</b>
            </p>
          )}
        </CardContent>
      </Card>
    </Link>
  )
}
